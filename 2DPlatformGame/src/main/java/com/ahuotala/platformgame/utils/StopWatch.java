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
package com.ahuotala.platformgame.utils;

/**
 * Sekuntikello
 *
 * @author ahuotala
 */
public class StopWatch implements Clock, Tickable {

    private long startingTime;
    private long endingTime;
    private long currentMs;
    private boolean running;

    public StopWatch() {
        startingTime = 0;
        endingTime = 0;
        running = false;
    }

    /**
     * Käynnistää sekuntikellon
     */
    public void start() {
        if (!running) {
            startingTime = currentMs();
            running = true;
        }
    }

    /**
     * Pysäyttää sekuntikellon
     */
    public void stop() {
        if (running) {
            endingTime = currentMs();
            running = false;
        }
    }

    /**
     * Nollaa sekuntikellon
     */
    public void reset() {
        startingTime = 0;
        endingTime = 0;
        running = false;
    }

    /**
     * Palauttaa käynnissä olevan kellon kuluneen ajan
     *
     * @return Kulunut aika sekunteina
     */
    public double getCurrentSeconds() {
        return (currentMs - startingTime) * 1.0 / 1000;
    }

    /**
     * Palauttaa käynnissä olevan kellon kuluneen ajan
     *
     * @return Kulunut aika millisekunteina
     */
    public long getCurrentMilliseconds() {
        return (currentMs - startingTime);
    }

    /**
     * Palauttaa kokonaisajan, joka kului metodien start() ja stop() välisenä
     * aikana.
     *
     * @return Kulunut kokonaisaika sekunteina
     */
    public double getTotalSeconds() {
        return (endingTime - startingTime) * 1.0 / 1000;
    }

    /**
     * Palauttaa kokonaisajan, joka kului metodien start() ja stop() välisenä
     * aikana.
     *
     * @return Kulunut kokonaisaika millisekunteina
     */
    public long getTotalMilliseconds() {
        return endingTime - startingTime;
    }

    public long getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(long startingTime) {
        this.startingTime = startingTime;
    }

    public long getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(long endingTime) {
        this.endingTime = endingTime;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public long currentMs() {
        return currentMs;
    }

    @Override
    public void currentMs(long currentMs) {
        this.currentMs = currentMs;
    }

    @Override
    public void tick() {
        currentMs(System.currentTimeMillis());
    }

}
