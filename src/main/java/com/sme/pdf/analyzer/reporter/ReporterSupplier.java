package com.sme.pdf.analyzer.reporter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.sme.pdf.analyzer.model.Report;

/**
 * Factory to create a reporter.
 */
public final class ReporterSupplier
{
    private static final Map<Report, Supplier<IReporter>> SUPPLIERS;

    static
    {
        final Map<Report, Supplier<IReporter>> suppliers = new HashMap<>();
        suppliers.put(Report.CONSOLE, ConsoleReporter::new);
        suppliers.put(Report.HTML, HtmlReporter::new);
        suppliers.put(Report.TEXT_HTML, TextHtmlReporter::new);

        SUPPLIERS = Collections.unmodifiableMap(suppliers);
    }

    // private constructor
    private ReporterSupplier()
    {
    }

    /**
     * Creates {@link IReporter} implementation according to {@link Report} constant.
     * 
     * @param report The type of report;
     * @return Returns instance {@link IReporter}
     */
    public static IReporter supplyReporter(Report report)
    {
        return SUPPLIERS.get(report).get();
    }
}
