/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListaServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        
        request.setAttribute("viesti", "ViiteArto");
        request.setAttribute("viitteet", rekisteri.getViitteet()); // hakee viitteet tietokannasta
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response); // ohjaudutaan index.jsp-sivulle
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille
    }
}
