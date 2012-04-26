package ohtu.viitearto.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.viitearto.Rekisteri;

public class PoistaViiteServlet extends HttpServlet {

    private Rekisteri rekisteri = Rekisteri.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {
            long id = Long.parseLong(request.getParameter("id"));
            rekisteri.poistaViite(id);
        } catch (Exception e) {
            
        }
        
        response.sendRedirect(request.getContextPath()+"/Viitteet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
    }

}
