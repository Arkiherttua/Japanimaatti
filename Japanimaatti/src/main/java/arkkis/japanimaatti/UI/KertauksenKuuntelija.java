/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import arkkis.japanimaatti.logiikka.OsaamisenTila;
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
    JButton ok, osasin, melkein, enOsannut;
    
    public KertauksenKuuntelija(Kertauspaneeli kertauspaneeli){
        this.kertauspaneeli = kertauspaneeli;
        
    }
    
    public void setNapit(JButton ok, JButton osasin, JButton melkein, JButton enOsannut){
        this.ok = ok;
        this.osasin = osasin;
        this.melkein = melkein;
        this.enOsannut = enOsannut;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ok)){
            valitseOikeaTapahtumaKunOkNappiaPainetaan();
        }else if (e.getSource().equals(osasin)){
            kertauspaneeli.paivitaOsaaminen(OsaamisenTila.OSATTU);
        } else if (e.getSource().equals(melkein)){
            kertauspaneeli.paivitaOsaaminen(OsaamisenTila.MELKEIN);
        } else if (e.getSource().equals(enOsannut)){
            kertauspaneeli.paivitaOsaaminen(OsaamisenTila.EI);
        }
    }
    /**
     * Metodiin on eriytytty if-else -puu, joka valitsee, mitä kertauspaneelin metodia
     * kutsutaan, kun ok-nappia painetaan, sillä ohjelman vaiheesta riippuu, mitä pitää
     * tapahtua.
     */
    private void valitseOikeaTapahtumaKunOkNappiaPainetaan(){
        if (ok.getText().equals("Seuraava")){
            kertauspaneeli.naytaSeuraava();
        } else if (ok.getText().equals("Valitse kerrattavat")){    
            kertauspaneeli.tallennaOsaamistasonTunniste();
            kertauspaneeli.haeKerrattavat();
        } else if (ok.getText().equals("Valitse tunniste")) {
            kertauspaneeli.tallennaKerrattavienTunniste();
            kertauspaneeli.luoKerrattavienValinta();    
        }
    }
    
}
