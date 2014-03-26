/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.tallennus.Tiedostonkasittelija;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kulmala
 */
public class Kertausmaatti {
    private Tiedostonkasittelija kasittelija;
    private ArrayList<String> tunnisteet;
    
    public Kertausmaatti(){
        kasittelija = new Tiedostonkasittelija();
        tunnisteet = new ArrayList();
    }
    
    public void setTiedosto(String tiedostonNimi){
        kasittelija.setFile(tiedostonNimi);
    }
    
    public void haeTunnisteet(){
        Scanner lukija = luoLukija();
        while (lukija.hasNextLine()){
            String rivi = lukija.nextLine(); //mene nyt vittu sinne uudelle riville vihaan tiedostoja
            String[] rivinSanat = rivi.split("\t");
            lisaaTunniste(rivinSanat[3]);     //tunniste siis aina neljännessä 'sanassa' rivillä
        }
        
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
    
    public void valitseKerrattavat(){
        
    }
}
