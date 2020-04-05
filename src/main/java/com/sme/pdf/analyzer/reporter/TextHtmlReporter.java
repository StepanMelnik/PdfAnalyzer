package com.sme.pdf.analyzer.reporter;

import static org.apache.commons.lang3.Validate.isInstanceOf;

import com.sme.pdf.analyzer.model.ILabel;
import com.sme.pdf.analyzer.model.TextResult;

/**
 * Reporter with html output that uses specific html tags to work with fonts.
 */
class TextHtmlReporter extends HtmlReporter
{
    @Override
    protected String createHeader()
    {
        return super.createHeader() + "<p>See fonts settings in alternative information of every char.</p>";
    }

    @Override
    protected String generateLine(ILabel label)
    {
        isInstanceOf(TextResult.class, label, "The reporter works with TextResult output only");

        String text = label.getLabel();
        if (System.lineSeparator().equals(text))
        {
            return "<br/>";
        }
        else
        {
            TextResult textResult = (TextResult) label;
            return String.format("<font face=\"%s\" size=\"%spt\" title=\"%s\">%s</font>", textResult.getFontResult().getName(), textResult.getFontSize(), textResult.getLabel(), textResult.getText());
        }
    }
}
