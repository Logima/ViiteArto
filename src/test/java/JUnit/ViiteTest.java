/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import junit.framework.TestCase;
import ohtu.viitearto.Viite;

/**
 *
 * @author nzeronen
 */
public class ViiteTest extends TestCase {
    
    public ViiteTest(String testName) {
        super(testName);
    }
    
    //before
    
    Viite viite = new Viite();
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAddress() {
        
        
        viite.setAddress("kumpula");
        String address = viite.getAddress();
        assertEquals("kumpula", address);
                        
    }
    
    public void testTitle() {
        
        viite.setTitle("testauksen kuninkaat");
        String title = viite.getTitle();
        assertEquals("testauksen kuninkaat", title);
    
    }
    
    public void testAuthor() {
        
        viite.setAuthor("Wikla");
        String author = viite.getAuthor();
        assertEquals("Wikla", author);
    
    }
    
    public void testJournal() {
        
        viite.setJournal("mikälie");
        String journal = viite.getJournal();
        assertEquals("mikälie", journal);
    
    }
    
    public void testBooktitle() {
        
        viite.setBooktitle("koodin orjat");
        String booktitle = viite.getBooktitle();
        assertEquals("koodin orjat", booktitle);
    
    }
    
    public void testPublisher() {
        
        viite.setPublisher("Otava");
        String publisher = viite.getPublisher();
        assertEquals("Otava", publisher);
    
    }
    
    public void testPages() {
        
        viite.setPages("101");
        String pages = viite.getPages();
        assertEquals("101", pages);
    
    }
    
    public void testYearMade() {
        
        viite.setYear(1890);
        int year = viite.getYear();
        assertEquals(1890 , year);
    
    }
    
    public void testVolume() {
        
        viite.setVolume(12);
        int volume = viite.getVolume();
        assertEquals(12 , volume);
    
    }
    
    public void testNumber() {
        
        viite.setNumber(15);
        int number = viite.getNumber();
        assertEquals(15 , number);
    
    }
    
    public void testType() {
        
        viite.setType("horror");
        String type = viite.getType();
        assertEquals("horror" , type);
    
    }

   
    
}
