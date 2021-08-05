<%-- 
    Document   : bill
    Created on : 14-06-2021, 13:38:18
    Author     : Grimmy
--%>

<%@page import="entity.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
    </head>
    <body>
        <% //get information(object) from servlet 
            ResultSet rs = (ResultSet) request.getAttribute("ketQua");
            ArrayList<Bill> arr = (ArrayList<Bill>) request.getAttribute("danhSach");
            String title = request.getAttribute("tieuDe").toString();
        %>

        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Date Create</th>
                    <th>Customer Name</th>
                    <th>Customer Phone</th>
                    <th>Customer Address</th>
                    <th>Total</th>
                    <th>Status</th>
                    <th>Customer ID</th>
                    <th>Bill Detail</th>
                </tr>
            </thead>
            <tbody>
                <%for (Bill bill : arr) {%>
                <tr>
                    <td><%=bill.getoID()%></td>
                    <td><%=bill.getDateCreate()%></td>
                    <td><%=bill.getCname()%></td>
                    <td><%=bill.getCphone()%></td>
                    <td><%=bill.getcAddress()%></td>
                    <td><%=bill.getTotal()%></td>
                    <td><%=(bill.getStatus()==1) ? "Done" : "Wait"%></td>
                    <td><%=bill.getCid()%></td>
                    <td><a href="ControllerBill?service=detail&oid=<%=bill.getoID()%>">ViewDetail</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
