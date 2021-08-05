<%-- 
    Document   : updateCategory
    Created on : 10-06-2021, 23:05:33
    Author     : Grimmy
--%>

<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
    </head>
    <body>
        <%
            Category cate = (Category)request.getAttribute("cate");
        %>
        <form action="ControllerCategory" method="POST">
            <table border="0">
                <caption><h1>Update Category</h1></caption>
                <tbody>
                    <tr>
                        <td>Category ID</td>
                        <td><input type="text" name="cateID" value="<%=cate.getCateID()%>" readonly /></td>
                    </tr>
                    <tr>
                        <td>Category Name</td>
                        <td><input type="text" name="cateName" value="<%=cate.getCateName()%>" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td><input type="radio" name="status" value="1" <%=(cate.getStatus()==1?"checked":"")%>/>Enable
                      <input type="radio" name="status" value="0" <%=(cate.getStatus()==0?"checked":"")%>/>Disable</td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Update" name="submit"/>  
               <input type="reset" value="Reset"/></p>
            <input type="hidden" value="updated" name="service"/>
        </form>
    </body>
</html>
