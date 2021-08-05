<%-- 
    Document   : admin
    Created on : 11-06-2021, 22:36:17
    Author     : Grimmy
--%>

<%@page import="entity.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <% //get information(object) from servlet 
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Admin> arr = (ArrayList<Admin>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>
 <input type="submit" onclick=window.location.href="addAdmin.jsp" value="Add new Admin" />
        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <thead>
                <tr>
                    <th>Admin ID</th>
                    <th>Username</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerAdmin?service=delete&id=<%=rs.getInt(1)%>">delete</a></td>
                    <td><a href="ControllerAdmin?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
       
    </body>
</html>
