/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;
import ohtu.viitearto.Tag;
import ohtu.viitearto.Tietoturva;
import ohtu.viitearto.Viite;
/**
 *
 * @author hekarhu
 */
public class MuokkaaViitettaServlet extends HttpServlet {
    private Rekisteri rekisteri = Rekisteri.getInstance();
    private Tietoturva turva = new Tietoturva();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        long id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
        
        rekisteri.paivitaBookViite(id, author, title, publisher, yearString, address);
        response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
    }

}
