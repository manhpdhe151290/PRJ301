<%-- 
    Document   : addCustomer
    Created on : 11-06-2021, 23:23:54
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
    </head>
    <body>
        <form action="ControllerCustomer" method="POST">
            <table border="0">
                <caption><h1>Add Customer</h1></caption>
                <tbody>
                    <tr>
                        <td>Customer Name</td>
                        <td><input type="text" name="cname" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Customer Phone</td>
                        <td><input type="text" name="cphone" value=""  /></td>
                    </tr>
                    <tr>
                        <td>Customer Address</td>
                        <td><input type="text" name="cAddress" value="" /></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="password" value="" required/></td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Add" name="submit"/>  
               <input type="reset" value="Reset"/></p>
            <input type="hidden" value="add" name="service"/>
        </form>
    </body>
</html>
