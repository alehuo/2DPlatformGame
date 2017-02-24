# Rakennekuvaus

Pelin pääluokkana toimii Game, joka sisältää myös peliloopin.
Pelilooppi suoritetaan 60 kertaa sekunnissa piirtäen näytölle kuvan ja lähettää päivittämiskutsun GamePanel-luokalle. 
Lisäksi pääluokan vastuulla on ottaa pelaajan näppäimistösyöte vastaan ja lähettää se Player-luokalle.

GamePanel-luokan vastuulla on tason ja entiteettien piirtäminen näytölle ja päivittämiskutsun lähettäminen GameLevel-luokalle.
Lisäksi GamePanel piirtää ohjetekstit sekä tämänhetkiset statistiikat pelaajan suorituksesta.

GameLevel-luokan tärkein tehtävä on ladata tasotiedostosta tiedot käyttäen FileReader-luokkaa.
GameLevel-luokkaan on kytketty kiinni GameUpdater, jonka tehtävä on päivittää sekuntikello, tason entiteetit ja pelaaja. 
GameUpdaterin lisäksi GameLevel-luokka sisältää tiedot pisteytyksestä ja entiteeteistä.

Entity-yliluokan perivät kaikki ne luokat, joiden halutaan olevan pelissä. Näitä ovat Coin, Cloud, Tile, Monster ja Player.

Entiteeteillä voi myöskin olla tekstuureista koostuvia animaatioita tai tekstuureja itsessään. Tekstuurien ja animaatioiden lataamiseen käytetään SpriteLoader ja AnimationLoader -luokkia (nämä lataajat hyödyntävät FileReader-luokkaa).

Monster-entiteetille on luotu MonsterAi-luokka, joka pitää huolen siitä, että monsterit lähestyvät (ja mahdollisesti vahingoittavat) pelaajaa, jos pelaaja tulee liian lähelle monsteria.
