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
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tämä luokka lataa animaatiot valmiiksi käyttöä varten.
 *
 * @author ahuotala
 */
public class AnimationLoader {

    private static final HashMap<String, Animation> ANIMATIONS = new HashMap();

    /**
     * Lisää tänne ladattavat animaatiotiedostot.
     */
    private static final String[] ANIMATIONFILES = {"coin.cfg"};

    private static boolean animationsLoaded = false;

    /**
     * AnimationLoader hakee animaatiotiedostot animations/ -kansiosta ja lisää
     * ne animaatiolistaan, joka on staattinen.
     *
     */
    private static void load() {

        //ClassLoader
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        int interval = 60;

        try {

            //Käydään tiedostot rivi riviltä läpi
            for (String tiedostoNimi : ANIMATIONFILES) {

                Animation animaatio = new Animation(interval);

                FileReader animationReader = new FileReader(cl.getResourceAsStream("animations/" + tiedostoNimi));

                //Tässä käydään animaatiotiedosto rivi riviltä läpi
                for (String rivi : animationReader.getLines()) {
                    if (rivi.startsWith("interval:")) {
                        try {
                            interval = Integer.parseInt(rivi.split(":")[1]);
                            animaatio.setInterval(interval);
                        } catch (NumberFormatException e) {

                        }
                    } else {
                        try {
                            String spriteName = rivi.trim();
                            Sprite spr = SpriteLoader.getSprite(spriteName);
                            if (spr != null) {
                                animaatio.addFrame(spr);
                            }

                        } catch (NumberFormatException e) {

                        }

                    }
                }
                //Animaation luku valmis, lisätään se listaan
                ANIMATIONS.put(tiedostoNimi.replace(".cfg", ""), animaatio);
            }

            animationsLoaded = true;

        } catch (IOException ex) {
            Logger.getLogger(AnimationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hakee animaation.
     *
     * @param name Animaation nimi (ilman .cfg -päätettä)
     * @return Animaatio
     */
    public static Animation getAnimation(String name) {
        if (!animationsLoaded) {
            load();
        }
        if (ANIMATIONS != null && ANIMATIONS.containsKey(name)) {
            return ANIMATIONS.get(name);
        }
        return null;
    }

}
