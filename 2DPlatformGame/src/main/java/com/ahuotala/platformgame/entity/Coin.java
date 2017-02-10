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

import com.ahuotala.platformgame.graphics.Animation;
import com.ahuotala.platformgame.graphics.AnimationLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Kolikko-entiteetin luokka
 *
 * @author ahuotala
 */
public class Coin extends Entity {

    private int yModifier = 0;
    private int index = 0;
    private boolean visible = true;

    //Kolikon animaatio
    private Animation coinAnimation;

    /**
     * Konstruktori
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Coin(int x, int y) {
        super(x, y);
        //Kolikko on 32x32 kokoinen (leveys x korkeus)
        super.setWidth(32);
        super.setHeight(32);
    }

    /**
     * Konstruktori. Asettaa kolikon pisteeseen (0,0)
     */
    public Coin() {
        super(0, 0);
        super.setWidth(32);
        super.setHeight(32);
    }

    /**
     * Piirtää kolikon näytölle
     * @param g Graphics -objekti
     */
    @Override
    public void render(Graphics g) {
        if (isVisible()) {
            if (coinAnimation == null) {
                coinAnimation = AnimationLoader.getAnimation("coin");
            }
            g.setColor(Color.YELLOW);
            g.drawImage(coinAnimation.currentFrame().getImage(), getX() - Player.offsetX, getY() + yModifier, getWidth(), getHeight(), null);
            super.getBounds().setBounds(getX() - Player.offsetX, getY() + yModifier, getWidth(), getHeight());
//            drawBounds(g);
        }
    }

    /**
     * Päivittää kolikon.
     * - Saa kolikon "hyppimään" ruudulla
     * - Päivittää animaatiota
     */
    @Override
    public void tick() {
        if (coinAnimation != null) {
            coinAnimation.tick();
        }
        Random r = new Random();
        yModifier = (int) Math.ceil(3 * Math.sin(Math.toRadians(index)));
        if (index == 360) {
            index = 0;
        } else if (index % (r.nextInt(5) + 1) == 0) {
            index += 5;
        }
    }

}
