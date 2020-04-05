package com.sme.pdf.analyzer.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Represent a Report to print result.
 */
public enum Report
{
    /**
     * Print a report in console.
     */
    CONSOLE,

    /**
     * Print a report as html output.
     */
    HTML,

    /**
     * Print a report as html output with specified html settings.
     */
    TEXT_HTML;

    /**
     * Gets an {@link Report} by its name.
     *
     * @param name The name to get a report for.
     * @return Returns the report specified by name.
     */
    public static Report fromName(String name)
    {
        for (Report report : Report.values())
        {
            if (StringUtils.equalsIgnoreCase(report.name(), name))
            {
                return report;
            }
        }
        throw new IllegalArgumentException("'" + name + "' is not a valid report");
    }
}
