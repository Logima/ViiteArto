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
import ohtu.viitearto.Viite;

/**
 *
 * @author Keni
 */
public class LisaaViiteServlet extends HttpServlet {
    
    private Rekisteri rekisteri = new Rekisteri();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Viitteet"); // ohjataan pyyntö Lista-servletille
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        
        if (title.length() > 0 && author.length() > 0) {
            Viite viite = new Viite(title, author);
            System.out.println(viite.toString());
            rekisteri.lisaaViite(viite);
            request.getRequestDispatcher("/Viitteet").forward(request, response); // ohjataan pyyntö samalle sivulle
        } else {
            doGet(request, response);
            return;
        }
    }
}
