/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.viitearto.servlets;

import java.io.IOException;
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
 * @author Keni
 */
public class LisaaViiteServlet extends HttpServlet {

    private Rekisteri rekisteri = Rekisteri.getInstance();
    private Tietoturva secure = new Tietoturva();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Viitteet"); // ohjataan pyyntö Viitteet-servletille
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");       
        
        String[] type = request.getParameterValues("type");
        Viite uusi = null;
        
        if (type[0].equals("Book"))
            uusi = lisaaBookViite(request, response);
        else if (type[0].equals("Article"))
            uusi = lisaaArticleViite(request, response);
        else if (type[0].equals("Inproceedings"))
            uusi = lisaaInproceedingsViite(request, response);

        rekisteri.lisaaViite(uusi);
        request.getRequestDispatcher("/Viitteet").forward(request, response); // ohjataan pyyntö samalle sivulle
    }

    private Viite lisaaInproceedingsViite(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String yearString = request.getParameter("year");   
        String booktitle = request.getParameter("booktitle");
        String address = request.getParameter("address");
        String pages = request.getParameter("pages");
        
        int year = 0;
        
        title = secure.estaCrossSiteScripting(title);
        author = secure.estaCrossSiteScripting(author);
        publisher = secure.estaCrossSiteScripting(publisher);
        yearString = secure.estaCrossSiteScripting(yearString);
        address = secure.estaCrossSiteScripting(address);
        pages = secure.estaCrossSiteScripting(pages);
        booktitle = secure.estaCrossSiteScripting(booktitle);

        secure.tarkistaPakollisetTiedot(title, author, null, booktitle);
        secure.tarkistaNumeroTiedot(yearString, null, null);
        
        if (secure.onkoVirheita()) {
            doGet(request, response);
            return null;
        }
        
        Viite uusiInproceedings = new Viite(author, title);
        uusiInproceedings.setType("Inproceedings");
        uusiInproceedings.setBooktitle(booktitle);
        
        if (publisher.length() > 0)
            uusiInproceedings.setPublisher(publisher);
        
        if (address.length() > 0)
            uusiInproceedings.setAddress(address);
        
        if (year != 0)
            uusiInproceedings.setYear(year);
        
        if (pages.length() > 0)
            uusiInproceedings.setPages(pages);
        
        return uusiInproceedings;
    }

    private Viite lisaaArticleViite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String yearString = request.getParameter("year");        
        String address = request.getParameter("address");
        String pages = request.getParameter("pages");
        String journal = request.getParameter("journal");
        String volumeString = request.getParameter("volume");
        String numberString = request.getParameter("number");
        
        title = secure.estaCrossSiteScripting(title);
        author = secure.estaCrossSiteScripting(author);
        publisher = secure.estaCrossSiteScripting(publisher);
        yearString = secure.estaCrossSiteScripting(yearString);
        address = secure.estaCrossSiteScripting(address);
        pages = secure.estaCrossSiteScripting(pages);
        journal = secure.estaCrossSiteScripting(journal);
        volumeString = secure.estaCrossSiteScripting(volumeString);
        numberString = secure.estaCrossSiteScripting(numberString);
        
        int year = 0;
        int number = 0;        
        int volume = 0;
        
        secure.tarkistaPakollisetTiedot(title, author, journal, null);
        secure.tarkistaNumeroTiedot(yearString, volumeString, numberString);
        
        if (secure.onkoVirheita()) {
            doGet(request, response);
            return null;
        }
        
        Viite uusiArticle = new Viite(title, author);
        uusiArticle.setType("Article");
        uusiArticle.setJournal(journal);
        
        if (publisher.length() > 0)
            uusiArticle.setPublisher(publisher);
        
        if (address.length() > 0)
            uusiArticle.setAddress(address);
        
        if (year != 0)
            uusiArticle.setYear(year);
        if (volume != 0)
            uusiArticle.setVolume(volume);
        if (number != 0)
            uusiArticle.setNumber(number);
        
        if (pages.length() > 0)
            uusiArticle.setPages(pages);
        
        return uusiArticle;
    }

    private Viite lisaaBookViite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String yearString = request.getParameter("year");        
        String address = request.getParameter("address");
        int year = 0;
        
        title = secure.estaCrossSiteScripting(title);
        author = secure.estaCrossSiteScripting(author);
        publisher = secure.estaCrossSiteScripting(publisher);
        yearString = secure.estaCrossSiteScripting(yearString);
        address = secure.estaCrossSiteScripting(address);
        
        secure.tarkistaPakollisetTiedot(title, author, null, null);
        secure.tarkistaNumeroTiedot(yearString, null, null);
        
        if (secure.onkoVirheita()) {
            doGet(request, response);
            return null;
        }
        
        Viite uusiBook = new Viite(title, author);
        uusiBook.setType("Book");
        
        if (publisher.length() > 0)
            uusiBook.setPublisher(publisher);
        
        if (address.length() > 0)
            uusiBook.setAddress(address);
        
        if (year != 0)
            uusiBook.setYear(year);
        
        return uusiBook;
    }
}
