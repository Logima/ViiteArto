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

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));
        Viite viite = rekisteri.haeViite(id); //tallettaa viitteen tyypin muuttujaan
        System.out.println("AEGTGAEGAEGAEAEG");
        if(viite.getType().equals("Book")){
            muokkaaBookViite(viite, request, response);
        }
        else if(viite.getType().equals("Inproceeding")){
            muokkaaInproceedingViite(viite, request, response);
        }
        else if(viite.getType().equals("Article")){
            muokkaaArticleViite(viite, request, response);
        }
        
            

        
        
        
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void muokkaaBookViite(Viite viite, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("muokkaaBooktitle", viite.getTitle());
       request.setAttribute("muokkaaAuthor", viite.getAuthor());
       request.setAttribute("muokkaaYear", viite.getYear());
       request.setAttribute("viitteenId", viite.getId());
        System.out.println("hsrhsrhsrsrrsrsr");
        
       RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/tiedot.jsp");
        dispatcher.forward(request, response);
    }

    private void muokkaaInproceedingViite(Viite viite, HttpServletRequest request, HttpServletResponse response) {
       throw new UnsupportedOperationException("Not yet implemented");
        

    }

    private void muokkaaArticleViite(Viite viite, HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
