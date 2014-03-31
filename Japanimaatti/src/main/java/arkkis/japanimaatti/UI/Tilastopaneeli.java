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
 * @author Kulmala
 */
public class Tilastopaneeli extends JPanel{
    private GraafinenUI ui;
    private JTextArea opiskellutAsiat;
    
    public Tilastopaneeli(GraafinenUI ui){
        this.ui = ui;
        
        GridLayout layout = new GridLayout(3,1);
        this.setLayout(layout);
        
        JTextArea otsikko = new JTextArea("Tässä tilastoja opiskelustasi");
        otsikko.setEditable(false);
        
        opiskellutAsiat = new JTextArea(ui.getAjastinmaatti().getOpiskellutAsiatJaOpiskelunKesto());
        opiskellutAsiat.setEditable(false);
        
        this.add(otsikko);
        this.add(opiskellutAsiat);
    }
    
    public void paivita(){
        opiskellutAsiat.setText(ui.getAjastinmaatti().getOpiskellutAsiatJaOpiskelunKesto());
    }
}
