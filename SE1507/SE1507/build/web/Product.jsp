<%-- 
    Document   : product
    Created on : 11-06-2021, 18:47:01
    Author     : Grimmy
--%>

<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <input type="button" onclick="window.location.href='ControllerProduct?service=preAdd'" value="add product">
        <%
            java.util.Enumeration em = session.getAttributeNames();
            if (em.hasMoreElements()) {
                String username = "";
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("username")) {
                        username = session.getAttribute("username").toString();%>
        <p>Hello: <%=username%></p>
        <%          }
                }
                if (username.equals("")) {%>
        <a href="ControllerLogin"> Login</a> 
        <%      }
            } else {%>
        <a href="ControllerLogin"> Login</a> 
        <%  }
         //get information(object) from servlet 
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Product> arr = (ArrayList<Product>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
            ArrayList<Category> cl = (ArrayList<Category>) request.getAttribute("catelist");
        %>
<!-- <input type="submit" onclick=window.location.href="ControllerProduct?service=preAdd" value="Add new Product" />-->
        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category</th>
                  <th>Add to Cart</th>
                   
                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><img src="image/<%=rs.getString(5)%>" width="40" height="40"/></td>
                    <td><%=rs.getString(6)%></td>
                    <td><%=(rs.getString(7).equals("1")) ? "Enable" : "Disable"%></td>
                    <%for (Category cate : cl) {
                            if (cate.getCateID() == Integer.parseInt(rs.getString(8))) {
                    %>
                    <td><%=cate.getCateName()%></td>
                    <%      }
                      }%>
                   <td><a href="ControllerProduct?service=add2Cart&pid=<%= rs.getString("pid") %>">Add to Cart</a></td>
<!--                   <td><a onclick ="return confirm('Are you sure?')" href="ControllerProduct?service=delete&pid=">delete</a></td>
                    <td><a href="ControllerProduct?service=update&pid=">update</a></td>-->
                   
                </tr>   
                <%}%>
            </tbody>
        </table>
            <h2><a href="showCart.jsp">Show Shopping Cart</h2>
       
    </body>
</html>
