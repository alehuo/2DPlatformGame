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

import com.ahuotala.platformgame.utils.StopWatch;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Pistejärjestelmän testit.
 *
 * @author alehuo
 */
public class ScoreTest {

    private Score score;
    private StopWatch stopWatch;

    @Before
    public void init() {
        stopWatch = new StopWatch();
        score = new Score(stopWatch);
    }

    @Test
    public void alkutilaOikein() {
        assertEquals(0, score.getCollectedCoins());
        assertEquals(0, score.getDefeatedMonsters());
        assertEquals(0, score.getValue());
        assertEquals(0, score.getDeltaTime());
    }

    @Test
    public void setteritToimivat() {

        int r1 = 561;
        int r2 = 264;

        score.setCollectedCoins(r1);
        assertEquals(r1, score.getCollectedCoins());

        score.setCollectedCoins(-r1);
        assertEquals(0, score.getCollectedCoins());

        score.setCollectedCoins(0);
        assertEquals(0, score.getCollectedCoins());

        score.setDefeatedMonsters(r2);
        assertEquals(r2, score.getDefeatedMonsters());

        score.setDefeatedMonsters(-r2);
        assertEquals(0, score.getDefeatedMonsters());

        score.setDefeatedMonsters(0);
        assertEquals(0, score.getDefeatedMonsters());

        score.setValue(r1 * r2);
        assertEquals(r1 * r2, score.getValue());

        score.setValue(-(r1 * r2));
        assertEquals(0, score.getValue());

        score.setValue(0);
        assertEquals(0, score.getValue());

    }

    @Test
    public void pisteetKeraantyvatOikein() {
        score.start();

        assertEquals(0, score.getCollectedCoins());

        score.collectCoin();
        assertEquals(50, score.getValue());
        assertEquals(1, score.getCollectedCoins());

        score.collectCoin();
        assertEquals(100, score.getValue());
        assertEquals(2, score.getCollectedCoins());

        score.defeatMonster();
        assertEquals(350, score.getValue());
        assertEquals(1, score.getDefeatedMonsters());
    }

}
