/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

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
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private Long id;

    public Viite() {
    }

    public Viite(String title, String author) {
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
}
