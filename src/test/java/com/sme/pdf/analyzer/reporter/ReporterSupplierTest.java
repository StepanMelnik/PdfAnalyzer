package com.sme.pdf.analyzer.reporter;

import org.junit.Assert;
import org.junit.Test;

import com.sme.pdf.analyzer.model.Report;

/**
 * Unit tests of {@link ReporterSupplier}.
 */
public class ReporterSupplierTest extends Assert
{
    @Test
    public void testSuppliers() throws Exception
    {
        assertTrue("Expects ConsoleReporter", ReporterSupplier.supplyReporter(Report.CONSOLE) instanceof ConsoleReporter);
        assertTrue("Expects HtmlReporter", ReporterSupplier.supplyReporter(Report.HTML) instanceof HtmlReporter);
    }
}
