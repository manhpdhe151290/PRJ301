<%-- 
    Document   : register
    Created on : 12-06-2021, 11:19:37
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register for customer</title>
    </head>
    <body>
        <form action="ControllerLogin" method="POST">
            <table border="0">
                <caption><h1>Register for customer</h1></caption>
                <tbody>
                    <tr>
                        <td>Your Name</td>
                        <td><input type="text" name="cname" value=""/></td>
                    </tr>
                    <tr>
                        <td>Your Phone Number</td>
                        <td><input type="text" name="cphone" value=""  /></td>
                    </tr>
                    <tr>
                        <td>Your Address</td>
                        <td><input type="text" name="cAddress" value="" /></td>
                    </tr>
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
               <input type="submit" value="Admin Register" name="service"/></p>
            <input type="hidden" value="registeredCustomer" name="service"/>
            <input type="submit" onclick=window.location.href="ControllerLogin" value="Login" />
        </form>
    </body>
</html>
