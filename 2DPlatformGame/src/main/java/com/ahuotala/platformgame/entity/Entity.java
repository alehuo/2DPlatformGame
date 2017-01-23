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

import java.awt.Graphics;

/**
 *
 * @author ahuotala
 */
public abstract class Entity {

    protected int x = 0;
    protected int y = 0;

    protected int dx = 0;
    protected int dy = 0;

    protected int xMovement = 1;
    protected int yMovement = 1;

    protected int width;
    protected int height;

    public Entity() {
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getxMovement() {
        return xMovement;
    }

    public void setxMovement(int xMovement) {
        this.xMovement = xMovement;
    }

    public int getyMovement() {
        return yMovement;
    }

    public void setyMovement(int yMovement) {
        this.yMovement = yMovement;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void move() {
        x += getDx();
        y += getDy();
    }

    /**
     * Entiteetin piirto
     *
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     * Entiteetin päivitys
     */
    public abstract void tick();

}
