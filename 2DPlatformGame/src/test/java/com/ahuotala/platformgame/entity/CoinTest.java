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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tile -luokan testit.
 *
 * @author ahuotala
 */
public class CoinTest {

    private Coin coin;

    private final int coinWidthHeight = 18;

    @Before
    public void setUp() {
        coin = new Coin(12, 24);
    }

    @Test
    public void alkutilaOikein() {
        assertEquals(coinWidthHeight * Game.scale, coin.getHeight());
        assertEquals(coinWidthHeight * Game.scale, coin.getWidth());
    }

    @Test
    public void alkutilaOikein2() {
        Coin coin2 = new Coin();
        assertEquals(0, coin2.getX());
        assertEquals(0, coin2.getY());
        assertEquals(coinWidthHeight * Game.scale, coin2.getWidth());
        assertEquals(coinWidthHeight * Game.scale, coin2.getHeight());
    }

    @Test
    public void asettaaKoonOikein() {

        int width = 52;
        int height = 66;
        coin.setWidth(width);
        coin.setHeight(height);

        assertEquals(width * Game.scale, coin.getWidth());
        assertEquals(width * Game.scale, coin.getBounds().getWidth(), 1.0);
        assertEquals(height * Game.scale, coin.getHeight());
        assertEquals(height * Game.scale, coin.getBounds().getHeight(), 1.0);

        coin.setWidth(width + 3);
        assertEquals((width + 3) * Game.scale, coin.getWidth());
        assertEquals((width + 3) * Game.scale, coin.getBounds().getWidth(), 1.0);

        coin.setHeight(height - 14);
        assertEquals((height - 14) * Game.scale, coin.getHeight());
        assertEquals((height - 14) * Game.scale, coin.getBounds().getHeight(), 1.0);
    }

    @Test
    public void asettaaKoordinaatitOikein() {
        assertEquals(12, coin.getX());
        assertEquals(24, coin.getY());
    }
}
