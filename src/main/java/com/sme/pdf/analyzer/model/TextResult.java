package com.sme.pdf.analyzer.model;

import static java.lang.String.format;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents text with font properties in pdf document.
 */
public class TextResult implements ILabel
{
    private String text;
    private float fontSize;
    private boolean italic;
    private boolean forceBold;
    private FontResult fontResult;

    public TextResult()
    {
    }

    /**
     * Initial constructor to create POJO with all needed properties.
     */
    public TextResult(String text, float fontSize, boolean italic, boolean forceBold, FontResult fontResult)
    {
        this();
        this.text = text;
        this.fontSize = fontSize;
        this.italic = italic;
        this.forceBold = forceBold;
        this.fontResult = fontResult;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public float getFontSize()
    {
        return fontSize;
    }

    public void setFontSize(float fontSize)
    {
        this.fontSize = fontSize;
    }

    public boolean isItalic()
    {
        return italic;
    }

    public void setItalic(boolean italic)
    {
        this.italic = italic;
    }

    public boolean isForceBold()
    {
        return forceBold;
    }

    public void setForceBold(boolean forceBold)
    {
        this.forceBold = forceBold;
    }

    public FontResult getFontResult()
    {
        return fontResult;
    }

    public void setFontResult(FontResult fontResult)
    {
        this.fontResult = fontResult;
    }

    @Override
    public String getLabel()
    {
        if (System.lineSeparator().equals(text))
        {
            return text;
        }
        else
        {
            return format("'%s': fontSize: %s; italic: %s; forceBold: %s; fontResult: %s", text, fontSize, italic, forceBold, fontResult.getLabel());
        }
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        TextResult that = (TextResult) obj;
        return new EqualsBuilder()
                .append(this.text, that.text)
                .append(this.fontSize, that.fontSize)
                .append(this.italic, that.italic)
                .append(this.forceBold, that.forceBold)
                .append(this.fontResult, that.fontResult)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
