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
 * Käyttöliittymäluokkien 'pääluokka', joka hallinnoi kokonaisuutta
 * 
 */
public class GraafinenUI implements Runnable{
    private JFrame frame;
    private Container contentPane;
    private Kertauspaneeli kertauspaneeli;
    private Ajastinpaneeli ajastinpaneeli;
    private JPanel paneelikortit;
    private Kertausmaatti kertausmaatti;
    private Ajastinmaatti ajastinmaatti;
    private Tilastopaneeli tilastot;
    private String nakyvaKortti;
    
    public GraafinenUI(Kertausmaatti kertain, Ajastinmaatti ajastin){
        this.frame = new JFrame("Japanimaatti");
        this.contentPane = frame.getContentPane();
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
        JPanel menu = luoValikko();
        contentPane.add(menu, BorderLayout.WEST);
                
        paneelikortit = new JPanel(new CardLayout());
        contentPane.add(paneelikortit);

        this.tilastot = new Tilastopaneeli(this);
        
        JPanel alku = new JPanel();
        this.kertauspaneeli= new Kertauspaneeli(this.kertausmaatti, this);
        this.ajastinpaneeli= new Ajastinpaneeli(this.ajastinmaatti, this);
        
        paneelikortit.add(alku, "alku");
        paneelikortit.add(tilastot, "tilastot");
        paneelikortit.add(kertauspaneeli, "kertain");
        paneelikortit.add(ajastinpaneeli, "ajastin");
        nakyvaKortti = "alku";
    }
    /**
     * Metodi luo JPanel-olion joka on ohjelman koko ajan näkyvillä oleva valikko
     * @return JPanel jossa halutut napit ja otsikkokenttä
     */
    private JPanel luoValikko(){
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
        
        ValikonKuuntelija k = new ValikonKuuntelija(this, kertausnappi, ajastinnappi, tilastonappi);
        kertausnappi.addActionListener(k);
        ajastinnappi.addActionListener(k);
        tilastonappi.addActionListener(k);
        return menu;
    }

    
    /**
     * Metodi asettaa näkyviin kertauspaneelin, ja valmistelee ohjelman sitä varten myös
     * mm. laittamalla käyttäjän valitsemaan tiedoston kertausta varten
     * jos tiedostoa ei valita, ei kertausmaattitoimintoja tehdä
     */
    public void kertausmaatti(){
        //ajastinmaatti.tallennaTiedot(); //tallenna tiedot aina, kun siirrytään korttiin joka ei ole ajastin
        File kertaustiedosto = kertauspaneeli.hankiTiedosto("Valitse tiedosto");
        if (kertaustiedosto != null){
            kertausmaatti.setTiedosto(kertaustiedosto);
            kertausmaatti.haeTunnisteet();
            //kertauspaneeli.paivitaTunnistekentta();
            kertauspaneeli.luoTunnisteidenValinta();
            CardLayout cd = (CardLayout)paneelikortit.getLayout();
            cd.show(paneelikortit, "kertain");
            nakyvaKortti = "kertain";
            kertausmaatti.talletaAloitusaika(); //vähän epätarkka paikka tallentaa, mutta menköön
        }
        
    }
    
    public void alkupaneeli(){
        //ajastinmaatti.tallennaTiedot();
        CardLayout cd = (CardLayout)paneelikortit.getLayout();
        cd.show(paneelikortit, "alku");
        nakyvaKortti = "alku";
    }
    
    /**
     * Metodi asettaa näkyviin ajastinpaneelin
     */
    public void ajastinmaatti(){
        ajastinpaneeli.paivita();
        //kertausmaatti.tallennaTiedostoon();
        CardLayout cd = (CardLayout)paneelikortit.getLayout();   
        cd.show(paneelikortit, "ajastin");
        nakyvaKortti = "ajastin";
    }
    
    /**
     * Metodi asettaa näkyviin tilastopaneelin
     */
    public void tilastomaatti(){
        tilastot.paivita(ajastinmaatti.getAjastustenKesto(), kertausmaatti.getOpiskelunKesto());
        //ajastinmaatti.tallennaTiedot(); //tallenna tiedot aina, kun siirretään korttiin joka ei ole ajastin
        //kertausmaatti.tallennaTiedostoon(); //samoin paitsi kertausmaatille
        CardLayout cd = (CardLayout)paneelikortit.getLayout();
        cd.show(paneelikortit, "tilastot");
        nakyvaKortti = "tilastot";
    }
    
    public void suljeAjastinmaatti(){
        ajastinmaatti.tallennaTiedot();
    }
    
    /**
     * Metodia kutsutaan, kun käyttäjä poistuu kertaus-kortista.
     * Se tallentaa kertauksen aiheuttamat muutokset sekä päivittää kertaukseen käytettyä aikaa
     */
    public void suljeKertausmaatti(){
        kertausmaatti.tallennaKerrattuAika();
        kertausmaatti.tallennaTiedostoon();
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
    
    public String getNakyvaKortti(){
        return nakyvaKortti;
    }
    
}
