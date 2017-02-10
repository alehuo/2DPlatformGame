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

import com.ahuotala.platformgame.Game;
import com.ahuotala.platformgame.graphics.Sprite;
import java.awt.Graphics;

/**
 * Pilvi -entiteetti
 *
 * @author ahuotala
 */
public class Cloud extends Entity {

    private Sprite sprite;

    /**
     * Konstruktori Pilvi-entiteetille
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Cloud(int x, int y) {
        super(x, y);
        //Kolikko on 24x24 kokoinen (leveys x korkeus)
        super.setWidth(64);
        super.setHeight(32);

        //Ladataan sprite
        sprite = Game.spr.getSprite("cloud");
    }

    /**
     * Konstruktori Pilvi-entiteetille, jossa pilvi asetetaan koordinaattiin
     * (0,0).
     */
    public Cloud() {
        super(0, 0);
        //Kolikko on 24x24 kokoinen (leveys x korkeus)
        super.setWidth(64);
        super.setHeight(32);

        //Ladataan sprite
        sprite = Game.spr.getSprite("cloud");
    }

    /**
     * Piirtää pilven ruudulle
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (sprite != null && isVisible()) {
            g.drawImage(sprite.getImage(), getX() - Player.offsetX, getY(), getWidth() * 2, getHeight() * 2, null);
            drawBounds(g);
        }
    }

    /**
     * Päivittää entiteetin (kun pelaaja liikkuu, on päivitettävä entiteetin
     * rajat).
     */
    @Override
    public void tick() {
        getBounds().setBounds(getX() - Player.offsetX, getY(), getWidth() * 2, getHeight() * 2);
    }

}
