package com.sme.pdf.analyzer.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests of {@link Type}
 */
public class TypeTest extends Assert
{
    @Test
    public void testFromName()
    {
        assertEquals(Type.FONT, Type.fromName("FONT"));
        assertEquals(Type.TEXT, Type.fromName("TEXT"));
        assertEquals(Type.IMAGE, Type.fromName("IMAGE"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_fromName_invalid()
    {
        Type.fromName("wrong name");
    }
}
