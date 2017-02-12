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
import com.ahuotala.platformgame.level.GameLevel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Pelipaneeli piirtää tason, entiteetit sekä pelaajan
 *
 * @author ahuotala
 */
public class GamePanel extends JPanel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    //Taso
    private GameLevel level;

    public GamePanel() {
        super();
    }

    /**
     * Aseta taso
     *
     * @param level
     */
    public void setLevel(GameLevel level) {
        this.level = level;
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
        Color c = new Color(93, 148, 251);
        g.setColor(c);
        g.fill3DRect(0, 0, Game.WINDOWWIDTH, Game.WINDOWHEIGHT, false);

        //Taso
        if (level != null) {
            level.getTiles().forEach((tile) -> {
                tile.render(g);
            });
            level.getEntities().forEach((entity) -> {
                entity.render(g);
            });
        }

        Player player = level.getPlayer();

        //Pelaaja
        if (player != null) {
            player.render(g);
            g.setColor(Color.WHITE);

            //Ohjeet
            g.drawString("[A] Vasen  [D] Oikea  [Space]  Hyppää", 4, 20);

            //Debuggausta varten x:t ja y:t jne..
//            g.drawString("playerX: " + (player.getX() - Game.STARTINGOFFSET + Player.offsetX), 4, 35);
//            g.drawString("playerY: " + player.getY(), 4, 50);
//            g.drawString("isFalling: " + player.isFalling(), 4, 65);
//            g.drawString("walkingDirection: " + player.getWd(), 4, 80);
//            g.drawString("playerBoundsX: " + (player.getBounds().getX()), 4, 95);
//            g.drawString("playerBoundsY: " + (player.getBounds().getY()), 4, 110);
            //Pisteytys
            Font f = g.getFont();
            Font newF = f.deriveFont(f.getSize() * 3F);
            g.setFont(newF);
            g.drawString("Score: " + level.getScore().getValue(), Game.WINDOWWIDTH / 2, 32);
            g.drawString("Time: " + level.getScore().getCurrentTime() / 1000 + " s", Game.WINDOWWIDTH / 2, 70);
        }

        //Todo..
    }

    /**
     * Antaa päivityskutsun tasolle.
     */
    public void tick() {
        level.tick();
    }
}
