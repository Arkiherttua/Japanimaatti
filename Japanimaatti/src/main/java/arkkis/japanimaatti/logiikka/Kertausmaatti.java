/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.Kertauspaneeli;
import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.tallennus.Tiedostonkasittelija;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;

/**
 *
 * Kertausmaatissa on kertaukseen liittyvä logiikka
 */
public class Kertausmaatti {
    private Tiedostonkasittelija kasittelija;
    private ArrayList<String> tunnisteet;
    private ArrayList<String[]> kerrattavat;
    private TekstiUI ui;
    private Kertauspaneeli paneeli;
    private Enum tila;
    private int moneskoRivi;
    
    public Kertausmaatti(){
        kasittelija = new Tiedostonkasittelija();
        kasittelija.setFile("JapanimaatinTiedostot/kanjit.txt"); //kovakoodausta...
        kerrattavat = new ArrayList();
        tunnisteet = new ArrayList();
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
     * Käytöstä pois jäänyt metodi joka luultavasti poistetaan...
     */
    
    public void kertaaKaikki(){
        //tämä paranee vielä paljon tämä ominaisuus...
//        Scanner lukija = luoLukija();
//        while (lukija.hasNextLine()){
//            String rivi = lukija.nextLine();
//            String[] rivinSanat = rivi.split(" ");
//            ui.tulostaJaVaadiEnter(rivinSanat[0]);
//            ui.tulostaJaVaadiEnter(rivinSanat[1]);
//            ui.tulostaJaVaadiEnter(rivinSanat[2]);
//        }
    }
    
    public void seuraavaKerrattava(){
        Random random = new Random();
        if (kerrattavat.isEmpty()){
            paneeli.naytaTekstiaEkassaKentassa("Mitään kerrattavaa ei valittu!"); //vaatii refaktorointia jotta riippuvuus poistuu...
        } else if (tila == KertausmaatinTila.TYHJA) { //
            moneskoRivi = random.nextInt(kerrattavat.size());
            //kertaaTama(moneskoRivi);
        }
    }
    
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
     * KertauksenKuuntelija kutsuu tätä metodia, joka tarkistaa, missä vaiheessa kertausta mennään ja asettaa seuraavan asian näkyviin
     */
    public String annaSeuraava(){
        if (tila == KertausmaatinTila.TYHJA){
            seuraavaKerrattava();
        }
        return kertaaTama(moneskoRivi);
    }
    
    public void haeKerrattavat(String tunniste){
        Scanner lukija = luoLukija();
        while (lukija.hasNextLine()){
            String rivi = lukija.nextLine();
            if (rivi.contains(tunniste)){
                String[] rivinSanat = rivi.split(" ");
                kerrattavat.add(rivinSanat);
            }
        }
    }
    
    /**
     * Kun kertaus aloitetaan, kutsutaan tätä metodia joka käy läpi tiedoston ja tekee listan siinä esiintyvistä tunnisteista
     */
    public void haeTunnisteet(){
        Scanner lukija = luoLukija();
        while (lukija.hasNextLine()){
            String rivi = lukija.nextLine(); //mene nyt sinne uudelle riville
            String[] rivinSanat = rivi.split(" ");
            lisaaTunniste(rivinSanat[3]);     //tunniste siis aina neljännessä 'sanassa' rivillä
        }
    }
   
    public String getTunnisteet(){
        String palautettava = "";
        for (String tunniste : tunnisteet) {
            palautettava += tunniste + " ";
        }
        return palautettava;
    }
    
    public void lisaaTunniste(String tunniste){
        if (!tunnisteet.contains(tunniste)){
            tunnisteet.add(tunniste);
        }
    }
    
    public Scanner luoLukija(){
        try {
            Scanner lukija = new Scanner(kasittelija.getFile());
            return lukija;
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy!");
        }
        return null;
    }
    
    public Enum getTila(){
        return tila;
    }
    
    public void valitseKerrattavat(){
        //tähän hieno metodi jossain vaiheessa
    }
}
