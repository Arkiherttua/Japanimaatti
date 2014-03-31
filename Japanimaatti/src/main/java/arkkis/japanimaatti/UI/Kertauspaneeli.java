/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import arkkis.japanimaatti.logiikka.Kertausmaatti;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Kulmala
 */
public class Kertauspaneeli extends JPanel{
    private int fonttikoko;
    private GraafinenUI ui;
    private JPanel cards;
    private Kertausmaatti kertain;
    
    public Kertauspaneeli(GraafinenUI ui){
        super();
        this.ui = ui;
        fonttikoko = 24; //joskus vielä tällä tehdään jotain
        
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);
        
        //BorderLayout layout = new BorderLayout();
        JTextArea ekaTekstikentta = new JTextArea("Ruutu 1");
        ekaTekstikentta.setEditable(false);
        JTextArea tokaTekstikentta = new JTextArea("Ruutu 2");
        tokaTekstikentta.setEditable(false);
        GridLayout tekstiLayout = new GridLayout(1, 2);
        
        JPanel tekstikentat = new JPanel(tekstiLayout);
        tekstikentat.add(ekaTekstikentta);
        tekstikentat.add(tokaTekstikentta);
        
        this.cards = new JPanel(new CardLayout());
        
        JButton seuraava = new JButton("seuraava");
        cards.add(seuraava);
        
        JButton osasin = new JButton("osasin");
        JButton melkein = new JButton("melkein osasin");
        JButton enOsannut = new JButton("en vielä osannut");
        GridLayout nappilayout = new GridLayout(1, 3);
        JPanel osaamisnapit = new JPanel(nappilayout);
        osaamisnapit.add(osasin);
        osaamisnapit.add(melkein);
        osaamisnapit.add(enOsannut);
        
        KertauksenKuuntelija kuuntelija = new KertauksenKuuntelija(this, seuraava, osasin, melkein, enOsannut);
        seuraava.addActionListener(kuuntelija);
        osasin.addActionListener(kuuntelija);
        melkein.addActionListener(kuuntelija);
        enOsannut.addActionListener(kuuntelija);
        
        cards.add(osaamisnapit);
        
        this.add(tekstikentat);
        this.add(cards);
        
    }
    
    public void kertausmaatti(){
        hankiTiedosto();
    }
    
    public void seuraava(){
        kertain.seuraavaKerrattava();
    }
    
    
    private File hankiTiedosto(){
        //String alkuPolku = System.getProperty("user.home") +"\\Documents\\GitHub\\japanimaatti";
        JFileChooser valitsija = new JFileChooser();
        int valinta = valitsija.showOpenDialog(ui.getFrame());
        if (valinta==JFileChooser.APPROVE_OPTION){
            return valitsija.getSelectedFile();
        }
        return null;
    }
}
