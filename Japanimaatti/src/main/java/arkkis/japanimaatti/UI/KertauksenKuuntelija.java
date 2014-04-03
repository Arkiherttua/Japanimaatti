/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * kertauksen kuuntelija kuuntelee kertauksene liittyviä nappeja
 */
public class KertauksenKuuntelija implements ActionListener{
    Kertauspaneeli kertauspaneeli;
    JButton seuraava, osasin, melkein, enOsannut;
    
    public KertauksenKuuntelija(Kertauspaneeli kertauspaneeli, JButton seuraava, JButton osasin, JButton melkein, JButton enOsannut){
        this.kertauspaneeli = kertauspaneeli;
        this.seuraava = seuraava;
        this.osasin = osasin;
        this.melkein = melkein;
        this.enOsannut = enOsannut;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(seuraava)){
            kertauspaneeli.naytaSeuraava();
            //String seuraava = kertauspaneeli.annaMerkki();
        }else if (e.getSource().equals(osasin)){
            
        } else if (e.getSource().equals(melkein)){
            
        } else if (e.getSource().equals(enOsannut)){
            
        }
    }
    
}
