package com.sme.pdf.analyzer.reporter;

import com.sme.pdf.analyzer.model.ILabel;
import com.sme.pdf.analyzer.model.TextResult;

/**
 * Reporter with html output that uses specifichtml tags to work with fonts.
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
        if (!TextResult.class.isInstance(label))
        {
            throw new IllegalArgumentException("The reporter works with TextResult output only");
        }

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
