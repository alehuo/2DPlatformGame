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
import com.ahuotala.platformgame.graphics.Sprite;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Tasoluokka.
 *
 * Taso sisältää blokit sekä entiteetit.
 *
 * @author ahuotala
 */
public class GameLevel {

    private List<Entity> entities;

    private List<Tile> tiles;

    private static final Logger LOG = Logger.getLogger(GameLevel.class.getName());

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
                    int y = Game.WINDOW_HEIGHT - 62;
                    while ((line = reader.readLine()) != null) {
                        y = Game.WINDOW_HEIGHT - 62;
                        //Jos rivi alkaa hashtagilla tai on tyhjä
                        if (line.startsWith("#") || line.isEmpty()) {
                            continue;
                        }

                        //Parsi tasot
                        String[] lineData = line.split(",", -1);
                        for (int i = 0; i < lineData.length; i++) {
                            String textureName = lineData[i];
                            Tile t = new Tile();
                            t.setX(x);
                            t.setY(y);
                            t.setTextureName(textureName);
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
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

}
