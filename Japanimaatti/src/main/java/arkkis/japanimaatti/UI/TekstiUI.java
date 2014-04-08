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
 * kokeiluversioksi luotu tekstipohjainen käyttöliittymä. Ei enää täysin yhteensopiva ohjelman kanssa.
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
    
    /**
     * Huonosti toteutettu menun tulostus. Menu nimittäin tulostuu mm. samalla kun ajastus laitetaan päälle.
     */
    
    public void menu(){
        System.out.println("Tässä vaihtoehdot, mitä voit tehdä:");
        System.out.println("Kirjoita 1, jos haluat kerrata kanjeja");
        System.out.println("Kirjoita 2, jos haluat käyttää ajastinta");
        System.out.println("Kirjoita exit, jos haluat poistua ohjelmasta");
        System.out.println("Kaikkien komentojen jälkeen paina enteriä.");
        
        String syote = lukija.nextLine();
        
        if (syote.equals("1")){
            kertausmaatti();
        } else if (syote.equals("2")){
            ajastinmaatti();
        } else if (syote.equals("exit")){
            ajastin.tallennaTiedot();
            return;
        } else {
            System.out.println("Komentoa ei tunnistettu");
        }
        menu();
        
    }
    
    /**
     * Kerää käyttäjältä tarpeelliset tiedot, jotta voi kutsua Ajastinaamtin ajastustoimintoa
     */
    public void ajastinmaatti(){
        System.out.println("Kuinka pitkäksi ajaksi haluat ajastaa (sekunteina)?");
        int syote = 0;
        while (true){
            try {
                syote = Integer.parseInt(lukija.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Anna positiivinen kokonaisluku, jooko");
            }

        }
        System.out.println("");
        System.out.println("Mitä opiskelet? Syötä jonkun jo opiskelemasi aiheen nimi tai uusi aihe");
        System.out.println("Jo opiskeltuja asioita: \n" + ajastin.opiskellutAsiat());
        String opiskeltava = lukija.next();
        ajastin.lisaaUusiOpiskelu(opiskeltava, syote); //jossain tulevassa versiossa tämä lisätään vasta ajastuksen jälkeen
        
        ajastin.ajasta(syote);
        System.out.println("");
        System.out.println("Ajastus päällä.");
        System.out.println("");
    }
    
    public void ajastusOhi(){
        System.out.println("Ajastus ohi!");
        tulostaTilastot();
    }
    
    public void tulosta(String tulostettava){
        System.out.println(tulostettava);
    }
    
    public void tulostaJaVaadiEnter(String tulostettava){
        System.out.println(tulostettava);
        String syote = lukija.next();
    }
    
    public void tulostaTilastot(){
        System.out.println(ajastin.tulostaTiedot());
    }

    private void kertausmaatti() {
        System.out.println("Avasit kertausmaatin. Joskus vielä tästä seuraa kaikkea hienoa");
        System.out.println("Toistaiseksi tämä vain pyörittää kovakoodatun tiedoston sisältöä. Kerran.");
        //kertain.kertaaKaikki();
    }
}
