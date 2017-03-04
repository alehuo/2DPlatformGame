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
import com.ahuotala.platformgame.entity.movement.MonsterMover;
import com.ahuotala.platformgame.graphics.Sprite;
import com.ahuotala.platformgame.graphics.SpriteLoader;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;

/**
 * Monsteri-luokka.
 *
 * @author ahuotala
 */
public class Monster extends Entity {

    private final MonsterAi ai = new MonsterAi(this);

    private boolean jumping = false;
    private boolean falling = true;
    private boolean killed = false;
    private boolean inGame = true;

    private WalkingDirection walkingDirection = WalkingDirection.LEFT;

    private final HashMap<WalkingDirection, Sprite> sprites = new HashMap();

    private final MonsterMover monsterMover = new MonsterMover();

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
        super.setYMovement(3 * Game.scale);
        super.setDy(super.getYMovement());
        //x-suunnassa napin painallus liikuttaa hirviötä 2 yksikköä
        super.setXMovement(2 * Game.scale);
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
        g.drawImage(sprites.get(walkingDirection).getImage(), getX() - Player.offsetX, getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        updateBounds();
        if (killed) {
            if (getY() < Game.WINDOWHEIGHT * 1.2) {
                setY(getY() + 8);
            }
        } else {
            if (jumping && !falling) {
                setY(getY() - 64 * Game.scale);
                jumping = false;
                falling = true;
            }
            ai.tick();
        }

    }

    /**
     * Tappaa hirviön.
     */
    public void kill() {
        killed = true;
    }

    public boolean isKilled() {
        return killed;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * move -metodi hoitaa monsterin liikuttamisen. (Tämä toiminnallisuus on
     * kutakuinkin sama kuin Player -luokassa.)
     *
     * @param tiles Lista pelin tiileistä, joiden avulla tarkistetaan törmäys.
     */
    public void move(List<Entity> tiles) {
        monsterMover.moveMonster(this, tiles);
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

    public boolean isJumping() {
        return jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setWalkingDirection(WalkingDirection walkingDirection) {
        this.walkingDirection = walkingDirection;
    }

}
