/*
 * Copyright (C) 2017 ahuotala
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.ahuotala.platformgame.entity;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tile -luokan testit.
 *
 * @author ahuotala
 */
public class TileTest {

    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(16, 26, "Test");
    }

    @Test
    public void asettaaKoonOikein() {
        assertEquals(32, tile.getWidth());
        assertEquals(32, tile.getHeight());
    }

    @Test
    public void asettaaKoordinaatitOikein() {
        assertEquals(16, tile.getX());
        assertEquals(26, tile.getY());
    }

    @Test
    public void asettaaTekstuurinNimenOikein() {
        assertEquals("Test", tile.getTextureName());
    }
}
