/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import arkkis.japanimaatti.logiikka.KertausmaatinTila;
import static arkkis.japanimaatti.logiikka.KertausmaatinTila.*;
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
    private JTextArea ekaTekstikentta;
    private JTextArea tokaTekstikentta;
    
    public Kertauspaneeli(Kertausmaatti kertain, GraafinenUI ui){
        //super();
        this.ui = ui;
        fonttikoko = 24; //joskus vielä tällä tehdään jotain
        this.kertain = kertain;
        kertain.setKertauspaneeli(this);
        
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);
        
        //BorderLayout layout = new BorderLayout();
        ekaTekstikentta = new JTextArea("Ruutu 1");
        ekaTekstikentta.setEditable(false);
        tokaTekstikentta = new JTextArea("Ruutu 2");
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
    
    public void naytaSeuraava(){
        String naytettava = kertain.annaSeuraava();
        if (kertain.getTila() == KertausmaatinTila.TYHJA){ //jos ollaan alussa, näytetään ekassa tekstikentässä, muuten tokassa
            ekaTekstikentta.setText(naytettava);
        } else {
            tokaTekstikentta.setText(naytettava);
        }
        //kertain.seuraavaKerrattava();
    }
    
    public void naytaTekstiaEkassaKentassa(String naytettava){
        ekaTekstikentta.setText(naytettava);
    }
    
    public void naytaTekstiaTokassaKentassa(String naytettava){
        tokaTekstikentta.setText(naytettava);
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
