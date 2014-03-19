
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class Tiedostonkasittelija {
    File tiedosto;
    Scanner lukija;
    
    
    public Tiedostonkasittelija(){
        
    }
    
    public String lueTiedosto() throws NullPointerException{

        if (lukija.hasNext()){
            return lukija.next();
        } else {
            return "TIEDOSTON LOPPU";
        }
       
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
