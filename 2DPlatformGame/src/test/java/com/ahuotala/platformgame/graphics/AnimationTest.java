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
        assertEquals(0, ani.getIndex());
        assertEquals(0, ani.getCount());
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

    @Test
    public void tickToimii() {
        int size = 50;
        Animation ani2 = new Animation(size);
        lisaaKehyksia(ani2, size);
        for (int i = 0; i < size + 1; i++) {
            assertEquals(0, ani2.getIndex());
            assertEquals(i, ani2.getCount());
            ani2.tick();
        }
        assertEquals(1, ani2.getIndex());
        assertEquals(0, ani2.getCount());

        size = 2;
        ani2 = new Animation(size);
        lisaaKehyksia(ani2, size);
        assertEquals(0, ani2.getIndex());
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                assertEquals(0, ani2.getIndex());
            } else {
                assertEquals(1, ani2.getIndex());
            }
            for (int j = 0; j < 3; j++) {
                ani2.tick();
            }
        }

    }

    public void lisaaKehyksia(Animation ani, int lkm) {
        Sprite spr = new Sprite();
        for (int i = 0; i < lkm; i++) {
            ani.addFrame(spr);
        }
    }
}
