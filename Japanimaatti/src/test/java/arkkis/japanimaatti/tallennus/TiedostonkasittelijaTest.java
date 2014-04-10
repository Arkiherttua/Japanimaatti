/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kulmala
 */
public class TiedostonkasittelijaTest {
    File tiedosto;
    
    public TiedostonkasittelijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            tiedosto = new File("JapanimaatinTiedostot/testi.txt");
            PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.print("");
            kirjoitin.close();
            //lukija = new Scanner(tiedosto);
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy");
        }
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void setFileToimiiKunTiedostoOlemassa() {
        Tiedostonkasittelija k = new Tiedostonkasittelija();
        k.setFile("JapanimaatinTiedostot/testi.txt");
        assertEquals(tiedosto, k.getFile());
    }
    
    @Test
    public void setFileToimiiKunTiedostoEiOlemassa() {
        Tiedostonkasittelija k = new Tiedostonkasittelija();
        File nulltiedosto = null;
        assertFalse(k.setFile(nulltiedosto));
    }
    
     @Test
    public void setFileStringparametrillaToimiiKunTiedostoEiOlemassa() {
        Tiedostonkasittelija k = new Tiedostonkasittelija();
        String eiOleTiedostonNimi = "blaablaablaa";
        assertFalse(k.setFile(eiOleTiedostonNimi));
    }
    
//    @Test
//    public void tiedostoonTallentaminenToimii() {
//        Tiedostonkasittelija k = new Tiedostonkasittelija();
//        k.setFile("JapanimaatinTiedostot/testi.txt");
//        k.tallennaTiedostoon("testi1");
//    }
    
    public File alustaTyhjaTestitiedosto(){ //asettaa testiluokalle tiedoston ja tyhjentää sen
       
        try {
            File tiedosto = new File("JapanimaatinTiedostot/testi.txt");
            PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.print("");
            kirjoitin.close();
            return tiedosto;
            //lukija = new Scanner(tiedosto);
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy");
        }
        
        return null;
        
    }
}
