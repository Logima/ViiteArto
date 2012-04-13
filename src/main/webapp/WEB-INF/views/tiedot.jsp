<%-- 
    Document   : tiedot
    Created on : 10.4.2012, 15:21:42
    Author     : kennyhei
--%>

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

        <br/>
        <b>${type}</b>
        
        <!-- Listaa viitteen kaikki olemassaolevat tiedot -->
        <ul>           
            <c:forEach var="info" items="${tiedot}">
                <li>${info}</li>
            </c:forEach>
        </ul>

        <br/>
        <form action="/PoistaViite" method="GET">          
            <input type="hidden" name="id" value="${id}"/>        
            <input type="submit" name="poisto" value="Poista viite">
        </form>

        <a href="/Viitteet">Etusivu</a>
    </body>
</html>
