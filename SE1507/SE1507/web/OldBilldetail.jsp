<%-- 
    Document   : billDetail
    Created on : 14-06-2021, 13:53:04
    Author     : Grimmy
--%>

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
            Bill b = (Bill) request.getAttribute("billdetail");
            ArrayList<BillDetail> bdl = (ArrayList<BillDetail>) request.getAttribute("billdetaillist");
            Customer cus = (Customer) request.getAttribute("customer");
            ArrayList<Product> prolist = (ArrayList<Product>) request.getAttribute("product");
            ArrayList<Category> cl = (ArrayList<Category>) request.getAttribute("categorylist");

        %>
        <table border="1">
            <caption><h1><%=title%></h1></caption>
            <tbody>
                <tr>
                    <td><h3>Order</h3></td>
                    <td>
                        <p>Order ID:   <%=b.getoID()%></p>
                        <p>Date Create:<%=b.getDateCreate()%></p>
                        <p>Status:     <%=(b.getStatus() == 1) ? "Enable" : "Disable"%></p>
                       
                    </td>
                </tr>
                <tr>
                    <% Double total =0.0;%>
                    <%for(BillDetail bd : bdl){%>
                    <% total +=bd.getTotal();%>
                    <%}%>
                    
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
                                
                                <%for (BillDetail bd : bdl) {%>
                                <tr>
                                    <td><%=bd.getPid()%></td>
                                    <%for (Product pro : prolist) {
                                        if (pro.getPid().equals(bd.getPid())) {
                                            String pname = pro.getPname();
                                            Double price = pro.getPrice();
                                            int cateid = pro.getCateID();%>   
                                    <td><%=pname%></td>
                                    <td><%=price%></td>
                                            <%for (Category cate : cl) {
                                                if (cate.getCateID() == cateid) {%>
                                    <td><%=cate.getCateName()%></td>        
                                                <%}%>
                                            <%}%>   
                                        <%}%>
                                    <%}%>    
                                    <td><%=bd.getQuantity()%></td>
                                    <td><%=bd.getTotal()%></td>                                                                        
                                </tr>                                    
                                <%}%>
                            </tbody>
                            <td>TotalAll</td>
                             <td></td>
                              <td></td>
                               <td></td>
                                <td></td>
                            <td><% out.print(total);%></td>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td><h3>Customer</h3></td>
                    <td>
                        <p>Customer ID:     <%=cus.getCid()%></p>
                        <p>Customer Name:   <%=cus.getCname()%></p>
                        <p>Customer Phone:  <%=cus.getCphone()%></p>
                        <p>Customer Address:<%=cus.getcAddress()%></p>
                        <p>Username:        <%=cus.getUsername()%></p>
                        <p>Status:          <%=(cus.getStatus() == 1) ? "Enable" : "Disable"%></p>
                    </td>
                    
                </tr>
            </tbody>
        </table>
                    
                   
        <input type="submit" onclick=window.location.href="ControllerBill?service=displayAll" value="Back to bill list" />
    </body>
</html>
