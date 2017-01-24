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

import java.awt.Color;
import java.awt.Graphics;

/**
 * Kolikko
 *
 * @author ahuotala
 */
public class Coin extends Entity {

    private int xModifier = 0;
    private int yModifier = 0;
    private int index = 0;

    public Coin(int x, int y) {
        super(x, y);
        //Kolikko on 24x24 kokoinen (leveys x korkeus)
        super.setWidth(24);
        super.setHeight(24);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(getX(), getY() + yModifier, getWidth(), getHeight());
    }

    @Override
    public void tick() {
        yModifier = (int) Math.ceil(3 * Math.sin(Math.toRadians(index)));

        if (index == 360) {
            index = 0;
        } else {
            index += 5;
        }
    }

}
