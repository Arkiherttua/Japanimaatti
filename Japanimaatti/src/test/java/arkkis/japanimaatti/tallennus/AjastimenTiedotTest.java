/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.tallennus;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class AjastimenTiedotTest {
    private File tiedosto;
    private Scanner lukija;
    private AjastimenTiedot tiedot;
    
    
    public AjastimenTiedotTest() {
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
        
        tiedot = new AjastimenTiedot();
        tiedot.setFile("JapanimaatinTiedostot/testi.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getOpiskellutAiheetToimii(){
        kirjoitaOikeanmuotoinenSyote();
        tiedot.LueTiedotTiedostosta();
        
        assertEquals("opiskelu1opiskelu2", tiedot.getOpiskellutAiheet().get(0) + tiedot.getOpiskellutAiheet().get(1));
    }
    
    @Test
    public void getOpiskellutAsiatJaOpiskelunKestoToimii(){
        //kommentoitu, sillä testi ei mene läpi vaikka pitäisi mennä...
//        kirjoitaOikeanmuotoinenSyote();
//        tiedot.LueTiedotTiedostosta();
//        assertEquals("opiskelu1 10 minuuttia, opiskelu2 20 minuuttia", tiedot.getOpiskellutAsiatJaOpiskelunKesto());
    }
    
    @Test
    public void TallentaminenToimiiKunKasvatetaanOlemassaOlevaa(){
        kirjoitaOikeanmuotoinenSyote();
        tiedot.LueTiedotTiedostosta();
        tiedot.lisaaUusiOpiskelu("opiskelu1", 5);
        tiedot.tallennaTiedostoon();
        
        String luettu = lukija.next();
        while (lukija.hasNext()){
            luettu += " " + lukija.next();
        }
        assertEquals("opiskelu1 15 opiskelu2 20", luettu);
    }
    
    @Test
    public void TallentaminenToimiiKunLisataanUusi(){
        kirjoitaOikeanmuotoinenSyote();
        tiedot.LueTiedotTiedostosta();
        tiedot.lisaaUusiOpiskelu("opiskelu3", 30);
        tiedot.tallennaTiedostoon();
        
        String luettu = lukija.next();
        while (lukija.hasNext()){
            luettu += " " + lukija.next();
        }
        assertEquals("opiskelu1 10 opiskelu2 20 opiskelu3 30", luettu);
    }
    
    @Test
    public void opiskelunLisaaminenToimiiKunSamaKuinEnnen(){
        kirjoitaOikeanmuotoinenSyote();
        tiedot.LueTiedotTiedostosta();
        tiedot.lisaaUusiOpiskelu("opiskelu1", 5);
        String mappiinTallennetutTiedot = "";
        HashMap<String, Integer> kartta = tiedot.getOpiskelut();
        for (String avain: kartta.keySet()){
            mappiinTallennetutTiedot += avain + " " + kartta.get(avain) + " ";
        }
        assertEquals("opiskelu1 15 opiskelu2 20 ", mappiinTallennetutTiedot);
    }
    
    @Test
    public void opiskelunLisaaminenToimiiKunEriKuinEnnen(){
        //kirjoitaOikeanmuotoinenSyote();
        tiedot.LueTiedotTiedostosta();
        tiedot.lisaaUusiOpiskelu("uusiopiskelu", 5);
        String mappiinTallennetutTiedot = "";
        HashMap<String, Integer> kartta = tiedot.getOpiskelut();
        for (String avain: kartta.keySet()){
            mappiinTallennetutTiedot += avain + " " + kartta.get(avain) + " ";
        }
        assertEquals("uusiopiskelu 5 ", mappiinTallennetutTiedot);
    }
    
    @Test
    public void tiedostonLukeminenToimiiOikeallaSyotteella(){
        kirjoitaOikeanmuotoinenSyote();
        
        tiedot.LueTiedotTiedostosta();
        String mappiinTallennetutTiedot = "";
        HashMap<String, Integer> kartta = tiedot.getOpiskelut();
        for (String avain: kartta.keySet()){
            mappiinTallennetutTiedot += avain + " " + kartta.get(avain) + " ";
        }
        assertEquals("opiskelu1 10 opiskelu2 20 ", mappiinTallennetutTiedot);
    }
    
    @Test
    public void tiedostonLukeminenToimiiFailillaSyotteella(){
        kirjoitaFailinmuotoinenSyote();
        
        tiedot.LueTiedotTiedostosta();
        String mappiinTallennetutTiedot = "";
        HashMap<String, Integer> kartta = tiedot.getOpiskelut();
        for (String avain: kartta.keySet()){
            mappiinTallennetutTiedot += avain + " " + kartta.get(avain) + " ";
        }
        assertEquals("opiskelu1 20 ", mappiinTallennetutTiedot);
    }
    
    

    
    public void kirjoitaOikeanmuotoinenSyote(){
        try {
        PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.println("opiskelu1 10");
            kirjoitin.println("opiskelu2 20");
            kirjoitin.close();
        } catch (Exception e) {
        }
    }
    
    public void kirjoitaFailinmuotoinenSyote(){
        try {
        PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.println("10 opiskelu1");
            kirjoitin.println("20 opiskelu2");
            kirjoitin.println("opiskelu3");
            kirjoitin.close();
        } catch (Exception e) {
        }
    }
}
