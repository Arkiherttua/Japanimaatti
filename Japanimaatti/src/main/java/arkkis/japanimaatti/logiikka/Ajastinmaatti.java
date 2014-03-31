/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.logiikka;

import arkkis.japanimaatti.UI.GraafinenUI;
import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.tallennus.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kulmala
 */
public class Ajastinmaatti {
    private TekstiUI UI;
    private GraafinenUI GUI;
    private AjastimenTiedot ajastimenTiedot;
    
    public Ajastinmaatti(){
        ajastimenTiedot = new AjastimenTiedot();
        ajastimenTiedot.setFile();
        ajastimenTiedot.LueTiedotTiedostosta();
    }
    
    public void setUI(TekstiUI UI){ //jossain vaiheessa ui:sta tullee rajapinta ei luokka
        this.UI = UI;
    }
    
    public void setGUI(GraafinenUI GUI){
        this.GUI = GUI;
    }
    
    public String opiskellutAsiat(){
        return ajastimenTiedot.getOpiskellutAiheet();
    }
    
    public String getOpiskellutAsiatJaOpiskelunKesto(){
        return ajastimenTiedot.getOpiskellutAsiatJaOpiskelunKesto();
    }
    
    public void lisaaUusiOpiskelu(String opiskeltava, int minuuttia){
        ajastimenTiedot.lisaaUusiOpiskelu(opiskeltava, minuuttia);
    }
    
    public String tulostaTiedot(){
        return ajastimenTiedot.getOpiskellutAsiatJaOpiskelunKesto();
    }
    
    public void tallennaTiedot(){
        ajastimenTiedot.tallennaTiedostoon();
    }
    
    public void ajasta(int aika){
        Timer ajastin = new Timer(true);
        
        ajastin.schedule(
            new TimerTask() {
                public void run() {   
                    //UI.ajastusOhi();
                    GUI.getAjastinpaneeli().ajastusOhi(); //tää kaataa ohjelman koska??
                }
            }, aika * 1000); //aika oletetaan siis annettavaksi sekunteina nyt testivaiheessa
        
//        ajastin.scheduleAtFixedRate(
//            new TimerTask() {
//                public void run() { 
//                    //jotain fiksua tähän
//                }
//            }, 0, aika * 60 * 1000); 
//        ajastin.cancel();
        
        
    }
    
}
