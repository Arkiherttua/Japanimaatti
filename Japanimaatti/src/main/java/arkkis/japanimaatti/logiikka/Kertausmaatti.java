/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.TekstiUI;
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
    private TekstiUI ui;
    
    public Kertausmaatti(){
        kasittelija = new Tiedostonkasittelija();
        kasittelija.setFile("JapanimaatinTiedostot/kanjit.txt"); //kovakoodausta...
        tunnisteet = new ArrayList();
    }
    
    public void setTiedosto(String tiedostonNimi){
        kasittelija.setFile(tiedostonNimi);
    }
    
    public void setUI(TekstiUI ui){
        this.ui = ui;
    }
    

    
    public void kertaaKaikki(){ //joo ei toimi atm, ei tee mitään koska??
        //tämä paranee vielä paljon tämä ominaisuus...
        Scanner lukija = luoLukija();
        while (lukija.hasNextLine()){
            String rivi = lukija.nextLine();
            String[] rivinSanat = rivi.split(" ");
            ui.tulostaJaVaadiEnter(rivinSanat[0]);
            ui.tulostaJaVaadiEnter(rivinSanat[1]);
            ui.tulostaJaVaadiEnter(rivinSanat[2]);
        }
    }
    
    public void seuraavaKerrattava(){
        
    }
    
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
    
    public void valitseKerrattavat(){
        //tähän hieno metodi jossain vaiheessa
    }
}
