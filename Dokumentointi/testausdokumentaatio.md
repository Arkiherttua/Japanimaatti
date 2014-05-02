Testausdokumentaatio
====================
Rivikattavuus jäi suhteellisen alhaiseksi. Pääsyynä oli ohjelman hankalasti
testattava rakenne. Esimerkiksi Ajastimaatin päätoiminnallisuus perustui
suoraan javan Timer-luokkaan, enkä siksi testannut itse ajastuksen toimintaa.
Muut ajastinmaatit metodit olivat getteri-tyyppisiä, eikä niitä pystynyt oikein
testaamaan ainakaan ilman monimutkaista säätöä muiden luokkien kanssa.

Gettereitä ja settereitä kertyi muuallekin paljon, eikä niitä vaadittu testattavaksi.

Mutantteja jäi eloon mm. siksi, että ohjelmassa oli loppujen lopuksi hyvin paljon
eri vaihtoehtoja, eikä kaikkien asioiden testaamisen toteuttamiseen lopulta 
ollut aikaa. Tunnisteita kun oli kahta tyyppiä, niin niiden eri "tiloja" on lopulta
varsin monta (esim. tunniste löytyy/osaamistaso ei, molemmat löytyvät, käyttäjä antaa
toiseen väärän syötteen, toiseen ei jne)

Testasin ohjelmaa matkan varrella käsin hyvin paljon. Vaikka mutanttikattavuutta ei
paperilla ole kovinkaan paljoa, olen varsin vakuuttunut siitä, että ohjelma toimii myös
ns. rajatapauksissa ja ylipäänsä sen pitäisi olla varsin sataprosenttisesti bugiton.