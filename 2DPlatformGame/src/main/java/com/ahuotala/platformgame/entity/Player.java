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
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
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
    private final int gravity = 1;
    private final int terminalVelocity = 5;
    private int verticalSpeed = 0;

    public Player(int x, int y) {
        super(x, y);
        //Pelaaja on 24x32 kokoinen (leveys x korkeus)
        super.setWidth(24);
        super.setHeight(32);
        super.setyMovement(0);
        super.setxMovement(4);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Näppäimen painallus. Tämän metodin avulla liikutetaan pelaajaa
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

        //Ylös
//        if (e.getKeyCode() == KeyEvent.VK_W) {
//            setDy(-getyMovement());
//        }
        //Alas
//        if (e.getKeyCode() == KeyEvent.VK_S) {
//            setDy(getyMovement());
//        }
        //Vasen
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setDx(-getxMovement());
        }

        //Oikea
        if (e.getKeyCode() == KeyEvent.VK_D) {
            setDx(getxMovement());
            Point2D p2d = new Point(x + getWidth() + 5, y + getHeight() / 2);
        }

        //Hyppy
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
//        LOG.log(Level.INFO, "dx: {0}, dy: {1}", new Object[]{getDx(), getDy()});
    }

    /**
     * Napin päästäminen pohjasta
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        //Ylös
        if (e.getKeyCode() == KeyEvent.VK_W) {
            setDy(0);
        }

        //Vasen
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setDx(0);
        }

        //Alas
        if (e.getKeyCode() == KeyEvent.VK_S) {
            setDy(0);
        }

        //Oikea
        if (e.getKeyCode() == KeyEvent.VK_D) {
            setDx(0);
        }
    }

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

    /**
     * Piirrä pelaaja näytölle
     *
     * @param g Graphics -objekti
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fill3DRect(getX(), getY(), getWidth(), getHeight(), true);
        drawBounds(g);
    }

    @Override
    public void tick() {
        move();
        if (jumping && !falling) {
            setY(getY() - 63);
            jumping = false;
            falling = true;
        }
        if (falling) {
            setY(getY() + verticalSpeed);
            if (verticalSpeed + gravity > terminalVelocity) {
                verticalSpeed = terminalVelocity;
            } else {
                verticalSpeed += gravity;
            }
        } else {
            verticalSpeed = 0;
        }
    }
}
