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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Kertauspaneeli muodostaa yhden 'kortin' käyttöliittymään, kortin joka on näkyvissä kun käyttäjä käyttää kertaustoimintoa
 * 
 */
public class Kertauspaneeli extends JPanel{
    private GraafinenUI ui;
    private JPanel nappikortit;
    private JPanel tekstikortit;
    //private JPanel kortit;
    private Kertausmaatti kertain;
    private JTextArea ekaTekstikentta;
    private JTextArea tokaTekstikentta;
    private JButton ok;
    //private JTextArea tunnistelista;
    //private JTextArea tunnistekentta;
    private KertauksenKuuntelija kuuntelija;
    private String osaamistasonTunniste;
    private String kerrattavienTunniste;
    
    public Kertauspaneeli(Kertausmaatti kertain, GraafinenUI ui){
        this.ui = ui;
        this.kertain = kertain;
        kertain.setKertauspaneeli(this);
        
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);

        JPanel tekstikentat = new JPanel();
        GridLayout tekstikenttienLayout = new GridLayout(1, 2);
        tekstikentat.setLayout(tekstikenttienLayout);
        
        ok = new JButton("Valitse tunniste"); //aluksi tämä teksti
        ekaTekstikentta = new JTextArea();
        ekaTekstikentta.setEditable(false);
        tokaTekstikentta = new JTextArea();
        
        tekstikentat.add(ekaTekstikentta);
        tekstikentat.add(tokaTekstikentta);
        //JPanel tunnisteidenValinta = luoTunnisteidenValinta();
        
        //this.kortit = new JPanel(new CardLayout());
        //this.tekstikortit = new JPanel(new CardLayout());
        this.nappikortit = new JPanel(new CardLayout());
        
        kuuntelija = new KertauksenKuuntelija(this);
        ok.addActionListener(kuuntelija);
        //JButton seuraava = luoSeuraavanappi();
        JPanel osaamisnapit = luoOsaamisnapit();

        kuuntelija.setNapit(ok, (JButton)osaamisnapit.getComponent(0), (JButton)osaamisnapit.getComponent(1), (JButton)osaamisnapit.getComponent(2));
        
        //tekstikortit.add(tunnisteidenValinta, "tunnisteet");
        //tekstikortit.add(tekstikentat, "tekstikentat");
        
        nappikortit.add(ok, "ok");
        nappikortit.add(osaamisnapit, "osaamisnapit");
        
        //this.add(tekstikortit);
        this.add(tekstikentat);
        this.add(nappikortit);
    }
    
//    private JPanel luoTunnistekortti(){
//        JPanel tunnistekortti = new JPanel();
//        this.ekaTekstikentta.setText("Valitsit tiedoston onnistuneesti. \nValitse vielä, millä tunnisteella varustetut \nmerkit haluat kerrata. Valittavat tunnisteet:\n" + kertain.getTunnisteet());
//        return tunnistekortti;
//    }
    
//    private JPanel luoTekstikentat(){
//        ekaTekstikentta = new JTextArea("Aloita kertaaminen painamalla seuraava-nappia");
//        ekaTekstikentta.setEditable(false);
//        tokaTekstikentta = new JTextArea("");
//        tokaTekstikentta.setEditable(false);
//        GridLayout tekstiLayout = new GridLayout(1, 2);
//        JPanel tekstikentat = new JPanel(tekstiLayout);
//        tekstikentat.add(ekaTekstikentta);
//        tekstikentat.add(tokaTekstikentta);
//        return tekstikentat;
//    }
    
    public void luoTunnisteidenValinta(){
        ekaTekstikentta.setText("Valitsit tiedoston onnistuneesti. \n"
                + "Valitse vielä, millä tunnisteella varustetut \nmerkit haluat kerrata. "+
                "Valittavat tunnisteet:\n" + kertain.getTunnisteet());

        tokaTekstikentta.setText("Kirjoita tunniste tähän ja paina valitse-nappia.\n"+
                "Jos et muokkaa tätä kenttää, ohjelma valitsee\n kerrattavaksi kaikki tiedoston rivit.");
//        CardLayout cd = (CardLayout)nappikortit.getLayout();
//        cd.show(nappikortit, "ok");
    }
    
    public void luoKerrattavienValinta(){
        ekaTekstikentta.setText("Valitse vielä, haluatko kerrata merkit\n, jotka olet merkinnyt ei-osatuiksi,\n"+
                "vai merkit, jotka olet merkinnyt\nmelkein osaavasi, vai kaikki merkit.");

        tokaTekstikentta.setText("Kirjoita tähän \"melkein\", jos haluat\nkerrata merkit, jotka jo melkein osaat.\n"+
                "Kirjoita \"ei\" jos haluat kerrata vain ne,\njoita et osaa lainkaan.\n"+
                "Jos et muokkaa kenttää,\npääset kertaamaan kaikki tiedoston rivit.");
        ok.setText("Valitse kerrattavat");
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
    
    /**
     * Kutsuu kertausmaatin haeTunnisteet-metodia tekstikentän tekstillä
     * ja vaihtaa näkyviin kertauksen vaatiman seuraava-napin
     */
    public void haeKerrattavat(){
        kertain.haeKerrattavat(kerrattavienTunniste, osaamistasonTunniste);
        if (kertain.getKerrattavienMaara()==0){
            yhtaanKerrattavaaEiValittu();
        } else {
            ekaTekstikentta.setText("Aloita kertaaminen valitsemalla seuraava");
            tokaTekstikentta.setText("");
            tokaTekstikentta.setEditable(false);
            ok.setText("Seuraava");
        }
    }
    
    /**
     * Jos käyttäjän valitsemalla tunniste+osaamistaso-kombinaatiolla ei ole
     * yhtään merkkiä, laitetaan käyttäjä valitsemaan uudestaan tunniste ja osaamistaso
     */
    public void yhtaanKerrattavaaEiValittu(){
        ekaTekstikentta.setText("Mitään kerrattavaa ei löytynyt!\nKokeile kerrata esim. kaikki tietyn osaamistason merkit\n"+
                    "tai kaikki tietyllä tunnisteella varustetut\nosaamistasosta riippumatta.\n"+
                    "Valittavat tunnisteet:\n" + kertain.getTunnisteet());

        tokaTekstikentta.setText("Kirjoita tunniste tähän ja paina valitse-nappia.\n"+
                "Jos et muokkaa tätä kenttää, ohjelma valitsee\n kerrattavaksi kaikki tiedoston rivit.");
        ok.setText("Valitse tunniste");
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
        cd.show(nappikortit, "ok");
    }
    
    private void vaihdaOsaamisnappeihin() {
        CardLayout cd = (CardLayout)nappikortit.getLayout();
        cd.show(nappikortit, "osaamisnapit");
    }
    
    /**
     * Metodi luo tiedostonvalitsimen, jolla käyttäjä valitsee tiedoston, jossa kerrattavat asiat ovat
     * @return valittua tiedostoa vastaava File-olio
     */
    public File hankiTiedosto(String otsikko){
        String alkupolku = System.getProperty("user.home") +"\\Documents\\GitHub\\Japanimaatti\\Japanimaatti\\JapanimaatinTiedostot"; //kovakoodausta tavallaan...
        JFileChooser valitsija = new JFileChooser(alkupolku);
        valitsija.setDialogTitle(otsikko);
        int valinta = valitsija.showOpenDialog(ui.getFrame());
        if (valinta==JFileChooser.APPROVE_OPTION){
            if (kertain.onkoTiedostoOikeanmuotoinen(valitsija.getSelectedFile())){
                return valitsija.getSelectedFile();
            } else {
                JOptionPane.showMessageDialog(this, "Valitse tiedosto, joka on muotoiltu vastaamaan ohjelman vaatimuksia!", "Varoitus", 0);
                return hankiTiedosto("Valitse tiedosto, joka on muotoiltu vastaamaan ohjelman vaatimuksia!");
            }
        } else if (valinta== JFileChooser.CANCEL_OPTION){
            ui.alkupaneeli(); //jos tiedostoa ei valittu, palataan ohjelman aloitustilaan
        }
        return null;
    }

    public void naytaTekstiaEkassaKentassa(String naytettava) {
        this.ekaTekstikentta.setText(naytettava);
    }

    /**
     * Metodi lukee toisesta tekstikentästä sinne kirjoitetun tunnisteen,
     * joka kertoo, minkä osaamistason merkit käyttäjä haluaa kerrata
     */
    public void tallennaOsaamistasonTunniste() {
        if (tokaTekstikentta.getText().equals("melkein")){
            osaamistasonTunniste = "\tMELKEIN";
        } else if (tokaTekstikentta.getText().equals("ei")){
            osaamistasonTunniste = "\tEI";
        } else {
            osaamistasonTunniste = ""; //jos käyttäjä ei ole muokannut kenttää tai on muokannut sitä väärin, kaikki merkit kerrataan
        }
    }
    
    public void tallennaKerrattavienTunniste(){
        kerrattavienTunniste = tokaTekstikentta.getText();
    }

    
    
    

    
}
