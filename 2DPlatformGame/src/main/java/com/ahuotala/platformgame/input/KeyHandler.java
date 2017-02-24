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
package com.ahuotala.platformgame.input;

import com.ahuotala.platformgame.entity.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Näppäimistönkuuntelija.
 *
 * @author ahuotala
 */
public class KeyHandler implements KeyListener {

    private final Player player;

    /**
     * Näppäimistönkuuntelija, joka "injektoi" pelaajan sisälleen.
     *
     * @param player Pelaaja
     */
    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Kun näppäintä painetaan, lähetetään KeyPressed-tapahtuma pelaajalle.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (player != null) {
            player.keyPressed(e);
        }
    }

    /**
     * Kun näppäin nostetaan, lähetetään KeyReleased-tapahtuma pelaajalle.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (player != null) {
            player.keyReleased(e);
        }
    }

}
