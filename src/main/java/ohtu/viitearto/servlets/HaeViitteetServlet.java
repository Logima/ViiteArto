/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;
import ohtu.viitearto.Tietoturva;
import ohtu.viitearto.Viite;

/**
 *
 * @author kennyhei
 */
public class HaeViitteetServlet extends HttpServlet {

    private Rekisteri rekisteri = Rekisteri.getInstance();
    private Tietoturva turva = new Tietoturva();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String haku = turva.estaCrossSiteScripting(request.getParameter("sana"));
        String viiteTyyppi = turva.estaCrossSiteScripting(request.getParameter("tyyppi"));

        if (haku.length() > 0) {
            if (viiteTyyppi.length() > 0) {
                request.setAttribute("tulokset", rekisteri.haeViiteHaunTuloksena(haku, viiteTyyppi));
            } else {
                request.setAttribute("tulokset", rekisteri.haeViiteHaunTuloksena(haku, null));
            }
        }
        
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/Viitteet");
        dispatcher.forward(request, response); // viedään request-tiedot eteenpäin Viitteet-servletille
    }

}
