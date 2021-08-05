/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
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
import model.DAOAdmin;
import model.DBConnect;

/**
 *
 * @author Grimmy
 */
public class ControllerAdmin extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin(dbconn);
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
                String sql = "select * from Admin";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Admin> arr = dao.getAllAdmin();
                //Controller add other item
                String titleTable = "List of Admin";
                //set information for view: setAttribute
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                //call view
                RequestDispatcher dis = request.getRequestDispatcher("/Admin.jsp");
                //run
                dis.forward(request, response);
            }
            
            if(service.equals("add")){
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
                Admin ad = new Admin(username, password);
                dao.addAdmin(ad);
                response.sendRedirect("ControllerAdmin");
            }
            
            if(service.equals("update")){
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "select * from Admin where adminID=" + id;
                ResultSet rs = dbconn.getData(sql);
                if(rs.next()){
                    Admin admin = new Admin(rs.getInt(1),rs.getString(2),rs.getString(3));
                    request.setAttribute("admin", admin);
                    dispatch(request,response,"/updateAdmin.jsp");
                }
            }
            if(service.equals("updated")){
                //get all information
                //update
                int adminID = Integer.parseInt(request.getParameter("adminID"));
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (password.length()<8) {
                    out.print("password is too short!");
                    return;
                }
                Admin ad = new Admin(adminID, username, password);
                int n = dao.updateAdmin(ad);
                response.sendRedirect("ControllerAdmin");
            }
            
            if (service.equals("delete")) {
                String adminID = request.getParameter("id");
                dao.deleteAdmin(Integer.parseInt(adminID));
                response.sendRedirect("ControllerAdmin");
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
