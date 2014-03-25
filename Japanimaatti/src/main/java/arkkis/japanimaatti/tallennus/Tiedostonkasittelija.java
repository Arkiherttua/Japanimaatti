
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class Tiedostonkasittelija {
    File tiedosto;
    Scanner lukija;
    
    
    public Tiedostonkasittelija(){
        
    }
    
    public void tallennaTiedostoon(String talletettava){
        try {
            PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.print(talletettava);
            kirjoitin.close();
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy, ei talletettu");
        }
        
        
    }
    
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
