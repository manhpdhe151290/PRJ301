<%-- 
    Document   : category
    Created on : 10-06-2021, 22:15:33
    Author     : Grimmy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Category"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
    </head>
    <body>
        <% //get information(object) from servlet 
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Category> arr = (ArrayList<Category>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>

        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Status</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=(rs.getInt(3)) == 1 ? "Enable" : "Disable"%></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerCategory?service=delete&id=<%=rs.getInt(1)%>">delete</a></td>
                    <td><a href="ControllerCategory?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <input type="submit" onclick=window.location.href="addCategory.jsp" value="Add new Category" />
        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Status</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%for (Category cate : arr) {%>
                <tr>
                    <td><%=cate.getCateID()%></td>
                    <td><%=cate.getCateName()%></td>
                    <td><%=(cate.getStatus()) == 1 ? "Enable" : "Disable"%></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="ControllerCategory?service=delete&id=<%=cate.getCateID()%>">delete</a></td>
                    <td><a href="ControllerCategory?service=update&id=<%=cate.getCateID()%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
