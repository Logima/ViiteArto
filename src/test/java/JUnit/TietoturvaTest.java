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
    public void pakollistenTietojenTarkistusMeneeLapiOikeillaSyotteilla() {
        String title = "Miniprojekti";
        String author = "HuNeLoSna";
        String booktitle = null;
        String journal = "magazine";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        assertEquals(false, turva.onkoVirheita());
    }
    
    @Test
    public void tietojenTarkistusLoytaaVirheitaVaarillaSyotteilla() {
        String title = "";
        String author = "";
        String booktitle = "book";
        String journal = "magazine";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        
        assertEquals(true, turva.onkoVirheita());
    }
    
    @Test
    public void virheIlmoituksetOnOlemassaKunAnnetaanVaariaSyotteita() {
        String title = "";
        String author = "HuNeLoSna";
        String booktitle = "book";
        String journal = "";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        ArrayList<String> virheet = turva.getVirheIlmoitukset();
        
        assertEquals(true, turva.onkoVirheita());
        assertEquals("Journal ei saa olla tyhjä!", virheet.get(0));
        assertEquals("Title ei saa olla tyhjä!", virheet.get(1));
    }
    
    @Test
    public void getAuthorErrorPalauttaaVirheIlmoituksen() {
        String title = "Miniprojekti";
        String author = "";
        String booktitle = "book";
        String journal = "Lehti";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        
        assertEquals("Author ei saa olla tyhjä!", turva.getAuthorError());
    }
    
    @Test
    public void getTitleErrorPalauttaaVirheIlmoituksen() {
        String title = "";
        String author = "HuNeLoSna";
        String booktitle = "book";
        String journal = "Lehti";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        
        assertEquals("Title ei saa olla tyhjä!", turva.getTitleError());
    }
    
    @Test
    public void getBooktitleErrorPalauttaaVirheIlmoituksen() {
        String title = "Miniprojekti";
        String author = "HuNeLoSna";
        String booktitle = "";
        String journal = "Lehti";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        
        assertEquals("Booktitle ei saa olla tyhjä!", turva.getBooktitleError());
    }
    
    @Test
    public void getJournalErrorPalauttaaVirheIlmoituksen() {
        String title = "Miniprojekti";
        String author = "HuNeLoSna";
        String booktitle = "book";
        String journal = "";
        
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        
        assertEquals("Journal ei saa olla tyhjä!", turva.getJournalError());
    }
    
    @Test
    public void getNumberErrorPalauttaaVirheIlmoituksen() {
        String yearString = "123yksikaksikolme";
        String volumeString = "23kaks";
        String numberString = "kymppikaks";
        turva.tarkistaNumeroTiedot(yearString, null, null);
        
        assertEquals("Vain numerot sallittuja!", turva.getNumberError());
        turva.nollaaVirheet();
        
        turva.tarkistaNumeroTiedot(null, volumeString, null);
        assertEquals("Vain numerot sallittuja!", turva.getNumberError());
        turva.nollaaVirheet();
        
        turva.tarkistaNumeroTiedot(null, null, numberString);
        assertEquals("Vain numerot sallittuja!", turva.getNumberError());
    }
    
    @Test
    public void numeroTietojenTarkistusMeneeLapiOikeillaSyotteilla() {
        String yearString = "2012";
        String volumeString = "04";
        String numberString = "19";
        
        turva.tarkistaNumeroTiedot(yearString, volumeString, numberString);
        assertEquals(false, turva.onkoVirheita());
    }
    
    @Test
    public void numeroTietojenTarkistusLoytaaVirheitaVaarillaSyotteilla() {
        String yearString = "123yksikaksikolme";
        String volumeString = "23kaks";
        String numberString = "kymppikaks";
        
        turva.tarkistaNumeroTiedot(numberString, volumeString, numberString);
        
        assertEquals(true, turva.onkoVirheita());
    }
    
    @Test
    public void virheenLisaysOnnistuu() {
        turva.lisaaVirhe("nimiVirhe", "Huono nimi!");
        
        ArrayList<String> virhe = turva.getVirheIlmoitukset();
        assertEquals("Huono nimi!", virhe.get(0));
    }
    
    @Test
    public void getPublisherErrorPalauttaaVirheIlmoituksen() {
        turva.lisaaVirhe("publisherError", "mälsää");
        assertEquals("mälsää", turva.getPublisherError());
    }
    
    @Test
    public void getPagesErrorPalauttaaVirheIlmoituksen() {
        turva.lisaaVirhe("pagesError", "Etkö osaa kirjoittaa?");
        assertEquals("Etkö osaa kirjoittaa?", turva.getPagesError());
    }

}
