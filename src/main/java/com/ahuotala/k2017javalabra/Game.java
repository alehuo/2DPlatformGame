/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahuotala.k2017javalabra;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author ahuotala
 */
public class Game extends JPanel implements Runnable {

    private JFrame frame;

    /**
     * Aseta nimi, minimi-, maksimi- ja suositeltu koko
     */
    public Game() {
        frame = new JFrame("Peli");
        frame.setMaximumSize(new Dimension(1280, 720));
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setMinimumSize(new Dimension(1280, 720));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void run() {

    }

}
