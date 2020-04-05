package com.sme.pdf.analyzer.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests of {@link Report}.
 */
public class ReportTest extends Assert
{
    @Test
    public void testFromName()
    {
        assertEquals(Report.CONSOLE, Report.fromName("CONSOLE"));
        assertEquals(Report.HTML, Report.fromName("HTML"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_fromName_invalid()
    {
        Report.fromName("wrong name");
    }
}
