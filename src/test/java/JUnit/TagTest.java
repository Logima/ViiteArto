/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import java.util.ArrayList;
import ohtu.viitearto.Tag;
import ohtu.viitearto.Viite;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kennyhei
 */
public class TagTest {
    
    private Tag tag;
    
    @Before
    public void setUp() {
        tag = new Tag();
    }
    
    @Test
    public void konstruktorissaVoidaanAsettaaTagilleNimi() {
        Tag uusi = new Tag("hunelosna");
        
        assertEquals("hunelosna", uusi.getNimi());
    }
    
    @Test
    public void tagilleVoiAsettaaNimenJaSenVoiPalauttaa() {
        tag.setNimi("ohtu");
        
        assertEquals("ohtu", tag.getNimi());
    }
    
    @Test
    public void tagilleVoiAsettaaListanViitteita() {
        Viite eka = new Viite("Mikko", "Matala");
        Viite toka = new Viite("Pekka", "Pelle");
        
        ArrayList<Viite> viitteet = new ArrayList<Viite>();
        viitteet.add(eka);
        viitteet.add(toka);
        
        tag.setViitteet(viitteet);
        assertEquals(false, tag.getViitteet().isEmpty());
    }
    
    @Test
    public void tagPalauttaaListanViitteita() {
        Viite eka = new Viite("Mikko", "Matala");
        Viite toka = new Viite("Pekka", "Pelle");
        
        ArrayList<Viite> viitteet = new ArrayList<Viite>();
        viitteet.add(eka);
        viitteet.add(toka);
        
        tag.setViitteet(viitteet);
        ArrayList<Viite> uusi = (ArrayList<Viite>) tag.getViitteet();
        assertEquals("Matala", uusi.get(0).getAuthor());
        assertEquals("Pelle", uusi.get(1).getAuthor());
    }
}
