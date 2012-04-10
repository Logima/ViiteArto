/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Keni
 */
public class Rekisteri {

    private EntityManager em;
    
    private EntityManagerFactory emf = null;

    public Rekisteri() {
        // käytetään "ViiteArtoPU"-konfiguraatiota
        emf = Persistence.createEntityManagerFactory("ViiteArtoPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void lisaaViite(Viite viite) {
        em = getEntityManager();
        
        em.getTransaction().begin();
        em.persist(viite);
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
        em.remove(poistettava);
        em.getTransaction().commit();
    }

    public Viite haeViite(long id) {
        em = getEntityManager();
        
        return em.find(Viite.class, id);
    }
    
}
