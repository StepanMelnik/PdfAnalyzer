package com.sme.pdf.analyzer.parser;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.sme.pdf.analyzer.model.FontResult;
import com.sme.pdf.analyzer.model.ILabel;

/**
 * Unit tests of {@link FontParser}.
 */
public class FontParserTest extends AParserTest
{
    @Test
    public void testFetchFonts() throws Exception
    {
        List<ILabel> list = new FontParser(new File(TEST_FILE_NAME)).parse();

        assertEquals("Expect only one embedded font", 1, list.size());
        assertEquals(new FontResult("AAABUG+LiberationSans-Bold", false, true, false), list.get(0));
    }
}
