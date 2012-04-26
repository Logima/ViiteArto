/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import java.util.ArrayList;
import ohtu.viitearto.Tietoturva;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kennyhei
 */
public class TietoturvaTest {
    
    private Tietoturva turva;
    
    @Before
    public void setUp() {
        turva = new Tietoturva();
    }
    
    @Test
    public void crossSiteScriptinginEstoOnnistuu() {
        String hyokkays = "<marquee>hyokkays</marquee>";
        hyokkays = turva.estaCrossSiteScripting(hyokkays);
        assertEquals("&lt;marquee&gt;hyokkays&lt;/marquee&gt;", hyokkays);
    }
    
    @Test
    public void virheenLisaysOnnistuu() {
        turva.lisaaVirhe("nimi", "Huono nimi!");
        
        ArrayList<String> virhe = turva.getVirheIlmoitukset();
        assertEquals("Huono nimi!", virhe.get(0));
    }
    
    @Test
    public void getPublisherErrorPalauttaaVirheIlmoituksen() {
        turva.lisaaVirhe("publisher", "mälsää");
        assertEquals("mälsää", turva.getPublisherError());
    }
    
    @Test
    public void getPagesErrorPalauttaaVirheIlmoituksen() {
        turva.lisaaVirhe("pages", "Etkö osaa kirjoittaa?");
        assertEquals("Etkö osaa kirjoittaa?", turva.getPagesError());
    }
    
    @Test
    public void getAddressErrorPalauttaaVirheIlmoituksen() {
        turva.lisaaVirhe("address", "Ei tuolla kukaan asu");
        assertEquals("Ei tuolla kukaan asu", turva.getAddressError());
    }

}
