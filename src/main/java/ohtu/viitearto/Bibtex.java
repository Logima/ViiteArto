package ohtu.viitearto;

import java.io.*;
import java.util.Map;
import org.jbibtex.*;

public class Bibtex {
    
    public static boolean importFile(File file) {
        BibTeXDatabase db = parse(file);
        if (db == null) return false;
        Rekisteri rekisteri = Rekisteri.getInstance();
        
        for (Map.Entry<Key, BibTeXEntry> entry : db.getEntries().entrySet()) {
            Viite v = new Viite();
            v.setType(entry.getValue().getType().getValue());
            for (Map.Entry<Key, Value> entry1 : entry.getValue().getFields().entrySet()) {
                try {
                    String fieldName = entry1.getKey().getValue().toLowerCase();
                    String fieldValue = entry1.getValue().toUserString();
                    if (fieldName.equals("address")) v.setAddress(fieldValue);
                    else if (fieldName.equals("author")) v.setAuthor(fieldValue);
                    else if (fieldName.equals("booktitle")) v.setBooktitle(fieldValue);
                    else if (fieldName.equals("journal")) v.setJournal(fieldValue);
                    else if (fieldName.equals("number")) v.setNumber(Integer.parseInt(fieldValue));
                    else if (fieldName.equals("pages")) v.setPages(fieldValue);
                    else if (fieldName.equals("publisher")) v.setPublisher(fieldValue);
                    else if (fieldName.equals("title")) v.setTitle(fieldValue);
                    else if (fieldName.equals("volume")) v.setVolume(Integer.parseInt(fieldValue));
                    else if (fieldName.equals("year")) v.setYear(Integer.parseInt(fieldValue));
                } catch(Exception e){
                }
            }
            rekisteri.lisaaViite(v);
        }
        
        
        return true;
    }
    
    private static BibTeXDatabase parse(File file) {
        try {
            Reader reader = new FileReader(file);
            BibTeXParser parser = new BibTeXParser();
            return parser.parse(reader);
        } catch (Exception e) {
            return null;
        }
    }
}
