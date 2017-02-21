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

import com.ahuotala.platformgame.Game;
import java.awt.Rectangle;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        Player.offsetX = 0;

        assertEquals(32 * Game.scale, tile.getWidth());
        assertEquals(32 * Game.scale, tile.getBounds().getWidth(), 1.0);
        assertEquals(32 * Game.scale, tile.getHeight());
        assertEquals(32 * Game.scale, tile.getBounds().getHeight(), 1.0);

        tile.setWidth(55);
        assertEquals(55 * Game.scale, tile.getWidth());
        assertEquals(55 * Game.scale, tile.getBounds().getWidth(), 1.0);

        tile.setHeight(52);
        assertEquals(52 * Game.scale, tile.getHeight());
        assertEquals(52 * Game.scale, tile.getBounds().getHeight(), 1.0);

        assertEquals(16, tile.getBounds().getX(), 1.0);
        assertEquals(26, tile.getBounds().getY(), 1.0);
    }

    @Test
    public void asettaaKoordinaatitOikein() {
        assertEquals(16, tile.getX());
        assertEquals(26, tile.getY());
    }

    @Test
    public void asettaaTekstuurinNimenOikein() {
        assertEquals("Test", tile.getTextureName());
        tile.setTextureName("12345");
        assertEquals("12345", tile.getTextureName());
    }

    @Test
    public void rajojenPaivitysToimii() {
        Player.offsetX = 0;
        tile.setX(58);
        tile.updateBounds();
        Rectangle bounds = tile.getBounds();
        assertEquals(58, bounds.getX(), 1.0);
        tile.setX(22);
        bounds = tile.getBounds();
        assertEquals(22, bounds.getX(), 1.0);

        tile.setY(492);
        tile.updateBounds();
        bounds = tile.getBounds();
        assertEquals(492, bounds.getY(), 1.0);
    }
}
