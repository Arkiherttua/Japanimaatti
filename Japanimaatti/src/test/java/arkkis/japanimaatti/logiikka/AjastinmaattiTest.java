/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arkkis.japanimaatti.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * No eipähän tästä saanut testattua juuri mitään ilman, että olisi eeppisesti
 * säätänyt ajastimen tiedot -luokan kanssa saman aikaisesti.
 * 
 */
public class AjastinmaattiTest {
    private Ajastinmaatti ajastin;
    
    public AjastinmaattiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ajastin = new Ajastinmaatti();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void aluksiAjastustenSummaOnNollaJaGetAjastustenKestoToimii() {
        assertTrue(0 == ajastin.getAjastustenKesto());
    }

}
