/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author kennyhei
 */
public class Tietoturva {
    
    private static HashMap<String, String> virheet;
    
    public Tietoturva() {
        virheet = new HashMap<String, String>();
        virheet.put("authorError", null);
        virheet.put("titleError", null);
        virheet.put("publisherError", null);
        virheet.put("booktitleError", null);
        virheet.put("pagesError", null);
        virheet.put("addressError", null);
        virheet.put("numberError", null);
        virheet.clear();
    }

    public String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
    
    public void lisaaVirhe(String virheenLaatu, String virhe) {
        virheet.put(virheenLaatu, virhe);
    }
    
    public void tarkistaPakollisetTiedot(String title, String author, String journal, String booktitle) {
       if (title.length() <= 0) {
            lisaaVirhe("titleError", "Title ei saa olla tyhjä!");
        }

        if (author.length() <= 0) {
            lisaaVirhe("authorError", "Author ei saa olla tyhjä!");
        }
        
        if (booktitle != null && booktitle.length() <= 0) {
            lisaaVirhe("booktitleError", "Booktitle ei saa olla tyhjä!");
        }
        
        if (journal != null && journal.length() <= 0) {
            lisaaVirhe("journalError", "Journal ei saa olla tyhjä!");
        }
    }
    
    public void tarkistaNumeroTiedot(String yearString, String volumeString, String numberString) {
        if (yearString != null && yearString.length() > 0) {
            try {
                int year = Integer.parseInt(yearString);
            } catch (Exception e) {
                lisaaVirhe("numberError", "Vain numerot sallittuja!");
                return;
            }
        }
        
        if (volumeString != null && volumeString.length() > 0) {
            try {
                int volume = Integer.parseInt(volumeString);
            } catch (Exception e) {
                lisaaVirhe("numberError", "Vain numerot sallittuja!");
                return;
            }
        }
        
        if (numberString != null && numberString.length() > 0) {
            try {
                int number = Integer.parseInt(numberString);
            } catch (Exception e) {
                lisaaVirhe("numberError", "Vain numerot sallittuja!");
            }
        } 
    }
    
    public String getAuthorError() {
        return virheet.get("authorError");
    }
    
    public String getTitleError() {
        return virheet.get("titleError");
    }
    
    public String getPublisherError() {
        return virheet.get("publisherError");
    }
    
    public String getBooktitleError() {
        return virheet.get("booktitleError");
    }
    
    public String getPagesError() {
        return virheet.get("pagesError");
    }
    
    public String getAddressError() {
        return virheet.get("addressError");
    }
    
    public String getNumberError() {
        return virheet.get("numberError");
    }
    
    public String getJournalError() {
        return virheet.get("journalError");
    }

    public void nollaaVirheet() {
        virheet.clear();
    }
    
    public boolean onkoVirheita() {
        return !virheet.isEmpty();
    }
    
    public ArrayList<String> getVirheIlmoitukset() {
        Collection<String> errors = virheet.values();
        ArrayList<String> kopio = new ArrayList<String>();
        
        for (String string : errors) {
            kopio.add(string);
        }
        
        return kopio;
    }
}
