<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viitteen tiedot</title>
    </head>
    <body>
        <h1>Viitteen tiedot</h1>

        <br>
        <b>${type}</b>
        
        <!-- Listaa viitteen kaikki olemassaolevat tiedot -->
        <ul>           
            <c:forEach var="info" items="${tiedot}">
                <li>${info}</li>
            </c:forEach>
        </ul>    
        
        <!-- Listaa kentät, joiden avulla muokataan viitettä -->
        <c:if test="${not empty mtiedot}">
            <form action="/MuokkaaViitetta" method="post">
                <table border="0" width="300" cellpadding="3" cellspacing="2" style="background-color: white">

                    
                    <c:forEach var="tiedot"
                               items="${mtiedot}">
                        <tr>
                            <td>${tiedot.value}</td><td><input type="text" value="${viiteFields[tiedot.key]}" name="field.${tiedot.key}"></td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td>Tags: </td><td><input type="text" value="${tags}" name="tags"></td>
                        </tr>
                </table>

                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="type" value="${type}">
                <input type="submit" name="tallennus" value="Tallenna">
            </form>
            <br>
        </c:if>
   
            <c:if test="${empty mtiedot}">
                <form action="/ViitteenTiedot" method="POST">
                    <input type="hidden" name="id" value="${id}">        
                    <input type="submit" name="muokkaus" value="Muokkaa">
                </form>

                <form action="/PoistaViite" method="GET">          
                    <input type="hidden" name="id" value="${id}">        
                    <input type="submit" name="poisto" value="Poista viite">
                </form>
            </c:if>
            
        <!-- Listaa virheet, jos niitä tuli viitettä muokattaessa -->
        <c:if test="${not empty errors}">
            <br/>
            <c:forEach var="error"
                       items="${errors}">
                <font color="red">${error}</font><br>
            </c:forEach>
        </c:if>
            
        <br>
        <c:if test="${not empty bibtex}">
            <pre style="border: 1px solid; background-color: #eee; padding: 5px; display: inline-block;">${bibtex}</pre>
            <br>
        </c:if>
        <a href="/BibtexTulostin?id=${id}">BibTeX</a><br>

        <a href="/Viitteet">Etusivu</a>
    </body>
</html>
