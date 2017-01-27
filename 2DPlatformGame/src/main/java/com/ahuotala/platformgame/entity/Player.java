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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.logging.Logger;

/**
 * Pelaaja -luokka
 *
 * @author ahuotala
 */
public class Player extends Entity implements KeyListener {

    private static final Logger LOG = Logger.getLogger(Player.class.getName());

    private boolean jumping = false;
    private boolean falling = true;

    //Painovoiman simuloimista varten
//    private int fallTicks = 0;
//    private int factor = 4;
    private WalkingDirection wd;

    public Player(int x, int y) {
        super(x, y);
        //Pelaaja on 24x32 kokoinen (leveys x korkeus)
        super.setWidth(24);
        super.setHeight(32);

        //Halutaan että y-suunnassa tiputaan yksi yksikkö ja x-suunnassa napin 
        //painallus liikuttaa pelaajaa 2 yksikköä
        super.setyMovement(1);
        super.setDy(super.getyMovement());

        super.setxMovement(2);

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
        g.setColor(Color.CYAN);
        g.fill3DRect(getX(), getY(), getWidth(), getHeight(), true);
        g.setColor(Color.GREEN);
        drawBounds(g);
    }

    /**
     * Pelaajan tick -toiminnallisuus on vastuussa pelaajan liikkumisesta sekä
     * hyppimisestä.
     */
    @Override
    public void tick() {
        if (jumping && !falling) {
            setY(getY() - 64);
            jumping = false;
            falling = true;
        }
    }

    public void move(List<Entity> tiles) {
        bounds.setLocation(getX(), getY());

        //Y-suunta (putoaminen)
        setY(getY() + getDy());
        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                falling = false;
                setY(getY() - getDy());
            }
        }
        //X-suunta (siirtyminen)
        setX(getX() + getDx());
        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                falling = false;
                setX(getX() - getDx());
            }
        }
    }

}
