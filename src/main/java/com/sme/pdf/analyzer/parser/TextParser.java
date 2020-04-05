package com.sme.pdf.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import com.sme.pdf.analyzer.model.FontResult;
import com.sme.pdf.analyzer.model.ILabel;
import com.sme.pdf.analyzer.model.TextResult;

/**
 * Opens pdf document and fetches all text with font properties.
 */
public class TextParser implements IPdfParser<ILabel>
{
    private final File file;

    public TextParser(File file)
    {
        this.file = file;
    }

    @Override
    public List<ILabel> parse()
    {
        try (PDDocument document = PDDocument.load(file))
        {
            AccessPermission accessPermission = document.getCurrentAccessPermission();
            if (!accessPermission.canExtractContent())
            {
                throw new IOException(file.getAbsolutePath() + " does not have permission to extract text");
            }

            if (document.isEncrypted())
            {
                throw new IOException(file.getAbsolutePath() + " is encrypted. Cannot continue.");
            }

            TextStripper textStripper = new TextStripper();
            textStripper.setSortByPosition(true);
            textStripper.getText(document);

            return textStripper.getList();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Cannot parse file: " + file.getAbsolutePath(), e);
        }
    }

    /**
     * Private implementation to strip text with properties from pdf file.
     */
    private static class TextStripper extends PDFTextStripper
    {
        private final List<ILabel> list = new ArrayList<>();

        TextStripper() throws IOException
        {
            super();
        }

        @Override
        protected void processTextPosition(TextPosition text)
        {
            super.processTextPosition(text);

            System.out.print("unicode: " + text.getUnicode());

            PDFont font = text.getFont();
            PDFontDescriptor fontDescriptor = font.getFontDescriptor();

            list.add(new TextResult(text.getUnicode(), text.getFontSize(),
                    Optional.ofNullable(fontDescriptor).map(param -> param.isItalic()).orElse(false),
                    Optional.ofNullable(fontDescriptor).map(param -> param.isForceBold()).orElse(false),
                    new FontResult(font.getName(), font.isDamaged(), font.isEmbedded(), font.isVertical())));

            System.out.println("; name = " + font.getName() + "; fontSize = " + text.getFontSize() + "; isDamaged = " + font.isDamaged() + "; isEmbedded" + font.isEmbedded() + "; isItalic"
                + fontDescriptor.isItalic() + "; forceBold = " + fontDescriptor.isForceBold());
        }

        @Override
        protected void processOperator(Operator operator, List<COSBase> operands) throws IOException
        {
            super.processOperator(operator, operands);
            if (OperatorName.NEXT_LINE.equals(operator.getName()))
            {
                list.add(new TextResult(System.lineSeparator(), 0, false, false, new FontResult("", false, false, false)));
            }
        }

        /**
         * Prepares a list of result properties.
         * 
         * @return Returns the result list.
         */
        List<ILabel> getList()
        {
            return list;
        }
    }
}
