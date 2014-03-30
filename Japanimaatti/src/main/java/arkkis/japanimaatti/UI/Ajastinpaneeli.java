/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Kulmala
 */
public class Ajastinpaneeli extends JPanel{
    private GraafinenUI ui;
    
    public Ajastinpaneeli(GraafinenUI ui){
        this.ui = ui;
        
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        
        JTextArea tekstikentta = new JTextArea("Kuinka pitkäksi aikaa haluat ajastaa? Syötä luku ja paina OK");
        tekstikentta.setEditable(false);
        
        JTextArea minuuttimaara = new JTextArea("Montako minuuttia?");
        
        JButton ok = new JButton("OK");
        
        this.add(tekstikentta);
        this.add(minuuttimaara);
        this.add(ok);
        
    }
    
    public void setAjastus(int minuutit, String opiskelu){
        
    }
}
