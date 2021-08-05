<%-- 
    Document   : addProduct
    Created on : 11-06-2021, 23:38:03
    Author     : Grimmy
--%>

<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <%  
            ArrayList<Category> cl = (ArrayList<Category>) request.getAttribute("catelist");
        %>
        <form action="ControllerProduct" method="POST">
            <table border="0">
                <caption><h1>Add Product</h1></caption>
                <tbody>
                    <tr>
                        <td>Product ID</td>
                        <td><input type="text" name="pid" value="" required /></td>
                    </tr>
                    <tr>
                        <td>Product Name</td>
                        <td><input type="text" name="pname" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantity" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="file" name="image" value="" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td><input type="radio" name="status" value="1" checked/>Enable
                            <input type="radio" name="status" value="0"/>Disable</td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td><select name="Cate">
                                <%for (Category cate : cl){%> 
                                <option value="<%=cate.getCateID()%>"> <%=cate.getCateName()%> </option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Add" name="submit"/>  
                <input type="reset" value="Reset"/></p>
            <input type="hidden" value="add" name="service"/>
        </form>
    </body>
</html>
