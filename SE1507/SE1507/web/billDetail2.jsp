<%-- 
    Document   : billDetail
    Created on : 14-06-2021, 13:53:04
    Author     : Grimmy
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<%@page import="entity.Customer"%>
<%@page import="entity.Bill"%>
<%@page import="entity.BillDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Detail</title>
    </head>
    <body>
        <%
            String title = request.getAttribute("tieuDe").toString();
            ResultSet rs1 = (ResultSet) request.getAttribute("ketQua1");
            ResultSet rs2 = (ResultSet) request.getAttribute("ketQua2");
        %>
        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <tbody>
                <%while (rs1.next()) {%>
                <tr>
                    <td><h3>Order</h3></td>
                    <td>
                        <p>Order ID:   <%=rs1.getString(1) %></p>
                        <p>Date Create:<%=rs1.getString(2)%></p>
                        <p>Status:     <%=(rs1.getString(3).equals("1")) ? "Enable" : "Disable"%></p>
                    </td>
                </tr>
                <tr>
                    <td><h3>Product</h3></td>
                    <td>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Category</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  double totalbill =0;
                                    while (rs2.next()) {
                                    double total =0;
                                    total= Double.parseDouble(rs2.getString(14))*Integer.parseInt(rs2.getString(15));
                                    totalbill += total;
                                %>
                                <tr>
                                    <td><%=rs2.getString(12)%></td>
                                    <td><%=rs2.getString(13)%></td>
                                    <td><%=rs2.getString(14)%></td>
                                    <td><%=rs2.getString(17)%></td>
                                    <td><%=rs2.getString(15)%></td>
                                    <td><%=total%></td>
                                </tr>
                                <%}%>
                                <tr>
                                    <td>Total</td>
                                    <td><%=totalbill%></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td><h3>Customer</h3></td>
                    <td>
                        <h2>Buyer</h2>
                        <p>Customer ID:     <%=rs1.getString(4) %></p>
                        <p>Customer Name:   <%=rs1.getString(5) %></p>
                        <p>Customer Phone:  <%=rs1.getString(6) %></p>
                        <p>Customer Address:<%=rs1.getString(7) %></p>
                        <p>Username:        <%=rs1.getString(8) %></p>
                        <h2>Receiver</h2>
                        <p> Name:   <%=rs1.getString(9) %></p>
                        <p> Phone:  <%=rs1.getString(10) %></p>
                        <p> Address:<%=rs1.getString(11) %></p>
                    </td>
                </tr>
                <%break;}%>
            </tbody>
        </table>
        <input type="submit" onclick=window.location.href="ControllerBill?service=displayAll" value="Back to bill list" />
    </body>
</html>
