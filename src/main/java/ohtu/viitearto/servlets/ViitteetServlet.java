package ohtu.viitearto.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;
import ohtu.viitearto.Tietoturva;
import ohtu.viitearto.Viite;


public class ViitteetServlet extends HttpServlet {

    private Tietoturva security = new Tietoturva();
    private Rekisteri rekisteri = Rekisteri.getInstance();
    private String lomakeTyyppi;
    private TreeMap<String, String> lomakeTiedot = new TreeMap<String, String>();
    private String viiteTyyppi;
    private List<Viite> hakuTulokset;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        if (hakuTulokset != null) {
            request.setAttribute("hakuTulokset", hakuTulokset);
            hakuTulokset = null;
        }
        
        if (lomakeTyyppi != null)
            luoLomake(request); // mennään asettamaan viitteen lisäämistä varten täytettävät kentät
        
        asetaViitteetSivulle(request);     
        asetaMahdollisetVirheIlmoitukset(request);      
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/viitteet.jsp");
        dispatcher.forward(request, response); // ohjaudutaan index.jsp-sivulle
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        hakuTulokset = (List<Viite>) request.getAttribute("tulokset");
        lomakeTyyppi = request.getParameter("viiteTyyppi");
        
        response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille
    }
    
    private void articleLomake() {
        lomakeTiedot = Viite.getArticleKentat();
    }

    private void inproceedingsLomake() {
        lomakeTiedot = Viite.getInproceedingsKentat();
    }

    private void bookLomake() {
        lomakeTiedot = Viite.getBookKentat();
    }

    private void luoLomake(HttpServletRequest request) {
        viiteTyyppi = null;
        lomakeTiedot.clear();

        if (lomakeTyyppi.equals("book")) {
            bookLomake();
            viiteTyyppi = "Book";
        } else if (lomakeTyyppi.equals("inproceedings")) {
            inproceedingsLomake();
            viiteTyyppi = "Inproceedings";
        } else if (lomakeTyyppi.equals("article")) {
            articleLomake();
            viiteTyyppi = "Article";
        }

//        lomakeTiedot.put("tag", "Tags: ");
        request.setAttribute("tiedot", lomakeTiedot);
        lomakeTyyppi = null;
    }

    private void asetaMahdollisetVirheIlmoitukset(HttpServletRequest request) {
        if (security.onkoVirheita())
            request.setAttribute("tiedot", lomakeTiedot);
            
        request.setAttribute("errors", security.getVirheIlmoitukset()); // näytetään virheet, jos niitä on
        security.nollaaVirheet();
    }

    private void asetaViitteetSivulle(HttpServletRequest request) {
        request.setAttribute("type", viiteTyyppi); // viitteen tyyppi
        request.setAttribute("viitteet", rekisteri.getViitteet()); // hakee viitteet tietokannasta
    }
}
