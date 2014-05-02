Japanimaatti - aiheen kuvaus
============================

Japanimaatti ohjelma, joka vahtaa, ett� sen k�ytt�j� opiskelee eik� vain irkkaa. Tallentaa tietoja opiskelun m��r�st� ja edistyksest�.

Ohjelmalla on kaksi perustoiminnallisuutta, ensimm�inen on: kirjoitusmerkkien kertaaminen (enkkuesimerkill�: ohjelma postaa n�kyviin "a dog", enteri� hakkaamalla k�ytt�j� saa esiin suomennoksen "koira", ja voi valita 'osasin' 'en osannut', ja ohjelma tallentaa tuloksen)
Toinen on ohjelman k�ytt�minen ajastimena (googlaa pomodoro techinique, jos kiinnostut siit�, miksi t�m� on j�rkev��), joka my�s tallentaa, mit� opiskeltiin (ennen tenttiviikkoa voi ihmetell�, montako tuntia mit�kin asiaa on treenannut)

k�ytt�j�t:
ohjelma on tarkoitettu ensisijaisesti ihan vain minulle, mutta yleisesti ottaen k�ytt�j� lienee japanin opiskelija. Jotain muutakin maatilla voi toki kerrata/ajastaa :)

opiskelijan toiminnot:
- kertaa/opettelee japanin kirjoitusmerkkej�
	- ohjelma tallettaa mit� k�ytt�j� jo osaa, ja sen mukaan postaa n�kyville merkki kerrallaan kerrattavaa
- 'munakello'
	- k�ytt�j� kertoo ohjelmalle, mit� opiskelee ja kuinka kauan, ajan p��tytty� munakello huutaa & k�ytt�j� p��see vapaalle & ohjelmaan tallentuu, mit� opiskeltiin
- tutkailee tilastoja
	- mit� on tullut opiskeltua


Japanimaatin rakenne
====================
Ohjelma on jaettu kolmeen pakkaukseen: k�ytt�liittym��n, logiikkaan ja tallennustoimintoihin. Varsinaista logiikkaa edustavat Kertausmaatti ja Ajastinmaatti,
jotka huolehtivat noista toiminnoista. Ajastinmaatti k�ytt�� ajastamiseen Javan Timer-luokkaa ja kutsuu AjastimenTiedot-luokan tallennusmetodeita. AjastimenTiedot
lukee ohjelman auetessa tiedostosta, mit� on toistaiseksi ajastettu, pit�� tiedot HashMapissa ajon aikana ja lopuksi tallentaa ne tiedostoon.
Kertausmaatilla on sis�ll��n jokseenkin kaikki kertaukseen liittyv�.

Tiedostojen k�sittelyn hoitaa Tiedostonk�sittelij�: sill� on erilaisia tiedoston luku- ja kirjoitusmetodeita ohjelman eri tarpeisiin. Muut ohjelmat eiv�t varsinaisiin
tiedostoihin koske.

K�ytt�liittym�� hoitelee GraafinenUI-luokka, joka p��asiassa hoitaa muiden luokkien v�liset yhteydet. N�kyv�ss� k�ytt�liittym�ss� on jatkuvasti vasemmalla valikko-palkki,
jonka eri osiot on toteutettu luokkina Tilastopaneeli, Ajastinpaneeli ja Kertauspaneeli. GraafinenUI asettaa niit� vuorotellen n�kyviin. Useassa kohtaa on hy�dynnetty CardLayout-
taika-asiaa, jolla n�kyviss� oleva JPanel vaihdetaan toiseksi.