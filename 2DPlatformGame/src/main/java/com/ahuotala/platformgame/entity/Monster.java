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
import com.ahuotala.platformgame.level.GameLevel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * Monsteri-luokka.
 *
 * @author ahuotala
 */
public class Monster extends Entity {

    private boolean jumping = false;
    private boolean falling = true;

    /**
     * Konstruktori.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Monster(int x, int y) {
        //Työn alla
        super(x, y);
        super.setWidth(32);
        super.setHeight(20);
        //y-suunnassa tiputaan kolme yksikköä
        super.setYMovement(3 * Game.SCALE);
        super.setDy(super.getYMovement());
        //x-suunnassa napin painallus liikuttaa pelaajaa 4 yksikköä
        super.setXMovement(2);
    }

    /**
     * Konstruktori.
     */
    public Monster() {
        this(0, 0);
    }

    /**
     * Liiku vasemmalle.
     */
    public void goLeft() {
        setDx(-getXMovement());
    }

    /**
     * Liiku oikealle.
     */
    public void goRight() {
        setDx(getXMovement());
    }

    /**
     * Hyppää.
     */
    public void jump() {
        if (!falling) {
            jumping = true;
            setDy(super.getYMovement());
        }
    }

    @Override
    public void render(Graphics g) {
        //Työn alla
        g.setColor(Color.blue);
        g.fillRect(x - Player.offsetX, getY(), getWidth(), getHeight());
        g.setColor(Color.red);
        drawBounds(g);
    }

    @Override
    public void tick() {
        updateBounds();
        //Työn alla
        if (jumping && !falling) {
            setY(getY() - 64 * Game.SCALE);
            jumping = false;
            falling = true;
        }
        goRight();
    }

    /**
     * move -metodi hoitaa monsterin liikuttamisen. (Tämä toiminnallisuus on
     * kutakuinkin sama kuin Player -luokassa.)
     *
     * @param tiles Lista pelin tiileistä, joiden avulla tarkistetaan törmäys.
     */
    public void move(List<Entity> tiles) {
        //Loopataan entiteetit ensin Y-suunnassa ja sitten X-suunnassa.
        setY(getY() + getDy());
        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                falling = false;
                setY(getY() - getDy());
                break;
            }
        }

        setX(getX() + getDx());

        //Estetään monsterin liikkuminen kartan rajojen yli
        if (getX() - Game.STARTINGOFFSET + Player.offsetX < 0 || getX() - Game.STARTINGOFFSET > GameLevel.levelWidth - Game.STARTINGOFFSET - getWidth()) {
            setX(getX() - getDx());

        }

        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                jump();
                setX(getX() - getDx());
                break;
            }
        }
        updateBounds();
    }

    /**
     * Päivitä rajat.
     */
    public void updateBounds() {
        super.getBounds().setLocation(x - Player.offsetX, getY());
    }

    @Override
    public void drawBounds(Graphics g) {
        g.setColor(Color.red);
        g.draw3DRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight(), true);
//        g.draw3DRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight(), true);

    }

    @Override
    public void setX(int x) {
        this.x = x;
        updateBounds();
    }

    @Override
    public void setY(int y) {
        this.y = y;
        updateBounds();
    }

}
