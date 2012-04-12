package ohtu.viitearto.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;
import ohtu.viitearto.Tietoturva;


public class ViitteetServlet extends HttpServlet {

    private Tietoturva security = new Tietoturva();
    private Rekisteri rekisteri = new Rekisteri();
    private String lomakeTyyppi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
 
        
        if (lomakeTyyppi != null) {
            if (lomakeTyyppi.equals("book")) {
                bookLomake(request, response);
            } else if (lomakeTyyppi.equals("inproceedings")) {
                inproceedingsLomake(request, response);
            } else if (lomakeTyyppi.equals("article")) {
                articleLomake(request, response);
            }

            lomakeTyyppi = null;
        }
        
        request.setAttribute("yearError", security.getYearError());
        request.setAttribute("authorError", security.getAuthorError());
        request.setAttribute("titleError", security.getTitleError());
        security.nollaaVirheet();
        
        request.setAttribute("viitteet", rekisteri.getViitteet()); // hakee viitteet tietokannasta
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/viitteet.jsp");
        dispatcher.forward(request, response); // ohjaudutaan index.jsp-sivulle
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        lomakeTyyppi = request.getParameter("viiteTyyppi");
        
        response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille
    }
    
    private void articleLomake(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Title: ");
        request.setAttribute("author", "Author: ");
        request.setAttribute("publisher", "Publisher: ");
        request.setAttribute("year", "Year: ");
        request.setAttribute("address", "Address: ");
        request.setAttribute("pages", "Pages: ");
        request.setAttribute("journal", "Journal: ");
        request.setAttribute("volume", "Volume: ");
        request.setAttribute("number", "Number: ");
    }

    private void inproceedingsLomake(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Title: ");
        request.setAttribute("author", "Author: ");
        request.setAttribute("publisher", "Publisher: ");
        request.setAttribute("year", "Year: ");
        request.setAttribute("address", "Address: ");
        request.setAttribute("booktitle", "Booktitle: ");
        request.setAttribute("pages", "Pages: ");
    }

    private void bookLomake(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Title: ");
        request.setAttribute("author", "Author: ");
        request.setAttribute("publisher", "Publisher: ");
        request.setAttribute("year", "Year: ");
        request.setAttribute("address", "Address: ");
    }
}
