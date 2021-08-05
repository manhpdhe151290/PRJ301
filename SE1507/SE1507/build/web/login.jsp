<%-- 
    Document   : login.jsp
    Created on : 12-06-2021, 04:06:34
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <style>
      
    </style>
    <body>
        <form action="ControllerLogin" method="POST">
            <caption><h1>Login</h1></caption>
            <p class="text-danger" style="font-size:20px">${mess}</p>
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="radio" name="status" value="1" checked/>Admin
                        <input type="radio" name="status" value="0" />Customer</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="checkbox" name="remember" value="ON" /> Remember Me</td>
                    </tr>
                </tbody>
            </table>
            <p>${alert}</p>
            <p>
                 <input type="submit" value="Change Password" name="service"/>
                 <input type="submit" value="Login" name="submit"/>             
               <input type="submit" value="Customer Signup" name="service"/>
               <input type="submit" value="Admin Signup" name="service"/></p>
            <input type="hidden" value="login" name="service"/>

        </form>
    </body>
</html>
