/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import ohtu.viitearto.Tag;
import ohtu.viitearto.Viite;
import ohtu.viitearto.Rekisteri;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Keni
 */
public class RekisteriTest {
    
    public RekisteriTest() {
    }

    private Rekisteri rekisteri;
    
    @Before
    public void setUp() {
        rekisteri = Rekisteri.getInstance();
    }
    
    @Test
    public void rekisteristaLuodaanSingleton() {
        Rekisteri toinen = Rekisteri.getInstance();
        
        assertEquals(true, toinen.equals(rekisteri));
    }
    
    @Test
    public void entityManagerLuodaanKutsusta() {
        assertNotNull(rekisteri.getEntityManager());
    }
    
    @Test
    public void viitteenPystyyLisaamaanJaHakemaan() {
        Viite uusi = new Viite();
        uusi.setId(1);
        rekisteri.lisaaViite(uusi);
        
        Viite haettava = rekisteri.haeViite(1);
        assertEquals(haettava, uusi);
    }
    
    @Test
    public void taginPystyyLisaamaanJaHakemaan() {
        Tag uusi = new Tag("uusi");
        rekisteri.lisaaTagi(uusi);
        
        Tag haettava = rekisteri.haeTag("uusi");
        assertEquals("uusi", haettava.getNimi());
    }
    
    @Test
    public void viitteenVoiPoistaa() {
        Viite uusi = new Viite();
        uusi.setId(1);
        rekisteri.lisaaViite(uusi);
        
        Viite haettava = rekisteri.haeViite(1);
        assertEquals(haettava, uusi);
        
        rekisteri.poistaViite(1);
        
        haettava = rekisteri.haeViite(1);
        assertEquals(null, haettava);
    }
    
    @Test
    public void viitteenJollaOnTagejaVoiPoistaa() {
        Viite uusi = new Viite();
        uusi.setId(1);
        
        ArrayList<Tag> tagiLista = new ArrayList<Tag>();
        tagiLista.add(new Tag("eka"));
        tagiLista.add(new Tag("toka"));
        uusi.setTagit(tagiLista);
        
        rekisteri.lisaaViite(uusi);  
        rekisteri.poistaViite(1);
        
        Viite haettava = rekisteri.haeViite(1);
        assertEquals(null, haettava);
}
    
    @Test
    public void haetaanListaViitteita() {
        Viite uusi = new Viite("tarina", "onni");
        Viite toka = new Viite("satu", "kielo");
        rekisteri.lisaaViite(uusi);
        rekisteri.lisaaViite(toka);
        
        List<Viite> lista = rekisteri.getViitteet();
        ArrayList<String> titleTiedot = new ArrayList<String>();
        titleTiedot.add(lista.get(0).getTitle());
        titleTiedot.add(lista.get(1).getTitle());
        
        Collections.sort(titleTiedot);
        
        assertEquals("satu", titleTiedot.get(0));
        assertEquals("tarina", titleTiedot.get(1));
    }
    
    @Test
    public void viitteenHakuYhdellaSanalla() {
        Viite uusi = new Viite("tarina", "onni");
        Viite toka = new Viite("satu", "kielo");
        rekisteri.lisaaViite(uusi);
        rekisteri.lisaaViite(toka);
        
        List<Viite> lista = rekisteri.haeViiteHakuSanoilla(null, "author", "kielo");
        assertEquals("satu", lista.get(0).getTitle());
    }
    
    @Test
    public void viitteenHakuViiteTyypinPerusteellaKunTyypitErilaiset() {
        Viite uusi = new Viite("tarina", "onni");
        Viite toka = new Viite("satu", "kielo");
        uusi.setType("Book");
        toka.setType("Article");
        rekisteri.lisaaViite(uusi);
        rekisteri.lisaaViite(toka);
        
        List<Viite> lista = rekisteri.haeViiteHakuSanoilla("Book", "all", "a");
        assertEquals("tarina", lista.get(0).getTitle());
        assertEquals(false, lista.contains(toka));
    }
    
    @Test
    public void viitteenHakuViiteTyypinPerusteellaKunTyypitSamat() {
        Viite uusi = new Viite("tarina", "onni");
        Viite toka = new Viite("satu", "kielo");
        uusi.setType("Article");
        toka.setType("Article");
        rekisteri.lisaaViite(uusi);
        rekisteri.lisaaViite(toka);
        
        List<Viite> lista = rekisteri.haeViiteHakuSanoilla("Article", "all", "a");
        
        assertEquals(true, lista.size() >= 2);
    }
    
    @Test
    public void haeViiteTageilla() {
        Viite uusi = new Viite("viiskauttaviis", "OhtuGroup");
        ArrayList<Tag> tagit = new ArrayList<Tag>();
        Tag eka = new Tag("pro");
        Tag toka = new Tag("coders");
        rekisteri.lisaaTagi(eka);
        rekisteri.lisaaTagi(toka);
        
        tagit.add(eka);
        tagit.add(toka);
        uusi.setTagit(tagit);
        rekisteri.lisaaViite(uusi);
        
        List<Viite> lista = rekisteri.haeViiteTageilla(null, "coders");
        assertEquals("viiskauttaviis", lista.get(0).getTitle());
        lista.clear();
        lista = rekisteri.haeViiteTageilla(null, "pro");
        assertEquals("viiskauttaviis", lista.get(0).getTitle());
        
    }
    
    @Test
    public void haeViiteTageillaJaViiteTyypilla() {
        Viite uusi = new Viite("viiskauttaviis", "OhtuGroup");
        Viite toinen = new Viite("MestariKarhu", "Henri");
        
        ArrayList<Tag> tagit = new ArrayList<Tag>();
        Tag eka = new Tag("luukkainen");
        Tag toka = new Tag("antaa");
        Tag kolmas = new Tag("täydet");
        Tag neljas = new Tag("pisteet");
        rekisteri.lisaaTagi(eka);
        rekisteri.lisaaTagi(toka);
        rekisteri.lisaaTagi(kolmas);
        rekisteri.lisaaTagi(neljas);
        
        tagit.add(eka);
        tagit.add(toka);
        tagit.add(kolmas);
        tagit.add(neljas);
        
        uusi.setTagit(tagit);
        uusi.setType("Book");
        
        toinen.setTagit(tagit);
        toinen.setType("Article");
        
        rekisteri.lisaaViite(uusi);
        rekisteri.lisaaViite(toinen);
        
        List<Viite> lista = rekisteri.haeViiteTageilla(null, "luukkainen");
        assertEquals(2, lista.size());
        
        lista.clear();
        
        lista = rekisteri.haeViiteTageilla("Article", "täydet");
        assertEquals("MestariKarhu", lista.get(0).getTitle());
        assertEquals(1, lista.size());
        
        lista.clear();
        
        lista = rekisteri.haeViiteTageilla("Book", "pisteet");
        assertEquals("viiskauttaviis", lista.get(0).getTitle());
        assertEquals(1, lista.size());
    }
}
