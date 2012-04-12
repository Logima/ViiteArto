package ohtu.viitearto.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;


public class ViitteetServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();
    private String virheIlmoitus;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
           
        if (virheIlmoitus != null) {
            request.setAttribute("virhe", virheIlmoitus);
            virheIlmoitus = null;
        }
        
        request.setAttribute("viitteet", rekisteri.getViitteet()); // hakee viitteet tietokannasta
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/viitteet.jsp");
        dispatcher.forward(request, response); // ohjaudutaan index.jsp-sivulle
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        virheIlmoitus = (String) request.getAttribute("virhe");
        response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille
    }
}
