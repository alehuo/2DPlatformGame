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
import com.ahuotala.platformgame.utils.Tickable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

/**
 * Entiteetti -luokka, jonka perivät kaikki ne luokat, joiden halutaan olevan
 * pelin sisällä.
 *
 * @author ahuotala
 */
public abstract class Entity implements Tickable {

    protected int x = 0;
    protected int y = 0;

    protected int dx = 0;
    protected int dy = 0;

    protected int xMovement = 1;
    protected int yMovement = 1;

    protected int width = 32;
    protected int height = 32;

    protected Rectangle bounds;

    protected boolean visible = true;

    /**
     * Entiteetin konstruktori
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x, y, width * Game.SCALE, height * Game.SCALE);
    }

    public int getX() {
        return x;
    }

    /**
     * Asettaa x-koordinaatin ja päivittää samalla rajat
     *
     * @param x-koordinaatti
     */
    public void setX(int x) {
        this.x = x;
        bounds.setLocation(x, y);
    }

    public int getY() {
        return y;
    }

    /**
     * Asettaa y-koordinaatin ja päivittää samalla rajat
     *
     * @param y-koordinaatti
     */
    public void setY(int y) {
        this.y = y;
        bounds.setLocation(x, y);
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

    /**
     * Asettaa entiteetin leveyden ja päivittää samalla rajat
     *
     * @param width Entiteetin leveys
     */
    public void setWidth(int width) {
        this.width = width * Game.SCALE;
        bounds.setSize(width * Game.SCALE, height);
    }

    public int getHeight() {
        return height;
    }

    /**
     * Asettaa entiteetin korkeuden ja päivittää samalla rajat
     *
     * @param height Entiteetin korkeus
     */
    public void setHeight(int height) {
        this.height = height * Game.SCALE;
        bounds.setSize(width, height * Game.SCALE);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Palauttaa törmäävätkö entiteetit.
     *
     * @param e Entiteetti
     * @return
     */
    public boolean collides(Entity e) {
        return visible && e.getBounds().intersects(getBounds());
    }

    public boolean collides(List<Entity> ents) {
        return visible && ents.stream().anyMatch((ent) -> (collides(ent)));
    }

    /**
     * Piirtää entiteetin rajat näytölle
     *
     * @param g Graphics -objekti
     */
    public void drawBounds(Graphics g) {
        g.setColor(Color.red);
        g.draw3DRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight(), true);
    }

    /**
     * Piirtää entiteetin näytölle (Tämä metodi toteutetaan tarpeen mukaan).
     *
     * @param g Graphics -objekti
     */
    public abstract void render(Graphics g);

}
