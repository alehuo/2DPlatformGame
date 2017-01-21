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
package com.ahuotala.platformgame.graphics;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Texture-tiedoston avaamiseen käytetty luokka
 *
 * @author ahuotala
 */
public class SpriteSheet {
    private static final Logger LOG = Logger.getLogger(SpriteSheet.class.getName());

    private BufferedImage spriteSheet;

    /**
     * Yksittäiset spritet
     */
    private HashMap<String, Sprite> sprites;

    /**
     * Spritesheet-tiedoston sijainti
     */
    private final String spriteSheetPath = "textures/spritesheet.png";


    /**
     * Konstruktori lataa spritesheet -tiedoston muistiin
     *
     */
    public SpriteSheet() {

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try {

            //Ladataan spritesheet
            spriteSheet = ImageIO.read(cl.getResourceAsStream(spriteSheetPath));

            sprites = new HashMap();

            //Erotellaan spritesheetistä spritet ja lisätään ne listaan
            String line = "";
            InputStream stream = cl.getResourceAsStream("tiles/tiles.cfg");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                if (stream != null) {
                    while ((line = reader.readLine()) != null) {

                        //Jos rivi alkaa hashtagilla tai on tyhjä
                        if (line.startsWith("#") || line.isEmpty()) {
                            continue;
                        }

                        //Parse variables
                        String[] lineData = line.split(",", -1);
                        String name = lineData[0];
                        int x = Integer.parseInt(lineData[1]);
                        int y = Integer.parseInt(lineData[2]);
                        int width = Integer.parseInt(lineData[3]);
                        int height = Integer.parseInt(lineData[4]);

                        Sprite tmpSprite = new Sprite();
                        tmpSprite.setImage(spriteSheet.getSubimage(x, y, width, height));
                        sprites.put(name, tmpSprite);
                        LOG.log(Level.INFO, "Ladattu tekstuuri ''{0}'' muistiin.", name);
                    }
                    stream.close();
                }
            }
        } catch (IOException | NumberFormatException e) {
            LOG.log(Level.SEVERE, null, e);
            System.exit(0);
        }
    }

    public Sprite getSprite(String name) {
        return sprites.get(name);
    }

}
