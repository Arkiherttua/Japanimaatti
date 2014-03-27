/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.tallennus.AjastimenTiedot;
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
 * @author ainohaav@cs
 */
public class KertausmaattiTest {
    private File tiedosto;
    private Scanner lukija;
    private Kertausmaatti kertain;
    
    public KertausmaattiTest() {
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
            lukija = new Scanner(tiedosto);
        } catch (Exception e){
            System.out.println("Tiedostoa ei löydy");
        }
        
        kertain = new Kertausmaatti();
        kertain.setTiedosto("JapanimaatinTiedostot/testi.txt");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tunnisteidenHakuToimiiKunTiedostoLooginen() { //ei menen läpi koska netbeans ja arg ja kiire
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        
        assertEquals("tunniste1 tunniste2 ", kertain.getTunnisteet());
        
    }
    
    public void kirjoitaOikeanmuotoinenSisaltoTiedostolle(){
        try {
        PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.println("dog [dog] koira tunniste1");
            kirjoitin.println("cat [kät] kissa tunniste1");
            kirjoitin.println("duck [dak] ankka tunniste2");
            kirjoitin.close();
        } catch (Exception e) {
        }
    }
}
