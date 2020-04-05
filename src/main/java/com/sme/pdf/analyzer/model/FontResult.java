package com.sme.pdf.analyzer.model;

import static java.lang.String.format;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents font properties in pdf document.
 */
public class FontResult implements ILabel
{
    private String name;
    private boolean damaged;
    private boolean embedded;
    private boolean vertical;

    public FontResult()
    {
    }

    /**
     * Initial constructor to create POJO with all needed properties.
     */
    public FontResult(String name, boolean damaged, boolean embedded, boolean vertical)
    {
        this();
        this.name = name;
        this.damaged = damaged;
        this.embedded = embedded;
        this.vertical = vertical;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isDamaged()
    {
        return damaged;
    }

    public void setDamaged(boolean damaged)
    {
        this.damaged = damaged;
    }

    public boolean isEmbedded()
    {
        return embedded;
    }

    public void setEmbedded(boolean embedded)
    {
        this.embedded = embedded;
    }

    public boolean isVertical()
    {
        return vertical;
    }

    public void setVertical(boolean vertical)
    {
        this.vertical = vertical;
    }

    @Override
    public String getLabel()
    {
        return format("Name: %s; damaged: %s; embedded: %s; vertical: %s", name, damaged, embedded, vertical);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(name)
                .append(damaged)
                .append(embedded)
                .append(vertical)
                .toHashCode();
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

        FontResult that = (FontResult) obj;
        return new EqualsBuilder()
                .append(this.name, that.name)
                .append(this.damaged, that.damaged)
                .append(this.embedded, that.embedded)
                .append(this.vertical, that.vertical)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
