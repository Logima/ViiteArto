/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.util.List;
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
    
    public Viite haeTag(String tunniste) {
        em = getEntityManager();
        
        return em.find(Viite.class, tunniste);
    }

    public List<Viite> haeViiteHaunTuloksena(String haku, String viiteTyyppi) {
        em = getEntityManager();
        
        Query q = null;

        System.out.println("VIITETYYPPI ON "+viiteTyyppi);
        if (viiteTyyppi != null) {
            q = em.createQuery("SELECT v FROM Viite v WHERE v.title LIKE :titleParam and v.type = :typeParam");
            q.setParameter("titleParam", haku + "%").setParameter("typeParam", viiteTyyppi);
        } else {
            q = em.createQuery("SELECT v FROM Viite v WHERE v.title LIKE :titleParam");
            q.setParameter("titleParam", haku + "%");
        }
        return q.getResultList();
    }
}
