/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.tallennus.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kulmala
 */
public class Ajastinmaatti {
    TekstiUI UI;
    AjastimenTiedot ajastimenTiedot;
    
    public Ajastinmaatti(){
        ajastimenTiedot = new AjastimenTiedot();
        ajastimenTiedot.setFile();
        ajastimenTiedot.LueTiedotTiedostosta();
    }
    
    public void setUI(TekstiUI UI){ //jossain vaiheessa ui:sta tullee rajapinta ei luokka
        this.UI = UI;
    }
    
    public String opiskellutAsiat(){
        return ajastimenTiedot.getOpiskellutAiheet();
    }
    
    public void lisaaUusiOpiskelu(String opiskeltava, int minuuttia){
        ajastimenTiedot.lisaaUusiOpiskelu(opiskeltava, minuuttia);
    }
    
    public String tulostaTiedot(){
        return ajastimenTiedot.tulostaTiedot();
    }
    
    public void tallennaTiedot(){
        ajastimenTiedot.tallennaTiedostoon();
    }
    
    public void ajasta(int aika){
        Timer ajastin = new Timer(true);
        
        ajastin.schedule(
            new TimerTask() {
                public void run() {   
                    UI.ajastusOhi();
                }
            }, aika * 1000); //aika oletetaan siis annettavaksi sekunteina
        
//        ajastin.scheduleAtFixedRate(
//            new TimerTask() {
//                public void run() { 
//                    //jotain fiksua tähän
//                }
//            }, 0, aika * 60 * 1000); 
//        ajastin.cancel();
        
        
    }
    
}
