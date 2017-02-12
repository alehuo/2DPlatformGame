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
package com.ahuotala.platformgame.level;

/**
 * Pisteytysjärjestelmä.
 *
 * @author ahuotala
 */
public class Score {

    private int value = 0;

    private int collectedCoins = 0;
    private int defeatedMonsters = 0;

    //Aloitusaika
    private long startingTime;

    //Lopetusaika
    private long endingTime;

    //Kulunut aika (sekunneissa)
    private long deltaTime;

    public int getValue() {
        return value;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    public int getDefeatedMonsters() {
        return defeatedMonsters;
    }

    public long getStartingTime() {
        return startingTime;
    }

    public long getEndingTime() {
        return endingTime;
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    /**
     * Asettaa pistemäärän. Jos määrä on negatiivinen, annetaan arvoksi nolla.
     *
     * @param value Pistemäärä
     */
    public void setValue(int value) {
        if (value < 0) {
            value = 0;
        }
        this.value = value;
    }

    /**
     * Asettaa kerätyt kolikot. Jos määrä on negatiivinen, annetaan arvoksi
     * nolla.
     *
     * @param collectedCoins Kerätyt kolikot
     */
    public void setCollectedCoins(int collectedCoins) {
        if (collectedCoins < 0) {
            collectedCoins = 0;
        }
        this.collectedCoins = collectedCoins;
    }

    /**
     * Asettaa tuhotut hirviöt. Jos määrä on negatiivinen, annetaan arvoksi
     * nolla.
     *
     * @param defeatedMonsters Tuhotut hirviöt
     */
    public void setDefeatedMonsters(int defeatedMonsters) {
        if (defeatedMonsters < 0) {
            defeatedMonsters = 0;
        }
        this.defeatedMonsters = defeatedMonsters;
    }

    /**
     * Asettaa aloitusajan. Jos määrä on negatiivinen, annetaan arvoksi nolla.
     *
     * @param startingTime Aloitusaika
     */
    public void setStartingTime(long startingTime) {
        if (startingTime < 0) {
            startingTime = 0;
        }
        this.startingTime = startingTime;
    }

    /**
     * Asettaa lopetusajan. Jos määrä on negatiivinen, annetaan arvoksi nolla.
     *
     * @param endingTime Lopetusaika
     */
    public void setEndingTime(long endingTime) {
        if (endingTime < 0) {
            endingTime = 0;
        }
        this.endingTime = endingTime;
    }

    /**
     * Asettaa delta -ajan. Jos määrä on negatiivinen, annetaan arvoksi nolla.
     *
     * @param deltaTime Delta -aika (lopetus - aloitus)
     */
    public void setDeltaTime(long deltaTime) {
        if (deltaTime < 0) {
            deltaTime = 0;
        }
        this.deltaTime = deltaTime;
    }

    public long getCurrentTime() {
        return System.currentTimeMillis() - startingTime;
    }

    /**
     * Aloita peli.
     */
    public void start() {
        //Nykyinen aika
        startingTime = System.currentTimeMillis();

        //Pisteytys
        value = 0;

        //Kerätyt kolikot
        collectedCoins = 0;

        //Tapetut hirviöt
        defeatedMonsters = 0;
    }

    /**
     * Lopeta peli.
     */
    public void stop() {
        endingTime = System.currentTimeMillis();
        deltaTime = (endingTime - startingTime) / 1000;
    }

    /**
     * Kerää kolikko.
     */
    public void collectCoin() {
        collectedCoins++;
        value += 50;
    }

    /**
     * Tapa hirviö.
     */
    public void defeatMonster() {
        defeatedMonsters++;
        value += 250;
    }

    /**
     * toString -metodi palauttaa pisteytyksen yhteenvedon.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Pistemäärä: " + value + ", kerätyt kolikot: " + collectedCoins + ", tapetut hirviöt: " + defeatedMonsters + ", tason suoritukseen kulunut aika: " + deltaTime + "s"; //To change body of generated methods, choose Tools | Templates.
    }

}
