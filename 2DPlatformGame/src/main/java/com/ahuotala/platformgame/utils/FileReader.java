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
package com.ahuotala.platformgame.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * FileReader -luokkaa käytetään tiedostojen helpompaan käsittelyyn.
 *
 * @author ahuotala
 */
public class FileReader {

    private final ArrayList<String> lines;

    /**
     * FileReader -luokka lataa tiedoston rivi riviltä muistiin.
     *
     * @param in InputStream -objekti
     * @throws IOException IOException
     */
    public FileReader(InputStream in) throws IOException {
        //Alusta lista
        lines = new ArrayList();

        //UTF-8 enkoodaus
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        String line = "";

        if (in != null) {
            while ((line = br.readLine()) != null) {
                //Jos rivi alkaa hashtagilla tai se on tyhjä, niin ohitetaan se
                //Muuten lisätään rivi listaan
                if (!line.startsWith("#") && !line.isEmpty()) {
                    lines.add(line);
                }
            }

            br.close();
        }
    }

    /**
     * FileReader -luokka lataa tiedoston rivi riviltä muistiin.
     *
     * @param filename Tiedostonimi
     * @throws FileNotFoundException FileNotFoundException
     * @throws IOException IOExceptopn
     */
    public FileReader(String filename) throws FileNotFoundException, IOException {
        //Alusta lista
        lines = new ArrayList();

        FileInputStream fis = null;
        fis = new FileInputStream(filename);
        //UTF-8 enkoodaus
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));

        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        br.close();

    }

    /**
     * Palauttaa luetut rivit.
     *
     * @return Luetut rivit
     */
    public ArrayList<String> getLines() {
        return lines;
    }

}
