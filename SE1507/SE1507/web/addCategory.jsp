<%-- 
    Document   : addCategory
    Created on : 11-06-2021, 23:16:51
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
    </head>
    <body>
        <form action="ControllerCategory" method="POST">
            <table border="0">
                <caption><h1>Add Category</h1></caption>
                <tbody>
                    <tr>
                        <td>Category Name</td>
                        <td><input type="text" name="cateName" value="" required/></td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Add" name="submit"/>  
               <input type="reset" value="Reset"/></p>
            <input type="hidden" value="add" name="service"/>
        </form>
    </body>
</html>
