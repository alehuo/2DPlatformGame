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
package com.ahuotala.platformgame.graphics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ahuotala
 */
public class AnimationTest {

    private Animation ani;

    @Before
    public void init() {
        ani = new Animation(10);
    }

    @Test
    public void alkutilaOikein() {
        assertEquals(0, ani.getFrames().size());
        assertNotNull(ani.getFrames());
    }

    @Test
    public void kehystenLisaysToimii() {
        Sprite spr = new Sprite();
        ani.addFrame(spr);
        assertEquals(1, ani.getFrames().size());
        assertNotNull(ani.getFrames());
        ani.addFrame(spr);
        assertEquals(2, ani.getFrames().size());
        assertNotNull(ani.getFrames());
    }

    @Test
    public void nextIndexToimii() {
        int count = 50;
        for (int i = 0; i < count; i++) {
            ani.addFrame(new Sprite());
        }

        assertEquals(0, ani.getIndex());
        int startIndex = ani.getIndex();
        for (int i = 0; i < 10; i++) {
            ani.nextIndex();
            assertEquals(startIndex + i + 1, ani.getIndex());
            assertNotNull(ani.currentFrame());
        }
        assertEquals(10, ani.getIndex());

        startIndex = ani.getIndex();
        for (int i = 0; i < 39; i++) {
            ani.nextIndex();
            assertEquals(startIndex + i + 1, ani.getIndex());
            assertNotNull(ani.currentFrame());
        }

        assertEquals(49, ani.getIndex());

        ani.nextIndex();

        assertEquals(0, ani.getIndex());

        assertNotNull(ani.currentFrame());
    }

    @Test
    public void intervalToimii() {
        ani.setInterval(55);
        assertEquals(55, ani.getInterval());
    }
}
