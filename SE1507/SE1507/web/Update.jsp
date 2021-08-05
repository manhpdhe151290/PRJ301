<%-- 
    Document   : Update
    Created on : Jul 2, 2021, 2:18:59 PM
    Author     : ADMIN
--%>

<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            // remove that product(with pid) from cart(session map)
            String pid = request.getParameter("pid");
            
            String quan = request.getParameter("quantity");
            int quan1=Integer.parseInt("quan");
            java.util.Enumeration em = session.getAttributeNames();
                int itemInCart=0;
                double totalCart = 0;
                while(em.hasMoreElements()) {
                // get key (aka pid)    
                 pid = em.nextElement().toString();
                
                Product pro = null;
                //get product (aka value) through pid (aka key)
                try {
                pro = (Product) session.getAttribute(pid);
                } catch (Exception e) {
                    System.out.println(e);
                    continue;
                }
                // CHAY THU DI
               
                double total = pro.getPrice()*quan1;
                totalCart += total;
                itemInCart++;
            
            //return to cart
            response.sendRedirect("showCart.jsp");
        %>
    </body>
</html>
