Testausdokumentaatio
====================
Rivikattavuus j�i suhteellisen alhaiseksi. P��syyn� oli ohjelman hankalasti
testattava rakenne. Esimerkiksi Ajastimaatin p��toiminnallisuus perustui
suoraan javan Timer-luokkaan, enk� siksi testannut itse ajastuksen toimintaa.
Muut ajastinmaatit metodit olivat getteri-tyyppisi�, eik� niit� pystynyt oikein
testaamaan ainakaan ilman monimutkaista s��t�� muiden luokkien kanssa.

Gettereit� ja settereit� kertyi muuallekin paljon, eik� niit� vaadittu testattavaksi.

Mutantteja j�i eloon mm. siksi, ett� ohjelmassa oli loppujen lopuksi hyvin paljon
eri vaihtoehtoja, eik� kaikkien asioiden testaamisen toteuttamiseen lopulta 
ollut aikaa. Tunnisteita kun oli kahta tyyppi�, niin niiden eri "tiloja" on lopulta
varsin monta (esim. tunniste l�ytyy/osaamistaso ei, molemmat l�ytyv�t, k�ytt�j� antaa
toiseen v��r�n sy�tteen, toiseen ei jne)

Testasin ohjelmaa matkan varrella k�sin hyvin paljon. Vaikka mutanttikattavuutta ei
paperilla ole kovinkaan paljoa, olen varsin vakuuttunut siit�, ett� ohjelma toimii my�s
ns. rajatapauksissa ja ylip��ns� sen pit�isi olla varsin sataprosenttisesti bugiton.