/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkkis.japanimaatti.UI;

import arkkis.japanimaatti.logiikka.KertausmaatinTila;
import arkkis.japanimaatti.logiikka.Kertausmaatti;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
        
        kuuntelija = new KertauksenKuuntelija(this);
        JButton seuraava = luoSeuraavanappi();
        JPanel osaamisnapit = luoOsaamisnapit();
        JButton tunnistenappi = luoTunnistenappi();
        kuuntelija.setNapit(seuraava, (JButton)osaamisnapit.getComponent(0), (JButton)osaamisnapit.getComponent(1), (JButton)osaamisnapit.getComponent(2), tunnistenappi);
        
        tekstikortit.add(tunnisteidenValinta, "tunnisteet");
        tekstikortit.add(tekstikentat, "tekstikentat");
        
        nappikortit.add(tunnistenappi, "tunnisteet");
        nappikortit.add(seuraava, "seuraava");
        nappikortit.add(osaamisnapit, "osaamisnapit");
        
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
        kertain.haeTunnisteet();
        tunnistelista = new JTextArea("Valitsit tiedoston onnistuneesti. \nValitse vielä, millä tunnisteella varustetut \nmerkit haluat kerrata. Valittavat tunnisteet:" +
            kertain.getTunnisteet());
        
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
        String tunniste = tunnistekentta.getText();
        kertain.haeKerrattavat(tunniste);
        
        CardLayout cd = (CardLayout)tekstikortit.getLayout();
        cd.show(tekstikortit, "tekstikentat");
        vaihdaSeuraavanappiin();
    }
    /**
     * Olennainen metodi, jota kertauksen kuuntelija kutsuu kun seuraava-nappia painetaan.
     * Näyttää siis kertausmaatilta saamansa tekstin oikeassa ruudussa, ja tarvittaessa vaihtaa
     * seuraava-napin osaamisnappeihin.
     */
    public void naytaSeuraava(){
        String naytettava = kertain.annaSeuraava();
        if (kertain.getKertauksenTila() == KertausmaatinTila.KANJI){ //jos ollaan alussa, näytetään ekassa tekstikentässä, muuten tokassa
            ekaTekstikentta.setText(naytettava);
        } else if (kertain.getKertauksenTila() == KertausmaatinTila.SUOMI) { //jos ollaan lopussa, vaihdetaan napit osaamisnappeihin
            vaihdaOsaamisnappeihin();
            tokaTekstikentta.setText(naytettava);
        } else {
            tokaTekstikentta.setText(naytettava);
        }
        //kertain.seuraavaKerrattava();
    }
    
    /**
     * Metodia kutsuu kertauksen kuuntelija, jolta saadun tiedon mukaan muokataan
     * kyseessä olevan merkin osaamistasoa. Metodi myös valmistelee kertauspaneelin
     * seuraavan merkin kertaamista varten.
     * @param osaaminen enum, joka kertoo, mitä nappia käyttäjä painoi eli osaako käyttäjä kerrattavan asian
     */
    public void paivitaOsaaminen(Enum osaaminen){
        kertain.paivitaOsaaminen(osaaminen);
        kertain.setKertauksenTila(KertausmaatinTila.TYHJA);
        this.ekaTekstikentta.setText("");
        this.tokaTekstikentta.setText("");
        naytaSeuraava();
        vaihdaSeuraavanappiin();
    }
    
    private void vaihdaSeuraavanappiin(){
        CardLayout cd = (CardLayout)nappikortit.getLayout();
        cd.show(nappikortit, "seuraava");
    }
    
    private void vaihdaOsaamisnappeihin() {
        CardLayout cd = (CardLayout)nappikortit.getLayout();
        cd.show(nappikortit, "osaamisnapit");
    }
    
    /**
     * Metodi luo tiedostonvalitsimen, jolla käyttäjä valitsee tiedoston, jossa kerrattavat asiat ovat
     * @return valittua tiedostoa vastaava File-olio
     */
    public File hankiTiedosto(){
        String alkupolku = System.getProperty("user.home") +"\\Documents\\GitHub\\Japanimaatti\\Japanimaatti\\JapanimaatinTiedostot"; //kovakoodausta tavallaan...
        JFileChooser valitsija = new JFileChooser(alkupolku);
        int valinta = valitsija.showOpenDialog(ui.getFrame());
        if (valinta==JFileChooser.APPROVE_OPTION){
            return valitsija.getSelectedFile();
        }
        return null;
    }

    public void naytaTekstiaEkassaKentassa(String naytettava) {
        this.ekaTekstikentta.setText(naytettava);
    }

    void paivitaTunnistekentta() {
        tunnistelista.setText("Valitsit tiedoston onnistuneesti. \nValitse vielä, millä tunnisteella varustetut \nmerkit haluat kerrata. Valittavat tunnisteet:" + kertain.getTunnisteet());
    }

    
}
