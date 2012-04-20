/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Keni
 */

@Entity
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class Viite implements Serializable {
    
    @Column
    private HashMap<String, String> fields;
    
    @Column
    private String type;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private Long id;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn
    private List<Tag> tagit; // lista tageista, jotka viitteellä on

    public Viite() {
        fields = new HashMap<String, String>();
        tagit = new ArrayList<Tag>();
    }

    public Viite(String title, String author) {
        this();
        fields.put("title", title);
        fields.put("author", author);
    }

    public String getAddress() {
        return fields.get("address");
    }

    public void setAddress(String address) {
        fields.put("address", address);
        getTiedot().clear();
    }

    public String getAuthor() {
        return fields.get("author");
    }

    public void setAuthor(String author) {
        fields.put("author", author);   
        getTiedot().clear();
    }

    public String getBooktitle() {
        return fields.get("booktitle");
    }

    public void setBooktitle(String booktitle) {
        fields.put("booktitle", booktitle);
        getTiedot().clear();
    }

    public String getJournal() {
        return fields.get("journal");
    }

    public void setJournal(String journal) {
        fields.put("journal", journal);   
        getTiedot().clear();
    }

    public String getNumber() {
        return fields.get("number");
    }

    public void setNumber(String number) {
        fields.put("number", number);
        getTiedot().clear();
    }

    public String getPages() {
        return fields.get("pages");
    }

    public void setPages(String pages) {
        fields.put("pages", pages); 
        getTiedot().clear();
    }

    public String getPublisher() {
        return fields.get("publisher");
    }

    public void setPublisher(String publisher) {
        fields.put("publisher", publisher);    
        getTiedot().clear();
    }

    public String getTitle() {
        return fields.get("title");
    }

    public void setTitle(String title) {
        fields.put("title", title);
        getTiedot().clear();
    }

    public String getVolume() {
        return fields.get("volume");
    }

    public void setVolume(String volume) {
        fields.put("volume", volume);
        getTiedot().clear();
    }

    public String getYear() {
        return fields.get("year");
    }

    public void setYear(String year) {
        fields.put("year", year);
        getTiedot().clear();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getField(String kentta) {
        return fields.get(kentta);
    }
    
    public void setField(String kentta, String arvo) {
        fields.put(kentta, arvo);
    }
    
    public ArrayList<String> getTiedot() {
        ArrayList<String> fieldsHtml = new ArrayList<String>();
        
        if (fieldsHtml.isEmpty()) {
            
            if (getTitle() != null && getTitle().length() > 0)
                fieldsHtml.add("<b>Title:</b> "+getTitle());

            if (getAuthor() != null && getAuthor().length() > 0)
                fieldsHtml.add("<b>Author:</b> "+getAuthor());
            
            if (getPublisher() != null && getPublisher().length() > 0)
                fieldsHtml.add("<b>Publisher:</b> "+getPublisher());
            
            if (getBooktitle() != null && getBooktitle().length() > 0)
                fieldsHtml.add("<b>Booktitle:</b> "+getBooktitle());
            
            if (getJournal() != null && getJournal().length() > 0)
                fieldsHtml.add("<b>Journal:</b> "+getJournal());
            
            if (getAddress() != null && getAddress().length() > 0)
                fieldsHtml.add("<b>Address:</b> "+getAddress());
            
            if (getYear() != null && getYear().length() > 0)
                fieldsHtml.add("<b>Year:</b> "+getYear());
            
            if (getVolume() != null && getVolume().length() > 0)
                fieldsHtml.add("<b>Volume:</b> "+getVolume());
            
            if (getNumber() != null && getNumber().length() > 0)
                fieldsHtml.add("<b>Number:</b> "+getNumber());
            
            if (getPages() != null && getPages().length() > 0)
                fieldsHtml.add("<b>Pages:</b> "+getPages());
            
            if (getTagit() != null && getTagit().size() > 0) {
                String listString = "";

                for (int i=0; i < getTagit().size(); ++i) {
                    
                    if (i != getTagit().size()-1)
                        listString += getTagit().get(i).getNimi() + ", ";
                    else
                        listString += getTagit().get(i).getNimi();
                }

                fieldsHtml.add("<b>Tags:</b> " + listString);
            }
        }
        return fieldsHtml;
    }
    
    public void setTagit(List<Tag> tagit) {
        if (tagit == null) {
            this.tagit = null;
            return;
        }
        this.tagit = tagit;
        
        for (int i=0; i < tagit.size(); ++i) { // kun viite saa listan siihen kuuluvista tageista
            if (!tagit.get(i).getViitteet().contains(this)) { // lisätään kyseinen viite jokaiselle
                tagit.get(i).getViitteet().add(this); // tagille
            }
        }
    }
    
    public static TreeMap<String, String> getBookKentat() {
        TreeMap<String, String> fields = new TreeMap<String, String>();
        fields.put("title", "<font color=\"red\">*</font> Title: ");
        fields.put("author", "<font color=\"red\">*</font> Author: ");
        fields.put("year", "Year: ");
        fields.put("publisher", "Publisher: ");
        fields.put("address", "Address: ");
        
        return fields;
    }
    
    public static TreeMap<String, String> getArticleKentat() {
        TreeMap<String, String> fields = new TreeMap<String, String>();
        fields.put("title", "<font color=\"red\">*</font> Title: ");
        fields.put("author", "<font color=\"red\">*</font> Author: ");
        fields.put("publisher", "Publisher: ");
        fields.put("year", "Year: ");
        fields.put("address", "Address: ");
        fields.put("pages", "Pages: ");
        fields.put("journal", "<font color=\"red\">*</font> Journal: ");
        fields.put("volume", "Volume: ");
        fields.put("number", "Number: ");
        
        return fields;
    }
    
    public static TreeMap<String, String> getInproceedingsKentat() {
        TreeMap<String, String> fields = new TreeMap<String, String>();
        fields.put("title", "<font color=\"red\">*</font> Title: ");
        fields.put("author", "<font color=\"red\">*</font> Author: ");
        fields.put("publisher", "Publisher: ");
        fields.put("year", "Year: ");
        fields.put("address", "Address: ");
        fields.put("booktitle", "<font color=\"red\">*</font> Booktitle: ");
        fields.put("pages", "Pages: ");
        return fields;
    }
    
    
    public List<Tag> getTagit() {
        return tagit;
    }
    
    public HashMap<String, String> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viite other = (Viite) obj;
        if (this.fields != other.fields && (this.fields == null || !this.fields.equals(other.fields))) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.tagit != other.tagit && (this.tagit == null || !this.tagit.equals(other.tagit))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.fields != null ? this.fields.hashCode() : 0);
        hash = 41 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.tagit != null ? this.tagit.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return "Title: "+getTitle()+"\n Author: "+getAuthor()+"\n Publisher: "+getPublisher()+
                "\n Pages: "+getPages()+"\n Address: "+getAddress()+"\n Year: "+getYear()+
                "\n Booktitle: "+getBooktitle()+"\n Journal: "+getJournal()+"\n Volume: "+
                getVolume()+"\n Number: "+getNumber();
    }
    
}
