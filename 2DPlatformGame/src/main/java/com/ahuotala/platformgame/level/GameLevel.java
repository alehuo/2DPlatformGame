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

import com.ahuotala.platformgame.Game;
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.entity.Tile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tasoluokka.
 *
 * Taso sisältää blokit, entiteetit sekä pelaajan.
 *
 * @author ahuotala
 */
public class GameLevel {

    private static final Logger LOG = Logger.getLogger(GameLevel.class.getName());

    /**
     * Tason entiteetit
     */
    private List<Entity> entities;

    /**
     * Pelaaja
     */
    private Player player;

    /**
     * Tason tiilit
     */
    private List<Entity> tiles;

    public GameLevel() {
        entities = new ArrayList();
        tiles = new ArrayList();

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try {

            //Erotellaan kartasta rivit
            String line = "";
            InputStream stream = cl.getResourceAsStream("maps/map1.cfg");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                if (stream != null) {
                    int x = 0;
                    while ((line = reader.readLine()) != null) {
                        int y = Game.windowHeight - 62;
                        //Jos rivi alkaa hashtagilla tai on tyhjä
                        if (line.startsWith("#") || line.isEmpty()) {
                            continue;
                        }

                        //Parsi tasot
                        String[] lineData = line.split(",", -1);
                        for (String textureName : lineData) {
                            Tile t = new Tile(x, y, textureName);
                            tiles.add(t);
                            LOG.log(Level.INFO, "Ladattu tiili ''{0}'' muistiin sijainnissa ({1},{2})", new Object[]{textureName, x, y});
                            y -= 32;
                        }

                        x += 32;
                    }
                    stream.close();
                }
            }
        } catch (IOException | NumberFormatException e) {
            LOG.log(Level.SEVERE, null, e);
            System.exit(0);
        }
    }

    public void setEntitites(List<Entity> entities) {
        this.entities = new ArrayList(entities);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getTiles() {
        return tiles;
    }

    public void setTiles(List<Entity> tiles) {
        this.tiles = new ArrayList(tiles);
    }

    public void tick() {
        //Päivitä tiilet
        getTiles().stream().forEach((tile) -> {
            tile.tick();
        });
        //Päivitä entiteetit
        getEntities().stream().forEach((entity) -> {
            entity.tick();
        });

        //Päivitä pelaaja
        player.move(getTiles());
        player.tick();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
