<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViiteArto</title>
    </head>
    <body>
        <h1>ViiteArto</h1>

        <h4>Lisää viite</h4>

        <form action="/Viitteet" method="post">
            <select name="viiteTyyppi">
                <option value="book">Book</option>
                <option value="inproceedings">Inproceedings</option>
                <option value="article">Article</option>
            </select>
            <input type="submit" name="valinta" value="Valitse"/>
        </form>

        <br/>

        <!-- Listaa virheet, jos niitä tuli viitettä lisättäessä -->
        <c:forEach var="error"
                   items="${errors}">
            <font color="red">${error}</font><br/>
        </c:forEach>

        <!-- Viitteen lisäämistä tarvittava lomake, joka looppaa kentät, joita voi täyttää
             lomakkeessa -->
        <c:if test="${not empty tiedot}">
            
            <b>${type}</b><br/><br/>
            <b>Tähdellä merkityt tiedot ovat pakollisia.</b>
            
            <form action="/LisaaViite" method="post">
                <table border="0" width="300" cellpadding="3" cellspacing="2" style="background-color: white">
                    
                    <c:forEach var="tiedot"
                               items="${tiedot}">
                        <tr>
                            <td>${tiedot.value}</td><td><input type="text" name="${tiedot.key}"/></td>
                        </tr>
                    </c:forEach>
                </table>

                <input type="hidden" name="type" value="${type}"> <!-- Viitteen tyyppi -->
                <input type="submit" name="lisays" value="Lisää viite"/>
            </form>
        </c:if>

        </br>

        <h4>Viitteet</h4>

        <c:if test="${not empty viitteet}">

            <!-- Listaa viitteet -->
            <table border="1" width="360" cellpadding="3" cellspacing="1" style="background-color: white">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Poista</th>
                </tr>
                <c:forEach var="viite" items="${viitteet}">
                    <tr>
                        <td>${viite.id}</a></td>
                        <td><a href="/ViitteenTiedot?id=${viite.id}">${viite.title}</a></td>
                        <td>${viite.author}</td>
                        <td><a href="/PoistaViite?id=${viite.id}">Poista</a></td>

                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </body>
</html>
