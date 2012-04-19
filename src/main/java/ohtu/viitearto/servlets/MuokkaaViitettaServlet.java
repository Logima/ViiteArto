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
        
        String type = request.getParameter("type");
        
        if (type.equals("Book"))
            muokkaaBook(request, response);
        else if (type.equals("Article"))
            muokkaaArticle(request, response);
        else
            muokkaaInproceedings(request, response);
        
        long id = Long.parseLong(request.getParameter("id"));
        
        response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
    }

    private void muokkaaBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
        
        turva.tarkistaPakollisetTiedot(title, author, null, null);
        turva.tarkistaNumeroTiedot(yearString, null, null);
        
        if (turva.onkoVirheita()) {
            doGet(request, response);
            return;
        }
        
//        lisaaOptionaalisetTiedot(uusiBook, publisher, address, null, yearString, null, null);
        
        rekisteri.paivitaBookViite(id, author, title, publisher, yearString, address);
    }

    private void muokkaaArticle(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
        String pages = turva.estaCrossSiteScripting(request.getParameter("pages"));
        String journal = turva.estaCrossSiteScripting(request.getParameter("journal"));
        String volumeString = turva.estaCrossSiteScripting(request.getParameter("volume"));
        String numberString = turva.estaCrossSiteScripting(request.getParameter("number"));
        
//        rekisteri.paivitaArticleViite(id, author, title, publisher, yearString, address);
    }

    private void muokkaaInproceedings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String booktitle = turva.estaCrossSiteScripting(request.getParameter("booktitle"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
        String pages = turva.estaCrossSiteScripting(request.getParameter("pages"));
        
        turva.tarkistaPakollisetTiedot(title, author, null, booktitle);
        turva.tarkistaNumeroTiedot(yearString, null, null);
        
        if (turva.onkoVirheita()) {
            doGet(request, response);
            return;
        }
        
        Viite uusiInproceedings = new Viite(author, title);
        uusiInproceedings.setType("Inproceedings");
        uusiInproceedings.setBooktitle(booktitle);
        
//        lisaaOptionaalisetTiedot(uusiInproceedings, publisher, address, pages, yearString, null, null);
//        rekisteri.paivitaInproceedingsViite(id, author, title, publisher, yearString, address);
    }

}
