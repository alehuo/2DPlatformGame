# Testausdokumentti

## Mitä en testannut?

Pelaajan liikkumisen testaus yksikkötesteillä osoittautui haastavaksi. Pelaajan ei pitäisi liikkua sen alueen yli, missä tasoa ei ole enää piirretty (itse tasotiedosto on rajoitetun kokoinen).
Testasin ominaisuutta itse siten, että liikutin pelaajan kokonaan vasempaan ja sitten oikeaan laitaan. Katsoin molemmissa päissä, että liikkuiko pelaaja tason yli.

Lisäksi kolikon "hyppimisen" mahdollistava koodi perustuu satunnaisuuteen, joten kyseisen ominaisuuden testasin silmämääräisesti.

## Bugeja

-