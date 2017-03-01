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
package com.ahuotala.platformgame.level;

import com.ahuotala.platformgame.entity.Coin;
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.entity.Monster;
import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.utils.StopWatch;
import java.util.List;

/**
 * GameUpdater-luokka hoitaa pelin tiilten, entiteettien, sekuntikellon,
 * pelaajan ja pisteytyksen päivityksen.
 *
 * @author ahuotala
 */
public class GameUpdater {

    /**
     * Päivittää pelin tiilet, entiteetit, sekuntikellon, pelaajan ja
     * pisteytyksen.
     *
     * @param tiles Tiilet
     * @param entities Entiteetit
     * @param stopWatch Sekuntikello
     * @param player Pelaaja
     * @param score Pisteytys
     */
    public void updateAll(List<Entity> tiles, List<Entity> entities, StopWatch stopWatch, Player player, Score score) {
        //Päivitä sekuntikello
        if (stopWatch != null) {
            stopWatch.tick();
        }
        //Päivitä tiilet
        tiles.stream().forEach((tile) -> {
            tile.tick();
        });
        //Päivitä pelaaja
        player.tick();
        player.move(tiles, entities, score);

        //Päivitä entiteetit
        entities.stream().forEach((Entity entity) -> {
            entity.tick();
            //Jos entiteetti on monsteri, liikuta sitä
            if (entity instanceof Monster) {
                ((Monster) entity).move(tiles);
                //Kytketään hirviön tekoäly kiinni pelaajaan.
                ((Monster) entity).getAi().process(player);
            } else if (entity instanceof Coin) {
                //Jos kolikko osuu pelaajaan
                if (entity.collides(player)) {
                    //Kerää kolikko
                    score.collectCoin();
                    //Aseta kolikko läpinäkymättömäksi
                    entity.setVisible(false);
                }
            }

        });
    }
}
