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
 * @author kennyhei
 */
public class ViitteenTiedotServlet extends HttpServlet {
    
    private Rekisteri rekisteri = new Rekisteri();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        long id = Long.parseLong(request.getParameter("id"));
        
        Viite viite = rekisteri.haeViite(id);
        
        request.setAttribute("title", viite.getTitle());
        request.setAttribute("author", viite.getAuthor());
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/tiedot.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
