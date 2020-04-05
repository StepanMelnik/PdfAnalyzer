package com.sme.pdf.analyzer.reporter;

import java.util.List;

import com.sme.pdf.analyzer.model.ILabel;

/**
 * Pdf reporter.
 */
public interface IReporter
{
    /**
     * Prepare a report.
     * 
     * @param list The list of objects that described pdf properties;
     * @param name The name of source file.
     */
    void report(List<ILabel> list, String name);
}
