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
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahuotala
 */
public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player(0, 0);
    }

    @Test
    public void alkutilaPelaajallaOikein() {
        assertEquals("X:ää ei aseteta oikein", 0, player.getX());
        assertEquals("Y:tä ei aseteta oikein", 0, player.getY());
        assertEquals("Leveyttä ei aseteta oikein", 24, player.getWidth());
        assertEquals("Pituutta ei aseteta oikein", 32, player.getHeight());
        assertEquals("Y-suuntaista liikkumismatkaa ei aseteta oikein", 0, player.getyMovement());
        assertEquals("X-suuntaista liikkumismatkaa ei aseteta oikein", 4, player.getxMovement());
    }

    @Test
    public void asettaaXnOikein() {
        player.setX(55);
        assertEquals(55, player.getX());
        player.setX(11);
        assertEquals(11, player.getX());
    }

    @Test
    public void asettaaYnOikein() {
        player.setY(23);
        assertEquals(23, player.getY());
        player.setY(42);
        assertEquals(42, player.getY());
    }

    @Test
    public void asettaaKorkeudenOikein() {
        player.setHeight(88);
        assertEquals(88, player.getHeight());
        player.setHeight(77);
        assertEquals(77, player.getHeight());
    }

    @Test
    public void asettaaLeveydenOikein() {
        player.setWidth(54);
        assertEquals(54, player.getWidth());
        player.setWidth(32);
        assertEquals(32, player.getWidth());
    }

    @Test
    public void asettaaLiikkuvuusMaaritteetOikein() {
        player.setxMovement(88);
        assertEquals(88, player.getxMovement());

        player.setyMovement(54);
        assertEquals(54, player.getyMovement());

        player.setDx(66);
        assertEquals(66, player.getDx());

        player.setDy(23);
        assertEquals(23, player.getDy());
    }

    @Test
    public void liikkuuOikein() {
        int xNyt = player.getX();
        int yNyt = player.getY();

        int xMaara = 22;
        int yMaara = -12;

        player.setDx(xMaara);
        player.setDy(yMaara);

        player.move();

        assertEquals(xNyt + xMaara, player.getX());
        assertEquals(yNyt + yMaara, player.getY());
    }

    @Test
    public void keyListenerToimii() throws AWTException {

        int xMovement = player.getxMovement();

        JTextField inputField = new JTextField();

        //Vasemmalle
        KeyEvent ke = new KeyEvent(inputField, 0, System.currentTimeMillis(), 0, KeyEvent.VK_A);

        //Painetaan A -nappia
        player.keyPressed(ke);

        assertEquals("X-siirtymä asetettiin väärin, kun liikuttiin vasemalle", -xMovement, player.getDx());

        //Päästetään nappi pohjasta
        player.keyReleased(ke);

        assertEquals("Nappia ei päästetty pois pohjasta oikein", 0, player.getDx());

        //Oikealle
        ke = new KeyEvent(inputField, 0, System.currentTimeMillis(), 0, KeyEvent.VK_D);

        //Painetaan D -nappia
        player.keyPressed(ke);

        assertEquals("Y-siirtymä on väärin, kun liikuttiin oikealle", xMovement, player.getDx());

        //Päästetään nappi pohjasta
        player.keyReleased(ke);

        assertEquals("Nappia ei päästetty pois pohjasta oikein", 0, player.getDx());

        //Hyppy
        ke = new KeyEvent(inputField, 0, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE);

        //Pelaaja voi hypätä vain silloin, kun se ei ole tippumassa
        player.setFalling(false);
        assertFalse("Tippumistilaa ei asetettu oikein", player.isFalling());

        //Paina välilyöntiä
        player.keyPressed(ke);

        assertTrue("Pelaajalle ei asetettu hyppytilaa, vaikka välilyöntiä painettiin", player.isJumping());
    }
}
