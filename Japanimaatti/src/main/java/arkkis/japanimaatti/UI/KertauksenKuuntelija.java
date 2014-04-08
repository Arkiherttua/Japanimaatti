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
 * kertauksen kuuntelija kuuntelee kertaukseen liittyviä nappeja
 */
public class KertauksenKuuntelija implements ActionListener{
    Kertauspaneeli kertauspaneeli;
    JButton seuraava, osasin, melkein, enOsannut, tunnistenappi;
    
    public KertauksenKuuntelija(Kertauspaneeli kertauspaneeli){
        this.kertauspaneeli = kertauspaneeli;
        
    }
    
    public void setNapit(JButton seuraava, JButton osasin, JButton melkein, JButton enOsannut, JButton tunnistenappi){
        this.seuraava = seuraava;
        this.osasin = osasin;
        this.melkein = melkein;
        this.enOsannut = enOsannut;
        this.tunnistenappi = tunnistenappi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(seuraava)){
            kertauspaneeli.naytaSeuraava();
            //String seuraava = kertauspaneeli.annaMerkki();
        }else if (e.getSource().equals(osasin)){
            kertauspaneeli.naytaSeuraava(); //näissä väliaikaista copypastea
        } else if (e.getSource().equals(melkein)){
            kertauspaneeli.naytaSeuraava();
        } else if (e.getSource().equals(enOsannut)){
            kertauspaneeli.naytaSeuraava();
        } else if (e.getSource().equals(tunnistenappi)){
            kertauspaneeli.haeKerrattavat();
        }
    }
    
}
