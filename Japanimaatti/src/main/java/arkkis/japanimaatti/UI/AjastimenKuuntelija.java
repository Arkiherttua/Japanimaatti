/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * nomen est omen, ajastimen kuuntelija kuuntelee ajastimeen liittyviä nappeja
 */
public class AjastimenKuuntelija implements ActionListener{
    Ajastinpaneeli ajastinpaneeli;
    JTextArea minuuttimaara;
    JButton ok;
    
    public AjastimenKuuntelija(Ajastinpaneeli ajastin, JTextArea minuuttimaara, JButton ok){
        this.ajastinpaneeli = ajastin;
        this.minuuttimaara = minuuttimaara;
        this.ok = ok;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ok)){
            ajastinpaneeli.setAjastus();
        }
    }
    
}
