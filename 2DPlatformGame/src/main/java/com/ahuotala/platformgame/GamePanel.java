/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahuotala.platformgame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author alehuo
 */
public class GamePanel extends JPanel {
    
    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public GamePanel() {
        super();
    }

    /**
     * Tätä käytetään piirtämiseen
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.fill3DRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, false);
    }
}
