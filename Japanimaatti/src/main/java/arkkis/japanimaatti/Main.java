package arkkis.japanimaatti;

import arkkis.japanimaatti.UI.TekstiUI;
import arkkis.japanimaatti.logiikka.*;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ){
        Ajastinmaatti ajastin = new Ajastinmaatti();
        Kertausmaatti kertain = new Kertausmaatti();
        TekstiUI ui = new TekstiUI(ajastin, kertain);
        ajastin.setUI(ui);
        kertain.setUI(ui);
        ui.alku();
    }
}
