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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Pilvi -luokan testit
 *
 * @author ahuotala
 */
public class CloudTest {

    private Cloud cloud;

    @Before
    public void init() {
        cloud = new Cloud();
    }

    @Test
    public void alkutilaOikein() {
        assertEquals(128 * Game.SCALE, cloud.getWidth());
        assertEquals(64 * Game.SCALE, cloud.getHeight());
        assertEquals(0, cloud.getX());
        assertEquals(0, cloud.getY());
    }

    @Test
    public void toinenKonstruktoriToimii() {
        Cloud cloud2 = new Cloud(42, 55);
        assertEquals(42, cloud2.getX());
        assertEquals(55, cloud2.getY());
        assertEquals(128 * Game.SCALE, cloud2.getWidth());
        assertEquals(64 * Game.SCALE, cloud2.getHeight());
    }
}
