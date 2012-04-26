package ohtu.viitearto.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.*;
import org.apache.commons.lang.WordUtils;

public class LisaaViiteServlet extends HttpServlet {

    private Rekisteri rekisteri = Rekisteri.getInstance();
    private Tietoturva secure = new Tietoturva();
    private PakollisetKentat pakollisetKentat = new PakollisetKentat();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Viitteet"); // ohjataan pyyntö Viitteet-servletille
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");       
        
        
        lisaaViite(request, response);

        
        request.getRequestDispatcher("/Viitteet").forward(request, response); // ohjataan pyyntö samalle sivulle
    }

    private void lisaaViite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Viite uusiViite = new Viite();
        
        for (Map.Entry<String, String[]> entry : ((Map<String, String[]>)request.getParameterMap()).entrySet()) {
            String field = entry.getKey();
            String val = secure.estaCrossSiteScripting(entry.getValue()[0]);
            if (field == null || !field.startsWith("field.") || val == null || val.length() == 0) continue;
            uusiViite.setField(field.substring(6), val);
        }
        
        for (String kentta : pakollisetKentat.getKentat(request.getParameter("type"))) {
            if (!uusiViite.containsField(kentta)) {
                secure.lisaaVirhe(kentta, WordUtils.capitalize(kentta) + " ei saa olla tyhjä!");
            }
        }
        
        if (secure.onkoVirheita()) {
            doGet(request, response);
            return;
        }
        
        uusiViite.setType(request.getParameter("type"));
        lisaaTagit(request, uusiViite);
        
        rekisteri.lisaaViite(uusiViite);
    }
    
    private void lisaaTagit(HttpServletRequest request, Viite viite) {
        String tag = request.getParameter("tags");

        if (tag.length() == 0) return;

        String[] tagit = tag.split(",");
        List<Tag> tagiLista = new ArrayList<Tag>();

        for (int i = 0; i < tagit.length; ++i) {
            tagit[i] = tagit[i].trim();
            if (rekisteri.haeTag(tagit[i]) == null) { // tarkistetaan onko saman niminen tag jo olemassa
                Tag uusi = new Tag(tagit[i]);
                tagiLista.add(uusi);
                rekisteri.lisaaTagi(uusi);
            } else {
                tagiLista.add(rekisteri.haeTag(tagit[i]));
            }
        }
        viite.setTagit(tagiLista);


    }
}
