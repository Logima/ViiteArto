/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Keni
 */
public class Rekisteri {

    private static Rekisteri instance;
    private EntityManager em;
    
    private EntityManagerFactory emf = null;

    public static Rekisteri getInstance() {
        if (instance == null) {
            instance = new Rekisteri();
        }
        
        return instance;
    }
    
    private Rekisteri() {
        // käytetään "ViiteArtoPU"-konfiguraatiota    
        emf = Persistence.createEntityManagerFactory("ViiteArtoPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void lisaaViite(Viite viite) {
        em = getEntityManager();
        
        em.getTransaction().begin();
        em.merge(viite);
        em.getTransaction().commit();
    }
    
    public void lisaaTagi(Tag uusi) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(uusi);
        em.getTransaction().commit();
    }
    
    public List<Viite> getViitteet() {
        em = getEntityManager();
        return em.createQuery("SELECT v FROM Viite v").getResultList();
    }

    public void poistaViite(long id) {
        em = getEntityManager();
        em.getTransaction().begin();

        Viite poistettava = em.find(Viite.class, id);
        List<Tag> tagit = poistettava.getTagit();

        if (tagit != null) {
            for (int i = 0; i < tagit.size(); ++i) {
                tagit.get(i).getViitteet().remove(poistettava); // tuhotaan tageilta tiedot poistettavaan
            }                                                   // viitteeseen

            poistettava.setTagit(null); // poistetaan tiedot tageihin poistettavalta viitteeltä
        }
        em.remove(poistettava);
        em.getTransaction().commit();
    }

    public Viite haeViite(long id) {
        em = getEntityManager();
        
        return em.find(Viite.class, id);
    }
    
    public Tag haeTag(String tunniste) {
        em = getEntityManager();
        
        return em.find(Tag.class, tunniste);
    }
    
    public List<Viite> haeViiteTageilla(String[] sanat, String viiteTyyppi) {
        em = getEntityManager();
        
        Query q = em.createQuery("SELECT t FROM Tag t");
        
        List<Tag> results = q.getResultList();  
        HashSet<Viite> refResults = new HashSet<Viite>();
        
        for (Tag t : results) { // loopataan kaikki tagit
            for (String hakusana : sanat) {
                if (t.getNimi().contains(hakusana)) // jos tagin nimessä hakusana
                    refResults.addAll(t.getViitteet()); // lisätään hakutuloksiin viitteet, joilla on kys. tag
            }  
        }
        
        return new ArrayList<Viite>(refResults);
    }
    
    public List<Viite> haeViiteHakuSanoilla(String[] sanat, String viiteTyyppi, String kentta) {
        em = getEntityManager();
        
        Query q = null;
        
        if (viiteTyyppi != null) {
            q = em.createQuery("SELECT v FROM Viite v WHERE v.type = :typeParam");
            q.setParameter("typeParam", viiteTyyppi);
        } else {
            q = em.createQuery("SELECT v FROM Viite v");
        }
        
        if (kentta.equals("all"))
            return haeKaikkienKenttienPerusteella(sanat, q, viiteTyyppi);
        else
            return haeYhdenKentanPerusteella(sanat, q, viiteTyyppi, kentta);
    }
    
    private List<Viite> haeKaikkienKenttienPerusteella(String[] sanat, Query q, String viiteTyyppi) { // sanat ok, viitetyyppi ok    
        List<Viite> results = q.getResultList();    
        ArrayList<Viite> finalResults = new ArrayList<Viite>(); // talletetaan lopulliset hakutulokset
               
        for (Viite v : results) {    
            for (String field : v.getFields().values()) {
                for (String hakusana : sanat) {
                    if (field.contains(hakusana)) {
                        finalResults.add(v);
                    }
                }
            }
        }
        return finalResults;
    }

    private List<Viite> haeYhdenKentanPerusteella(String[] sanat, Query q, String viiteTyyppi, String kentta) {
        List<Viite> results = q.getResultList();
        Iterator i = results.iterator();

        while (i.hasNext()) {
            Viite v = (Viite) i.next();
            if (v.getField(kentta) == null) {
                i.remove();
            } else {
                for (int j = 0; j < sanat.length; ++j) {
                    if (!v.getField(kentta).contains(sanat[j])) {
                        i.remove();
                        break;
                    }

                }
            }
        }
        return results;
    }
}
