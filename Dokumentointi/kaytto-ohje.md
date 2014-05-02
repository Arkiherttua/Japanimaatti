Japanimaatti - k‰yttˆohje
=========================

Ohjelmalla on kaksi perustoiminnallisuutta (kirjoitusmerkkien) kertaaminen,
ja ajastin (pomodoro-opiskelutekniikkaa varten, tai miksei vaikka kokkaamiseenkin)

Eri toimintojen v‰lill‰ siirryt‰‰n ohjelman vasemmassa laidassa olevasta valikosta.

Ohjelma suljetaan ptavanomaiseen tapaan painamalla rastia. Jotta tiedot varmasti tallentuvat, 
pit‰‰ ohjelman sulkeutuessa k‰ytt‰j‰n olla tilastot-v‰lilehdess‰ eli poistunut muista v‰lilehdist‰.

Kertaus
=======
Kun k‰ytt‰j‰ painaa Kertaa-painiketta, tulee siin tiedostonvalitsin, jolla valitaan tiedosta kerrattavaksi.
Tiedoston tulee olla .txt -muotoinen, ja muotoiltu tietyll‰ tavalla. Tiedostossa tulee olla rivej‰, joissa kullakin
on 5 sarkaimella eroteltua teksti"kappaletta". Rivien tulee siis n‰ytt‰‰ t‰lt‰:
kirjoitusmerkki		kirjoitusmerkin ‰‰nt‰mys	suomennos	tunniste(esim. mist‰ kirjan kappaleesta merkki on)	osaamistaso(eli onko k‰ytt‰j‰ merkinnyt osaavansa merkin)

Jos k‰ytt‰j‰ onnistuu valitsemaan oikeanmuotoisen tiedoston, ohjelma luettelee tiedostossa esiintyv‰t tunnisteet.
K‰ytt‰j‰ valitsee haluamansa tunnisteen korvaamalla oikeanpuolimmaisen tekstikent‰n tekstin kyseisell‰ tunnistesanalla.

T‰m‰n j‰lkeen valitaan, kerrataanko esimerkiksi vain se merkit, joiden osaamistaso on "ei" (=ei muistettu lainkaan).
Valinta toimii vastaavasti kuin edellinen kohta, kaikki tekstikent‰n teksti tulee korvata.

T‰m‰n j‰lkeen k‰ytt‰j‰ p‰‰see aloittamaan kertauksen. Ohjelma asettaa n‰kyville satunnaisessa j‰rjestyksess‰ valitut tunnisteet
sis‰lt‰vi‰ merkkej‰, ja kun sek‰ niiden ‰‰nt‰mys ett‰ suomennos on n‰ytetty, kysyy k‰ytt‰j‰lt‰, muistiko k‰ytt‰j‰ ‰‰nt‰mysken ja
suomennoksen. Ohjelmaan ei siis kirjoiteta merkkej‰ tms, k‰ytt‰j‰n on tarkoitus vain muistella itse, tunteeko kyseiset merkit.

Kertaustilasta voi poistua valitsemalla jonkin muun v‰lilehden. T‰llˆin ohjelma tallentaa tiedostoon mahdolliset osaamistason muutokset.
Muu tieto, kuten valittu tiedosto, katoaa, ja k‰ytt‰j‰n tulee valita uudestaan tiedosto ja tunnisteet jos h‰n haluaa jatkaa kertaamista.

Ajastus
=======
Ajastus-v‰lilehdess‰ k‰ytt‰j‰ asettaa p‰‰lle "munakellon". Ohjelma listaa aiheet, joita on jo ajastettu, ja k‰ytt‰j‰ voi valita jonkun
jo tallennetun aiheen tai luoda uuden. Joka tapauksessa aihe kirjoitetaan sille varattuun kentt‰‰n: j‰lleen kaikki muu teksti tulee
poistaa kent‰st‰. Samoin minuuttim‰‰r‰ kirjoitetaan sille varattuun kentt‰‰n.

Ajastin k‰ynnistyy, kun k‰ytt‰j‰ painaa ok-nappia. Ohjelma ilmoittaa ‰‰nell‰ ja tekstill‰, kun ajastus on ohi, ja tallentaa automaattisesti
tiedot.

Tilastot
========
Tilastoja ohjelman k‰ytˆst‰ p‰‰see katsomaan valitsemalla "N‰yt‰ tilastoja" -v‰lilehden. Se listaa kaikki aiheet, joita k‰ytt‰j‰ on ajastanut,
sek‰ kertoo, kauanko k‰ytt‰j‰ on k‰ytt‰nyt ohjelman ajastus/kertaustoimintoja menossa olevalla ohjelman k‰yttˆkerralla.