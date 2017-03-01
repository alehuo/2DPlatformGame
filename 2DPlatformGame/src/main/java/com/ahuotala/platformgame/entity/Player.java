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
import com.ahuotala.platformgame.level.Score;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;

/**
 * Pelaaja -luokka.
 *
 * @author ahuotala
 */
public class Player extends Entity implements KeyListener {

    public static int offsetX;

    private boolean jumping = false;
    private boolean falling = true;

    private WalkingDirection wd;

    private final HashMap<WalkingDirection, Sprite> sprites;

    private int health = 1000;

    /**
     * Konstruktori.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Player(int x, int y) {
        super(x, y);

        //Alusta HashMap
        sprites = new HashMap();

        //Pelaaja on 24x32 kokoinen (leveys x korkeus)
        super.setWidth(24);
        super.setHeight(32);

        //y-suunnassa tiputaan kolme yksikköä
        super.setYMovement(3 * Game.scale);
        super.setDy(super.getYMovement());
        //x-suunnassa napin painallus liikuttaa pelaajaa 4 yksikköä
        super.setXMovement(4 * Game.scale);

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
        //Vasen, oikea ja hyppy
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                wd = WalkingDirection.LEFT;
                setDx(-getXMovement());
                break;
            case KeyEvent.VK_D:
                wd = WalkingDirection.RIGHT;
                setDx(getXMovement());
                break;
            case KeyEvent.VK_SPACE:
                jump();
                break;
            default:
                break;
        }
    }

    /**
     * Napin päästäminen pohjasta.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //Vasen, oikea
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setDx(0);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            setDx(0);
        }
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
        if (sprites.get(WalkingDirection.LEFT) == null) {
            sprites.put(WalkingDirection.LEFT, SpriteLoader.getSprite("plr_left"));
        }
        if (sprites.get(WalkingDirection.RIGHT) == null) {
            sprites.put(WalkingDirection.RIGHT, SpriteLoader.getSprite("plr_right"));
        }
        g.drawImage(sprites.get(wd).getImage(), getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Pelaajan tick -toiminnallisuus on vastuussa pelaajan y-suuntaisesta
     * hyppimisestä.
     */
    @Override
    public void tick() {
        if (jumping && !falling) {
            setY(getY() - 64 * Game.scale);
            jumping = false;
            falling = true;
        }
    }

    /**
     * move -metodi hoitaa pelaajan liikuttamisen.
     *
     * @param tiles Lista pelin tiileistä, joiden avulla tarkistetaan törmäys.
     * @param monsters Lista pelin entiteeteistä (näistä poimitaan hirviöt)
     * @param s Pisteytys
     */
    public void move(List<Entity> tiles, List<Entity> monsters, Score s) {
        boolean onGround = false;
        //Loopataan entiteetit ensin Y-suunnassa ja sitten X-suunnassa.
        setY(getY() + getDy());
        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                onGround = true;
                falling = false;
                setY(getY() - getDy());
                break;
            }
        }

        //Hirviön törmäyksentunnistus
        for (Entity monster : monsters) {
            if (monster.collides(this) && !onGround && monster instanceof Monster) {
                ((Monster) monster).kill();
                if (s != null) {
                    s.defeatMonster();
                }
                falling = false;
                setY(getY() - getDy());
                break;
            }
        }
        offsetX += getDx();

        //Estetään pelaajan liikkuminen kartan rajojen yli
        if (getX() - Game.STARTINGOFFSET + offsetX < 0 || getX() - Game.STARTINGOFFSET + offsetX > GameLevel.levelWidth - Game.STARTINGOFFSET - getWidth()) {
            offsetX -= getDx();
        }

        for (Entity tile : tiles) {
            if (tile.collides(this)) {
                offsetX -= getDx();
                break;
            }
        }
    }

    /**
     * Vahingoita pelaajaa.
     *
     * @param amount Vahingon määrä.
     */
    public void damagePlayer(int amount) {
        if (health - amount > 0 && amount > 0) {
            health -= amount;
        } else if (amount > 0) {
            health = 0;
        }
    }

    public int getHealth() {
        return health;
    }

}
