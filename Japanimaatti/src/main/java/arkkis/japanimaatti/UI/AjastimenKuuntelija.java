/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Kulmala
 */
public class AjastimenKuuntelija implements ActionListener{
    Ajastinpaneeli ajastinpaneeli;
    JTextField minuuttimaara;
    JButton ok;
    
    public AjastimenKuuntelija(Ajastinpaneeli ajastin, JTextField minuuttimaara, JButton ok){
        this.ajastinpaneeli = ajastin;
        this.minuuttimaara = minuuttimaara;
        this.ok = ok;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ok)){
            int minuuttia;
            try {
                minuuttia = Integer.parseInt(minuuttimaara.getText());
            } catch (Exception exc){
                minuuttia = 1; //väliaikainen random kovakoodaus :D
            }
            ajastinpaneeli.setAjastus(minuuttia, "testi"); //kovakoodia tässäkin
        }
    }
    
}
