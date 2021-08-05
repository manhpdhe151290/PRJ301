/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Category;
import entity.Customer;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCategory;
import model.DAOCustomer;
import model.DBConnect;
import model.DAOProduct;

/**
 *
 * @author Grimmy
 */
public class ControllerProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnect dbconn = new DBConnect();
        DAOProduct dao = new DAOProduct(dbconn);
        DAOCategory dao1 = new DAOCategory(dbconn);
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            HttpSession session = request.getSession();
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                //Model: entity, model
                //Controller (Servlet): get action from view --> model --> select view
                //View: JSP
                //Controller call Model
                String sql = "select * from Product";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Product> arr = dao.getAllProduct();
                ArrayList<Category> catelist = dao1.getAllCategory();
                request.setAttribute("catelist", catelist);
                //Controller add other item
                String titleTable = "List of Product";
                //set information for view: setAttribute
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                //call view
                RequestDispatcher dis = request.getRequestDispatcher("/Product.jsp");
                //run
                dis.forward(request, response);
            }
            
            if (service.equals("preAdd")) {
                ArrayList<Category> catelist = dao1.getAllCategory();
                request.setAttribute("catelist", catelist);
                dispatch(request, response, "/addProduct.jsp");
            }
            if (service.equals("add")) {
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                int cateID = Integer.parseInt(request.getParameter("Cate"));
                String sql = "select * from Product where pid='"+pid+"'";
                ResultSet rs = dbconn.getData(sql);
                if (rs.next()) {
                    out.print("product id is existed!");
                    return;
                }
                Product pro = new Product(pid, pname, quantity, price, image, description, 1, cateID);
                dao.addProduct(pro);
                response.sendRedirect("ControllerProduct");
            }

            if (service.equals("update")) {
                ArrayList<Category> catelist1 = dao1.getAllCategory();
                request.setAttribute("catelist", catelist1);
               String pid = request.getParameter("pid");
                String sql = "select * from Product where pid='"+pid+"'";
                ResultSet rs = dbconn.getData(sql);
                if (rs.next()) {
                    Product pro = new Product(rs.getString(1), rs.getString(2), rs.getInt(3),
                            rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                    request.setAttribute("pro", pro);
                    dispatch(request, response, "/updateProduct.jsp");
                }
            }
            if (service.equals("updated")) {
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("Cate"));
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                dao.updateProduct(pro);
                response.sendRedirect("ControllerProduct");
            }

            if (service.equals("delete")) {
                String pid = request.getParameter("pid");
                dao.deleteProduct(pid);
                response.sendRedirect("ControllerProduct");

            }
//             if (service.equals("addToCart")) {
//                String pid = request.getParameter("pid");
//                Product pro = null;
//                String sql = "select * from Product where pid='"+pid+"'";
//                ResultSet rs = dbconn.getData(sql);
//                if (rs.next()) {
//                    pro = new Product(rs.getString(1), rs.getString(2),
//                            rs.getInt(3), rs.getDouble(4), rs.getString(5), 
//                            rs.getString(6), rs.getInt(7), rs.getInt(8));
//                }
//                request.setAttribute("pro", pro);
//                dispatch(request, response, "/add2Cart.jsp");
//            }
 if(service.equalsIgnoreCase("add2Cart")) {
                          
                String pid = request.getParameter("pid");
                System.out.println("pid= "+pid);
                // send parameter pid to add2Cart
                String url = "/add2Cart.jsp?pid="+pid+"";              
                dispatch(request, response, url);
            }
//            if (service.equals("showCart")) {
//                ArrayList<Product> arr = dao.getAllProduct();
//                request.setAttribute("arr", arr);
//                dispatch(request, response, "/showCart.jsp");
//            }
         if (service.equals("removeAllFromCart")) {
                Enumeration em = session.getAttributeNames();
                //getkeys()
                //for(;em.hasMoreElements();){
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    session.removeAttribute(id);
                }
               response.sendRedirect("showCart.jsp");
            }
         if (service.equals("checkOut")) {
                String total = request.getParameter("total");
                String cname = request.getParameter("cname");
                String cphone = request.getParameter("cphone");
                String caddress = request.getParameter("caddress");
                String city = request.getParameter("city");
                request.setAttribute("city1", city);
                String national=request.getParameter("state");
                request.setAttribute("state1", national);
                request.setAttribute("total", total);
                int itemInCart = Integer.parseInt(request.getParameter("itemInCart"));
                if (itemInCart == 0) {
                    request.setAttribute("messenger", "Your Cart is empty!!");
                    dispatch(request, response, "checkout.jsp");
                } else {
                    java.util.Enumeration em = session.getAttributeNames();
                    if (em.hasMoreElements()) {
                        String username = "";
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("username")) {
                                username = session.getAttribute("username").toString();
                                addBillToDataBase(username, total, cname, cphone, caddress, request, response);
                            }
                        }
                        if (username.equals("")) {
                            request.setAttribute("alert", "You need login as a customer to checkout");
                            dispatch(request, response, "/login.jsp");
                        }
                    } else {
                        request.setAttribute("alert", "You need login as a customer to checkout");
                        dispatch(request, response, "/login.jsp");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addBillToDataBase(String username, String total, String cname, String cphone,
            String caddress, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        DBConnect dbconn = new DBConnect();
        DAOCustomer daoc = new DAOCustomer(dbconn);
        DAOBill daobill = new DAOBill(dbconn);
        DAOBillDetail daobd = new DAOBillDetail(dbconn);
        DAOProduct dao = new DAOProduct(dbconn);
        HttpSession session = request.getSession();
        ArrayList<Customer> cuslist = daoc.getAllCustomer();
        Customer customer = null;
        for (Customer cus : cuslist) {
            if (cus.getUsername().equalsIgnoreCase(username)) {
                customer = cus;
            }
        }
        int max = 0;
        ArrayList<Bill> billlist = daobill.getAllBill();
        for (Bill bill : billlist) {
            if (Integer.parseInt(bill.getoID()) > max) {
                max = Integer.parseInt(bill.getoID());
            }
        }
        max = max + 1;
        double totalbill = Double.parseDouble(total);
        daobill.addBill(new Bill(Integer.toString(max), cname,
                cphone, caddress, totalbill, customer.getCid()));
        java.util.Enumeration em = session.getAttributeNames();
        ArrayList<Product> arr = dao.getAllProduct();
        session.removeAttribute(username);
        Product pro = null;
        while (em.hasMoreElements()) {
            int count = 0;
            double totalp = 0;
            String id = em.nextElement().toString();
            if (!id.equals("username")) {
//                count = Integer.parseInt(session.getAttribute(id).toString());
            
                for (Product pro1 : arr) {
                    if (pro1.getPid().equals(id)) {
                        pro = pro1;
                    }
                }
                 pro = (Product) session.getAttribute(id);
                 count = pro.getQuantity();
            }
            totalp = pro.getQuantity() * pro.getPrice();
            daobd.addBillDetail(new BillDetail(pro.getPid(), String.valueOf(max),
                    count, pro.getPrice(), totalp));
        }
        session.invalidate();
        request.setAttribute("messenger", "Check Out Successful!!!!");
        dispatch(request, response, "checkout.jsp");
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String URL) {
        RequestDispatcher dis = request.getRequestDispatcher(URL);
        try {
            dis.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
