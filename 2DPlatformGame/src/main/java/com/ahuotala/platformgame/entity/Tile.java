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

import com.ahuotala.platformgame.graphics.Sprite;
import com.ahuotala.platformgame.graphics.SpriteLoader;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Tiili -luokka.
 *
 * @author ahuotala
 */
public class Tile extends Entity {

    private final int widthHeight = 32;

    private String textureName;

    private Sprite sprite;

    private boolean collisionDetectionEnabled = true;

    /**
     * Konstruktori.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @param textureName Tekstuurin nimi
     */
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
     * Piirrä tiili.
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (sprite == null) {
            sprite = SpriteLoader.getSprite(textureName);
        }
        //Piirrä tekstuuri
        g.drawImage(sprite.getImage(), x - Player.offsetX, y, getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        updateBounds();
    }

    @Override
    public void drawBounds(Graphics g) {
        g.setColor(Color.red);
        g.draw3DRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight(), true);
    }

    /**
     * Päivitä rajat.
     */
    public void updateBounds() {
        super.getBounds().setLocation(x - Player.offsetX, y);
    }

    /**
     * Palauttaa, törmääkö entiteetti.
     *
     * @param e Entiteetti
     * @return Törmääkö entiteetti
     */
    @Override
    public boolean collides(Entity e) {
        //Päivittää rajat
        updateBounds();
        return isCollisionDetectionEnabled() && super.collides(e);
    }

    public void setCollisionDetectionEnabled(boolean collisionDetectionEnabled) {
        this.collisionDetectionEnabled = collisionDetectionEnabled;
    }

    public boolean isCollisionDetectionEnabled() {
        return collisionDetectionEnabled;
    }

}
