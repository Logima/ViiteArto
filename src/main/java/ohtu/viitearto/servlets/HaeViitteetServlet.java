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
        
        String hakusanat = turva.estaCrossSiteScripting(request.getParameter("hakuSanat"));
        String kentta = request.getParameter("kentta");
        
        String viiteTyyppi = request.getParameter("tyyppi");

        if (hakusanat.length() > 0) {

            String[] sanat = hakusanat.split(",");

            if (kentta.equals("tag")) {
                if (viiteTyyppi.length() > 0) {
                    request.setAttribute("tulokset", rekisteri.haeViiteTageilla(viiteTyyppi, sanat));
                } else {
                    request.setAttribute("tulokset", rekisteri.haeViiteTageilla(null, sanat));
                }
            } else {

                if (viiteTyyppi.length() > 0) {
                    request.setAttribute("tulokset", rekisteri.haeViiteHakuSanoilla(viiteTyyppi, kentta, sanat));
                } else {
                    request.setAttribute("tulokset", rekisteri.haeViiteHakuSanoilla(null, kentta, sanat));
                }
            }
        }
        
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/Viitteet");
        dispatcher.forward(request, response); // viedään request-tiedot eteenpäin Viitteet-servletille
    }

}
