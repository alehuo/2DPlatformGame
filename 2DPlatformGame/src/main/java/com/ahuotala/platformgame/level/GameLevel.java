package com.ahuotala.platformgame.level;

import com.ahuotala.platformgame.Game;
import com.ahuotala.platformgame.entity.Cloud;
import com.ahuotala.platformgame.entity.Coin;
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
                    int x = Game.STARTINGOFFSET;
                    while ((line = reader.readLine()) != null) {
                        int y = (Game.WINDOWHEIGHT - 62);
                        //Jos rivi alkaa hashtagilla tai on tyhjä
                        if (line.startsWith("#") || line.isEmpty()) {
                            continue;
                        }

                        //Parsi tasot
                        String[] lineData = line.split(",", -1);
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
                                entities.add(obj);
//                                    LOG.log(Level.INFO, "Ladattu entiteetti ''{0}'' muistiin sijainnissa ({1},{2})", new Object[]{className, x, y});

                            } else {
                                Tile t = new Tile(x, y, textureName);
                                tiles.add(t);
//                                LOG.log(Level.INFO, "Ladattu tiili ''{0}'' muistiin sijainnissa ({1},{2})", new Object[]{textureName, x, y});
                            }
                            y -= 32;
                        }

                        x += 32;
                    }
                    levelWidth = x;
                    stream.close();
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException | NumberFormatException e) {
//            LOG.log(Level.SEVERE, null, e);
//            System.exit(0);
        }

        score = new Score();
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

    public void tick() {
        //Päivitä tiilet
        getTiles().stream().forEach((tile) -> {
            tile.tick();
        });
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

    public Score getScore() {
        return score;
    }

}
