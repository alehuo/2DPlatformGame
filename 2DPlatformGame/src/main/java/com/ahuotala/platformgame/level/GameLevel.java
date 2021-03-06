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
import com.ahuotala.platformgame.entity.Cloud;
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.entity.Tile;
import com.ahuotala.platformgame.utils.FileReader;
import com.ahuotala.platformgame.utils.StopWatch;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Tasoluokka.
 *
 * Taso sisältää blokit, entiteetit sekä pelaajan.
 *
 * @author ahuotala
 */
public class GameLevel {

    //Logger
    private static final Logger LOG = Logger.getLogger(GameLevel.class.getName());

    //Tason leveys
    public static int levelWidth;

    //Tason entiteetit
    private List<Entity> entities;

    //Pelaaja
    private Player player;

    //Tiilet
    private List<Entity> tiles;

    //Pistetytys
    private final Score score;

    //Sekuntikello
    private final StopWatch stopWatch;

    private int finishX;

    private final GameUpdater updater = new GameUpdater();

    /**
     * Tasoluokka. Ladataan ensin tiilet ja sen jälkeen entiteetit muistiin.
     */
    public GameLevel() {
        entities = new ArrayList();
        tiles = new ArrayList();

        generateClouds();

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        InputStream stream = cl.getResourceAsStream("maps/map1.cfg");
        try {

            //Avataan FileReader, joka lataa rivit muistiin
            FileReader fr = new FileReader(stream);

            //x-koordinaatti
            int x = Game.startingOffset;
            int finalY = 0;

            for (String line : fr.getLines()) {

                //y-koordinaatti
                int y = (Game.WINDOWHEIGHT - 62 / Game.scale);

                //Parsi tasot
                String[] lineData = line.split(",", -1);

                //Käy tasot läpi yksitellen
                for (String textureName : lineData) {

                    //Jos tekstuurin nimi alkaa "entity_" -merkkijonolla, niin ladataan se entiteetteihin
                    if (!textureName.isEmpty() && textureName.startsWith("entity_")) {
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
                    } else if (!textureName.isEmpty()) {
                        //Luodaan uusi tiili
                        Tile t = new Tile(x, y, textureName);
                        //Lisätään se tiles -listaan
                        tiles.add(t);
                    }
                    //Kasvata y:tä
                    y -= 32 * Game.scale;
                    finalY = y;
                }
                //Kasvata x:ää
                x += 32 * Game.scale;
                levelWidth = x;
            }

            //Lisätään vielä maaliviiva
            int finishHeight = 2;
            finishX = x - 32 * Game.scale;
            int startY = finalY - finishHeight * 32 * Game.scale + 32 * Game.scale;
            for (int i = 0; i < finishHeight; i++) {
                Tile finishTile = new Tile(finishX, startY + 32 * Game.scale * i, "finish");
                finishTile.setCollisionDetectionEnabled(false);
                tiles.add(finishTile);
            }

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        }
        stopWatch = new StopWatch();
        score = new Score(stopWatch);
        score.start();
    }

    /**
     * Palauttaa tiilet ja entiteetit yhtenä listana. Toiminnallisuutta
     * käytetään GamePanel-luokassa.
     *
     * @return Lista entiteeteistä ja tiilistä
     */
    public List<Entity> getTilesAndEntities() {
        List<Entity> entitiesAndTiles = new ArrayList();
        entitiesAndTiles.addAll(tiles);
        entitiesAndTiles.addAll(entities);
        return entitiesAndTiles;
    }

    /**
     * tick() -metodi päivittää tason tiilet, entiteetit ja pelaajan.
     */
    public void tick() {
        updater.updateAll(tiles, entities, stopWatch, player, score);
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

    /**
     * Palauttaa, onko peli loppunut.
     *
     * @return Pelin loppumistila
     */
    public boolean isGameOver() {
        return player.getX() + Player.offsetX > finishX || player.getHealth() == 0;
    }

    //Luo 3-4 satunnaista pilveä peliin.
    private void generateClouds() {
        Random r = new Random();
        int pilvienLkm = r.nextInt(2) + 3;
        int xOffset = 60;
        if (pilvienLkm < 4) {
            xOffset = 160;
        }
        for (int i = 0; i < pilvienLkm; i++) {
            Cloud c = new Cloud(i * 128 * Game.scale + xOffset, 50 + r.nextInt(80) - r.nextInt(40));
            entities.add(c);
        }
    }
}
