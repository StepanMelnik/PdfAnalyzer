package com.sme.pdf.analyzer.reporter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sme.pdf.analyzer.model.ILabel;

/**
 * Console Reporter.
 */
class ConsoleReporter implements IReporter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleReporter.class);

    @Override
    public void report(List<ILabel> list, String name)
    {
        LOGGER.debug("The file contains the following fonts: ");
        list.stream().forEach(item -> LOGGER.debug(item.getLabel()));
    }
}
