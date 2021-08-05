<%-- 
    Document   : updateCustomer
    Created on : 11-06-2021, 16:00:14
    Author     : Grimmy
--%>

<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
    </head>
    <body>
        <%
            Customer cus = (Customer)request.getAttribute("cus");
        %>
        <form action="ControllerCustomer" method="POST">
            <table border="0">
                <caption><h1>Update Customer</h1></caption>
                <tbody>
                    <tr>
                        <td>Customer ID</td>
                        <td><input type="text" name="cid" value="<%=cus.getCid()%>" readonly /></td>
                    </tr>
                    <tr>
                        <td>Customer Name</td>
                        <td><input type="text" name="cname" value="<%=cus.getCname()%>" /></td>
                    </tr>
                    <tr>
                        <td>Customer Phone</td>
                        <td><input type="text" name="cphone" value="<%=cus.getCphone()%>"  /></td>
                    </tr>
                    <tr>
                        <td>Customer Address</td>
                        <td><input type="text" name="cAddress" value="<%=cus.getcAddress()%>" /></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="<%=cus.getUsername()%>"  /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="password" value="<%=cus.getPassword()%>" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td><input type="radio" name="status" value="1" <%=(cus.getStatus()==1?"checked":"")%>/>Enable
                        <input type="radio" name="status" value="0" <%=(cus.getStatus()==0?"checked":"")%>/>Disable</td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Update" name="submit"/>  
               <input type="reset" value="Reset"/></p>
            <input type="hidden" value="updated" name="service"/>
        </form>
    </body>
</html>
