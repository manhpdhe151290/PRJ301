<%-- 
    Document   : updateProduct
    Created on : 11-06-2021, 18:50:19
    Author     : Grimmy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <%
            Product pro = (Product) request.getAttribute("pro");
            ArrayList<Category> cl = (ArrayList<Category>) request.getAttribute("catelist");
        %>
        <form action="ControllerProduct" method="POST">
            <table border="0">
                <caption><h1>Update Product</h1></caption>
                <tbody>
                    <tr>
                        <td>Product ID</td>
                        <td><input type="text" name="pid" value="<%=pro.getPid()%>" readonly /></td>
                    </tr>
                    <tr>
                        <td>Product Name</td>
                        <td><input type="text" name="pname" value="<%=pro.getPname()%>" /></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantity" value="<%=pro.getQuantity()%>"  /></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price" value="<%=pro.getPrice()%>" /></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="file" name="image" value="<%=pro.getImage()%>" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="<%=pro.getDescription()%>" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td><input type="radio" name="status" value="1" <%=(pro.getStatus() == 1 ? "checked" : "")%>/>Enable
                            <input type="radio" name="status" value="0" <%=(pro.getStatus() == 0 ? "checked" : "")%>/>Disable</td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td><select name="Cate">
                                <%for (Category cate : cl) {
                                if (pro.getCateID() == cate.getCateID()) {%> 
                                <option value="<%=cate.getCateID()%>"  selected> <%=cate.getCateName()%> </option>
                                <%} else {%>
                                <option value="<%=cate.getCateID()%>"> <%=cate.getCateName()%> </option>
                                <%}
                                }%>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Update" name="submit"/>  
                <input type="reset" value="Reset"/></p>
            <input type="hidden" value="updated" name="service"/>
        </form>
    </body>
</html>
