package com.sme.pdf.analyzer.parser;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sme.pdf.analyzer.model.ILabel;

/**
 * Abstraction to parse pdf file.
 */
abstract class APdfParser<T extends ILabel> implements IPdfParser<ILabel>
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final File file;

    public APdfParser(File file)
    {
        this.file = file;
    }

    @Override
    public final List<ILabel> parse()
    {
        logger.debug("Process {} file", file.getAbsolutePath());
        List<T> list = new ArrayList<>();

        try (PDDocument document = PDDocument.load(file))
        {
            AccessPermission accessPermission = document.getCurrentAccessPermission();
            if (!accessPermission.canExtractContent())
            {
                throw new IOException(file.getAbsolutePath() + " does not have permission to extract text");
            }

            for (PDPage page : document.getPages())
            {
                list.addAll(innerPageParse(page));
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Cannot parse file: " + file.getAbsolutePath(), e);
        }

        return list.stream().distinct().collect(toList());
    }

    /**
     * Inner implementation to parse pdf page.
     * 
     * @param page The pdf page;
     * @return Returns a list of parsed objects as a result.
     */
    protected abstract List<T> innerPageParse(PDPage page);
}
