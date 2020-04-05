package com.sme.pdf.analyzer.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests of {@link TextResult}
 */
public class TextResultTest extends Assert
{
    @Test
    public void testGetSet() throws Exception
    {
        TextResult textResult = new TextResult();
        textResult.setFontResult(new FontResult("Ariel", true, false, true));
        textResult.setFontSize(100);
        textResult.setForceBold(false);
        textResult.setItalic(true);
        textResult.setText("Simple text");

        assertEquals(new FontResult("Ariel", true, false, true), textResult.getFontResult());
        assertEquals("Expect correct font size", 100, textResult.getFontSize(), 0);
        assertEquals(false, textResult.isForceBold());
        assertEquals(true, textResult.isItalic());
        assertEquals("Simple text", textResult.getText());
    }

    @Test
    public void testConstructor() throws Exception
    {
        TextResult textResult = new TextResult("Simple text", 1, true, true, new FontResult("Times", true, true, true));
        assertEquals(new FontResult("Times", true, true, true), textResult.getFontResult());
        assertEquals("Expect correct font size", 1, textResult.getFontSize(), 0);
        assertEquals(true, textResult.isForceBold());
        assertEquals(true, textResult.isItalic());
        assertEquals("Simple text", textResult.getText());
    }

    @Test
    public void testLabel() throws Exception
    {
        TextResult textResult = new TextResult("My text", 1, false, false, new FontResult("Times New Roman", true, true, true));
        assertEquals("'My text': fontSize: 1.0; italic: false; forceBold: false; fontResult: Name: Times New Roman; damaged: true; embedded: true; vertical: true", textResult.getLabel());
    }
}
