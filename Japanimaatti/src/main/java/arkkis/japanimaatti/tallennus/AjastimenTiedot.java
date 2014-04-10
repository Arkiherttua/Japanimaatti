/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arkkis.japanimaatti.tallennus;


import java.util.HashMap;
/**
 *
 * Luokka pitää huolta ajastimeen liittyvistä tiedoista, niiden tallentamisesta ja niiden lukemisesta
 */
public class AjastimenTiedot {
    private HashMap<String, Integer> opiskelut;
    private Tiedostonkasittelija kasittelija;
    
    public AjastimenTiedot(){
        opiskelut = new HashMap<String, Integer>();
        kasittelija = new Tiedostonkasittelija();
    }
    
    public void setFile(){
        kasittelija.setFile("JapanimaatinTiedostot/ajastintiedosto.txt"); //tähän sen tiedoston nimi!!!
    }
    
    public void setFile(String polku){
        kasittelija.setFile(polku);
    }
    
    public HashMap getOpiskelut(){
        return this.opiskelut;
    }
    
    public Tiedostonkasittelija getTiedostonkasittelija(){
        return kasittelija;
    }
    
    /**
     * Metodi käy läpi hashmapin jossa kaikki tiedot ovat ohjelman juoksemisen aikana ja luo niistä merkkijonon
     * @return opiskellut asiat ja opiskelujen kestot
     */
    public String getOpiskellutAsiatJaOpiskelunKesto(){
        String palautettava = "";
        palautettava += "Tässä tilastot opiskelustasi: \n";
        for (String avain: opiskelut.keySet()) {
            palautettava += avain + " " + opiskelut.get(avain) + " minuuttia\n";
        }
        return palautettava;
    }
    /**
     * Metodi käy läpi hashmapin, jossa kaikki tiedot ovat ohjelman juoksemisen aikana ja listaa opiskellut aiheet
     * @return string-muotoinen lista opiskelluista asioista
     */
    
    public String getOpiskellutAiheet(){
        String palautettava = "";
        for (String avain : opiskelut.keySet()) {
            palautettava += avain + " ";
        }
        //palautettava = palautettava.substring(0, palautettava.length()-3);
        return palautettava;
    }
    
    /**
     * Metodi lisää ohjelman päälläolon aikaiseen tietorakenteeseen (hashmappiin) tietyn pituisen opiskelun tiettyä aihetta
     * @param opiskeltava mitä on opiskeltu
     * @param minuuttia kuinka kauan opiskelu on kestänyt
     */
    
    public void lisaaUusiOpiskelu(String opiskeltava, int minuuttia){
        opiskeltava = opiskeltava.toLowerCase(); // varmistetaan ettei esim. caps lock haittaa
            if (opiskelut.containsKey(opiskeltava)){
                minuuttia += opiskelut.get(opiskeltava);
            }
            opiskelut.put(opiskeltava, minuuttia);
        
    }
    
    /**
     * Kun ohjelma käynnistetään, luetaan tiedostosta tiedot hashmappiin, josta niitä ohjelman päällaolon aikana käytetään
     */
    
    public void LueTiedotTiedostosta(){
        String menossaMappiin = "";
        try {
            menossaMappiin = kasittelija.lueTiedostoSanaKerrallaan();
        } catch (NullPointerException e){
            System.out.println("URPO!"); //tähän jotain fiksua joskus
        }
        
        String luettu = kasittelija.lueTiedostoSanaKerrallaan();
        
        while (!luettu.equals("TIEDOSTON LOPPU")){
            try {
                int arvo = Integer.parseInt(luettu);
                opiskelut.put(menossaMappiin, arvo);
            } catch (Exception e){
                menossaMappiin = luettu;
            }
            luettu = kasittelija.lueTiedostoSanaKerrallaan();
        }
    }
    
    /**
     * Kun ohjelma suljetaan, tallennetaan tällä metodilla hashmappiin säilötyt tiedot tiedostoon.
     */
    
    public void tallennaTiedostoon(){
        String palautettava = "";
        for (String avain: opiskelut.keySet()) {
            palautettava += avain + " " + opiskelut.get(avain) + " ";
        }
        kasittelija.tallennaTiedostoon(palautettava);
    }
}
