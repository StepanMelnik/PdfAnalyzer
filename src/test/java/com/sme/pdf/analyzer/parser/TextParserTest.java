package com.sme.pdf.analyzer.parser;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.sme.pdf.analyzer.model.FontResult;
import com.sme.pdf.analyzer.model.ILabel;
import com.sme.pdf.analyzer.model.TextResult;

/**
 * Unit tests of {@link TextParser}.
 */
public class TextParserTest extends AParserTest
{
    /**
     * The test asserts only first row in the list.
     */
    @Test
    public void testFetchTextAndFonts() throws Exception
    {
        List<ILabel> list = new TextParser(new File(TEST_FILE_NAME)).parse();

        assertEquals("Expect all fetched chars", 112, list.size());
        assertEquals(new TextResult("F", 15.0f, false, false, new FontResult("AAABUG+LiberationSans-Bold", false, true, false)), list.get(0));
    }
}
