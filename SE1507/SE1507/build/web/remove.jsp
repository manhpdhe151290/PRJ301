<%-- 
    Document   : remove
    Created on : Jun 24, 2021, 2:48:03 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            // remove that product(with pid) from cart(session map)
            String pid = request.getParameter("pid");
            session.removeAttribute(pid);
            
            //return to cart
            response.sendRedirect("showCart.jsp");
        %>
    </body>
</html>
