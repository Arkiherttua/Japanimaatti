package arkkis.japanimaatti;

import arkkis.japanimaatti.UI.GraafinenUI;
import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.logiikka.*;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ){
        Ajastinmaatti ajastin = new Ajastinmaatti();
        Kertausmaatti kertain = new Kertausmaatti();
        
        TekstiUI ui = new TekstiUI(); //nämä kolme riviä poistetaan kunhan saan poistettua koko kys. ui-luokan
        ajastin.setUI(ui);
        kertain.setUI(ui);
        //ui.alku();
        GraafinenUI graafinenUI = new GraafinenUI(kertain, ajastin);
        ajastin.setGUI(graafinenUI);
        SwingUtilities.invokeLater(graafinenUI);
        
    }
}
