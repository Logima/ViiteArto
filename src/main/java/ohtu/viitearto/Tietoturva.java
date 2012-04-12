/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto;

import java.util.HashMap;

/**
 *
 * @author kennyhei
 */
public class Tietoturva {
    
    public static HashMap<String, String> virheet;
    
    public Tietoturva() {
        virheet = new HashMap<String, String>();
        virheet.put("yearVirhe", null);
        virheet.put("authorVirhe", null);
        virheet.put("titleVirhe", null);
        virheet.put("authorVirhe", null);
    }

    public String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
    
    public void lisaaVirhe(String virheenLaatu, String virhe) {
        virheet.put(virheenLaatu, virhe);
    }

    public void nollaaVirheet() {
        virheet.clear();
    }
}
