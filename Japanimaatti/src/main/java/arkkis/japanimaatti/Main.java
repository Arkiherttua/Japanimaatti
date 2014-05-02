package arkkis.japanimaatti;

import arkkis.japanimaatti.UI.GraafinenUI;
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
        
        GraafinenUI graafinenUI = new GraafinenUI(kertain, ajastin);
        ajastin.setGUI(graafinenUI);
        SwingUtilities.invokeLater(graafinenUI);
        
    }
}
