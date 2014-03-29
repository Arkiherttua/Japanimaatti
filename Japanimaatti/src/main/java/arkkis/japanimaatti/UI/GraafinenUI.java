/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

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
 *
 * @author Kulmala
 */
public class GraafinenUI implements Runnable{
    private JFrame frame;
    private Container contentPane;
    private JPanel kertauspaneeli, ajastinpaneeli;
    private JPanel cards;
    
    public GraafinenUI(){
        this.frame = new JFrame("Japanimaatti");
        this.contentPane = frame.getContentPane();
        this.ajastinpaneeli = new Ajastinpaneeli();
        this.kertauspaneeli = new Kertauspaneeli();
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

        JPanel tilastot = new JPanel();
        tilastot.add(new JTextField("Ei vielä tuettu"));
        
        JPanel alku = new JPanel();
        Kertauspaneeli kertain = new Kertauspaneeli();
        Ajastinpaneeli ajastin = new Ajastinpaneeli();
        cards.add(alku, "alku");
        cards.add(tilastot, "tilastot");
        cards.add(kertain, "kertain");
        cards.add(ajastin, "ajastin");
        
    }
    
    public void kertausmaatti(){
        CardLayout cd = (CardLayout)cards.getLayout();
        cd.show(cards, "kertain");
    }
    
    public void ajastinmaatti(){
        CardLayout cd = (CardLayout)cards.getLayout();
        cd.show(cards, "ajastin");
    }
    
    public void tilastomaatti(){
//        JTextField eiVielaTuettu = new JTextField("Ei vielä tuettu");
//        eiVielaTuettu.setEditable(false);
//        
//        JPanel tilastot = new JPanel();
//        tilastot.add(eiVielaTuettu);
        
        CardLayout cd = (CardLayout)cards.getLayout();
        cd.show(cards, "tilastot");
    }
    
}
