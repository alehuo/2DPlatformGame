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

import com.ahuotala.platformgame.Game;
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.graphics.Sprite;
import java.awt.Graphics;

/**
 *
 * @author ahuotala
 */
public class Tile extends Entity {

    private final int widthHeight = 32;

    private String textureName;

    private Sprite spr;

    public Tile(int x, int y, String textureName) {
        super(x, y);
        super.setWidth(widthHeight);
        super.setHeight(widthHeight);
        this.textureName = textureName;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    /**
     * Piirrä tiili
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (spr == null) {
            spr = Game.spr.getSprite(textureName);
        }

        //Piirrä tekstuuri
        g.drawImage(spr.getImage(), x, y, widthHeight, widthHeight, null);

        //Piirrä rajat debuggausta varten
        drawBounds(g);
    }

    @Override
    public void tick() {

    }

}
