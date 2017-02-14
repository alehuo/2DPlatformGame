package com.ahuotala.platformgame.entity;

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
import com.ahuotala.platformgame.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Entity -luokan testit.
 *
 * @author ahuotala
 */
public class EntityTest {

    private Entity entity;

    @Before
    public void setUp() {
        entity = new TestEntity();
    }

    @Test
    public void alkutilaOikein() {

        int x = 234;
        int y = 567;
        int width = 32;
        int height = 32;

        assertEquals(x, entity.getX());
        assertEquals(y, entity.getY());

        Rectangle r = new Rectangle(x, y, width * Game.SCALE, height * Game.SCALE);
        assertEquals(r, entity.getBounds());

        assertTrue(entity.isVisible());
    }

    @Test
    public void asettaaNakyvyyden() {
        assertTrue(entity.isVisible());
        entity.setVisible(false);
        assertFalse(entity.isVisible());
    }

    @Test
    public void asettaaXnOikein() {
        entity.setX(55);
        assertEquals(55, entity.getX());
        assertEquals(55, entity.getBounds().getX(), 1.0);
        entity.setX(11);
        assertEquals(11, entity.getX());
        assertEquals(11, entity.getBounds().getX(), 1.0);
    }

    @Test
    public void asettaaYnOikein() {
        entity.setY(23);
        assertEquals(23, entity.getY());
        assertEquals(23, entity.getBounds().getY(), 1.0);
        entity.setY(42);
        assertEquals(42, entity.getY());
        assertEquals(42, entity.getBounds().getY(), 1.0);
    }

    @Test
    public void asettaaKorkeudenOikein() {
        entity.setHeight(88);
        assertEquals(88 * Game.SCALE, entity.getHeight());
        assertEquals(88 * Game.SCALE, entity.getBounds().getHeight(), 1.0);
        entity.setHeight(77);
        assertEquals(77 * Game.SCALE, entity.getHeight());
        assertEquals(77 * Game.SCALE, entity.getBounds().getHeight(), 1.0);
    }

    @Test
    public void asettaaLeveydenOikein() {
        entity.setWidth(54);
        assertEquals(54 * Game.SCALE, entity.getWidth());
        assertEquals(54 * Game.SCALE, entity.getBounds().getWidth(), 1.0);
        entity.setWidth(32);
        assertEquals(32 * Game.SCALE, entity.getWidth());
        assertEquals(32 * Game.SCALE, entity.getBounds().getWidth(), 1.0);
    }

    @Test
    public void asettaaLiikkuvuusMaaritteetOikein() {
        entity.setxMovement(88);
        assertEquals(88, entity.getxMovement());

        entity.setyMovement(54);
        assertEquals(54, entity.getyMovement());

        entity.setDx(66);
        assertEquals(66, entity.getDx());

        entity.setDy(23);
        assertEquals(23, entity.getDy());
    }

    /**
     * Testientiteetti
     */
    private class TestEntity extends Entity {

        TestEntity() {
            super(234, 567);
        }

        @Override
        public void render(Graphics g) {

        }

        @Override
        public void tick() {

        }

    }

}
