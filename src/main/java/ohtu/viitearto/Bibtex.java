package ohtu.viitearto;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    else if (fieldName.equals("number")) v.setNumber(fieldValue);
                    else if (fieldName.equals("pages")) v.setPages(fieldValue);
                    else if (fieldName.equals("publisher")) v.setPublisher(fieldValue);
                    else if (fieldName.equals("title")) v.setTitle(fieldValue);
                    else if (fieldName.equals("volume")) v.setVolume(fieldValue);
                    else if (fieldName.equals("year")) v.setYear(fieldValue);
                } catch(Exception e){
                }
            }
            rekisteri.lisaaViite(v);
        }
        
        
        return true;
    }
    
    public static boolean output(Viite v, Writer writer) {
        BibTeXDatabase db = new BibTeXDatabase();
        
        addEntry(db, v);
        
        try {
            BibTeXFormatter formatter = new BibTeXFormatter();
            formatter.format(db, writer);
        } catch (Exception ex) {
            return false;
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

    public static boolean output(List<Viite> viitteet, PrintWriter writer) {
        BibTeXDatabase db = new BibTeXDatabase();
        for (Viite viite : viitteet) {
            addEntry(db, viite);
        }
        
        try {
            BibTeXFormatter formatter = new BibTeXFormatter();
            formatter.format(db, writer);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    private static void addEntry(BibTeXDatabase db, Viite v){
        BibTeXEntry e = new BibTeXEntry(new Key(v.getType()), new Key(v.getId().toString()));
        
        if (v.getTitle() != null && v.getTitle().length() > 0)
            e.addField(new Key("Title"), new StringValue(v.getTitle(), StringValue.Style.BRACED));

        if (v.getAuthor() != null && v.getAuthor().length() > 0)
            e.addField(new Key("Author"), new StringValue(v.getAuthor(), StringValue.Style.BRACED));

        if (v.getPublisher() != null && v.getPublisher().length() > 0)
            e.addField(new Key("Publisher"), new StringValue(v.getPublisher(), StringValue.Style.BRACED));

        if (v.getBooktitle() != null && v.getBooktitle().length() > 0)
            e.addField(new Key("Booktitle"), new StringValue(v.getBooktitle(), StringValue.Style.BRACED));

        if (v.getJournal() != null && v.getJournal().length() > 0)
            e.addField(new Key("Journal"), new StringValue(v.getJournal(), StringValue.Style.BRACED));

        if (v.getAddress() != null && v.getAddress().length() > 0)
            e.addField(new Key("Address"), new StringValue(v.getAddress(), StringValue.Style.BRACED));

        if (v.getYear() != null && v.getYear().length() > 0)
            e.addField(new Key("Year"), new StringValue(v.getYear() + "", StringValue.Style.BRACED));

        if (v.getVolume() != null && v.getVolume().length() > 0)
            e.addField(new Key("Volume"), new StringValue(v.getVolume() + "", StringValue.Style.BRACED));

        if (v.getNumber() != null && v.getNumber().length() > 0)
            e.addField(new Key("Number"), new StringValue(v.getNumber() + "", StringValue.Style.BRACED));

        if (v.getPages() != null && v.getPages().length() > 0)
            e.addField(new Key("Pages"), new StringValue(v.getPages(), StringValue.Style.BRACED));
        
        db.addObject(e);
    }
}
