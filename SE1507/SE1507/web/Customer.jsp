<%-- 
    Document   : customer
    Created on : 11-06-2021, 15:55:44
    Author     : Grimmy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Customer"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer</title>
    </head>
    <body>
        <% //get information(object) from servlet 
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Customer> arr = (ArrayList<Customer>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>
 <input type="submit" onclick=window.location.href="addCustomer.jsp" value="Add new Customer" />
        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Customer Phone</th>
                    <th>Customer Address</th>
                    <th>Username</th>
                    <th>Status</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=rs.getString(7)%></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerCustomer?service=delete&cid=<%=rs.getString(1)%>">delete</a></td>
                    <td><a href="ControllerCustomer?service=update&cid=<%=rs.getString(1)%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>            
       
    </body>
</html>
