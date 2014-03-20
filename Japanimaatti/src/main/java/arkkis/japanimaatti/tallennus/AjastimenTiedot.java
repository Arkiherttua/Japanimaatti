/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arkkis.japanimaatti.tallennus;


import java.util.HashMap;
/**
 *
 * @author ainohaav@cs
 */
public class AjastimenTiedot {
    HashMap<String, Integer> opiskelut;
    Tiedostonkasittelija kasittelija;
    
    public AjastimenTiedot(){
        opiskelut = new HashMap<>();
        kasittelija = new Tiedostonkasittelija();
    }
    
    public void setFile(){
        kasittelija.setFile("JapanimaatinTiedostot/ajastintiedosto.txt"); //tähän sen tiedoston nimi!!!
    }
    
    public void setFile(String polku){
        kasittelija.setFile(polku);
    }
    
    public String tulostaTiedot(){
        String palautettava = "Tässä tilastot opiskelustasi: \n";
        for (String avain: opiskelut.keySet()) {
            palautettava += avain + " " + opiskelut.get(avain) + "\n";
        }
        return palautettava;
    }
    
    public void LueTiedotTiedostosta(){
        String menossaMappiin = "";
        try {
            menossaMappiin = kasittelija.lueTiedosto();
        } catch (NullPointerException e){
            System.out.println("URPO!"); //tähän jotain fiksua joskus
        }
        
        String luettu = kasittelija.lueTiedosto();
        
        while (!luettu.equals("TIEDOSTON LOPPU")){
            try {
                int arvo = Integer.parseInt(luettu);
                opiskelut.put(menossaMappiin, arvo);
            } catch (Exception e){
                menossaMappiin = luettu;
            }
            luettu = kasittelija.lueTiedosto();
        }
    }
}
