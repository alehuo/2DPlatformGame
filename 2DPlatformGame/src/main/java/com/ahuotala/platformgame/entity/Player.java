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
import com.ahuotala.platformgame.graphics.SpriteLoader;
import com.ahuotala.platformgame.level.GameLevel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Pelaaja -luokka
 *
 * @author ahuotala
 */
public class Player extends Entity implements KeyListener {

    public static int offsetX;

    private boolean jumping = false;
    private boolean falling = true;

    private WalkingDirection wd;

    private Sprite left;
    private Sprite right;

    /**
     * Konstruktori
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Player(int x, int y) {
        super(x, y);
        //Pelaaja on 24x32 kokoinen (leveys x korkeus)
        super.setWidth(24);
        super.setHeight(32);

        //y-suunnassa tiputaan kolme yksikköä
        super.setyMovement(3 * Game.SCALE);
        super.setDy(super.getyMovement());
        //x-suunnassa napin painallus liikuttaa pelaajaa 4 yksikköä
        super.setxMovement(4 * Game.SCALE);

        wd = WalkingDirection.RIGHT;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Näppäimen painallus: tämän metodin avulla liikutetaan pelaajaa.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //Vasen
        if (e.getKeyCode() == KeyEvent.VK_A) {
            wd = WalkingDirection.LEFT;
            setDx(-getxMovement());
        }

        //Oikea
        if (e.getKeyCode() == KeyEvent.VK_D) {
            wd = WalkingDirection.RIGHT;
            setDx(getxMovement());
        }

        //Hyppy
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    /**
     * Napin päästäminen pohjasta.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        //Vasen
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setDx(0);
        }

        //Oikea
        if (e.getKeyCode() == KeyEvent.VK_D) {
            setDx(0);
        }
    }

    /**
     * Hyppää.
     */
    public void jump() {
        if (!falling) {
            jumping = true;
            setDy(super.getyMovement());
        }
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public WalkingDirection getWd() {
        return wd;
    }

    public void setWd(WalkingDirection wd) {
        this.wd = wd;
    }

    /**
     * Piirrä pelaaja näytölle.
     *
     * @param g Graphics -objekti
     */
    @Override
    public void render(Graphics g) {
        if (left == null) {
            left = SpriteLoader.getSprite("plr_left");
        }
        if (right == null) {
            right = SpriteLoader.getSprite("plr_right");
        }
        g.setColor(Color.CYAN);
        if (wd == WalkingDirection.LEFT) {
            g.drawImage(left.getImage(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(right.getImage(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    /**
     * Pelaajan tick -toiminnallisuus on vastuussa pelaajan y-suuntaisesta
     * hyppimisestä.
     */
    @Override
    public void tick() {
        if (jumping && !falling) {
            setY(getY() - 64 * Game.SCALE);
            jumping = false;
            falling = true;
        }
    }

    /**
     * move -metodi hoitaa pelaajan liikuttamisen.
     *
     * @param tiles Lista pelin tiileistä, joiden avulla tarkistetaan törmäys.
     */
    public void move(List<Entity> tiles) {
        //Y-suunta (putoaminen)
        setY(getY() + getDy());
        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                falling = false;
                setY(getY() - getDy());
                break;
            }
        }

        offsetX = offsetX + getDx();

        //Estetään pelaajan liikkuminen kartan rajojen yli
        if (getX() - Game.STARTINGOFFSET + offsetX < 0 || getX() - Game.STARTINGOFFSET + offsetX > GameLevel.levelWidth - Game.STARTINGOFFSET - getWidth()) {
            offsetX = offsetX - getDx();
        }

        for (Entity tile : tiles) {
            //Päivitä tiilen sijainti (Tämä kuitenkin rikkoo Single responsibility -periaatetta)
            ((Tile) tile).updateBounds();
            if (tile.collides(this)) {
                offsetX = offsetX - getDx();
                break;
            }
        }
    }

}
