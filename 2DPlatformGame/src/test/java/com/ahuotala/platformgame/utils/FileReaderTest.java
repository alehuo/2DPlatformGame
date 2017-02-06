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

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ahuotala
 */
public class FileReaderTest {

    @Test
    public void lukeeTiedostonOikein() throws IOException {
        //Class loader
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        
        //Tiedostonlukija
        FileReader f = new FileReader(cl.getResourceAsStream("test/FileReaderTest.txt"));

        assertEquals("123", f.getLines().get(0));
        assertEquals("456", f.getLines().get(1));
        assertEquals("789", f.getLines().get(2));
        assertEquals(3, f.getLines().size());
    }
}
