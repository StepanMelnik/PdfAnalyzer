package com.sme.pdf.analyzer;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sme.pdf.analyzer.model.Report;

/**
 * Command line parser.
 */
class CLIParser
{
    static final String SOURCE = "source";
    static final String TYPE = "type";
    static final String REPORT = "report";
    static final String HELP = "help";

    private static final Logger LOGGER = LoggerFactory.getLogger(CLIParser.class);

    private final String[] args;
    private Options options;

    CLIParser(String[] args)
    {
        this.args = args;
        initOptions();
    }

    private void initOptions()
    {
        options = new Options();
        options.addOption(SOURCE, true, "The Pdf source file to fetch properties from");
        options.addOption(TYPE, true,
                "The type of parser. For example, FONT is the type to fetch info about fonts; TEXT is the type to fetch all text and an info about fonts used by text; IMAGE is the type to fetch images info.");
        options.addOption(REPORT, true,
                "How to report result. For example, CONSOLE allows to print result in console; HTML generates a result as html document; TEXT_HTML generates a report as html output with specified html settings.");
        options.addOption(HELP, false, "Help");
    }

    /**
     * Parse command line arguments.
     * 
     * @return Returns the properties of arguments.
     */
    Properties parse()
    {
        if (args.length == 0)
        {
            help();
        }

        CommandLine commandLine = parseCommandLine();

        Properties properties = new Properties();

        Optional.ofNullable(commandLine.getOptionValue(SOURCE)).ifPresent(value -> properties.put(SOURCE, value));
        Optional.ofNullable(commandLine.getOptionValue(TYPE)).ifPresent(value -> properties.put(TYPE, value));
        properties.put(REPORT, commandLine.getOptionValue(REPORT, Report.CONSOLE.name()));

        validate(properties);

        return properties;
    }

    private void validate(Properties properties)
    {
        requireNonNull(properties.get(SOURCE), SOURCE + " option is obligatory");
        requireNonNull(properties.get(TYPE), TYPE + " option is obligatory");
    }

    private CommandLine parseCommandLine()
    {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = null;

        try
        {
            commandLine = parser.parse(options, args);

            if (commandLine.hasOption(HELP))
            {
                help();
            }
        }
        catch (ParseException | NullPointerException e)
        {
            LOGGER.error("Cannot parse command line", e);
            help();
        }

        return commandLine;
    }

    private void help()
    {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("Pdf Parser", options);

        System.exit(0);
    }
}
