Japanimaatti - aiheen kuvaus
============================

Japanimaatti ohjelma, joka vahtaa, että sen käyttäjä opiskelee eikä vain irkkaa. Tallentaa tietoja opiskelun määrästä ja edistyksestä.

Ohjelmalla on kaksi perustoiminnallisuutta, ensimmäinen on: kirjoitusmerkkien kertaaminen (enkkuesimerkillä: ohjelma postaa näkyviin "a dog", enteriä hakkaamalla käyttäjä saa esiin suomennoksen "koira", ja voi valita 'osasin' 'en osannut', ja ohjelma tallentaa tuloksen)
Toinen on ohjelman käyttäminen ajastimena (googlaa pomodoro techinique, jos kiinnostut siitä, miksi tämä on järkevää), joka myös tallentaa, mitä opiskeltiin (ennen tenttiviikkoa voi ihmetellä, montako tuntia mitäkin asiaa on treenannut)

käyttäjät:
ohjelma on tarkoitettu ensisijaisesti ihan vain minulle, mutta yleisesti ottaen käyttäjä lienee japanin opiskelija. Jotain muutakin maatilla voi toki kerrata/ajastaa :)

opiskelijan toiminnot:
- kertaa/opettelee japanin kirjoitusmerkkejä
	- ohjelma tallettaa mitä käyttäjä jo osaa, ja sen mukaan postaa näkyville merkki kerrallaan kerrattavaa
- 'munakello'
	- käyttäjä kertoo ohjelmalle, mitä opiskelee ja kuinka kauan, ajan päätyttyä munakello huutaa & käyttäjä pääsee vapaalle & ohjelmaan tallentuu, mitä opiskeltiin
- tutkailee tilastoja
	- mitä on tullut opiskeltua


Japanimaatin rakenne
====================
Ohjelma on jaettu kolmeen pakkaukseen: käyttöliittymään, logiikkaan ja tallennustoimintoihin. Varsinaista logiikkaa edustavat Kertausmaatti ja Ajastinmaatti,
jotka huolehtivat noista toiminnoista. Ajastinmaatti käyttää ajastamiseen Javan Timer-luokkaa ja kutsuu AjastimenTiedot-luokan tallennusmetodeita. AjastimenTiedot
lukee ohjelman auetessa tiedostosta, mitä on toistaiseksi ajastettu, pitää tiedot HashMapissa ajon aikana ja lopuksi tallentaa ne tiedostoon.
Kertausmaatilla on sisällään jokseenkin kaikki kertaukseen liittyvä.

Tiedostojen käsittelyn hoitaa Tiedostonkäsittelijä: sillä on erilaisia tiedoston luku- ja kirjoitusmetodeita ohjelman eri tarpeisiin. Muut ohjelmat eivät varsinaisiin
tiedostoihin koske.

Käyttöliittymää hoitelee GraafinenUI-luokka, joka pääasiassa hoitaa muiden luokkien väliset yhteydet. Näkyvässä käyttöliittymässä on jatkuvasti vasemmalla valikko-palkki,
jonka eri osiot on toteutettu luokkina Tilastopaneeli, Ajastinpaneeli ja Kertauspaneeli. GraafinenUI asettaa niitä vuorotellen näkyviin. Useassa kohtaa on hyödynnetty CardLayout-
taika-asiaa, jolla näkyvissä oleva JPanel vaihdetaan toiseksi.