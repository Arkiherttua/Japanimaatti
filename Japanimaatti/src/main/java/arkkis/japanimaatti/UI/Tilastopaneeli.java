/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * Tilastopaneeli muodostaa yhden 'kortin' käyttöliittymään, kortin, joka on näkyvissä kun käyttäjä katselee tilastoja
 */
public class Tilastopaneeli extends JPanel{
    private GraafinenUI ui;
    private JTextArea opiskellutAsiat;
    private JTextArea muutTilastot;
    private int kauankoAjastettu = 0;
    
    public Tilastopaneeli(GraafinenUI ui){
        this.ui = ui;
        
        GridLayout layout = new GridLayout(3,1);
        this.setLayout(layout);
        
        JTextArea otsikko = new JTextArea("Tässä tilastoja opiskelustasi");
        otsikko.setEditable(false);
        luoTekstikentat();
          
        this.add(otsikko);
        this.add(opiskellutAsiat);
        this.add(muutTilastot);
    }
    
    private void luoTekstikentat(){
        opiskellutAsiat = new JTextArea(ui.getAjastinmaatti().getOpiskellutAsiatJaOpiskelunKesto());
        opiskellutAsiat.setEditable(false);
        
        muutTilastot = new JTextArea("Ajastusten kesto yhteensä (ohjelman tällä käyttökerralla)\n" + 
                kauankoAjastettu + " minuuttia");
        muutTilastot.setEditable(false);
    }
    
    public void paivita(int ajastuksenKesto){
        opiskellutAsiat.setText(ui.getAjastinmaatti().getOpiskellutAsiatJaOpiskelunKesto());
        kauankoAjastettu += ajastuksenKesto;
        muutTilastot.setText("Ajastusten kesto yhteensä (ohjelman tällä käyttökerralla)\n" + 
                kauankoAjastettu + " minuuttia");
    }
}
