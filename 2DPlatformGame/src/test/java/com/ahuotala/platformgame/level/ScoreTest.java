/*
 * Copyright (C) 2017 alehuo
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
package com.ahuotala.platformgame.level;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Pistejärjestelmän testit.
 *
 * @author alehuo
 */
public class ScoreTest {

    private Score s;

    @Before
    public void init() {
        s = new Score();
    }

    @Test
    public void alkutilaOikein() {
        assertEquals(0, s.getCollectedCoins());
        assertEquals(0, s.getDefeatedMonsters());
        assertEquals(0, s.getValue());
        assertEquals(0, s.getStartingTime());
        assertEquals(0, s.getEndingTime());
        assertEquals(0, s.getDeltaTime());
    }

    @Test
    public void aloitusOnnistuu() {
        s.start();
        assertTrue(s.getStartingTime() != 0);
    }

    @Test
    public void setteritToimivat() {
        
        int r1 = 561;
        int r2 = 264;
        int r3 = 127;

        s.setCollectedCoins(r1);
        assertEquals(r1, s.getCollectedCoins());

        s.setCollectedCoins(-r1);
        assertEquals(0, s.getCollectedCoins());

        s.setCollectedCoins(0);
        assertEquals(0, s.getCollectedCoins());

        s.setDefeatedMonsters(r2);
        assertEquals(r2, s.getDefeatedMonsters());

        s.setDefeatedMonsters(-r2);
        assertEquals(0, s.getDefeatedMonsters());

        s.setDefeatedMonsters(0);
        assertEquals(0, s.getDefeatedMonsters());

        s.setDeltaTime(r3);
        assertEquals(r3, s.getDeltaTime());

        s.setDeltaTime(-r3);
        assertEquals(0, s.getDeltaTime());

        s.setDeltaTime(0);
        assertEquals(0, s.getDeltaTime());

        s.setStartingTime(r3 * 2 + r2);
        assertEquals(r3 * 2 + r2, s.getStartingTime());

        s.setStartingTime(-(r3 * 2 + r2));
        assertEquals(0, s.getStartingTime());

        s.setStartingTime(0);
        assertEquals(0, s.getStartingTime());

        s.setEndingTime(r1 * 2 + r3);
        assertEquals(r1 * 2 + r3, s.getEndingTime());

        s.setEndingTime(-(r1 * 2 + r3));
        assertEquals(0, s.getEndingTime());

        s.setEndingTime(0);
        assertEquals(0, s.getEndingTime());

        s.setValue(r1 * r2);
        assertEquals(r1 * r2, s.getValue());

        s.setValue(-(r1 * r2));
        assertEquals(0, s.getValue());

        s.setValue(0);
        assertEquals(0, s.getValue());

    }

    @Test
    public void pisteetKeraantyvatOikein() {
        s.start();

        assertEquals(0, s.getCollectedCoins());

        s.collectCoin();
        assertEquals(50, s.getValue());
        assertEquals(1, s.getCollectedCoins());

        s.collectCoin();
        assertEquals(100, s.getValue());
        assertEquals(2, s.getCollectedCoins());

        s.defeatMonster();
        assertEquals(350, s.getValue());
        assertEquals(1, s.getDefeatedMonsters());

        //Asetetaan vielä kulunut aika
        s.setDeltaTime(550);

        assertEquals("Pistemäärä: 350, kerätyt kolikot: 2, tapetut hirviöt: 1, tason suoritukseen kulunut aika: 550s", s.toString());
    }

    @Test
    public void lopetusOnnistuu() {

        s.start();

        try {
            //Nukutaan hetki
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScoreTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        s.stop();

        assertTrue(s.getStartingTime() > 0);
        assertTrue(s.getEndingTime() > 0);
        assertTrue(s.getDeltaTime() > 0);
    }

}
