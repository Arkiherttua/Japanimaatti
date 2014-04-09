/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import arkkis.japanimaatti.logiikka.Ajastinmaatti;
import arkkis.japanimaatti.logiikka.Kertausmaatti;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Kayttoliittymäluokkien 'pääluokka'
 * 
 */
public class GraafinenUI implements Runnable{
    private JFrame frame;
    private Container contentPane;
    private Kertauspaneeli kertauspaneeli;
    private Ajastinpaneeli ajastinpaneeli;
    private JPanel cards;
    private Kertausmaatti kertausmaatti;
    private Ajastinmaatti ajastinmaatti;
    private Tilastopaneeli tilastot;
    
    public GraafinenUI(Kertausmaatti kertain, Ajastinmaatti ajastin){
        this.frame = new JFrame("Japanimaatti");
        this.contentPane = frame.getContentPane();
        //this.ajastinpaneeli = new Ajastinpaneeli(this); //nämä turhia?
        //this.kertauspaneeli = new Kertauspaneeli(this);
        this.kertausmaatti = kertain;
        this.ajastinmaatti = ajastin;
    }

    @Override
    public void run() {
        frame.setPreferredSize(new Dimension(700,400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.setVisible(true);
        frame.pack();
    }

    private void luoKomponentit(Container contentPane) {
        JTextField otsikko = new JTextField("Valikko");
        otsikko.setEditable(false);
        JButton kertausnappi = new JButton("Kertaa");
        JButton ajastinnappi = new JButton("Ajasta");
        JButton tilastonappi = new JButton("Näytä tilastoja");
        
        GridLayout layout = new GridLayout(4, 1);

        JPanel menu = new JPanel(layout);
        menu.add(otsikko);
        menu.add(kertausnappi);
        menu.add(ajastinnappi);
        menu.add(tilastonappi);
        
        contentPane.add(menu, BorderLayout.WEST);
        
        Kuuntelija k = new Kuuntelija(this, kertausnappi, ajastinnappi, tilastonappi);
        kertausnappi.addActionListener(k);
        ajastinnappi.addActionListener(k);
        tilastonappi.addActionListener(k);
        
        cards = new JPanel(new CardLayout());
        contentPane.add(cards);

        this.tilastot = new Tilastopaneeli(this);
        //tilastot.add(new JTextField("Ei vielä tuettu"));
        
        JPanel alku = new JPanel();
        Kertauspaneeli kertain = new Kertauspaneeli(this.kertausmaatti, this);
        Ajastinpaneeli ajastin = new Ajastinpaneeli(this.ajastinmaatti, this);
        this.ajastinpaneeli = ajastin;
        this.kertauspaneeli = kertain;
        cards.add(alku, "alku");
        cards.add(tilastot, "tilastot");
        cards.add(kertain, "kertain");
        cards.add(ajastin, "ajastin");
        
    }
    
    public JButton luoNappi(String teksti){
        JButton nappi = new JButton(teksti);
        return nappi;
    }
    
    /**
     * Metodi asettaa näkyviin kertauspaneelin, ja valmistelee ohjelman sitä varten myös
     * mm. laittamalla käyttäjän valitsemaan tiedoston kertausta varten
     */
    public void kertausmaatti(){
        ajastinmaatti.tallennaTiedot(); //tallenna tiedot aina, kun siirretään korttiin joka ei ole ajastin
        File kertaustiedosto = kertauspaneeli.hankiTiedosto();
        kertausmaatti.setTiedosto(kertaustiedosto);
        kertausmaatti.haeTunnisteet();
        kertauspaneeli.paivitaTunnistekentta();
        CardLayout cd = (CardLayout)cards.getLayout();
        cd.show(cards, "kertain");
    }
    
    /**
     * Metodi asettaa näkyviin ajastinpaneelin
     */
    public void ajastinmaatti(){
        ajastinpaneeli.paivita();
        CardLayout cd = (CardLayout)cards.getLayout();   
        cd.show(cards, "ajastin");
    }
    
    /**
     * Metodi asettaa näkyviin tilastopaneelin
     */
    public void tilastomaatti(){
        tilastot.paivita();
        ajastinmaatti.tallennaTiedot(); //tallenna tiedot aina, kun siirretään korttiin joka ei ole ajastin
        CardLayout cd = (CardLayout)cards.getLayout();
        cd.show(cards, "tilastot");
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
    public Ajastinpaneeli getAjastinpaneeli(){
        return this.ajastinpaneeli; 
    }
    
    public Kertauspaneeli getKertauspaneeli(){
        return this.kertauspaneeli;
    }
    
    public Kertausmaatti getKertausmaatti(){
        return kertausmaatti;
    }
    
    public Ajastinmaatti getAjastinmaatti(){
        return ajastinmaatti;
    }
    
}
