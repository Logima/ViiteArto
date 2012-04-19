/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.persistence.*;

/**
 *
 * @author Keni
 */

@Entity
@TableGenerator(name="tab", initialValue=0, allocationSize=1)
public class Viite implements Serializable {
    
    @Column
    private String title;  
    
    @Column
    private String author;  
    
    @Column
    private String journal;
    
    @Column
    private String booktitle; 
    
    @Column
    private String publisher;  
    
    @Column
    private String pages; 
    
    @Column
    private String address;
    
    @Column
    private int yearMade;   
    
    @Column
    private int volume;
    
    @Column
    private int number;
    
    @Column
    private String type;
    
    /*
     * Sisältää tiedot viitteen eri kenttien sisällöistä.
     */
    private ArrayList<String> fields;
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private Long id;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn
    private List<Tag> tagit; // lista tageista, jotka viitteellä on

    public Viite() {
        fields = new ArrayList<String>();
        tagit = new ArrayList<Tag>();
    }

    public Viite(String title, String author) {
        this();
        this.title = title;
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getYear() {
        return yearMade;
    }

    public void setYear(int yearMade) {
        this.yearMade = yearMade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public ArrayList<String> getTiedot() {
        if (fields.isEmpty()) {
            
            if (getTitle() != null && getTitle().length() > 0)
                fields.add("<b>Title:</b> "+getTitle());

            if (getAuthor() != null && getAuthor().length() > 0)
                fields.add("<b>Author:</b> "+getAuthor());
            
            if (getPublisher() != null && getPublisher().length() > 0)
                fields.add("<b>Publisher:</b> "+getPublisher());
            
            if (getBooktitle() != null && getBooktitle().length() > 0)
                fields.add("<b>Booktitle:</b> "+getBooktitle());
            
            if (getJournal() != null && getJournal().length() > 0)
                fields.add("<b>Journal:</b> "+getJournal());
            
            if (getAddress() != null && getAddress().length() > 0)
                fields.add("<b>Address:</b> "+getAddress());
            
            if (getYear() != 0)
                fields.add("<b>Year:</b> "+getYear());
            
            if (getVolume() != 0)
                fields.add("<b>Volume:</b> "+getVolume());
            
            if (getNumber() != 0)
                fields.add("<b>Number:</b> "+getNumber());
            
            if (getPages() != null && getPages().length() > 0)
                fields.add("<b>Pages:</b> "+getPages());
            
            if (getTagit() != null && getTagit().size() > 0) {
                String listString = "";

                for (int i=0; i < getTagit().size(); ++i) {
                    
                    if (i != getTagit().size()-1)
                        listString += getTagit().get(i).getNimi() + ", ";
                    else
                        listString += getTagit().get(i).getNimi();
                }

                fields.add("<b>Tags:</b> " + listString);
            }
        }
        return fields;
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
}
