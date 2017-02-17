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
package com.ahuotala.platformgame.entity;

import com.ahuotala.platformgame.ai.MonsterAi;
import java.awt.Graphics;

/**
 * Monsteri-luokka.
 *
 * @author ahuotala
 */
public class Monster extends Entity {

    private final MonsterAi ai;

    /**
     * Konstruktori.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Monster(int x, int y) {
        //Työn alla
        super(x, y);
        ai = new MonsterAi();
    }

    @Override
    public void render(Graphics g) {
        //Työn alla
    }

    @Override
    public void tick() {
        //Työn alla
    }

}
