<%-- 
    Document   : Home Page
    Created on : 09-06-2021, 16:31:56
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
      <style>
        p{
            text-align: center;
        }
        a{
            float:right;
           
        }
    </style>
    <body>
        <%
            String username = request.getAttribute("username").toString();
        %>
        <h2 style="text-align:center;" >Hello: <%=username%></h2>
        
        <h1 style="text-align:center" >Home Page</h1>
        <p>
            <input type="submit" onclick=window.location.href="ControllerCustomer?service=displayAll" value="Customer" />
            <input type="submit" onclick=window.location.href="ControllerAdmin?service=displayAll" value="Admin" />
            <input type="submit" onclick=window.location.href="ControllerProduct?service=displayAll" value="Product" />
            <input type="submit" onclick=window.location.href="ControllerCategory?service=displayAll" value="Category" />
            <input type="submit" onclick=window.location.href="ControllerBill?service=displayAll" value="Bill" />
            <input type="submit" onclick=window.location.href="ControllerLogin" value="Login" />
            <input type="submit" onclick=window.location.href="logout" value="Logout" />
        </p>
    </body>
</html>
