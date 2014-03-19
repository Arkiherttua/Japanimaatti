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
        kasittelija.setFile(null); //tähän sen tiedoston nimi!!!
    }
    
    public void LueTiedotTiedostosta(){
        String menossaMappiin = "";
        try {
            menossaMappiin = kasittelija.lueTiedosto();
        } catch (NullPointerException e){
            System.out.println("URPO!"); //tähän jotain fiksua joskus
        }
        
        while (!kasittelija.lueTiedosto().equals("TIEDOSTON LOPPU")){
            try {
                
            } catch (Exception e){
                
            }
        }
    }
}
