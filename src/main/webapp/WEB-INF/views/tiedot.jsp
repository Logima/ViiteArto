<%-- 
    Document   : tiedot
    Created on : 10.4.2012, 15:21:42
    Author     : kennyhei
--%>

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
        </ul>

        <br/>
        <form action="${pageContext.request.contextPath}/PoistaViite"
              method="GET">          
            <input type="hidden" name="id" value="${id}"/>        
            <input type="submit" value="Poista viite"> 
        </form>
            
        <a href="${pageContext.request.contextPath}/Viitteet">Etusivu</a>
    </body>
</html>
