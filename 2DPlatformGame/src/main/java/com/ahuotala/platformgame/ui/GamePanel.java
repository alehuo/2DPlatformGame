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
package com.ahuotala.platformgame.ui;

import com.ahuotala.platformgame.Game;
import com.ahuotala.platformgame.entity.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ahuotala
 */
public class GamePanel extends JPanel {

    private Player player;

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
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Tausta
        Color c = new Color(141, 159, 255);
        g.setColor(c);
        g.fill3DRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, false);

        try {
            BufferedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("cloud.png"));
            g.drawImage(image, 100, 50, image.getWidth() * 3, image.getHeight() * 3, null);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Tähän väliin kartan, taustan sekä käyttöliittymän piirtäminen
        //########################
        //Pelaaja
        if (player != null) {
            player.render(g);
        }

    }

    public void setPlayer(Player p) {
        player = p;
    }
}
