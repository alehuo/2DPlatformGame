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
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Hirviö-luokan testit.
 *
 * @author ahuotala
 */
public class MonsterTest {

    private Monster monster;

    @Before
    public void setUp() {
        monster = new Monster(13, 0);
    }

    @Test
    public void alkutilaHirviollaOikein() {
        assertEquals("X:ää ei aseteta oikein", 13, monster.getX());
        assertEquals("Y:tä ei aseteta oikein", 0, monster.getY());
        assertEquals("Leveyttä ei aseteta oikein", 24 * Game.scale, monster.getWidth());
        assertEquals("Korkeutta ei aseteta oikein", 12 * Game.scale, monster.getHeight());
        assertEquals("Y-suuntaista liikkumismatkaa ei aseteta oikein", 3 * Game.scale, monster.getYMovement());
        assertEquals("X-suuntaista liikkumismatkaa ei aseteta oikein", 2 * Game.scale, monster.getXMovement());
        assertEquals("Y-suuntaista putoamista ei aseteta oikein", 3 * Game.scale, monster.getDy());
        assertTrue("Tippumistilaa ei aseteta oikein.", monster.isFalling());
        assertFalse("Hyppäämistilaa ei aseteta oikein.", monster.isJumping());
    }

    @Test
    public void alkutilaHirviollaOikein2() {
        Monster monster2 = new Monster();
        assertEquals("X:ää ei aseteta oikein", 0, monster2.getX());
        assertEquals("Y:tä ei aseteta oikein", 0, monster2.getY());
    }

    @Test
    public void asettaaXnOikein() {
        monster.setX(55);
        assertEquals(55, monster.getX());
        monster.setX(11);
        assertEquals(11, monster.getX());
    }

    @Test
    public void asettaaYnOikein() {
        monster.setY(23);
        assertEquals(23, monster.getY());
        monster.setY(42);
        assertEquals(42, monster.getY());
    }

    @Test
    public void asettaaKorkeudenOikein() {
        monster.setHeight(88);
        assertEquals(88 * Game.scale, monster.getHeight());
        monster.setHeight(77);
        assertEquals(77 * Game.scale, monster.getHeight());
    }

    @Test
    public void asettaaLeveydenOikein() {
        monster.setWidth(54);
        assertEquals(54 * Game.scale, monster.getWidth());
        monster.setWidth(32);
        assertEquals(32 * Game.scale, monster.getWidth());
    }

    @Test
    public void asettaaLiikkuvuusMaaritteetOikein() {
        monster.setXMovement(88);
        assertEquals(88, monster.getXMovement());

        monster.setYMovement(54);
        assertEquals(54, monster.getYMovement());

        monster.setDx(66);
        assertEquals(66, monster.getDx());

        monster.setDy(23);
        assertEquals(23, monster.getDy());
    }

    @Test
    public void hakeeTekoalynOikein() {
        assertNotNull(monster.getAi());
    }

    @Test
    public void liikkuminenToimii() {
        int xMovement = monster.getXMovement();
        int yMovement = monster.getYMovement();

        assertEquals(0, monster.getDx());

        monster.goLeft();

        assertEquals(-xMovement, monster.getDx());

        monster.setDx(0);
        monster.goRight();

        assertEquals(xMovement, monster.getDx());

        monster.setDx(0);

        List<Entity> entities = new ArrayList();
        for (int i = 0; i < 150; i++) {
            Tile tmpTile = new Tile(i * 32, -2, "coin_1");
            entities.add(tmpTile);
        }

        assertTrue(monster.isFalling());
        for (int i = 0; i < 10; i++) {
            monster.move(entities);
        }

        int preX = monster.getX();

        monster.goLeft();
//        Rectangle preBounds = monster.getBounds();
        for (int i = 0; i < 55; i++) {
            monster.move(entities);
            assertEquals(preX - monster.getDx(), monster.getX());
            assertEquals(preX - monster.getDx(), monster.getBounds().getX(), 1.0);
            assertNotEquals(preX, monster.getBounds().getX(), 1.0);
            preX = monster.getX();
        }
        assertFalse(monster.isFalling());
        assertTrue(monster.isJumping());

        monster.jump();

        assertTrue(monster.isJumping());
        assertEquals(yMovement, monster.getDy());
    }

}
