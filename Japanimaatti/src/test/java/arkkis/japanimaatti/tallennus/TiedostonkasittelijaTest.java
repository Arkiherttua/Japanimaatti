/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    Tiedostonkasittelija kasittelija;
    
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
            alustaTyhjaTestitiedosto();
            kasittelija = new Tiedostonkasittelija();
            kasittelija.setFile(tiedosto);
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy setUpissa");
        }
    }
    
    @After
    public void tearDown() {
    }

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
    
    @Test
    public void tiedostoonTallentaminenToimii() {
        kasittelija.tallennaTiedostoon("testi1");
        String a = "";
        try {
            Scanner lukija = new Scanner(tiedosto);
            a = lukija.nextLine();
        } catch (Exception e){
            a = "Tiedostoa ei löydy!";
        }
        assertEquals("testi1", a);
    }
    
    @Test
    public void tiedostonMuokkaaminenToimii() {
        kasittelija.setFile(tiedosto);
        kasittelija.tallennaTiedostoon("testi1\ttesti2\ttesti3\ttesti4\ttesti5");
        String a = "";
        ArrayList<String[]> lista = new ArrayList<>();
        lista.add(new String[]{"testi1", "testi2", "testi3", "testi4", "muokattuTesti5"});
        try {
            kasittelija.muokkaaTiedostonTiettyjaRiveja(lista);
            Scanner lukija = new Scanner(tiedosto);
            a += lukija.nextLine();
        } catch (Exception e){
            a += "Tiedostoa ei löydy";
        }
        assertEquals("testi1\ttesti2\ttesti3\ttesti4\tmuokattuTesti5", a);
    }

    
    public File alustaTyhjaTestitiedosto(){ //asettaa testiluokalle tiedoston ja tyhjentää sen
       
        try {
            tiedosto = new File("JapanimaatinTiedostot/testi.txt");
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
