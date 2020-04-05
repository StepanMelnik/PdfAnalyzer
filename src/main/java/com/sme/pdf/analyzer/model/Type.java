package com.sme.pdf.analyzer.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Represent a type strategy to analyze pdf file and generate a specific result.
 */
public enum Type
{
    /**
     * The type to fetch an info about fonts used in pdf document.
     */
    FONT,

    /**
     * The type to fetch all text and an info about fonts used by text in pdf document.
     */
    TEXT,

    /**
     * The type to fetch and info about images used in pdf document.
     */
    IMAGE;

    /**
     * Gets an {@link Type} by its name.
     *
     * @param name The name to get a type for.
     * @return Returns the type specified by name.
     */
    public static Type fromName(String name)
    {
        for (Type type : Type.values())
        {
            if (StringUtils.equalsIgnoreCase(type.name(), name))
            {
                return type;
            }
        }
        throw new IllegalArgumentException("'" + name + "' is not a valid type");
    }
}
