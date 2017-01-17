/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ahuotala.platformgame;

import javax.swing.SwingUtilities;

/**
 * Pääluokka
 * @author ahuotala
 */
public class Main {
    public static void main(String[] args) {
        //Kutsu peliä
        Game g = new Game();
        SwingUtilities.invokeLater(g);
    }
}
