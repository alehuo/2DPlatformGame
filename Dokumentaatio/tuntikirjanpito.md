### Tuntikirjanpito
Päivä | Tunnit | Kuvaus
--------------- | ----- | ------
17.01.2017 | 2h | Repon luonti ja aiheen suunnittelua, peli-ikkunan luonti
18.01.2017 | 0.5h | Luotu pelaajalle näppäimistönkuuntelija liikkumista varten
18.01.2017 | 1h | Asetettu taustalle eri sävy ja luotu testitekstuuri (Pilvi)
20.01.2017 | 1h | Ensimmäisen testin kirjoittelua sekä Pit- ja Checkstyle -raporttien tekemistä
21.01.2017 | 1.5h | Lisätty muutama entiteettiluokka (Pilvi ja kolikko) sekä mahdollisuus ladata tekstuureja sprite-tiedostosta (tiles.cfg kertoo tekstuurin nimen sekä koon)
23.01.2017 | 1h | Paranneltu EntityTest -testin kattavuutta, päivitetty Pit -raportti ja lisäty Entity -luokalla tick() -metodi jolla voidaan päivittää Entiteettien tila
23.01.2017 | 1h | Luotu Tiili -luokka joka on Entiteetti -luokan aliluokka
24.01.2017 | 1h | Toteutettu perus hyppytoiminnallisuus pelaajalle. Pelaaja myös pysyy nyt tasolla, eikä tipu sen läpi.
27.01.2017 | 2h | Saatu pelaaja pysymään kentässä. Nyt pelaaja pysähtyy jos vastaan tulee korkeampi este, eikä mene sen läpi niin kuin aiemmin. Lisätty muutama JUnit -testi ja päivitetty Pit- sekä Checkstyle -raportit. Lisäksi muutettu pelin rakennetta siten, että kaikki entiteetit ladataan tasoon.
2.02.2017 | 4h | Pelitaso liikkuu nyt pelaajan mukana. Lisäksi päivitetty spritesheet.
3.02.2017 | 3h | Lisätty mahdollisuus lisätä entiteettejä suoraan tasotiedostoon ja luotu Score -luokka. Lisätty testit Score -luokalle sekä paranneltu vanhoja testejä. Pelaajan pistemäärä näkyy nyt näytöllä.
6.02.2017 | 2h | Luotu FileReader-luokka tiedostojen lukemista varten. Siirretty pistekirjanpito GameLevel -luokkaan, koska pisteet ovat tasokohtaisia.
7.02.2017 | 2h | Korjauttu törmäyksentunnistus ja lisätty ohjeistusta, että miten pelissä liikutaan.
10.02.2017 | 3h | Luotu Animaatio -luokka sekä animaatioita lataava AnimationLoader. Lisäksi tehty kolikon pyörimisanimaatio Pyxel Edit -ohjelmiston avulla ja lisätty se peliin.
11.02.2017 | 1h | Paranneltu kolikon pyörimisanimaatiota ja lisätty valmius pelaajan kävelemisanimaatioille.
12.02.2017 | 1h | Siirretty sekuntikello omaksi luokakseen Score -luokasta. Lisäksi luotu Clock -rajapinta, joka mahdollistaa Sekuntikellon testauksen helposti.
14.02.2017 | 0.5h | Tehty pelaajalle tekstuurit
15.02.2017 | 1.5h | Päivitetty luokkien testejä ja lisätty mahdollisuus skaalata peliä.
16.02.2017 | 1h | Paranneltu testejä rivikattavuuden ja mutaatioiden osalta.
17.02.2017 | 1h | Päivitetty Javadoccia, Checkstyle- ja Pit- raportit sekä luotu Monster ja MonsterAi -luokat (Luokkia ei ole vielä liitetty mihinkään).
20.02.2017 | 2h | Korjattu hirviön liikkuminen.
21.02.2017 | 3h | Toteutettu hirviölle tekoäly ja lisätty hirviölle tekstuurit. Päivitetty lisäksi luokkakaavio.
22.02.2017 | 2h | Toteutettu loppupisteiden näyttäminen, kun peli loppuu.
23.02.2017 | 3h | Toteutettu toiminnallisuus, joka vahingoittaa pelaajaa jos pelaaja ei liiku monsterin alueelta pois tarpeeksi nopeasti (käyttää pelaajan health-järjestelmää).
24.02.2017 | 4h | Pilkottu ohjelmaa paloihin, päivitetty JavaDoc, Checkstyle- ja Pit -raportit. Tehty tasosta pidempi ja lisätty pilvet taivaalle.
... | ... | ...
