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

    public List<Viite> haeViiteYhdellaHakuSanalla(String haku, String viiteTyyppi, String kentta) {
        em = getEntityManager();
        
        Query q = null;
        
        if (viiteTyyppi != null) {
            q = em.createQuery("SELECT v FROM Viite v WHERE v.type = :typeParam");
            q.setParameter("typeParam", viiteTyyppi);
        } else {
            q = em.createQuery("SELECT v FROM Viite v");
        }
        List<Viite> results = q.getResultList();
        Iterator i = results.iterator();
        while (i.hasNext()) {
            Viite v = (Viite) i.next();
            if (!v.getField(kentta).contains(haku)) i.remove();
        }
        return results;
    }

    public List<Viite> haeViiteTageilla(String haku, String viiteTyyppi) {
        Tag t = haeTag(haku);
        if(t == null) return null;
        List<Viite> haettavat = t.getViitteet();
        List<Viite> kopio = new ArrayList<Viite>();
        
        for (Viite viite : haettavat) {
            kopio.add(viite);
        }  
        
        if (viiteTyyppi != null) {
            for (int i=0; i < kopio.size(); ++i) {
                if (!kopio.get(i).getType().equals(viiteTyyppi)) {
                    kopio.remove(kopio.get(i));
                }
            }
        }
        
        return kopio;
    }
    
    public List<Viite> haeViiteKahdellaHakuSanalla(String ekaHaku, String tokaHaku, String viiteTyyppi, String ekaKentta, String tokaKentta, String operand) {
        
        List<Viite> ekaTulos, tokaTulos;
        
        if (ekaKentta.equals("tag")) {
            ekaTulos = haeViiteTageilla(ekaHaku, viiteTyyppi);
        } else {
            ekaTulos = haeViiteYhdellaHakuSanalla(ekaHaku, viiteTyyppi, ekaKentta);
        }
        
        if (tokaKentta.equals("tag")) {
            tokaTulos = haeViiteTageilla(tokaHaku, viiteTyyppi);
        } else {
            tokaTulos = haeViiteYhdellaHakuSanalla(tokaHaku, viiteTyyppi, tokaKentta);
        }
        
        if (operand.equals("and")) {
            Iterator i = ekaTulos.iterator();
            while (i.hasNext()) {
                Viite v = (Viite) i.next();
                if (!tokaTulos.contains(v)) i.remove();
            }
        } else { // or          
            if (!ovatkoTuloksetSamat(ekaTulos, tokaTulos)) {
                ekaTulos.addAll(tokaTulos);
                ekaTulos = poistaDuplikaatit(ekaTulos);
            }
        }
        
        
        return ekaTulos;
    }

    private boolean ovatkoTuloksetSamat(List<Viite> ekaTulos, List<Viite> tokaTulos) {
        if (ekaTulos.size() != tokaTulos.size())
            return false;
        
        for (int i=0; i < ekaTulos.size(); ++i) {
            if (!ekaTulos.get(i).toString().equals(tokaTulos.get(i).toString()))
                return false;
        }
        
        return true;
    }
    
    private List<Viite> poistaDuplikaatit(List<Viite> ekaTulos) {
        for (int i=0; i < ekaTulos.size(); ++i) {
            for (int j=0; j < ekaTulos.size(); ++j) {
                if (j == i)
                    continue;
                
                if (ekaTulos.get(i).toString().equals(ekaTulos.get(j).toString()))
                    ekaTulos.remove(ekaTulos.get(j));
            }
        }
        
        return ekaTulos;
    }
}
