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

        Tähdellä merkityt tiedot ovat pakollisia.

        <form action="/LisaaViite" method="post">
            <table border="0" width="200" cellpadding="3" cellspacing="2" style="background-color: white">
                <tr>
                    <td><font color="red">*</font> Title:</td><td> <input type="text" name="title"/></td>
                        
                    <c:if test="${not empty titleError}">
                        <td><font color="red">${titleError}</font></td>
                    </c:if>
                </tr>
                <tr>
                    <td><font color="red">*</font> Author:</td><td> <input type="text" name="author"/></td>
                        
                    <c:if test="${not empty authorError}">
                        <td><font color="red">${authorError}</font></td>
                    </c:if>
                </tr>
                <tr>
                    <td>Year: </td><td><input type="text" name="year"/></td>

                    <c:if test="${not empty yearError}">
                        <td><font color="red">${yearError}</font></td>
                    </c:if>

                </tr>
                <tr>
                    <td>Publisher:</td><td> <input type="text" name="publisher"/></td>
                </tr>
            </table>
            <input type="submit" name="lisays" value="Lisää viite"/>
        </form>

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
