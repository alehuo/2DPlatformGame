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
package com.ahuotala.platformgame.entity.movement;

import com.ahuotala.platformgame.Game;
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.entity.Monster;
import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.level.GameLevel;
import java.util.List;

/**
 * Hirviön liikuttamiseen käytetty luokka.
 *
 * @author ahuotala
 */
public class MonsterMover {

    /**
     * moveMonster-metodi hoitaa hirviön liikuttamisen.
     *
     * @param monster Hirviö
     * @param tiles Tiilet
     */
    public void moveMonster(Monster monster, List<Entity> tiles) {
        //Loopataan entiteetit ensin Y-suunnassa ja sitten X-suunnassa.
        monster.setY(monster.getY() + monster.getDy());
        for (Entity tile : tiles) {
            if (tile.collides(monster)) {
                monster.setFalling(false);
                monster.setY(monster.getY() - monster.getDy());
                break;
            }
        }

        monster.setX(monster.getX() + monster.getDx());

        //Estetään monsterin liikkuminen kartan rajojen yli
        if (monster.getX() - Game.startingOffset + Player.offsetX < 0 || monster.getX() - Game.startingOffset > GameLevel.levelWidth - Game.startingOffset - monster.getWidth()) {
            monster.setX(monster.getX() - monster.getDx());
        }

        for (Entity tile : tiles) {
            if (tile.collides(monster)) {
                monster.jump();
                monster.setX(monster.getX() - monster.getDx());
                break;
            }
        }
    }
}
