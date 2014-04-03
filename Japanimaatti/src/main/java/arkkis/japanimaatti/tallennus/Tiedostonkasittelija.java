
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * Luokka huolehtii tiedostoon kirjoittamisesta ja tiedoston lukemisesta, ainoa luokka jolla todella on File
 */

public class Tiedostonkasittelija {
    File tiedosto;
    Scanner lukija;
    
    
    public Tiedostonkasittelija(){
        
    }
    
    /**
     * Metodi tallentaa annetun stringin tiedostoon, joka luokalla on luokkamuuttujana
     * @param talletettava teksti, joka halutaan tallentaa
     */
    public void tallennaTiedostoon(String talletettava){
        try {
            PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.print(talletettava);
            kirjoitin.close();
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy, ei talletettu");
        }
        
        
    }
    
    /**
     * Metodi lukee tiedostoa, yksi sana per jokainen kutsu
     * @return palauttaa tiedostosta luetun sanan tai TIEDOSTON LOPPU jos ollaan lopussa
     * @throws NullPointerException jos tiedostoa ei ole, ohjelma heittää tällaisen
     */
    
    public String lueTiedosto() throws NullPointerException{

        if (lukija.hasNext()){
            return lukija.next();
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
}
