/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.GraafinenUI;
import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.tallennus.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Ajastinmaatti hoitaa ajastuksen, ja kutsuu AjastimenTiedot-luokan tallennus- yms. metodeita
 *
 */
public class Ajastinmaatti {
    private TekstiUI UI;
    private GraafinenUI GUI;
    private AjastimenTiedot ajastimenTiedot;
    private int ajastustenKesto = 0; //ohjelman ajon aikaisten kaikkien ajastusten kestojen summa
    
    public Ajastinmaatti(){
        ajastimenTiedot = new AjastimenTiedot();
        ajastimenTiedot.setFile();
        ajastimenTiedot.LueTiedotTiedostosta();
    }
    
    public void setUI(TekstiUI UI){
        this.UI = UI;
    }
    
    public void setGUI(GraafinenUI GUI){
        this.GUI = GUI;
    }
    /**
     * Metodi kutsuu ajastimen tietojen metodia, joka palauttaa aakkostettuna
     * listana opiskellut asiat ja tekee listasta tulostuskelpoisen stringin.
     * @return string, jossa kaikki aiheet välilyönnillä eroteltuna
     */
    public String getOpiskellutAsiat(){
        String palautettava = "";
        ArrayList<String> opiskellut = ajastimenTiedot.getOpiskellutAiheet();
        for (String opiskeltu : opiskellut) {
            palautettava += opiskeltu + " ";
        }
        return palautettava.substring(0, palautettava.length()-1); //poistaa välilyönnin lopusta
    }
    
    /**
     * Metodi kutsuu ajastimen tietojen metodia, joka palauttaa aakkostettuna
     * listana opiskellut asiat opiskelun keston kera ja tekee listasta tulostuskelpoisen stringin.
     * @return string-muotoinen lista jossa opiskelut kestoineen enterillä eroteltuna
     */
    public String getOpiskellutAsiatJaOpiskelunKesto(){
        String palautettava = "";
        ArrayList<String> opiskellut = ajastimenTiedot.getOpiskellutAsiatJaOpiskelunKesto();
        for (String opiskeltu : opiskellut) {
            palautettava += opiskeltu + "\n";
        }
        return palautettava.substring(0, palautettava.length()-1);
    }
    
    public void lisaaUusiOpiskelu(String opiskeltava, int minuuttia){
        ajastimenTiedot.lisaaUusiOpiskelu(opiskeltava, minuuttia);
    }
    
    public void tallennaTiedot(){
        ajastimenTiedot.tallennaTiedostoon();
    }
    
    /**
     * Metodi toteuttaa varsinaisen ajastuksen javan Timer-luokkaa käyttäen
     * @param aika käyttäjän antama aika ajastukselle. Lopullisessa versiossa minuutteja.
     */
    public void ajasta(int aika){
        final int ajastuksenKesto = aika;
        Timer ajastin = new Timer(true);
        ajastin.schedule(
            new TimerTask() {
                public void run() {
                    GUI.getAjastinpaneeli().ajastusOhi();
                    ajastustenKesto += ajastuksenKesto;
                }
            }, aika * 60 * 1000); //aika oletetaan siis annettavaksi minuutteina
    }
    
    public int getAjastustenKesto(){
        return ajastustenKesto;
    }
    
}
