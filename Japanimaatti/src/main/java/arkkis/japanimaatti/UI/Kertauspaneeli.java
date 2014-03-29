/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Kulmala
 */
public class Kertauspaneeli extends JPanel{
    public Kertauspaneeli(){
        JButton testinappi = new JButton("kertausmaatti");
        GridLayout layout = new GridLayout(4, 1);
        this.add(testinappi);

        
        //contentPane.add(menu, BorderLayout.WEST);
    }
}
