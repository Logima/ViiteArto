package ohtu.viitearto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tietoturva {
    
    private static HashMap<String, String> virheet;
    
    public Tietoturva() {
        virheet = new HashMap<String, String>();
    }

    public String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
    
    public void lisaaVirhe(String virheenKentta, String virhe) {
        virheet.put(virheenKentta, virhe);
    }
    
    public String getAuthorError() {
        return virheet.get("author");
    }
    
    public String getTitleError() {
        return virheet.get("title");
    }
    
    public String getPublisherError() {
        return virheet.get("publisher");
    }
    
    public String getBooktitleError() {
        return virheet.get("booktitle");
    }
    
    public String getPagesError() {
        return virheet.get("pages");
    }
    
    public String getAddressError() {
        return virheet.get("address");
    }
    
    public String getNumberError() {
        return virheet.get("number");
    }
    
    public String getJournalError() {
        return virheet.get("journal");
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
