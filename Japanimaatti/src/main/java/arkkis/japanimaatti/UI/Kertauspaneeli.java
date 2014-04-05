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
 * Kertauspaneeli muodostaa yhden 'kortin' käyttöliittymään, kortin joka on näkyvissä kun käyttäjä käyttää kertaustoimintoa
 * 
 */
public class Kertauspaneeli extends JPanel{
    private int fonttikoko;
    private GraafinenUI ui;
    private JPanel nappikortit;
    private JPanel tekstikortit;
    private Kertausmaatti kertain;
    private JTextArea ekaTekstikentta;
    private JTextArea tokaTekstikentta;
    private JTextArea tunnistelista;
    private JTextArea tunnistekentta;
    private KertauksenKuuntelija kuuntelija;
    
    public Kertauspaneeli(Kertausmaatti kertain, GraafinenUI ui){
        this.ui = ui;
        fonttikoko = 24; //joskus vielä tällä tehdään jotain
        this.kertain = kertain;
        kertain.setKertauspaneeli(this);
        
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);

        JPanel tekstikentat = luoTekstikentat();
        JPanel tunnisteidenValinta = luoTunnisteidenValinta();
        
        this.tekstikortit = new JPanel(new CardLayout());
        this.nappikortit = new JPanel(new CardLayout());
        
        JButton seuraava = luoSeuraavanappi();
        JPanel osaamisnapit = luoOsaamisnapit();
        JButton tunnistenappi = luoTunnistenappi();
        kuuntelija = new KertauksenKuuntelija(this, seuraava, (JButton)osaamisnapit.getComponent(0), (JButton)osaamisnapit.getComponent(1), (JButton)osaamisnapit.getComponent(2), tunnistenappi);
        
        tekstikortit.add(tunnisteidenValinta);
        tekstikortit.add(tekstikentat);
        
        nappikortit.add(seuraava);
        nappikortit.add(osaamisnapit);
        
        this.add(tekstikortit);
        this.add(nappikortit);
        
    }
    
    private JPanel luoTekstikentat(){
        ekaTekstikentta = new JTextArea("Aloita kertaaminen painamalla seuraava-nappia");
        ekaTekstikentta.setEditable(false);
        tokaTekstikentta = new JTextArea("");
        tokaTekstikentta.setEditable(false);
        GridLayout tekstiLayout = new GridLayout(1, 2);
        
        JPanel tekstikentat = new JPanel(tekstiLayout);
        tekstikentat.add(ekaTekstikentta);
        tekstikentat.add(tokaTekstikentta);
        return tekstikentat;
    }
    
    private JPanel luoTunnisteidenValinta(){
        tunnistelista = new JTextArea("Valitsit tiedoston onnistuneesti. Valitse vielä, millä tunnisteella varustetut merkit haluat kerrata. Valittavat tunnisteet:");
        tunnistelista.setEditable(false);
        tunnistekentta = new JTextArea("Kirjoita tunniste tähän ja paina valitse-nappia.");
        GridLayout tekstiLayout = new GridLayout(1, 2);
        
        JPanel tunnisteidenValinta = new JPanel(tekstiLayout);
        tunnisteidenValinta.add(tunnistelista);
        tunnisteidenValinta.add(tunnistekentta);
        return tunnisteidenValinta;
    }
    
    private JPanel luoOsaamisnapit(){
        JButton osasin = new JButton("osasin");
        JButton melkein = new JButton("melkein osasin");
        JButton enOsannut = new JButton("en vielä osannut");
        GridLayout nappilayout = new GridLayout(1, 3);
        JPanel osaamisnapit = new JPanel(nappilayout);
        osaamisnapit.add(osasin);
        osaamisnapit.add(melkein);
        osaamisnapit.add(enOsannut);
        
        osasin.addActionListener(kuuntelija);
        melkein.addActionListener(kuuntelija);
        enOsannut.addActionListener(kuuntelija);
        return osaamisnapit;
    }
    
    private JButton luoSeuraavanappi(){
        JButton seuraava = new JButton("seuraava");
        seuraava.addActionListener(kuuntelija);
        return seuraava;
    }
    
    private JButton luoTunnistenappi(){
        JButton tunnistenappi = new JButton("valitse tunniste");
        tunnistenappi.addActionListener(kuuntelija);
        return tunnistenappi;
    }
    
    public void haeKerrattavat(){
        String tunniste = tunnistekentta.getText(); //ei turvallista, toistaiseksi maailma hajoaa jos syöttää epäkorrektin tunnisteen
        kertain.haeKerrattavat(tunniste);
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
    
    
    public File hankiTiedosto(){
        JFileChooser valitsija = new JFileChooser();
        int valinta = valitsija.showOpenDialog(ui.getFrame());
        if (valinta==JFileChooser.APPROVE_OPTION){
            return valitsija.getSelectedFile();
        }
        return null;
    }
}
