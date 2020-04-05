package com.sme.pdf.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;

import com.sme.pdf.analyzer.model.FontResult;

/**
 * Opens pdf document and fetches all used fonts.
 */
public class FontParser extends APdfParser<FontResult>
{
    public FontParser(File file)
    {
        super(file);
    }

    @Override
    protected List<FontResult> innerPageParse(PDPage page)
    {
        List<FontResult> list = new ArrayList<>();
        Iterable<COSName> fonts = page.getResources().getFontNames();
        fonts.forEach(cosName ->
        {
            try
            {
                PDFont pdfFont = page.getResources().getFont(cosName);
                FontResult fontResult = new FontResult(pdfFont.getName(), pdfFont.isDamaged(), pdfFont.isEmbedded(), pdfFont.isVertical());
                list.add(fontResult);
            }
            catch (IOException e)
            {
                throw new RuntimeException("Cannot fetch fonts from resources", e);
            }
        });
        return list;
    }
}
