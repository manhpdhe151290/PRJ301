/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
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
import model.DAOCategory;
import model.DBConnect;

/**
 *
 * @author Grimmy
 */
public class ControllerCategory extends HttpServlet {

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
        DAOCategory dao = new DAOCategory(dbconn);
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
                String sql = "select * from Category";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Category> arr = dao.getAllCategory();
                //Controller add other item
                String titleTable = "List of Category";
                //set information for view: setAttribute
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                //call view
                RequestDispatcher dis = request.getRequestDispatcher("/Category.jsp");
                //run
                dis.forward(request, response);
            }
            
            if(service.equals("add")){
                String cate = request.getParameter("cateName");
                String sql = "Select * from category where cateName ='" + cate + "'";
                ResultSet rs = dbconn.getData(sql);
                if (rs.next()) {
                    out.print("category is existed!");
                    return;
                }
                Category cat = new Category(cate, 1);
                dao.addCategory(cat);
                response.sendRedirect("ControllerCategory");
            }
            
            if(service.equals("update")){
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "select * from Category where cateID=" + id;
                ResultSet rs = dbconn.getData(sql);
                if(rs.next()){
                    Category cate = new Category(rs.getInt(1),rs.getString(2),rs.getInt(3));
                    request.setAttribute("cate", cate);
                    dispatch(request,response,"/updateCategory.jsp");
                }
            }
            if(service.equals("updated")){
                //get all information
                //update
                String cate = request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("cateID"));

                Category cat = new Category(cateID, cate, status);
                dao.updateCategory(cat);
                response.sendRedirect("ControllerCategory");
            }
            
            if (service.equals("delete")) {
                int cateID = Integer.parseInt(request.getParameter("id"));
                dao.deleteCategory(cateID);
                response.sendRedirect("ControllerCategory");

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
