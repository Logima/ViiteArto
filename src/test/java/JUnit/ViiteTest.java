/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import java.util.HashMap;
import ohtu.viitearto.Tag;
import java.util.TreeMap;
import ohtu.viitearto.Viite;
import java.util.ArrayList;
import ohtu.viitearto.Tietoturva;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author nzeronen
 */
public class ViiteTest {
    
    Viite viite;
    
    @Before
    public void setUp() {
        viite = new Viite();
    }
    
    @Test
    public void testAddress() {
        viite.setAddress("kumpula");
        String address = viite.getAddress();
        assertEquals("kumpula", address);                  
    }
    
    @Test
    public void testTitle() {
        viite.setTitle("testauksen kuninkaat");
        String title = viite.getTitle();
        assertEquals("testauksen kuninkaat", title);
    }
    
    @Test
    public void testAuthor() {
        viite.setAuthor("Wikla");
        String author = viite.getAuthor();
        assertEquals("Wikla", author);
    }
    
    @Test
    public void testJournal() {
        viite.setJournal("mikälie");
        String journal = viite.getJournal();
        assertEquals("mikälie", journal);
    }
    
    @Test
    public void testBooktitle() { 
        viite.setBooktitle("koodin orjat");
        String booktitle = viite.getBooktitle();
        assertEquals("koodin orjat", booktitle);
    }
    
    @Test
    public void testPublisher() {
        viite.setPublisher("Otava");
        String publisher = viite.getPublisher();
        assertEquals("Otava", publisher);
    }
    
    @Test
    public void testPages() {
        viite.setPages("101");
        String pages = viite.getPages();
        assertEquals("101", pages);
    }
    
    @Test
    public void testYearMade() {
        viite.setYear("1890");
        String year = viite.getYear();
        assertEquals("1890" , year);
    }
    
    @Test
    public void testVolume() {
        viite.setVolume("12");
        String volume = viite.getVolume();
        assertEquals("12" , volume);
    }
    
    @Test
    public void testNumber() {
        viite.setNumber("15");
        String number = viite.getNumber();
        assertEquals("15" , number);
    }
    
    @Test
    public void testType() {
        viite.setType("horror");
        String type = viite.getType();
        assertEquals("horror" , type);
    }
    
    @Test
    public void testId() {
        viite.setId(1);
        long id = viite.getId();
        assertEquals(1, id);
    }
    
    @Test
    public void testSetGetField() {
        viite.setField("height", "185cm");
        String pituus = viite.getField("height");
        assertEquals("185cm", pituus);
    }
    
    @Test
    public void testBookKentat() {
        TreeMap<String, String> kentat = Viite.getBookKentat();
        assertEquals(false, kentat.isEmpty());
    }
    
    @Test
    public void testArticleKentat() {
        TreeMap<String, String> kentat = Viite.getArticleKentat();
        assertEquals(false, kentat.isEmpty());
    }
    
    @Test
    public void testInproceedingsKentat() {
        TreeMap<String, String> kentat = Viite.getInproceedingsKentat();
        assertEquals(false, kentat.isEmpty());
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("Title: "+null+"\n Author: "+null+"\n Publisher: "+null+
                "\n Pages: "+null+"\n Address: "+null+"\n Year: "+null+
                "\n Booktitle: "+null+"\n Journal: "+null+"\n Volume: "+
                null+"\n Number: "+null, viite.toString());
    }
    
    @Test
    public void tagitTest() {
        ArrayList<Tag> tagit = new ArrayList<Tag>();
        tagit.add(new Tag("ihana"));
        tagit.add(new Tag("koodaus"));
        viite.setTagit(tagit);
        
        ArrayList<Tag> lista = (ArrayList<Tag>) viite.getTagit();
        assertEquals("ihana", lista.get(0).getNimi());
        assertEquals("koodaus", lista.get(1).getNimi());
    }
    
    @Test
    public void getTiedotToimii() {
        viite.setTitle("Mein Code");
        viite.setAuthor("OhtuTeam");
        viite.setPublisher("Otava");
        viite.setBooktitle("Opus");
        viite.setJournal("Linkki");
        viite.setAddress("FIN");
        viite.setYear("2012");
        viite.setVolume("21");
        viite.setNumber("1");
        viite.setPages("100");
        
        ArrayList<Tag> tagit = new ArrayList<Tag>();
        tagit.add(new Tag("ihana"));
        tagit.add(new Tag("koodaus"));
        
        viite.setTagit(tagit);
        
        ArrayList<String> tiedot = viite.getTiedot();
        
        assertEquals("<b>Title:</b> Mein Code", tiedot.get(0));
        assertEquals("<b>Author:</b> OhtuTeam", tiedot.get(1));
        assertEquals("<b>Publisher:</b> Otava", tiedot.get(2));
        assertEquals("<b>Booktitle:</b> Opus", tiedot.get(3));
        assertEquals("<b>Journal:</b> Linkki", tiedot.get(4));
        assertEquals("<b>Address:</b> FIN", tiedot.get(5));
        assertEquals("<b>Year:</b> 2012", tiedot.get(6));
        assertEquals("<b>Volume:</b> 21", tiedot.get(7));
        assertEquals("<b>Number:</b> 1", tiedot.get(8));
        assertEquals("<b>Pages:</b> 100", tiedot.get(9));
        assertEquals("<b>Tags:</b> ihana, koodaus", tiedot.get(10));
        
    }
    
    @Test
    public void fielditPalautetaan() {
        viite.setField("author", "OhtuTeam");
        viite.setField("title", "Miniprojekti");
        
        HashMap<String, String> kentat = viite.getFields();
        assertEquals("OhtuTeam", kentat.get("author"));
        assertEquals("Miniprojekti", kentat.get("title"));
    }
    
    @Test
    public void tagitVoiNollata() {
        viite.setTagit(null);
        assertEquals(null, viite.getTagit());
    }
    
    
    
}
