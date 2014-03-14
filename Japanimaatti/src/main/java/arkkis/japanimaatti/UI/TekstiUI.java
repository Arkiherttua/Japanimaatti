/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package arkkis.japanimaatti.UI;
import java.util.Scanner;
/**
 *
 * @author Kulmala
 */
public class TekstiUI {
    Scanner lukija;
    
    public TekstiUI(){
        lukija = new Scanner(System.in);
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
            System.out.println("Ei vielä tuettu");
        } else if (syote.equals("exit")){
            return;
        } else {
            System.out.println("Komentoa ei tunnistettu");
        }
        menu();
        
    }
}
