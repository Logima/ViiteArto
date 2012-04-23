package ohtu.viitearto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlImport {
    public static void importUrl(String url) {
        Tietoturva security = new Tietoturva();
        
        url = url.trim();
        if (url.isEmpty()) {
            security.lisaaVirhe("urlImportError", "Tyhjä URL annettu.");
            return;
        }

        try {

            String bibtex = "";

            if (url.contains("dl.acm.org") && url.matches(".*id=\\d+.*")) {
                URL u;
                if (url.matches(".*id=\\d+\\.\\d+.*")) {
                    u = new URL("http://dl.acm.org/exportformats.cfm?expformat=bibtex&id=" + url.replaceAll(".+id=\\d+\\.(\\d+).*", "$1") + "&parent_id=" + url.replaceAll(".+id=(\\d+).*", "$1"));
                } else {
                    u = new URL("http://dl.acm.org/exportformats.cfm?expformat=bibtex&id=" + url.replaceAll(".+id=(\\d+).*", "$1"));
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                String str;
                boolean save = false;
                while ((str = in.readLine()) != null) {
                    if (str.contains("</pre>")) {
                        break;
                    }
                    if (save) {
                        bibtex += str + "\n";
                    }
                    if (str.contains("<PRE id=\"")) {
                        save = true;
                    }
                }
                in.close();
            }
            System.out.println("bibtex:" + bibtex);
            if (!bibtex.isEmpty()) {
                Bibtex.importString(bibtex);
            } else {
                security.lisaaVirhe("urlImportError", "URL import epäonnistui.");
            }
        } catch (Exception e) {
        }
    }
}
