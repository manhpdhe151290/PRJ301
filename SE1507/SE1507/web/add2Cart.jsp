
<%@page import="model.DAOProduct"%>
<%@page import="model.DBConnect"%>
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
                DBConnect dBConnect = new DBConnect();
                DAOProduct dao = new DAOProduct(dBConnect);
                
                // get pid from ControllerProduct
                String pid = request.getParameter("pid");
                
                // check the product in the cart (aka session map)
                Product pro = (Product) session.getAttribute(pid);
                
                if(pro==null) { // the cart ( or session map ) does not have this product (or key - value ) yet
                    // set this new product with quantity of 1
                    Product pro2 = dao.getProduct(pid);
                    pro2.setQuantity(1);
                    
                    session.setAttribute(pid, pro2);                  
                    
                } else { // the cart have this product already
                    // add 1 to number of item 
                    
                    //System.out.println("product add 1");
                    pro.setQuantity(pro.getQuantity()+1);
                    
                    // code below with or without is okay
                    //session.setAttribute(pid, pro);
                }
        %>
        <h1>Product with pid=<%=pid%> was added to the  Cart</h1>
        <h2><a href="showCart.jsp"> Cart</h2>
        <h2><a href="ControllerProduct">List Product</h2>
       
    </body>
</html>
