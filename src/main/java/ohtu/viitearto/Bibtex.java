package ohtu.viitearto;

import java.io.*;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.WordUtils;
import org.jbibtex.*;

public class Bibtex {
    
    public static boolean importFile(File file) {
        try {
            Reader reader = new FileReader(file);
            return importFromReader(reader);
        } catch (FileNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean importString(String str) {
        StringReader reader = new StringReader(str);
        return importFromReader(reader);
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
    
    public static String outputString(Viite v) {
        StringWriter sw = new StringWriter();
        if(!output(v, sw)) return null;
        return sw.toString();
    }
    
    private static boolean importFromReader(Reader reader) {
        BibTeXDatabase db = parse(reader);
        if (db == null) return false;
        lisaaKantaan(db);
        return true;
    }
    
    private static void lisaaKantaan(BibTeXDatabase db) {
        Rekisteri rekisteri = Rekisteri.getInstance();
        
        for (Map.Entry<Key, BibTeXEntry> entry : db.getEntries().entrySet()) {
            Viite v = new Viite();
            v.setType(entry.getValue().getType().getValue());
            for (Map.Entry<Key, Value> field : entry.getValue().getFields().entrySet()) {
                v.setField(field.getKey().getValue().toLowerCase(), field.getValue().toUserString());
            }
            String type = WordUtils.capitalize(v.getType());
            v.setType(type);
            rekisteri.lisaaViite(v);
        }
    }
    
    private static BibTeXDatabase parse(Reader reader) {
        try {
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
    
    private static void addEntry(BibTeXDatabase db, Viite v) {
        BibTeXEntry e = new BibTeXEntry(new Key(v.getType()), new Key(v.getId().toString()));
        
        for (Map.Entry<String, String> entry : v.getFields().entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null ||
                    entry.getKey().length() == 0 || entry.getValue().length() == 0) continue;
            e.addField(new Key(entry.getKey()), new StringValue(entry.getValue(), StringValue.Style.BRACED));
        }
        
        db.addObject(e);
    }
}
