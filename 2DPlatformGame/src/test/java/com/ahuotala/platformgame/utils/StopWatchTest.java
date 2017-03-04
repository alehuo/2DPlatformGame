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
package com.ahuotala.platformgame.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Sekuntikellon testaukseen käytetyt luokat
 *
 * @author alehuo
 */
public class StopWatchTest {

    private StopWatch s;

    @Before
    public void init() {
        s = new StopWatch();
    }

    @Test
    public void alkutilaOikein() {
        s.currentMs(System.currentTimeMillis());
        assertEquals(0, s.getStartingTime());
        assertEquals(0, s.getEndingTime());
        assertTrue(s.getCurrentMilliseconds() > 0);
        assertTrue(s.getCurrentSeconds() > 0);
        assertEquals(0, s.getTotalMilliseconds());
        assertEquals(0, s.getTotalSeconds(), 1.0);
    }

    @Test
    public void kaynnistysJaPysaytysToimii() throws InterruptedException {
        updateCurrentMs();
        s.start();
        Thread.sleep(800);
        updateCurrentMs();
        assertTrue(s.getStartingTime() > 0);
        assertTrue(s.isRunning());

        Thread.sleep(800);
        updateCurrentMs();
        s.stop();

        assertTrue(s.getEndingTime() > 0);
        assertFalse(s.isRunning());
        assertTrue(s.getStartingTime() > 0);

        assertTrue(s.getTotalMilliseconds() > 0);
        assertTrue(s.getTotalMilliseconds() > 0);

        s.reset();

        assertEquals(0, s.getStartingTime());
        assertEquals(0, s.getEndingTime());
        assertFalse(s.isRunning());
    }

    @Test
    public void ajanMittausToimii() {

        long startingTime = System.currentTimeMillis();
        int delta = 35409385;

        //Aseta aloitusaika täsmällisesti
        s.currentMs(startingTime);

        s.start();

        s.currentMs(startingTime + delta);

        assertEquals(delta, s.getCurrentMilliseconds());

        assertNotEquals(startingTime + delta, s.getCurrentMilliseconds());

        assertTrue(s.getCurrentMilliseconds() > 0);
        assertTrue(s.getCurrentMilliseconds() < delta * 2);

        assertEquals(delta * 1.0 / 1000, s.getCurrentSeconds(), 1.0);

        assertNotEquals((startingTime + delta) * 1.0 / 1000, s.getCurrentSeconds(), 1.0);

        assertTrue(s.getCurrentSeconds() > 0);
        assertTrue(s.getCurrentSeconds() < (startingTime + delta) * 2.0 / 1000);

        s.currentMs(startingTime + 2 * delta);
        s.stop();

        assertEquals(2 * delta, s.getTotalMilliseconds());
        assertTrue(s.getTotalMilliseconds() > 0);

        assertEquals(2.0 * delta / 1000, s.getTotalSeconds(), 1.0);
        assertTrue(s.getTotalSeconds() > 0);
    }

    @Test
    public void setteritToimivat() {
        s.setRunning(false);
        assertFalse(s.isRunning());
        s.setRunning(true);
        assertTrue(s.isRunning());
        s.setStartingTime(555);
        assertEquals(555, s.getStartingTime());
        s.setEndingTime(5555);
        assertEquals(5555, s.getEndingTime());
    }

    @Test
    public void resetToimii() throws InterruptedException {
        updateCurrentMs();
        s.start();
        Thread.sleep(500);
        updateCurrentMs();
        s.stop();
        assertTrue(s.getStartingTime() > 0);
        assertTrue(s.getEndingTime() > 0);

        s.reset();
        assertEquals(0, s.getStartingTime());
        assertEquals(0, s.getEndingTime());
        assertFalse(s.isRunning());
        assertFalse(s.getStartingTime() != 0);
        assertFalse(s.getEndingTime() != 0);
    }

    public long updateCurrentMs() {
        long currentMillis = System.currentTimeMillis();
        s.currentMs(currentMillis);
        return currentMillis;
    }
}
