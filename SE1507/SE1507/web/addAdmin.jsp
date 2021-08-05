<%-- 
    Document   : addAdmin
    Created on : 11-06-2021, 22:58:08
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Admin</title>
    </head>
    <body>
        <form action="ControllerAdmin" method="POST">
            <table border="0">
                <caption><h1>Add Admin</h1></caption>
                <tbody>
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
