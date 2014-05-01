/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Kulmala
 */
public class Kuuntelija implements ActionListener{
    private JButton kertaus, ajastus, tilastot;
    private GraafinenUI ui;
    
    public Kuuntelija(GraafinenUI ui, JButton kertaus, JButton ajastus, JButton tilastot){
        this.ui = ui;
        this.kertaus = kertaus;
        this.ajastus = ajastus;
        this.tilastot = tilastot;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        suljeEdellinenKortti();
        if (e.getSource().equals(kertaus)){
            ui.kertausmaatti();
        }else if (e.getSource().equals(ajastus)){
            ui.ajastinmaatti();
        } else if (e.getSource().equals(tilastot)){
            ui.tilastomaatti();
        }
    }
    
    /**
     * Metodi tarkistaa, mikä kortti ohjelmalla on näkyvissä, ja tarpeen mukaan
     * kutsuu metodeita, jotka liittyvät kys. kortin sulkemiseen ennen kuin se suljetaan
     */
    public void suljeEdellinenKortti(){
        if (ui.getNakyvaKortti().equals("ajastin")){
            ui.suljeAjastinmaatti();
        } else if (ui.getNakyvaKortti().equals("kertain")){
            ui.suljeKertausmaatti();
        }
    }
    
}
