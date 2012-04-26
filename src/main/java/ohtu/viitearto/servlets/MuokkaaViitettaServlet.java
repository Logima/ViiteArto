package ohtu.viitearto.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.*;
import org.apache.commons.lang.WordUtils;

public class MuokkaaViitettaServlet extends HttpServlet {
    private Rekisteri rekisteri = Rekisteri.getInstance();
    private Tietoturva secure = new Tietoturva();
    private PakollisetKentat pakollisetKentat = new PakollisetKentat();
    private long id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        setId(request, response);
        
        response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        setId(request, response);
        
        muokkaaViite(request, response);
        
        response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
    }

    private void muokkaaViite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Viite muokattavaViite = rekisteri.haeViite(id);
        
        for (Map.Entry<String, String[]> entry : ((Map<String, String[]>)request.getParameterMap()).entrySet()) {
            String field = entry.getKey();
            String val = secure.estaCrossSiteScripting(entry.getValue()[0]);
            if (field == null || !field.startsWith("field.") || val == null) continue;
            muokattavaViite.setField(field.substring(6), val);
        }
        
        for (String kentta : pakollisetKentat.getKentat(request.getParameter("type"))) {
            if (!muokattavaViite.containsField(kentta) || muokattavaViite.getField(kentta).length() == 0) {
                secure.lisaaVirhe(kentta, WordUtils.capitalize(kentta) + " ei saa olla tyhjä!");
            }
        }
        
        if (secure.onkoVirheita()) {
            response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
            return;
        }
        
        muokattavaViite.setType(request.getParameter("type"));
        muokkaaTagit(request, muokattavaViite);
        
        rekisteri.lisaaViite(muokattavaViite);
    }
    
    private void muokkaaTagit(HttpServletRequest request, Viite muokattava) {
        String tag = secure.estaCrossSiteScripting(request.getParameter("tags"));
        
        for (int i=0; i < muokattava.getTagit().size(); ++i) {
            List<Viite> taginViitteet = muokattava.getTagit().get(i).getViitteet();
            taginViitteet.remove(muokattava);
            muokattava.getTagit().get(i).setViitteet(taginViitteet);
            rekisteri.lisaaTagi(muokattava.getTagit().get(i));
        }
        
        muokattava.setTagit(null);
        
        if (tag.length() == 0) return;

        String[] tagit = tag.split(",");
        List<Tag> tagiLista = new ArrayList<Tag>();

        for (int i = 0; i < tagit.length; ++i) {
            tagit[i] = tagit[i].trim();
            if (rekisteri.haeTag(tagit[i]) == null) {
                Tag uusi = new Tag(tagit[i]);
                tagiLista.add(uusi);
                rekisteri.lisaaTagi(uusi);
            } else {
                tagiLista.add(rekisteri.haeTag(tagit[i]));
            }
        }
        muokattava.setTagit(tagiLista);
    }

    private void setId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath()+"/Viitteet");
        }
    }
}
