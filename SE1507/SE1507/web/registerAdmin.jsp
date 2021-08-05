<%-- 
    Document   : registerAdmin
    Created on : 14-06-2021, 16:12:00
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register for admin</title>
    </head>
    <body>
        <form action="ControllerLogin" method="POST">
            <table border="0">
                <caption><h1>Register for admin</h1></caption>
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value=""/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value=""/></td>
                    </tr>
                    <tr>
                        <td>Confirm your Password</td>
                        <td><input type="password" name="repassword" value=""/></td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Register" name="submit"/>  
               <input type="reset" value="Reset"/>
               <input type="submit" name="service" value="Customer Register"/></p>
            <input type="hidden" value="registeredAdmin" name="service"/>
            <input type="submit" onclick=window.location.href="ControllerLogin" value="Login" />
        </form>
    </body>
</html>
