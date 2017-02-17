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
package com.ahuotala.platformgame.ai;

/**
 * Tekoäly hirviöille.
 *
 * @author ahuotala
 */
public class MonsterAi {
    //Työn alla
    //Jos pelaaja liikkuu vasemmalle, liikuta myös hirviötä vasemmalle
    //Jos pelaaja liikkuu oikealle ja on tarpeeksi lähellä, hirviö lähtee karkuun
    //Jos pelaaja hyppää, anna mahdollisuus pelaajalle hypätä hirviön päälle (tällöin hidasta vauhtia / pysäytä hirviö)
    //vasen, oikea, hyppäys
    //lähesty , karkuun , freeze

    /**
     * Konstruktori.
     */
    public MonsterAi() {
    }

    /**
     * Palauttaa seuraavaksi tehtävän liikkeen.
     *
     * @return Seuraavaksi tehtävä liike.
     */
    public int nextMove() {
        return 0;
    }

}
