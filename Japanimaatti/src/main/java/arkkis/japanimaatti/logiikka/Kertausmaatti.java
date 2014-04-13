package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.Kertauspaneeli;
import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.tallennus.Tiedostonkasittelija;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * Kertausmaatissa on kertaukseen liittyvä logiikka
 */
public class Kertausmaatti {
    private Tiedostonkasittelija kasittelija;
    private HashSet<String> tunnisteet;
    private ArrayList<String[]> kerrattavat;
    private TekstiUI ui;
    private Kertauspaneeli paneeli;
    private Enum tila;
    private int moneskoRivi;
    
    public Kertausmaatti(){
        kasittelija = new Tiedostonkasittelija();
        //kasittelija.setFile("JapanimaatinTiedostot/kanjit.txt"); //kovakoodausta...
        kerrattavat = new ArrayList();
        tunnisteet = new HashSet();
        tila = KertausmaatinTila.TYHJA;
    }
    
    public void setKertauspaneeli(Kertauspaneeli paneeli){
        this.paneeli = paneeli;
    }
    
    public void setTiedosto(String tiedostonNimi){
        kasittelija.setFile(tiedostonNimi);
    }
    
    public void setTiedosto(File tiedosto){
        kasittelija.setFile(tiedosto);
    }
    
    public void setUI(TekstiUI ui){
        this.ui = ui;
    }
    
    /**
     * Metodi arpoo, monenneltako riviltä kerrattavat-listaa kerrataan seuraava merkki
     * ja päivittää luokkamuuttujaa moneskoRivi, joka sisältää tuon tiedon
     */
    public void arvoSeuraavaKerrattavaRivi(){
        Random random = new Random();
        if (kerrattavat.isEmpty()){
            paneeli.naytaTekstiaEkassaKentassa("Mitään kerrattavaa ei valittu!"); //vaatii refaktorointia jotta riippuvuus poistuu...
        } else if (tila == KertausmaatinTila.TYHJA) { //
            moneskoRivi = random.nextInt(kerrattavat.size());
        }
    }
    /**
     * Varsin oleellinen metodi, joka palauttaa merkin joka pitää kys. hetkellä tulostaa
     * sekä päivittää kertausmaatin tilan tätä vastaavaksi
     * @param index rivi, jonka sisältöä käsitellään
     * @return näytettävä teksti, eli merkki/merkin ääntämys/merkin suomennos
     */
    public String kertaaTama(int index){
        String palautettava = "";
        if (tila==KertausmaatinTila.TYHJA) {
            palautettava += kerrattavat.get(index)[0]; //näytä kanji
            tila = KertausmaatinTila.KANJI;
        } else if (tila == KertausmaatinTila.KANJI) {
            palautettava = kerrattavat.get(index)[1]; //näytä ääntämys
            tila = KertausmaatinTila.KANA;
        } else if (tila == KertausmaatinTila.KANA) {
            palautettava = kerrattavat.get(index)[1] + " " + kerrattavat.get(index)[2]; //näytä yhä ääntämys ja lisää suomennos
            tila = KertausmaatinTila.SUOMI;
        }
        return palautettava;
    }
    /**
     * Metodi tarkistaa, soveltuuko annettu tiedosto kertaamiseen, eli onko se muotoiltu oikein,
     * sillä väärin muotoiltu tiedosto hajottaisi kertausmaatin
     * @param tiedosto viite tiedostoon, jonka muotoilua tutkitaan
     * @return true jos tiedosto on kelvollinen, muuten false
     */
    public boolean onkoTiedostoOikeanmuotoinen(File tiedosto){
        kasittelija.setFile(tiedosto);
        String luettu = kasittelija.lueTiedostoRiviKerrallaan();
        while (!luettu.equals("TIEDOSTON LOPPU")){
            String[] rivinSanat = luettu.split(" ");
            if (!(rivinSanat.length == 4 || rivinSanat.length == 5)){ 
                return false; //jos rivillä väärä määrä sanoja, return false
            }
            luettu = kasittelija.lueTiedostoRiviKerrallaan();
        }
        return true;
    }
    
    /**
     * KertauksenKuuntelija kutsuu tätä metodia, joka tarkistaa, missä vaiheessa kertausta mennään ja asettaa seuraavan asian näkyviin
     */
    public String annaSeuraava(){
        if (tila == KertausmaatinTila.TYHJA){
            arvoSeuraavaKerrattavaRivi();
        }
        return kertaaTama(moneskoRivi);
    }
    
    /**
     * Metodi lukee tiedoston, etsii sieltä valitulla tunnisteella varustetut rivit ja tallentaa ne ohjelman ajonaikaiseen tietorakenteeseen
     * 
     * @param tunniste tällä tunnisteella varustetut rivit tutkitaan
     */
    public void haeKerrattavat(String tunniste){
        kasittelija.luoLukija();
        String rivi = kasittelija.lueTiedostoRiviKerrallaan();
        while (!rivi.equals("TIEDOSTON LOPPU")){
            if (rivi.contains(tunniste) || !tunnisteet.contains(tunniste)){ //lisätään kerrattavaksi joko tunnisteella varustetut, tai jos tunniste on huono, niin kaikki
                String[] rivinSanat = rivi.split(" ");
                kerrattavat.add(rivinSanat);
            }
            rivi = kasittelija.lueTiedostoRiviKerrallaan();
        }
        
        
    }
    
    /**
     * Kun kertaus aloitetaan, kutsutaan tätä metodia joka käy läpi tiedoston ja tekee listan siinä esiintyvistä tunnisteista
     */
    public void haeTunnisteet(){
        String rivi = kasittelija.lueTiedostoRiviKerrallaan();
        while (!rivi.equals("TIEDOSTON LOPPU")){
            String[] rivinSanat = rivi.split(" ");
            tunnisteet.add(rivinSanat[3]);
            rivi = kasittelija.lueTiedostoRiviKerrallaan();
        }
    }
    
    /**
     * palauttaa listan valitussa tiedostossa esiintyvistä tunnisteista
     * @return string-muotoinen listaus
     */
    public String getTunnisteet(){
        String palautettava = "";
        for (String tunniste : tunnisteet) {
            palautettava += tunniste + " ";
        }
        return palautettava;
    }
    public ArrayList<String[]> getKerrattavat(){
        return this.kerrattavat;
    }
    
    public Enum getKertauksenTila(){
        return tila;
    }
    
    public void setKertauksenTila(Enum tila){
        this.tila = tila;
    }
    
    /**
     * Metodi päivittää tiedon siitä, osasiko käyttäjä kerrattavan merkin. Toistaiseksi tieto tallentuu vain ohjelman käytön ajaksi
     * parannuksia tähän lienee kyllä luvassa.
     * @param osaaminen Enum joka kertoo, väittikö käyttäjä osaavansa merkin vai eikö
     */
    public void paivitaOsaaminen(Enum osaaminen) {
        kerrattavat.get(moneskoRivi)[4] = osaaminen.toString();
    }
    
    /**
     * Metodi laittaa tiedostonkäsittelijän tallentamaan kerrattavat-listan
     * sisällön tiedostoon, eli osaamistiedot päivittyvät
     */
    public void tallennaTiedostoon(){
        kasittelija.muokkaaTiedostonTiettyjaRiveja(kerrattavat);
    }
    
}
