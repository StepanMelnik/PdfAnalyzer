package com.sme.pdf.analyzer;

import static com.sme.pdf.analyzer.model.Report.fromName;
import static com.sme.pdf.analyzer.reporter.ReporterSupplier.supplyReporter;
import static java.nio.file.Paths.get;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sme.pdf.analyzer.model.ILabel;
import com.sme.pdf.analyzer.model.Type;
import com.sme.pdf.analyzer.parser.FontParser;
import com.sme.pdf.analyzer.parser.TextParser;
import com.sme.pdf.analyzer.reporter.IReporter;

/**
 * Main Entry point to start application.
 */
public final class Main
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    Main()
    {
    }

    /**
     * Run application with specified arguments.
     * 
     * @param args The array of arguments.
     */
    void run(String[] args)
    {
        CLIParser cliParser = new CLIParser(args);
        Properties properties = cliParser.parse();

        LOGGER.debug("Ceate properties: " + properties);

        File file = get(properties.getProperty(CLIParser.SOURCE)).toFile();
        validateFile(file);

        List<ILabel> list = parsePdf(properties, file);

        IReporter reporter = supplyReporter(fromName(properties.getProperty(CLIParser.REPORT)));
        reporter.report(list, getBaseName(file.getName()));
    }

    private List<ILabel> parsePdf(Properties properties, File file)
    {
        Type type = Type.fromName(properties.getProperty(CLIParser.TYPE));
        switch (type)
        {
            case FONT:
                return new FontParser(file).parse();

            case TEXT:
                return new TextParser(file).parse();

            case IMAGE:
                throw new IllegalArgumentException("IMAGE type has not supported yet");

            default:
                throw new IllegalArgumentException(type + "type has not supported yet");
        }
    }

    private void validateFile(File file)
    {
        if (!file.exists())
        {
            throw new IllegalArgumentException(file + " does not exist");
        }

        if (!"PDF".equalsIgnoreCase(getExtension(file.getName())))
        {
            throw new IllegalArgumentException(file + " is not PDF file");
        }
    }

    /**
     * Main entry point.
     * 
     * @param args The list of arguments to start application.
     */
    public static void main(String[] args)
    {
        new Main().run(args);
    }
}
