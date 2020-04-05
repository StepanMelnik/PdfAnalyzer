package com.sme.pdf.analyzer.parser;

import java.util.List;

/**
 * Pdf Parser.
 */
public interface IPdfParser<T>
{
    /**
     * Parses pdf document and return a list of specified objects.
     * 
     * @return Returns a list of parsed objects.
     */
    List<T> parse();
}
