<%-- 
    Document   : showCart
    Created on : Jun 23, 2021, 9:41:21 PM
    Author     : PC
--%>

<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <style>
body {
  font-family: Arial;
  font-size: 17px;
  padding: 8px;
}

* {
  box-sizing: border-box;
}

.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-25,
.col-50,
.col-75 {
  padding: 0 16px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 20px 15px 20px;
  border: 1px solid lightgrey;
  border-radius: 3px;
}

input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

label {
  margin-bottom: 10px;
  display: block;
}

.icon-container {
  margin-bottom: 20px;
  padding: 7px 0;
  font-size: 24px;
}

.btn {
  background-color: #04AA6D;
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

.btn:hover {
  background-color: #45a049;
}

a {
  color: #2196F3;
}

hr {
  border: 1px solid lightgrey;
}

span.price {
  float: right;
  color: grey;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
  .col-25 {
    margin-bottom: 20px;
  }
}
</style>
    </head>
    <body>
        
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Sub Total</th>
                    <th>Remove</th>                                    
                </tr>
            </thead>
            <tbody>
            <%  //aka : tương đương với
                java.util.Enumeration em = session.getAttributeNames();
                int itemInCart=0;
                double totalCart = 0;
                while(em.hasMoreElements()) {
                // get key (aka pid)    
                String pid = em.nextElement().toString();
                
                Product pro = null;
                //get product (aka value) through pid (aka key)
                try {
                pro = (Product) session.getAttribute(pid);
                } catch (Exception e) {
                    System.out.println(e);
                    continue;
                }
                // CHAY THU DI
               
                double total = pro.getPrice()*pro.getQuantity();
                totalCart += total;
                itemInCart++;
            %>
            
                <tr>
                    <td><%= pro.getPid() %></td>
                    <td><%= pro.getPname()%></td>
                    <td><input value="<%= pro.getQuantity()%>"  id="quantity"  name="quantity1" type="text"</td>
                    <td><%= pro.getPrice()%></td>
                    <td><%= total %></td>
                    <td><a onclick ="return confirm('Are you sure?')" href="remove.jsp?pid=<%= pro.getPid() %>">Remove</td>
                    
                </tr>
            <%  }
            %>
            
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><a onclick ="return confirm('Are you sure?')" href="ControllerProduct?service=removeAllFromCart">Remove all</a></td>
            </tr>
        </table>
            <br>
            
            <h3><%out.print("Total= "+totalCart);%></h3>
            <h2><a href="ControllerProduct"> List Product</a></h2>
        <form action="ControllerProduct" method="POST">     
        <div class="row">
          <div class="col-50">
            <h3>Billing Address</h3>
            <label for="fname"><i class="fa fa-user"></i> Full Name</label>
            <input type="text" id="fname" name="cname" placeholder="John M. Doe">
            <label for="email"><i class="fa fa-envelope"></i> Phone</label>
            <input type="text" id="email" name="cphone" placeholder="0338622864">
            <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
            <input type="text" id="adr" name="caddress" placeholder="542 W. 15th Street">
            <label for="city"><i class="fa fa-institution"></i> City</label>
            <input type="text" id="city" name="city" placeholder="New York">
            <div class="row">
              <div class="col-50">
                <label for="state">State</label>
                <input type="text" id="state" name="state" placeholder="NY">
              </div>                    
            </div>
          </div>                
        </div>
            <input type="checkbox" checked="checked" name="sameadr"> Shipping address same as billing
          <p><input type="submit" value="Check Out" name="submit"/>  
               <input type="reset" value="Reset"/></p>
            <input type="hidden" value="checkOut" name="service"/>
            <input type="hidden" value="<%=totalCart%>" name="total"/>
            <input type="hidden" value="<%=itemInCart%>" name="itemInCart"/>
        </form>
<!--        <script>
function myFunction() {
  var x = document.getElementById("quantity");
  x.value = x.value;
}
</script>-->
    </body>
    
</html>