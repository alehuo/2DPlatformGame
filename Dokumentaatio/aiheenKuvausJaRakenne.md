### Aiheen kuvaus ja rakenne
**Aihe:** Projektin aiheena on Super Mario Bros -tyylinen tasohyppelypeli.
Peliss� tulee olemaan yksi taso, joka sis�lt�� hirvi�it� ja ker�tt�vi� kolikoita.
Pelaaja saa pisteit� ker�tess��n kolikoita sek� menett�� "healthia" jos h�n ep�onnistuu v�ist�m��n monstereita.
Lis�ksi tason suoritusaika, ker�tyt kolikot sek� tuhotut hirvi�t (Hirvi�t pystyy tuhoamaan hypp��m�ll� niiden p��lle)
vaikuttavat loppupisteytykseen. Kun k�ytt�j� on suorittanut tason, peli loppuu. Pelin loputtua pelaajan loppupisteet n�ytet��n n�yt�ll�.
	Peliss� on my�s tarkoitus olla tekstuureja sek� tekstuureista koostuvia animaatioita (esim. kolikon py�ritt�minen ja pelaajan k�vely). N�it� varten tarvitaan konfiguraatiotiedostoja sek�
jollain kuvank�sittelyohjelmalla piirrettyj� kuvia. Tekstuurien ja animaatioiden suunnittelussa k�ytet��n Pyxel Edit -ohjelmistoa.
	Liikkumiseen peliss� k�ytet��n A- ja D -n�pp�int� sek� v�lily�nti�, jolla pelaaja hypp��.

**K�ytt�j�t:** Peli� k�ytt�� vain yksi k�ytt�j� ja lis�ksi peliss� on tietokoneohjattuja hirvi�it�.

### Kaikkien k�ytt�jien toiminnot & k�ytt�tapaukset
* Liikkuminen (eteen, taakse, hyppy)
* Kolikoiden ker��minen
* Hirvi�iden tappaminen

### Luokkakaavio
![luokkakaavio](Luokkakaavio.png)

### Sekvenssikaaviot

## Pelin alustus ja peliloopin toiminta
![Peliloopin toiminta](sekvenssikaavio_peliloopin_toiminta.png)
Kaavio n�ytt��, miten pelin k�ynnistyminen tapahtuu ja mit� pelilooppi tekee pelin py�riess�.

## Pelaajan liikkuminen
![Pelaajan liikkuminen](sekvenssikaavio_pelaajan_liikkuminen.png)
Kaavio sis�lt�� pelaajan liikkumisen alkaen n�pp�imen painalluksesta peliloopin k�sittelem��n liikkumistoiminnallisuuteen.
My�s n�pp�imen p��st�minen pohjasta on mallinnettu.