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
import com.ahuotala.platformgame.ai.MonsterAi;
import com.ahuotala.platformgame.graphics.Sprite;
import com.ahuotala.platformgame.graphics.SpriteLoader;
import com.ahuotala.platformgame.level.GameLevel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;

/**
 * Monsteri-luokka.
 *
 * @author ahuotala
 */
public class Monster extends Entity {

    private boolean jumping = false;
    private boolean falling = true;

    private WalkingDirection wd = WalkingDirection.LEFT;
    private HashMap<WalkingDirection, Sprite> sprites;

    private MonsterAi ai;

    /**
     * Konstruktori.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Monster(int x, int y) {
        super(x, y);
        super.setWidth(24);
        super.setHeight(12);
        //y-suunnassa tiputaan kolme yksikköä
        super.setYMovement(3 * Game.SCALE);
        super.setDy(super.getYMovement());
        //x-suunnassa napin painallus liikuttaa hirviötä 3 yksikköä
        super.setXMovement(3);
        //Syötetään Monster-olio tekoälylle.
        ai = new MonsterAi(this);
        sprites = new HashMap();
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
        if (sprites.get(WalkingDirection.LEFT) == null) {
            sprites.put(WalkingDirection.LEFT, SpriteLoader.getSprite("monster_left"));
        }
        if (sprites.get(WalkingDirection.RIGHT) == null) {
            sprites.put(WalkingDirection.RIGHT, SpriteLoader.getSprite("monster_right"));
        }
        g.drawImage(sprites.get(wd).getImage(), getX() - Player.offsetX, getY(), getWidth(), getHeight(), null);
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
        //Tekoäly
        switch (ai.nextMove()) {
            case -1:
                wd = WalkingDirection.LEFT;
                goLeft();
                break;
            case 1:
                wd = WalkingDirection.RIGHT;
                goRight();
                break;
            case 0:
                setDx(0);
                break;
        }
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
    public void setX(int x) {
        this.x = x;
        updateBounds();
    }

    @Override
    public void setY(int y) {
        this.y = y;
        updateBounds();
    }

    public MonsterAi getAi() {
        return ai;
    }
}
