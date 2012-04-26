package ohtu.viitearto;

import java.util.HashMap;


public class PakollisetKentat {
    private HashMap<String, String[]> kentat = new HashMap<String, String[]>();
    
    public PakollisetKentat() {
        kentat.put("Book", new String[]{"title", "author"});
        kentat.put("Article", new String[]{"title", "author", "journal"});
        kentat.put("Improceedings", new String[]{"title", "author", "booktitle"});
    }
    
    public String[] getKentat(String viiteTyyppi) {
        if (!kentat.containsKey(viiteTyyppi)) return new String[]{"title", "author"};
        return kentat.get(viiteTyyppi);
    }
}
