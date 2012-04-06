/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;

/**
 *
 * @author Keni
 */
public class ListaServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        request.setAttribute("viesti", "ViiteArto");
        request.setAttribute("viitteet", rekisteri.getViitteet()); // hakee viitteet tietokannasta
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/index.jsp");
        dispatcher.forward(request, response); // ohjaudutaan index.jsp-sivulle
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille
    }
}
