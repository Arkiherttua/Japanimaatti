
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * Luokka huolehtii tiedostoon kirjoittamisesta ja tiedoston lukemisesta, ainoa luokka jolla todella on File
 */

public class Tiedostonkasittelija {
    File tiedosto;
    Scanner lukija;
    
    /**
     * Metodi tallentaa annetun stringin tiedostoon, joka luokalla on luokkamuuttujana
     * @param talletettava teksti, joka halutaan tallentaa
     */
    public void tallennaTiedostoon(String talletettava){
        try {
            PrintWriter kirjoitin = new PrintWriter(tiedosto); //tilalle metodikutsu? (riippuu siitä miten paljon koodi räjähtää) 
            kirjoitin.print(talletettava);
            kirjoitin.close();
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy, ei talletettu");
        }
    }
    
    /**
     * Tallentaa annetun arraylistin tiedostoon, joka luokalla on luokkamuuttujana
     * @param talletettava 
     */
    public void tallennaTiedostoon(ArrayList<String> talletettava){
        try {
            PrintWriter kirjoitin = new PrintWriter(tiedosto); //tilalle tähänkin metodikutsu? (riippuu siitä miten paljon koodi räjähtää) 
            for (String rivi : talletettava) {
                kirjoitin.println(rivi);
            }
            kirjoitin.close();
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy, ei talletettu");
        }
    }
    
    /**
     * Metodi saa listan string-taulukoita, jotka sisältävät tietoa, jonka halutaan päätyvän tiedostoon.
     * @param muokattavatRivit lista taulukoita: yhdessä taulukossa yhden rivin sanat
     */
    public void muokkaaTiedostonTiettyjaRiveja(ArrayList<String[]> muokattavatRivit){
        luoLukija();
        ArrayList<String> tiedostonSisalto = new ArrayList();
        boolean olikoMuokattujenListalla = false;
        
        while (lukija.hasNextLine()){
            String[] rivinTeksti = lukija.nextLine().split("\t");
            for (String[] rivi : muokattavatRivit) {
                if (rivi[0].equals(rivinTeksti[0])){ //jos eka sana sama, rivit toivottavasti samat, eli halutaan tallentaa muokattu rivi tiedostoon
                    tiedostonSisalto.add(rivi[0] + rivi[1] + rivi[2] + rivi[3] + rivi[4]);
                    olikoMuokattujenListalla = true;
                }
            }
            if (!olikoMuokattujenListalla){
                tiedostonSisalto.add(lukija.nextLine());
            }
            olikoMuokattujenListalla = false;
        }
        tallennaTiedostoon(tiedostonSisalto);
//        while (lukija.hasNextLine()){
//            String[] rivinSanat = lukija.nextLine().split("\t");
//            String ekaSana = rivinSanat[0];
//            int poistaTamaRivi = -1; //jos mitään ei tarvitse poistaa, tämä indeksi pysyy negatiivisena
//            for (int i = 0; i < muokattavatRivit.size(); i++) {
//                if (ekaSana.equals(muokattavatRivit.get(i)[0])){ //jos eka sana on sama, rivit ovat samat (toivottavasti), kirjoitetaan muokattu rivi tiedostoon
//                    poistaTamaRivi = i;
//                    kirjoitin.print(taulukkoTulostusmuotoon(muokattavatRivit.get(i)));
//                    System.out.println("nyt löytyi sama sana");
//                    break;
//                }
//            }
//            if (poistaTamaRivi != -1){ //jos tätä muokattu, pitää jotain poistaa
//                muokattavatRivit.remove(poistaTamaRivi);
//            } else {
//                kirjoitin.print(rivinSanat);
//                System.out.println("nyt löytyi eri sana");
//            }
//        }
        //kirjoitin.close();
    }
    
    /**
     * Metodi muokkaa saamansa string-taulukon jälleen yhdeksi tabeilla erotelluksi stringiksi,
     * jotta taulukon sisältö saadana tiedostoon fiksusti
     * @param taulukko tiedot, jotka halutaan yhdeksi stringiksi
     * @return yhdeksi stringiksi muutettu taulukon sisältö
     */
    public String taulukkoTulostusmuotoon(String[] taulukko){
        String palautettava = "";
        for (int i = 0; i < taulukko.length; i++) {
            palautettava += taulukko[i] + "\t";
        }
        return palautettava.substring(0, palautettava.length()-1);
    }
    
    /**
     * Metodi lukee tiedostoa, yksi sana per jokainen kutsu
     * @return palauttaa tiedostosta luetun sanan tai TIEDOSTON LOPPU jos ollaan lopussa
     * @throws NullPointerException jos tiedostoa ei ole, ohjelma heittää tällaisen
     */
    
    public String lueTiedostoSanaKerrallaan() {
        if (lukija.hasNext()){
            return lukija.next();
        } else {
            return "TIEDOSTON LOPPU";
        }
    }
    
    /**
     * Metodi lukee tiedostoa, yksi rivi per jokainen kutsu
     * @return palauttaa tiedostosta luetun rivin stringinä tai TIEDOSTON LOPPU jos ollaan lopussa
     * @throws NullPointerException jos tiedostoa ei ole, ohjelma heittää tällaisen
     */
    public String lueTiedostoRiviKerrallaan() {
        if (lukija.hasNextLine()){
            return lukija.nextLine();
        } else {
            return "TIEDOSTON LOPPU";
        }
    }
    
    public File getFile(){
        return tiedosto;
    }
    
    /**
     * Metodi asettaa luokalle tiedoston, jonka nimen se saa. Jos tiedostoa ei ole, se antaa "hienon" virheilmoituksen
     * @param nimi halutun tiedoston nimi
     * @return totuusarvo siitä, onnistuiko tiedoston asettaminen
     */
    public boolean setFile(String nimi){
        try {
            tiedosto = new File(nimi);
            lukija = new Scanner(tiedosto);
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy!");
            return false;
        }
        return true;
    }
    
    public boolean setFile(File tied){
        try {
            tiedosto = tied;
            lukija = new Scanner(tiedosto);
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy!"); //tähän fiksumpi virheilmoitus joskus
            return false;
        }
        return true;
    }
    
    public void luoLukija(){
        try {
            lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy!");
        }
    }
    
    public PrintWriter luoKirjoitin(){
        try {
            PrintWriter kirjoitin = new PrintWriter(tiedosto);
            return kirjoitin;
        } catch (Exception e) {
            System.out.println("Tiedostoa ei löydy!");
        }
        return null;
    }
}
