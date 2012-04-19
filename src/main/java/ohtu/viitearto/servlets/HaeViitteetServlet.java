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
        
        String ekaHakusana = turva.estaCrossSiteScripting(request.getParameter("ekaSana"));
        String tokaHakusana = turva.estaCrossSiteScripting(request.getParameter("tokaSana"));
        
        String operand = request.getParameter("operand");
        
        String ekaKentta = request.getParameter("ekaKentta");
        String tokaKentta = request.getParameter("tokaKentta");
        String viiteTyyppi = request.getParameter("tyyppi");

        if (ekaHakusana.length() > 0 && tokaHakusana.length() <= 0) { // haetaan yhdellä hakusanalla
            if (ekaKentta.equals("tag")) {
                
                if (viiteTyyppi.length() > 0)
                    request.setAttribute("tulokset", rekisteri.haeViiteTageilla(ekaHakusana, viiteTyyppi));
                else
                    request.setAttribute("tulokset", rekisteri.haeViiteTageilla(ekaHakusana, null));
                
            } else {
                
                if (viiteTyyppi.length() > 0)
                    request.setAttribute("tulokset", rekisteri.haeViiteYhdellaHakuSanalla(ekaHakusana, viiteTyyppi, ekaKentta)); // haetaan viitetyypin kanssa
                else
                    request.setAttribute("tulokset", rekisteri.haeViiteYhdellaHakuSanalla(ekaHakusana, null, ekaKentta)); // haetaan ilman viitetyyppiä
            }
        }
        
        if (ekaHakusana.length() > 0 && tokaHakusana.length() > 0) { // haetaan kahdella hakusanalla

            if (viiteTyyppi.length() > 0) {
                request.setAttribute("tulokset", rekisteri.haeViiteKahdellaHakuSanalla(ekaHakusana, tokaHakusana, viiteTyyppi, ekaKentta, tokaKentta, operand)); // haetaan viitetyypin kanssa
            } else {
                request.setAttribute("tulokset", rekisteri.haeViiteKahdellaHakuSanalla(ekaHakusana, tokaHakusana, null, ekaKentta, tokaKentta, operand)); // haetaan ilman viitetyyppiä
            }

        }
        
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/Viitteet");
        dispatcher.forward(request, response); // viedään request-tiedot eteenpäin Viitteet-servletille
    }

}
