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
import com.ahuotala.platformgame.entity.Entity;
import com.ahuotala.platformgame.entity.Player;
import com.ahuotala.platformgame.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private List<Entity> entities;

    public GamePanel() {
        super();
        entities = new ArrayList<>();
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
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

        //Tähän väliin kartan, taustan sekä käyttöliittymän piirtäminen
        //########################
        //Muut entiteetit
        entities.forEach((entity) -> {
            entity.render(g);
        });

        //Pelaaja
        if (player != null) {
            player.render(g);
            g.setColor(Color.WHITE);
            //Debuggausta varten x:t ja y:t jne..
            g.drawString("Pelaajan X: " + player.getX(), 2, 10);
            g.drawString("Pelaajan Y: " + player.getY(), 2, 22);
        }
        g.drawString("Entiteettejä pelissä: " + entities.size(), 2, 34);

    }

    public void setPlayer(Player p) {
        player = p;
    }
}
