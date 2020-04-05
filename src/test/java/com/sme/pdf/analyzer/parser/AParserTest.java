package com.sme.pdf.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * Abstraction to create and cleanup pdf file.
 */
abstract class AParserTest extends Assert
{
    protected static final String TEST_FILE_NAME = "test.pdf";

    @Before
    public void setUp() throws IOException
    {
        FileUtils.deleteQuietly(new File(TEST_FILE_NAME));

        try (PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            InputStream stream = FontParserTest.class.getClassLoader().getResourceAsStream("com/sme/pdf/analyzer/parser/LiberationSans-Bold.ttf");
            PDType0Font font = PDType0Font.load(document, stream);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page))
            {
                contentStream.beginText();
                contentStream.setFont(font, 15);
                contentStream.setLeading(15 * 1.2f);

                contentStream.newLineAtOffset(100, 600);
                contentStream.showText("FontParserTest creates pdf");
                contentStream.newLine();
                contentStream.showText("with embedded LiberationSans-Bold.ttf font.");
                contentStream.newLine();

                contentStream.showText("Supports Unicode:");
                contentStream.newLine();

                contentStream.showText("Test <-> тест <-> täst");
                contentStream.newLine();

                contentStream.endText();
            }

            document.save(new File(TEST_FILE_NAME));
        }
    }

    @After
    public void tearDown()
    {
        FileUtils.deleteQuietly(new File(TEST_FILE_NAME));
    }
}
