/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import arkkis.japanimaatti.logiikka.Ajastinmaatti;
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
    private Ajastinmaatti ajastin;
    private JTextArea tekstikentta, minuuttimaara, mitaOpiskelet, opiskellutAsiat;
    private JButton ok;
    
    public Ajastinpaneeli(Ajastinmaatti ajastin, GraafinenUI ui){
        super();
        this.ajastin = ajastin;
        this.ui = ui;
        
        GridLayout layout = new GridLayout(5, 1);
        this.setLayout(layout);
        
        tekstikentta = new JTextArea("Syötä ajastuksen tiedot ja paina OK");
        tekstikentta.setEditable(false);
        
        minuuttimaara = new JTextArea("Montako minuuttia?");
        mitaOpiskelet = new JTextArea("Mitä opiskelet?");
        opiskellutAsiat = new JTextArea("Jo opiskellut asiat:\n" + this.ajastin.opiskellutAsiat());
        opiskellutAsiat.setEditable(false);
        
        ok = new JButton("OK");
        
        AjastimenKuuntelija kuuntelija = new AjastimenKuuntelija(this, minuuttimaara, ok);
        ok.addActionListener(kuuntelija);
        
        this.add(tekstikentta);
        this.add(minuuttimaara);
        this.add(mitaOpiskelet);
        this.add(opiskellutAsiat);
        this.add(ok);
        
    }
    
    public void setAjastus(){
        int minuuttia = 0;
        try {
            minuuttia = Integer.parseInt(minuuttimaara.getText());
            ajastin.ajasta(minuuttia);
            ajastin.lisaaUusiOpiskelu(mitaOpiskelet.getText(), minuuttia);
            ok.setText("Ajastus käynnissä!");
            ok.setEnabled(false);
        } catch (Exception e){
            
            minuuttimaara.setText("Anna minuuttimäärä kokonaislukuna! Pelkkiä numeroita kiitos!");
        }
    }
    
    public void ajastusOhi(){
        tekstikentta.setText("Ajastus ohi ja tiedot tallennettu.\n Voit asettaa uuden ajastuksen vastaavasti kuin asetit edellisen.");
        ok.setText("OK");
        ok.setEnabled(true);
    }
    
    public void paivita(){
        opiskellutAsiat.setText("Jo opiskellut asiat:\n" + this.ajastin.opiskellutAsiat());
        tekstikentta.setText("Syötä ajastuksen tiedot ja paina OK");
        minuuttimaara.setText("Montako minuuttia?");
        mitaOpiskelet.setText("Mitä opiskelet?");
    }
}
