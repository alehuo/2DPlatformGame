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

import com.ahuotala.platformgame.utils.FileReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
    /**
     * Spritesheet-tiedoston sijainti
     */
    public static final String SPRITESHEETPATH = "textures/spritesheet.png";

    private BufferedImage spriteSheet;

    /**
     * Yksittäiset spritet
     */
    private HashMap<String, Sprite> sprites;

    /**
     * Konstruktori lataa spritesheet -tiedoston muistiin
     *
     */
    public SpriteSheet() {

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try {

            //Ladataan spritesheet-kuva
            spriteSheet = ImageIO.read(cl.getResourceAsStream(SPRITESHEETPATH));

            sprites = new HashMap();

            //Erotellaan spritesheetistä spritet ja lisätään ne listaan
            InputStream stream = cl.getResourceAsStream("tiles/tiles.cfg");

            FileReader fr = new FileReader(stream);

            for (String line : fr.getLines()) {

                //Parsi muuttujat
                String[] lineData = line.split(",", -1);
                //Nimi
                String name = lineData[0];
                //x-koordinaatti
                int x = Integer.parseInt(lineData[1]);
                //y-koordinaatti
                int y = Integer.parseInt(lineData[2]);
                //leveys
                int width = Integer.parseInt(lineData[3]);
                //korkeus
                int height = Integer.parseInt(lineData[4]);

                Sprite tmpSprite = new Sprite();
                tmpSprite.setImage(spriteSheet.getSubimage(x, y, width, height));
                //Lisää tekstuuri sprites -listaan
                sprites.put(name, tmpSprite);
            }

        } catch (IOException | NumberFormatException e) {
            LOG.log(Level.SEVERE, null, e);
        }
    }

    public Sprite getSprite(String name) {
        return sprites.get(name);
    }

}
