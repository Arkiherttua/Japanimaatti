/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.tallennus.Tiedostonkasittelija;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kulmala
 */
public class Ajastinmaatti {
    Tiedostonkasittelija kasittelija;
    TekstiUI UI;
    
    public void setUI(TekstiUI UI){ //jossain vaiheessa ui:sta tullee rajapinta ei luokka
        this.UI = UI;
    }
    
    public void setKasittelija(){
        kasittelija = new Tiedostonkasittelija();
    }
    
    public void ajasta(int aika){
        Timer ajastin = new Timer(true);
        
        ajastin.schedule(
            new TimerTask() {
                public void run() {   
                    UI.ajastusOhi();
                }
            }, aika * 60 * 1000); //aika oletetaan siis annettavaksi minuutteina
        
//        ajastin.scheduleAtFixedRate(
//            new TimerTask() {
//                public void run() { 
//                    //jotain fiksua tähän
//                }
//            }, 0, aika * 60 * 1000); 
//        ajastin.cancel();
        
        
    }
    
}
