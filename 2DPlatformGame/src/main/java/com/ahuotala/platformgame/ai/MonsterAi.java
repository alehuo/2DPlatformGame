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
package com.ahuotala.platformgame.ai;

import com.ahuotala.platformgame.entity.Monster;
import com.ahuotala.platformgame.entity.Player;

/**
 * Tekoäly hirviöille.
 *
 * @author ahuotala
 */
public class MonsterAi {

    private boolean canMove = false;

    private Monster m;

    //-1 = vasen, 1 = oikea, 0 = pysy paikallaan
    private int move = 0;

    /**
     * Konstruktori.
     *
     * @param m Hirviö
     */
    public MonsterAi(Monster m) {
        this.m = m;
    }

    /**
     * Palauttaa seuraavaksi tehtävän liikkeen.
     *
     * @return Seuraavaksi tehtävä liike.
     */
    public int nextMove() {
        return move;
    }

    /**
     * Havaitse pelaajan sijainti ja liikuta hirviötä sen mukaan.
     *
     * @param p Pelaaja
     */
    public void detectPlayer(Player p) {
        //Alue missä hirviö alkaa tunnistamaan pelaajan läsnäolon
        canMove = Math.abs(m.getX() - p.getX() - Player.offsetX) < 500;

        if (!canMove) {
            move = 0;
            return;
        }

        //Jos erotus on pienempää kuin nolla, niin tiedetään, että pelaaja on vasemmalla puolella
        if (m.getX() - p.getX() - Player.offsetX < 0) {
            move = 1;
        } else {
            move = -1;
        }
    }

}
