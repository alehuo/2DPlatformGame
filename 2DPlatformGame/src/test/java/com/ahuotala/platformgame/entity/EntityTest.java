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
import java.awt.Graphics;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertEquals(234, entity.getX());
        assertEquals(567, entity.getY());
    }

    @Test
    public void asettaaXnOikein() {
        entity.setX(55);
        assertEquals(55, entity.getX());
        entity.setX(11);
        assertEquals(11, entity.getX());
    }

    @Test
    public void asettaaYnOikein() {
        entity.setY(23);
        assertEquals(23, entity.getY());
        entity.setY(42);
        assertEquals(42, entity.getY());
    }

    @Test
    public void asettaaKorkeudenOikein() {
        entity.setHeight(88);
        assertEquals(88, entity.getHeight());
        entity.setHeight(77);
        assertEquals(77, entity.getHeight());
    }

    @Test
    public void asettaaLeveydenOikein() {
        entity.setWidth(54);
        assertEquals(54, entity.getWidth());
        entity.setWidth(32);
        assertEquals(32, entity.getWidth());
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

//    @Test
//    public void liikkuuOikein() {
//        int xNyt = entity.getX();
//        int yNyt = entity.getY();
//
//        int xMaara = 22;
//        int yMaara = -12;
//
//        entity.setDx(xMaara);
//        entity.setDy(yMaara);
//
//        entity.move();
//
//        assertEquals(xNyt + xMaara, entity.getX());
//        assertEquals(yNyt + yMaara, entity.getY());
//    }
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
