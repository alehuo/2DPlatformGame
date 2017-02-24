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
import com.ahuotala.platformgame.level.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Pelipaneeli piirtää tason, entiteetit sekä pelaajan.
 *
 * @author ahuotala
 */
public class GamePanel extends JPanel {

    //SerialVersionUID
    private static final long serialVersionUID = 1L;

    //Taso
    private GameLevel level;

    /**
     * Pelipaneelin konstruktori.
     */
    public GamePanel() {
        super();
    }

    /**
     * Aseta pelitaso pelipaneeliin, jotta sen entiteetit voidaan piirtää
     * näytölle (ja päivittää niitä).
     *
     * @param level pelitaso
     */
    public void setLevel(GameLevel level) {
        this.level = level;
    }

    /**
     * Tätä käytetään piirtämiseen.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        Player player = level.getPlayer();

        super.paintComponent(g);

        //Tausta
        Color c = new Color(93, 148, 251);
        g.setColor(c);
        g.fill3DRect(0, 0, Game.WINDOWWIDTH, Game.WINDOWHEIGHT, false);
        if (!level.isGameOver()) {
            //Taso
            if (level != null) {
                level.getTilesAndEntities().forEach((entity) -> {
                    entity.render(g);
                });
                if (player != null) {
                    player.render(g);
                    g.setColor(Color.YELLOW);
                }
            }

            //Pelaaja
            if (player != null) {
                //Ohjeet
                g.drawString("[A] Vasen  [D] Oikea  [Space]  Hyppää", 4, 20);
                //Pisteytys
                Font f = g.getFont();
                Font newF = f.deriveFont(f.getSize() * 2F);
                g.setFont(newF);
                g.drawString("Score: " + level.getScore().getValue(), Game.WINDOWWIDTH / 9, Game.WINDOWHEIGHT - 64);
                g.drawString("Time: " + level.getScore().getCurrentTime() / 1000 + " s", Game.WINDOWWIDTH / 9 + 250, Game.WINDOWHEIGHT - 64);
                g.drawString("Health: " + player.getHealth() + " hp", Game.WINDOWWIDTH / 9 + 500, Game.WINDOWHEIGHT - 64);
            }

        } else {
            //player.getX() - Game.STARTINGOFFSET + Player.offsetX
            Score score = level.getScore();
            int textOffsetY = 128;
            int textOffsetX = -Game.WINDOWWIDTH / 4;
            //Pysäytä sekuntikello
            if (score.getStopWatch().isRunning()) {
                score.stop();
            }
            g.setColor(Color.YELLOW);
            //Pisteytys
            Font f = g.getFont();
            Font newF = f.deriveFont(f.getSize() * 4F);
            g.setFont(newF);
            g.drawString("THE END", Game.WINDOWWIDTH / 2 + textOffsetX, 32 + textOffsetY);
            g.drawString("Collected coins: " + score.getCollectedCoins(), Game.WINDOWWIDTH / 2 + textOffsetX, 160 + textOffsetY);
            g.drawString("Defeated monsters: " + score.getDefeatedMonsters(), Game.WINDOWWIDTH / 2 + textOffsetX, 224 + textOffsetY);
            g.drawString("Elapsed time: " + Math.ceil(score.getStopWatch().getTotalSeconds()) + " second(s) ", Game.WINDOWWIDTH / 2 + textOffsetX, 288 + textOffsetY);
            g.drawString("Final score: " + score.getValue() + " points", Game.WINDOWWIDTH / 2 + textOffsetX, 352 + textOffsetY
            );
        }

    }

    /**
     * Antaa päivityskutsun tasolle.
     */
    public void tick() {
        level.tick();
    }
}
