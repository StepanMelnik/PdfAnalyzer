package com.sme.pdf.analyzer;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests of {@link CLIParser}.
 */
public class CLIParserTest extends Assert
{
    @Test
    public void testAllArgs() throws Exception
    {
        CLIParser cliParser = new CLIParser(new String[] {"-source", "Test.Pdf", "-type", "FONT"});
        Properties properties = cliParser.parse();

        assertEquals("Expects source element", "Test.Pdf", properties.get("source"));
        assertEquals("Expects FONT type element", "FONT", properties.get("type"));
    }

    @Test(expected = NullPointerException.class)
    public void testRequireSourceOption() throws Exception
    {
        CLIParser cliParser = new CLIParser(new String[] {"-type", "FONT"});
        cliParser.parse();
    }

    @Test(expected = NullPointerException.class)
    public void testRequireTypeOption() throws Exception
    {
        CLIParser cliParser = new CLIParser(new String[] {"-source", "Test.Pdf"});
        cliParser.parse();
    }

    @Test(expected = NullPointerException.class)
    public void testWrongOption() throws Exception
    {
        CLIParser cliParser = new CLIParser(new String[] {"wrongOption", "Test.Pdf"});
        cliParser.parse();
    }
}
