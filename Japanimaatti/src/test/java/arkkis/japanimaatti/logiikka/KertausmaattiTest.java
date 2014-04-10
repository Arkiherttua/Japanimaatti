/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.tallennus.AjastimenTiedot;
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
            kirjoitin.print(""); //tyhjennetään tiedosto edellisten testien tms jäljiltä
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
    public void toimiikoTiedostonOikeanmuotoisuudenTarkitusKunOnOikeanmuotoinen() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        
        assertTrue(kertain.onkoTiedostoOikeanmuotoinen(tiedosto));
    }
    
    @Test
    public void toimiikoTiedostonOikeanmuotoisuudenTarkitusKunEiOleOikeanmuotoinen() {
        kirjoitaFeilaavaSisaltoTiedostolle();
        assertFalse(kertain.onkoTiedostoOikeanmuotoinen(tiedosto));
    }

    @Test
    public void tunnisteidenHakuToimiiKunTiedostoLooginen() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        
        assertEquals("tunniste2 tunniste1 ", kertain.getTunnisteet());
    }
    
    @Test
    public void haeKerrattavatToimiiKunKaikkiHyvin() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        ArrayList<String[]> kerrattavat = kertain.getKerrattavat();
        
        assertEquals("duck [dak] ankka tunniste2", kerrattavat.get(0)[0] + " " + kerrattavat.get(0)[1] + " " + kerrattavat.get(0)[2] + " " + kerrattavat.get(0)[3]);
    }
    
    
    @Test
    public void kertausmaatinTilaAluksiTyhja() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        
        assertEquals(KertausmaatinTila.TYHJA, kertain.getKertauksenTila());
    }
    
    @Test
    public void tilaOikeaKunMerkkiNakyvilla() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        kertain.annaSeuraava();
        assertEquals(KertausmaatinTila.KANJI, kertain.getKertauksenTila());
    }
    
    @Test
    public void tilaOikeaKunAantaminenNakyvilla() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        kertain.annaSeuraava();
        kertain.annaSeuraava();
        assertEquals(KertausmaatinTila.KANA, kertain.getKertauksenTila());
    }
    
    @Test
    public void tilaOikeaKunSuomennosNakyvilla() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        kertain.annaSeuraava();
        kertain.annaSeuraava();
        kertain.annaSeuraava();
        assertEquals(KertausmaatinTila.SUOMI, kertain.getKertauksenTila());
    }
    
    @Test
    public void annaSeuraavaToimiiOsa1() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        String ekaKerrattava = kertain.annaSeuraava();
        assertEquals("duck", ekaKerrattava);
    }
    
    @Test
    public void annaSeuraavaToimiiOsa2() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        kertain.annaSeuraava();
        String tokaKerrattava = kertain.annaSeuraava();
        assertEquals("[dak]", tokaKerrattava);
    }
    
    @Test
    public void annaSeuraavaToimiiOsa3() {
        kirjoitaOikeanmuotoinenSisaltoTiedostolle();
        kertain.haeTunnisteet();
        kertain.haeKerrattavat("tunniste2");
        kertain.annaSeuraava();
        kertain.annaSeuraava();
        String kolmasKerrattava = kertain.annaSeuraava();
        assertEquals("[dak] ankka", kolmasKerrattava);
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
    
    public void kirjoitaFeilaavaSisaltoTiedostolle(){
        try {
        PrintWriter kirjoitin = new PrintWriter(tiedosto);
            kirjoitin.println("tällä rivillä on liian monta sanaa");
            kirjoitin.println("tälläTaasLiianVähän");
            kirjoitin.println("vika rivi kelpaisi:");
            kirjoitin.print("dog [dog] koira eläin");
            kirjoitin.close();
        } catch (Exception e) {
        }
    }
}
