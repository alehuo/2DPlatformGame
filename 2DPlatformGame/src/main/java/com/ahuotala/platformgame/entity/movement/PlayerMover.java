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
import static com.ahuotala.platformgame.entity.Player.offsetX;
import com.ahuotala.platformgame.level.GameLevel;
import java.util.List;

/**
 * Pelaajan liikuttamiseen käytetty luokka.
 *
 * @author ahuotala
 */
public class PlayerMover {

    /**
     * movePlayer-metodi hoitaa pelaajan liikuttamisen.
     *
     * @param player Pelaaja
     * @param tiles Tiilet
     * @param monsters Hirviöt
     */
    public void movePlayer(Player player, List<Entity> tiles, List<Entity> monsters) {
        boolean onGround = false;
        //Loopataan entiteetit ensin Y-suunnassa ja sitten X-suunnassa.
        player.setY(player.getY() + player.getDy());
        for (Entity tile : tiles) {
            if (tile.collides(player)) {
                onGround = true;
                player.setFalling(false);
                player.setY(player.getY() - player.getDy());
                break;
            }
        }

        //Jos pelaaja hyppää hirviön päälle, niin tuhoa hirviö
        for (Entity entity : monsters) {
            if (entity.collides(player) && !onGround && entity instanceof Monster) {
                //Merkkaa hirviö tapetuksi
                ((Monster) entity).kill();
                player.setFalling(false);
                player.setY(player.getY() - player.getDy());
                break;
            }
        }
        offsetX += player.getDx();

        //Estetään pelaajan liikkuminen kartan rajojen yli
        if (player.getX() - Game.startingOffset + offsetX < 0 || player.getX() - Game.startingOffset + offsetX > GameLevel.levelWidth - Game.startingOffset - player.getWidth()) {
            offsetX -= player.getDx();
        }

        for (Entity tile : tiles) {
            if (tile.collides(player)) {
                offsetX -= player.getDx();
                break;
            }
        }
    }
}
