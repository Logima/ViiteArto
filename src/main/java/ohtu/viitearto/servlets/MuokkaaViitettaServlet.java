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
    private long id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
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
        
        response.sendRedirect(request.getContextPath()+"/ViitteenTiedot?id="+id);
    }

    private void muokkaaBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
             
        if (!pakollistenTietojenTarkistus(request, response, title, author, null, null)) return;
        
        Viite muokattava = rekisteri.haeViite(id);
        
        muutaTiedot(muokattava, title, author, publisher, address, null, yearString, null, null, null, null);
        
        rekisteri.lisaaViite(muokattava);
    }

    private void muokkaaArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
        String pages = turva.estaCrossSiteScripting(request.getParameter("pages"));
        String journal = turva.estaCrossSiteScripting(request.getParameter("journal"));
        String volumeString = turva.estaCrossSiteScripting(request.getParameter("volume"));
        String numberString = turva.estaCrossSiteScripting(request.getParameter("number"));   
        
        if (!pakollistenTietojenTarkistus(request, response, title, author, journal, null)) return;
        
        Viite muokattava = rekisteri.haeViite(id);
        
        muutaTiedot(muokattava, title, author, publisher, address, pages, yearString, volumeString, numberString, null, journal);
        rekisteri.lisaaViite(muokattava);
    }

    private void muokkaaInproceedings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Long.parseLong(request.getParameter("id"));
        
        String title = turva.estaCrossSiteScripting(request.getParameter("title"));
        String author = turva.estaCrossSiteScripting(request.getParameter("author"));
        String publisher = turva.estaCrossSiteScripting(request.getParameter("publisher"));
        String yearString = turva.estaCrossSiteScripting(request.getParameter("year"));
        String booktitle = turva.estaCrossSiteScripting(request.getParameter("booktitle"));
        String address = turva.estaCrossSiteScripting(request.getParameter("address"));
        String pages = turva.estaCrossSiteScripting(request.getParameter("pages"));
        
        if (!pakollistenTietojenTarkistus(request, response, title, author, null, booktitle)) return;
        
        Viite muokattava = rekisteri.haeViite(id);
        
        muutaTiedot(muokattava, title, author, publisher, address, pages, yearString, null, null, booktitle, null);
        rekisteri.lisaaViite(muokattava);
    }
    
    private void muutaTiedot(Viite muokattava, String title, String author, String publisher, String address, String pages,
            String year, String volume, String number, String booktitle, String journal) {
        
        muokattava.setTitle(title);
        muokattava.setAuthor(author);  
        muokattava.setPublisher(publisher);
        muokattava.setAddress(address);
        muokattava.setPages(pages);
        muokattava.setBooktitle(booktitle);
        muokattava.setJournal(journal);
        
        if (year != null && year.length() > 0) {
            try {
                int nro = Integer.parseInt(year);
                muokattava.setYear(year);
            } catch (Exception e) {
            }
        } else {
            muokattava.setYear(year);
        }
        
        if (volume != null && volume.length() > 0) {
            try {
                int nro = Integer.parseInt(volume);
                muokattava.setVolume(volume);
            } catch (Exception e) {
            }
        } else {
            muokattava.setVolume(volume);
        }
        
        if (number != null && number.length() > 0) {
            try {
                int nro = Integer.parseInt(number);
                muokattava.setNumber(number);
            } catch (Exception e) {
            }
        } else {
            muokattava.setNumber(number);
        }
    }

    private boolean pakollistenTietojenTarkistus(HttpServletRequest request, HttpServletResponse response, String title, String author, String journal, String booktitle) throws ServletException, IOException {
        turva.tarkistaPakollisetTiedot(title, author, journal, booktitle);
        
        if (turva.onkoVirheita()) {
            doGet(request, response);
            return false;
        }
        
        return true;
    }

}
