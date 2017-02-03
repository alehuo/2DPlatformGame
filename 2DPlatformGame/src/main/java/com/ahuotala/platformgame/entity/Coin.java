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
import java.util.Random;

/**
 * Kolikko.
 *
 * @author ahuotala
 */
public class Coin extends Entity {

    private int yModifier = 0;
    private int index = 0;
    private boolean visible = true;

    public Coin(int x, int y) {
        super(x, y);
        //Kolikko on 24x24 kokoinen (leveys x korkeus)
        super.setWidth(24);
        super.setHeight(24);
    }

    public Coin() {
        super(0, 0);
        super.setWidth(24);
        super.setHeight(24);
    }

    @Override
    public void render(Graphics g) {
        if (isVisible()) {
            g.setColor(Color.YELLOW);
            g.fillOval(getX() - Player.offsetX, getY() + yModifier, getWidth(), getHeight());
//            drawBounds(g);
        }
    }

    @Override
    public void tick() {
        Random r = new Random();
        yModifier = (int) Math.ceil(3 * Math.sin(Math.toRadians(index)));
        if (index == 360) {
            index = 0;
        } else if (index % (r.nextInt(5) + 1) == 0) {
            index += 5;
        }
        getBounds().setBounds(getX() - Player.offsetX, getY() + yModifier, getWidth(), getHeight());
    }

}
