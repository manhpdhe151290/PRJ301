/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author Grimmy
 */
public class ControllerCustomer extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer(dbconn);
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");

            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                //Model: entity, model
                //Controller (Servlet): get action from view --> model --> select view
                //View: JSP
                //Controller call Model
                String sql = "select * from Customer";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Customer> arr = dao.getAllCustomer();
                //Controller add other item
                String titleTable = "List of Customer";
                //set information for view: setAttribute
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                //call view
                RequestDispatcher dis = request.getRequestDispatcher("/Customer.jsp");
                //run
                dis.forward(request, response);
            }
            
            if(service.equals("add")){
                String name = request.getParameter("cname");
                String phone = request.getParameter("cphone");
                String adress = request.getParameter("cAddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String sql = "Select * from admin where username ='" + username + "'";
                ResultSet rs = dbconn.getData(sql);
                if (rs.next()) {
                    out.print("username is existed!");
                    return;
                }
                if (password.length()<8) {
                    out.print("password is too short!");
                    return;
                }
                Customer cus = new Customer(name, phone, adress, username, password,1);
                dao.addCustomer(cus);
                response.sendRedirect("ControllerCustomer");
            }
            
            if(service.equals("update")){
                int id = Integer.parseInt(request.getParameter("cid"));
                String sql = "select * from Customer where cid=" + id;
                ResultSet rs = dbconn.getData(sql);
                if(rs.next()){
                    Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                    request.setAttribute("cus", cus);
                    dispatch(request,response,"/updateCustomer.jsp");
                }
            }
            if(service.equals("updated")){
                String cusID = request.getParameter("cid");
                String name = request.getParameter("cname");
                String phone = request.getParameter("cphone");
                String adress = request.getParameter("cAddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String status = request.getParameter("status");
                int cid = Integer.parseInt(cusID);
                int sta = Integer.parseInt(status);
                if (password.length()<8) {
                    out.print("password is too short!");
                    return;
                }
                Customer cus = new Customer(cid, name, phone, adress, username, password, sta);
                dao.updateCustomer(cus);
                response.sendRedirect("ControllerCustomer");
            }
            
            if (service.equals("delete")) {
                String cid = request.getParameter("cid");
                dao.deleteCustomer(cid);
                response.sendRedirect("ControllerCustomer");

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String URL){
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
