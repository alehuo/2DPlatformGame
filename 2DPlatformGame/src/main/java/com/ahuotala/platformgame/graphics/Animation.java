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
package com.ahuotala.platformgame.graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Animaatio -luokka.
 *
 * @author ahuotala
 */
public class Animation {

    private final List<Sprite> frames;
    private int index = 0;
    private int interval;
    private int count = 0;

    /**
     * Animaatio.
     *
     * @param interval Aika kehysten välillä
     */
    public Animation(int interval) {
        frames = new ArrayList();
        this.interval = interval;
    }

    /**
     * Lisää kehyksen listaan.
     *
     * @param sprite Kehys
     */
    public void addFrame(Sprite sprite) {
        index = 0;
        frames.add(sprite);
    }

    /**
     * Palauttaa nykyisen kehyksen.
     *
     * @return Nykyinen kehys
     */
    public Sprite currentFrame() {
        return frames.get(index);
    }

    public List<Sprite> getFrames() {
        return frames;
    }

    public int getIndex() {
        return index;
    }

    public int getCount() {
        return count;
    }

    /**
     * Kasvattaa indeksiä yhdellä (ja siirtyy nollaan, jos kehyksen indeksi on
     * suurin mahdollinen).
     */
    public void nextIndex() {
        if (index == frames.size() - 1) {
            index = 0;
        } else {
            index++;
        }
    }

    /**
     * Asettaa kehyksen aikavälin nollaten laskurin.
     *
     * @param interval Kehyksen aikaväli
     */
    public void setInterval(int interval) {
        this.interval = interval;
        count = 0;
    }

    public int getInterval() {
        return interval;
    }

    /**
     * tick() -metodi varmistaa sen, että animaatio vaihtaa seuraavaan
     * kehykseen.
     */
    public void tick() {
        if (count == interval) {
            nextIndex();
            count = 0;
        } else {
            count++;
        }
    }

}
