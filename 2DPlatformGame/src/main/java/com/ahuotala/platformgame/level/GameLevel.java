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
import com.ahuotala.platformgame.utils.FileReader;
import com.ahuotala.platformgame.utils.StopWatch;
import java.io.IOException;
import java.io.InputStream;
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

    public static int levelWidth;

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

    /**
     * Pisteytys
     */
    private Score score;

    /**
     * Sekuntikello
     */
    private StopWatch stopWatch;

    /**
     * Tasoluokka. Ladataan ensin tiilet ja sen jälkeen entiteetit muistiin.
     */
    public GameLevel() {
        entities = new ArrayList();
        tiles = new ArrayList();

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        InputStream stream = cl.getResourceAsStream("maps/map1.cfg");
        try {

            //Avataan FileReader, joka lataa rivit muistiin
            FileReader fr = new FileReader(stream);

            //x-koordinaatti
            int x = Game.STARTINGOFFSET;

            for (String line : fr.getLines()) {

                //y-koordinaatti
                int y = (Game.WINDOWHEIGHT - 62);

                //Parsi tasot
                String[] lineData = line.split(",", -1);

                //Käy tasot läpi yksitellen
                for (String textureName : lineData) {
                    //Jos tekstuurin nimi alkaa "entity_" -merkkijonolla, niin ladataan se entiteetteihin
                    if (textureName.startsWith("entity_")) {
                        String className = textureName.replaceAll("entity_", "");
                        //Parsitaan luokan nimi
                        className = className.substring(0, 1).toUpperCase() + className.substring(1).toLowerCase();
                        //Ladataan luokka
                        Class cls = Class.forName("com.ahuotala.platformgame.entity." + className);
                        Entity obj = (Entity) cls.newInstance();
                        obj.setX(x);
                        obj.setY(y);
                        //Lisätään se entities -listaan
                        entities.add(obj);
                    } else {
                        //Luodaan uusi tiili
                        Tile t = new Tile(x, y, textureName);
                        //Lisätään se tiles -listaan
                        tiles.add(t);
                    }
                    //Kasvata y:tä
                    y -= 32;
                }
                //Kasvata x:ää
                x += 32;
                levelWidth = x;
            }

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        stopWatch = new StopWatch();
        score = new Score(stopWatch);
        score.start();
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

    /**
     * tick() -metodi päivittää tason tiilet, entiteetit ja pelaajan.
     */
    public void tick() {
        //Päivitä sekuntikello
        if (stopWatch != null) {
            stopWatch.tick();
        }
        //Päivitä tiilet
        getTiles().stream().forEach((tile) -> {
            tile.tick();
        });
        //Päivitä pelaaja
        player.move(getTiles());
        player.tick();
        //Päivitä entiteetit
        getEntities().stream().forEach((entity) -> {
            entity.tick();

            //Jos entiteetti osuu pelaajaan
            if (entity.collides(player)) {
                //Kerää kolikko
                score.collectCoin();
                entity.setVisible(false);
            }

        });

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }

}
