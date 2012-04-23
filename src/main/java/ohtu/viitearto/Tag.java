/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kennyhei
 */

@Entity
public class Tag implements Serializable {
    
    @Id
    private String nimi;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    private List<Viite> viitteet; // lista viitteistä, joihin tagi kuuluu

    public Tag() {
        viitteet = new ArrayList<Viite>();
    }
    
    public Tag(String nimi) {
        viitteet = new ArrayList<Viite>();
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public List<Viite> getViitteet() {
        return viitteet;
    }
    
    public void setViitteet(List<Viite> viitteet) {
        this.viitteet = viitteet;
    }
    
    public void poistaViite(Viite poistettava) {
        viitteet.remove(poistettava);
    }
    
}
