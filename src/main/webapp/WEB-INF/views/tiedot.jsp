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
        <ul>
            <li>Title: ${title}</li>

            <li>Author: ${author}</li>

            <c:if test="${year!='0'}">
                <li>Year: ${year}</li>
            </c:if>

            <li>Publisher: ${publisher}</li>
            <li>Booktitle: ${booktitle}</li>
            <li>Pages: ${pages}</li>
            <li>Address: ${address}</li>
            <li>Volume: ${volume}</li>
            <li>Number: ${number}</li>
            <li>Journal: ${journal}</li>
        </ul>

        <br/>
        <form action="/PoistaViite" method="GET">          
            <input type="hidden" name="id" value="${id}"/>        
            <input type="submit" name="poisto" value="Poista viite">
        </form>

        <a href="/Viitteet">Etusivu</a>
    </body>
</html>
