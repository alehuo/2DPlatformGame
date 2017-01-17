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
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        //Pelaaja
        player = new Player();
        player.setX(150);
        player.setY(150);
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
        g.setColor(Color.BLACK);
        g.fill3DRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, false);

        //Testausta  varten
        if (player != null) {
            player.render(g);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (player.getX() < Game.WINDOW_WIDTH - 100) {
                player.setX(player.getX() + 2);
            }

        }

    }

}
