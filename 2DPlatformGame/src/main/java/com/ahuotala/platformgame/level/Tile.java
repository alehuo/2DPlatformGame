/*
 * Copyright (C) 2017 Aleksi Huotala
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
 * @author Aleksi Huotala
 */
public class Tile extends Entity {

    private final int widthHeight = 32;

    private String textureName;

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
        Sprite spr = Game.spr.getSprite(textureName);
        if (spr != null) {
            //Päivitä palikan rajat kun ruutua liikutetaan
//            bounds.setLocation(x, y);
            //Piirrä tekstuuri
            g.drawImage(spr.getImage(), x, y, widthHeight, widthHeight, null);
//            g.setColor(Color.red);
//            g.drawRect(x, y, widthHeight, widthHeight);
            drawBounds(g);
        }
    }

    @Override
    public void tick() {

    }

}
