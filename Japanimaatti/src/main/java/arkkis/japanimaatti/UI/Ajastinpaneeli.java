/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Kulmala
 */
public class Ajastinpaneeli extends JPanel{
    
    public Ajastinpaneeli(){
        JButton testinappi = new JButton("ajastinmaatti");
        GridLayout layout = new GridLayout(4, 1);
        this.add(testinappi);
    }
}
