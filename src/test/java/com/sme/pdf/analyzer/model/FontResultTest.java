package com.sme.pdf.analyzer.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests of {@link FontResult}
 */
public class FontResultTest extends Assert
{
    @Test
    public void testSetGet() throws Exception
    {
        final String name = "Times";

        FontResult fontResult = new FontResult();
        fontResult.setDamaged(false);
        fontResult.setEmbedded(true);
        fontResult.setName(name);
        fontResult.setVertical(false);

        assertEquals(false, fontResult.isDamaged());
        assertEquals(true, fontResult.isEmbedded());
        assertEquals(name, fontResult.getName());
        assertEquals(false, fontResult.isVertical());
    }

    @Test
    public void testConstructor() throws Exception
    {
        final String name = "Times";

        FontResult fontResult = new FontResult(name, false, true, false);

        assertEquals(false, fontResult.isDamaged());
        assertEquals(true, fontResult.isEmbedded());
        assertEquals(name, fontResult.getName());
        assertEquals(false, fontResult.isVertical());
    }

    @Test
    public void testGetLabel() throws Exception
    {
        FontResult fontResult = new FontResult("Ariel", false, true, false);

        assertEquals("Name: Ariel; damaged: false; embedded: true; vertical: false", fontResult.getLabel());
    }
}
