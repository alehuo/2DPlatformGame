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
import com.ahuotala.platformgame.level.GameLevel;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Pelaaja -luokan testit.
 *
 * @author ahuotala
 */
public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player(Game.WINDOWWIDTH / 2, 0);
    }

    @Test
    public void alkutilaPelaajallaOikein() {
        assertEquals("X:ää ei aseteta oikein", Game.WINDOWWIDTH / 2, player.getX());
        assertEquals("Y:tä ei aseteta oikein", 0, player.getY());
        assertEquals("Leveyttä ei aseteta oikein", 24 * Game.scale, player.getWidth());
        assertEquals("Korkeutta ei aseteta oikein", 32 * Game.scale, player.getHeight());
        assertEquals("Y-suuntaista liikkumismatkaa ei aseteta oikein", 3 * Game.scale, player.getYMovement());
        assertEquals("X-suuntaista liikkumismatkaa ei aseteta oikein", 4 * Game.scale, player.getXMovement());
        assertEquals("Y-suuntaista putoamista ei aseteta oikein", 3 * Game.scale, player.getDy());
        assertEquals("Pelaajan HP:ta ei aseteta oikein", 1000, player.getHealth());
    }

    @Test
    public void vahingoittuuOikein() {
        int startHealth = 1000;
        int dmgAmount = 442;
        assertEquals("Pelaajan HP:ta ei aseteta oikein", startHealth, player.getHealth());
        player.damagePlayer(442);
        assertEquals("Pelaaja ei vahingoitu oikein.", startHealth - dmgAmount, player.getHealth());
        player.damagePlayer(-400);
        assertEquals("Pelaajan ei kuuluisi vahingoittua negatiivista määrää.", startHealth - dmgAmount, player.getHealth());
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
        assertEquals(88 * Game.scale, player.getHeight());
        player.setHeight(77);
        assertEquals(77 * Game.scale, player.getHeight());
    }

    @Test
    public void asettaaLeveydenOikein() {
        player.setWidth(54);
        assertEquals(54 * Game.scale, player.getWidth());
        player.setWidth(32);
        assertEquals(32 * Game.scale, player.getWidth());
    }

    @Test
    public void asettaaLiikkuvuusMaaritteetOikein() {
        player.setXMovement(88);
        assertEquals(88, player.getXMovement());

        player.setYMovement(54);
        assertEquals(54, player.getYMovement());

        player.setDx(66);
        assertEquals(66, player.getDx());

        player.setDy(23);
        assertEquals(23, player.getDy());
    }

    @Test
    public void liikkuuOikein() {

        //Asetetaan kentän leveys, ettei rajojen tarkistus hajota testiä
        GameLevel.levelWidth = 9001;

        int xNyt = player.getX() - Game.STARTINGOFFSET + Player.offsetX;
        int yNyt = player.getY();

        int xMaara = 22;
        int yMaara = -12;

        player.setDx(xMaara);
        player.setDy(yMaara);

        List<Entity> entities = new ArrayList();

        player.move(entities, new ArrayList(), null);

        assertEquals(xNyt + xMaara, player.getX() - Game.STARTINGOFFSET + Player.offsetX);
        assertEquals(yNyt + yMaara, player.getY());
    }

    @Test
    public void keyListenerToimii() throws AWTException {

        int xMovement = player.getXMovement();

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

    @Test
    public void liikkumissuuntaOikein() {
        assertEquals(WalkingDirection.RIGHT, player.getWd());

        player.setWd(WalkingDirection.LEFT);

        assertEquals(WalkingDirection.LEFT, player.getWd());
    }

    @Test
    public void hyppaaOikein() {
        player.setDy(0);
        player.setFalling(false);
        player.jump();
        assertEquals(player.getYMovement(), player.getDy());
    }

}
