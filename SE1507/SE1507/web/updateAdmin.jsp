<%-- 
    Document   : updateAdmin
    Created on : 11-06-2021, 22:38:42
    Author     : Grimmy
--%>

<%@page import="entity.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Admin</title>
    </head>
    <body>
        <%
            Admin admin = (Admin)request.getAttribute("admin");
        %>
        <form action="ControllerAdmin" method="POST">
            <table border="0">
                <caption><h1>Update Admin</h1></caption>
                <tbody>
                    <tr>
                        <td>Admin ID</td>
                        <td><input type="text" name="adminID" value="<%=admin.getAdminID()%>" readonly /></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="<%=admin.getUsername()%>" readonly/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="<%=admin.getPassword()%>" /></td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Update" name="submit"/>  
               <input type="reset" value="Reset"/></p>
            <input type="hidden" value="updated" name="service"/>
        </form>
    </body>
</html>
