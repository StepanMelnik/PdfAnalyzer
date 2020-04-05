package com.sme.pdf.analyzer.reporter;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.writeStringToFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sme.pdf.analyzer.model.ILabel;

/**
 * Reporter with html output.
 */
class HtmlReporter implements IReporter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlReporter.class);

    @Override
    public void report(List<ILabel> list, String name)
    {
        try
        {
            StringBuilder file = new StringBuilder(createHeader());

            for (ILabel label : list)
            {
                file.append(generateLine(label));
            }

            file.append(createdFooter());

            LOGGER.debug("Save a report in \"{}.html\" file", name);
            writeStringToFile(new File(name + ".html"), file.toString(), UTF_8);
        }
        catch (IOException e)
        {
            throw new RuntimeException(String.format("Canot create %s file", name + ".html"));
        }
    }

    /**
     * Generates an html line.
     * 
     * @param label The label text;
     * @return returns html line.
     */
    protected String generateLine(ILabel label)
    {
        return "<p>" + label.getLabel() + "</p>\n";
    }

    private Object createdFooter()
    {
        return "\n</body></html>";
    }

    /**
     * Generates html header.
     * 
     * @return Returns html header.
     */
    protected String createHeader()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n")
                .append("<html><head>")
                .append("<title>Pdf Analyzer</title>\n")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"UTF-8\">\n")
                .append("</head>\n")
                .append("<body>\n");
        return builder.toString();
    }
}
