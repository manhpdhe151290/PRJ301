<%-- 
    Document   : changePassword
    Created on : 14-06-2021, 16:27:07
    Author     : Grimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <form action="ControllerLogin" method="POST">
            <table border="0">
                <caption><h1>Change Password</h1></caption>
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Old Password</td>
                        <td><input type="password" name="oldpassword" value="" /></td>
                    </tr>
                    <tr>
                        <td>New Password</td>
                        <td><input type="password" name="newpassword" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="radio" name="status" value="1" checked/>Admin
                        <input type="radio" name="status" value="0" />Customer</td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Change Password" name="submit"/>
               <input type="submit" onclick=window.location.href="ControllerLogin" value="Login" /></p>
            <input type="hidden" value="passchanged" name="service"/>
        </form>
    </body>
</html>
