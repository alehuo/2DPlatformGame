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

import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.input.KeyHandler;
import com.ahuotala.platformgame.level.GameLevel;
import com.ahuotala.platformgame.ui.GamePanel;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Pääluokka 2DPlatformGame -pelille.
 *
 * @author ahuotala
 */
public class Game implements Runnable {

    /**
     * Kehyksen leveys.
     */
    public static final int WINDOWWIDTH = 1000;

    /**
     * Kehyksen korkeus.
     */
    public static final int WINDOWHEIGHT = 600;

    /**
     * Pelin skaalaus.
     */
    public static int scale = 2;

    /**
     * Aloitussiirtymä.
     */
    public static final int STARTINGOFFSET = WINDOWWIDTH / 2 - 64;

    /**
     * Main -metodi aloittaa uuden pelin.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }

    //Kehyksen otsikko
    private final String windowTitle = "2DPlatformGame";

    //JFrame
    private final JFrame frame;

    //Päivitystahti
    private final int tickrate = 60;

    //Pelipaneeli
    private final GamePanel gamePanel;

    //Suoritustila
    private boolean running;

    /**
     * Konstruktori, missä luodaan sekä kehys, pelaaja että pelipaneeli.
     */
    public Game() {
        if (scale > 2) {
            scale = 2;
        }
        //Luo kehys
        frame = new JFrame(windowTitle);
        frame.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Pelitaso
        GameLevel gameLevel = new GameLevel();

        //Luo pelaaja
        Player player = new Player(WINDOWWIDTH / 2, WINDOWHEIGHT / 2 + (int) Math.floor(0.1 * WINDOWHEIGHT / 2));
        gameLevel.setPlayer(player);

        //Luo pelipaneeli ja lisää pelitaso sinne
        gamePanel = new GamePanel();
        gamePanel.setLevel(gameLevel);

        //Aseta pelipaneeli contentPaneen
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
     * run() -metodi on ns. "Game loop", eli se suorittaa piirtämisen ja
     * pelilogiikan päivittämisen.
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

        //Pelilooppi, mikä on riippumaton ruudunpäivitysnopeudesta.
        while (running) {

            //Aika tällä hetkellä
            long now = System.nanoTime();
            delta += (now - lastTime) / tickInterval;
            lastTime = now;

            //Rajoita ruudunpäivitysnopeus asettamalla tähän "false"
            boolean render = false;

            while (delta >= 1) {
                ticks++;
                gamePanel.tick();
                delta -= 1;
                //Sallitaan pelin päivittää kehys kun ollaan päivitetty pelilogiikka
                render = true;
            }

            //Nuku 1 millisekunnin ajan
            if (render) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
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
                frame.setTitle(windowTitle + " (" + frames + " fps, " + ticks + " ticks)");
                frames = 0;
                ticks = 0;
            }
        }
    }

}
