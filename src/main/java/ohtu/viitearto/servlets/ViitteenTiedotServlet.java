package ohtu.viitearto.servlets;

import java.io.IOException;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Bibtex;
import ohtu.viitearto.Rekisteri;
import ohtu.viitearto.Tietoturva;
import ohtu.viitearto.Viite;

public class ViitteenTiedotServlet extends HttpServlet {

    private Rekisteri rekisteri = Rekisteri.getInstance();
    private Tietoturva turva = new Tietoturva();
    private Viite muokattava;
    private boolean muokataanko = false;
    private TreeMap<String, String> muokkausTiedot = new TreeMap<String, String>();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (!muokataanko) {
            asetaTiedot(request);
        } else {
            maaritaViitteenTyyppi(muokattava);
            asetaMuokkaustiedot(request);
            muokataanko = false;
        }
        
        asetaMahdollisetVirheIlmoitukset(request);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/tiedot.jsp");
        dispatcher.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {
            long id = Long.parseLong(request.getParameter("id"));
            muokattava = rekisteri.haeViite(id);
            muokataanko = true;

            response.sendRedirect(request.getRequestURI() + "?id=" + muokattava.getId()); // POST-pyynnöt ohjataan doGetille
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath()+"/Viitteet");
        }
        
    }

    private void maaritaViitteenTyyppi(Viite muokattava) {
        if (muokattava.getType().equals("Book"))
            muokkausTiedot = Viite.getBookKentat();
        else if (muokattava.getType().equals("Inproceedings"))
            muokkausTiedot = Viite.getInproceedingsKentat();
        else
            muokkausTiedot = Viite.getArticleKentat();
    }

    private void asetaTiedot(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));

        Viite viite = rekisteri.haeViite(id);

        request.setAttribute("tiedot", viite.getTiedot());
        request.setAttribute("id", viite.getId());
        request.setAttribute("type", viite.getType());
        request.setAttribute("bibtex", Bibtex.outputString(viite));
    }

    private void asetaMuokkaustiedot(HttpServletRequest request) {
        request.setAttribute("viiteFields", muokattava.getFields());
        request.setAttribute("mtiedot", muokkausTiedot);
        request.setAttribute("tags", muokattava.getTagitStringina());
        request.setAttribute("id", muokattava.getId());
        request.setAttribute("type", muokattava.getType());
    }
    
    private void asetaMahdollisetVirheIlmoitukset(HttpServletRequest request) {
        request.setAttribute("errors", turva.getVirheIlmoitukset()); // näytetään virheet, jos niitä on
        turva.nollaaVirheet();
    }
}
