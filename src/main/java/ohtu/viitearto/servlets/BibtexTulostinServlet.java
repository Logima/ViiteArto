package ohtu.viitearto.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Bibtex;
import ohtu.viitearto.Rekisteri;
import ohtu.viitearto.Viite;

public class BibtexTulostinServlet extends HttpServlet {
    
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/x-bibtex;charset=UTF-8");
        
        try {
            long id = Long.parseLong(request.getParameter("id"));

            Viite viite = rekisteri.haeViite(id);
            if (viite == null) {
                return;
            }

            response.setHeader("Content-Disposition", "attachment; filename=\"" + viite.getTitle() + ".bib\"");

            PrintWriter out = response.getWriter();

            Bibtex.output(viite, out);
            out.close();
        } catch (Exception e) {
            List<Viite> viitteet = rekisteri.getViitteet();
            if (viitteet == null) {
                return;
            }
            
            String nimi = request.getParameter("nimi").trim();
            if (nimi.isEmpty()) nimi = "viitteet";

            response.setHeader("Content-Disposition", "attachment; filename=\"" + nimi + ".bib\"");

            PrintWriter out = response.getWriter();
            
            Bibtex.output(viitteet, out);
            out.close();
        }
        
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }// </editor-fold>
}
