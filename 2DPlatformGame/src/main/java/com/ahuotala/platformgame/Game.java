/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahuotala.platformgame;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Pääluokka
 *
 * @author ahuotala
 */
public class Game implements Runnable {

    /**
     * JFrame
     */
    private JFrame frame;

    /**
     *
     */
    private boolean running;

    /**
     * Kehyksen leveys
     */
    public static int WINDOW_WIDTH = 1280;

    /**
     * Kehyksen korkeus
     */
    public static int WINDOW_HEIGHT = 720;

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    public Game() {
        frame = new JFrame("Peli");
        frame.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GamePanel g = new GamePanel();

        frame.setContentPane(g);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }

    public static void main(String[] args) {
        //Kutsu peliä
        Game g = new Game();
        g.start();
//        SwingUtilities.invokeLater(g);
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
        double tickInterval = 1000000000D / 60D;

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
        
        Kun looppi käynnistyy, otetaan ylös tämän hetkinen aika.
        Sen jälkeen kun on kulunut tickInterval verran aikaa, tarkistetaan onko delta suurempi tai yhtäsuuri kuin yksi.
        Jos näin on, päivitetään pelilogiikka ja asetetaan delta takaisin nollan lähelle.
         */
        while (running) {

            //Aika tällä hetkellä
            long now = System.nanoTime();

            delta += (now - lastTime) / tickInterval;

            lastTime = now;

            //Rajoita ruudunpäivitysnopeus asettamalla tähän "false"
            boolean render = true;

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
                frames++;
            }
        }
    }

    /**
     * Metodi joka suoritetaan 60 kertaa sekunnissa (16,66ms) tick(). Tämä
     * metodi on riippumaton ruudunpäivitysnopeudesta.
     */
    public void tick() {

    }
}
