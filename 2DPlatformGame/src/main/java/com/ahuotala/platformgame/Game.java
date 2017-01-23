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
package com.ahuotala.platformgame;

import com.ahuotala.platformgame.entity.Cloud;
import com.ahuotala.platformgame.entity.Coin;
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.graphics.SpriteSheet;
import com.ahuotala.platformgame.input.KeyHandler;
import com.ahuotala.platformgame.level.GameLevel;
import com.ahuotala.platformgame.ui.GamePanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Pääluokka
 *
 * @author ahuotala
 */
public class Game implements Runnable {

    /**
     * Kehyksen leveys
     */
    public static int WINDOW_WIDTH = 640;

    /**
     * Kehyksen korkeus
     */
    public static int WINDOW_HEIGHT = 480;

    /**
     * Kehyksen otsikko
     */
    public static String WINDOW_TITLE = "2DPlatformGame";

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    public static SpriteSheet spr = new SpriteSheet();

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
    /**
     * JFrame
     */
    private final JFrame frame;
    /**
     *
     */
    private boolean running;

    /**
     * 60 päivitystä sekunnissa
     */
    private final int tickrate = 60;

    /**
     * Piirtoalusta
     */
    private final GamePanel gamePanel;

    /**
     * Pelaaja
     */
    private final Player player;

    private final List<Entity> entities;

    public Game() {
        //Luo kehys
        frame = new JFrame(WINDOW_TITLE);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        entities = new ArrayList<>();
        //Luo pelaaja
        player = new Player();
        player.setX(200);
        player.setY(200);

        //Luo tasoon entiteetit
        entities.add(new Coin(350, 200));
        entities.add(new Coin(250, 200));
        entities.add(new Cloud(40, 40));
        entities.add(new Cloud(250, 60));

        GameLevel gameLevel = new GameLevel();
        gameLevel.setEntitites(entities);

        //Luo pelipaneeli ja lisää pelaaja sinne sekä taso
        gamePanel = new GamePanel();
        gamePanel.setPlayer(player);
        gamePanel.setLevel(gameLevel);

        frame.setContentPane(gamePanel);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocusInWindow();

        //Lisää pelaajan näppäimistönkuuntelija
        frame.addKeyListener(new KeyHandler(player));

    }

    /**
     * Käynnistä peli
     */
    private synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    /**
     * Pysäytä peli
     */
    private synchronized void stop() {
        running = false;
    }

    /**
     *
     */
    @Override
    public void run() {

        //Starting time
        long lastTime = System.nanoTime();

        //Halutaan päivittää 60 kertaa sekunnissa
        double tickInterval = 1000000000D / tickrate;

        //Luotujen kehysten lukumäärä
        int frames = 0;

        //"tikkien" lukumäärä
        int ticks = 0;

        //Viimeisimmän päivityksen ajankohta
        long lastTimer = System.currentTimeMillis();

        //Väliaika
        double delta = 0;

        /*
        Pelilooppi, mikä on riippumaton ruudunpäivitysnopeudesta.
         */
        while (running) {

            //Aika tällä hetkellä
            long now = System.nanoTime();

            delta += (now - lastTime) / tickInterval;

            lastTime = now;

            //Rajoita ruudunpäivitysnopeus asettamalla tähän "false"
            boolean render = false;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                //Sallitaan pelin päivittää kehys kun ollaan päivitetty pelilogiikka
                render = true;
            }

            //Nuku 1 millisekunnin ajan
            if (render) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }

            //Piirrä kehys
            if (render) {
                gamePanel.repaint();
                frames++;
            }

            int interval = 1000;

            if (System.currentTimeMillis() - lastTimer >= interval) {
                lastTimer += interval;
                frame.setTitle(WINDOW_TITLE + " (" + frames + " fps, " + ticks + " ticks)");
                frames = 0;
                ticks = 0;
            }
        }
    }

    /**
     * Metodi joka suoritetaan 60 kertaa sekunnissa (16,66ms) tick(). Tämä
     * metodi on riippumaton ruudunpäivitysnopeudesta.
     */
    public void tick() {
        //Liikuta pelaajaa
        if (player != null) {
            player.move();
        }
        //Päivitä entiteetit
        entities.stream().forEach((entity) -> {
            entity.tick();
        });
    }

}
