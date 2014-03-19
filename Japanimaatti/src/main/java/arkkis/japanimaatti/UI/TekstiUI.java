/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package arkkis.japanimaatti.UI;
import arkkis.japanimaatti.logiikka.*;
import arkkis.japanimaatti.tallennus.Tiedostonkasittelija;
import java.util.Scanner;
/**
 *
 * @author Kulmala
 */
public class TekstiUI {
    Scanner lukija;
    Ajastinmaatti ajastin;
    Kertausmaatti kertain;
    
    public TekstiUI(Ajastinmaatti a, Kertausmaatti k){
        lukija = new Scanner(System.in);
        ajastin = a;
        kertain = k;
        //a.setKasittelija(new Tiedostonkasittelija());
        
    }
    
    public void alku(){
        
        System.out.println("Käynnistit eeppisen japanimaatin, jee.");
        System.out.println("");
        menu();
        
    }
    
    public void menu(){
        System.out.println("Tässä vaihtoehdot, mitä voit tehdä:");
        System.out.println("Kirjoita 1, jos haluat kerrata kanjeja");
        System.out.println("Kirjoita 2, jos haluat käyttää ajastinta");
        System.out.println("Kirjoita exit, jos haluat poistua ohjelmasta");
        System.out.println("Kaikkien komentojen jälkeen paina enteriä.");
        
        String syote = lukija.nextLine();
        
        if (syote.equals("1")){
            System.out.println("Ei vielä tuettu");
        } else if (syote.equals("2")){
            ajastinmaatti();
        } else if (syote.equals("exit")){
            return;
        } else {
            System.out.println("Komentoa ei tunnistettu");
        }
        menu();
        
    }
    
    public void ajastinmaatti(){
        System.out.println("Kuinka pitkäksi ajaksi haluat ajastaa (minuutteina)?");
        int syote = 0;
        while (true){
            try {
                syote = lukija.nextInt();
                break;
            } catch (Exception e){
                System.out.println("Anna positiivinen kokonaisluku, jooko");
            }
        }
        
        ajastin.ajasta(syote);
        System.out.println("");
        System.out.println("Ajastus päällä.");
        System.out.println("");
    }
    
    public void ajastusOhi(){
        System.out.println("Ajastus ohi!");
    }
}
