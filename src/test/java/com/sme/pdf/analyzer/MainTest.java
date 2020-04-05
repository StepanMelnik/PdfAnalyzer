package com.sme.pdf.analyzer;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit tests of {@link Main}.
 */
public class MainTest
{
    @Test
    @Ignore
    public void testFontInCosole() throws Exception
    {
        new Main().run(new String[] {"-source", "test.pdf", "-type", "FONT", "-report", "CONSOLE"});
    }

    @Test
    public void testFontInHtml() throws Exception
    {
        new Main().run(new String[] {"-source", "test.pdf", "-type", "FONT", "-report", "HTML"});
    }

    @Test
    @Ignore
    public void testTextInConsole() throws Exception
    {
        new Main().run(new String[] {"-source", "test.pdf", "-type", "TEXT", "-report", "CONSOLE"});
    }

    @Test
    @Ignore
    public void testTextInHtml() throws Exception
    {
        new Main().run(new String[] {"-source", "test.pdf", "-type", "TEXT", "-report", "HTML"});
    }

    @Test
    @Ignore
    public void testTextInTextHtml() throws Exception
    {
        new Main().run(new String[] {"-source", "test.pdf", "-type", "TEXT", "-report", "TEXT_HTML"});
    }
}
