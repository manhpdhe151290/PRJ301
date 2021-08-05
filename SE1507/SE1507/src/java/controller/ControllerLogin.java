/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
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
import javax.servlet.http.HttpSession;
import model.DAOAdmin;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author Grimmy
 */
public class ControllerLogin extends HttpServlet {

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
        DAOCustomer dao1 = new DAOCustomer(dbconn);
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");

            if (service == null) {
                dispatch(request, response, "/login.jsp");
            }

            if (service.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int status = Integer.parseInt(request.getParameter("status"));
                request.setAttribute("username", username);
                if (status == 1) {
                    ArrayList<Admin> arr = dao.getAllAdmin();
                    for (Admin ad : arr) {
                        if (ad.getUsername().equalsIgnoreCase(username)) {
                            if (ad.getPassword().equals(password)) {
                                dispatch(request, response, "/HomePage.jsp");
                            } else {
                                request.setAttribute("mess", "Wrong user or pass");
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                            }
                        }
                    }
                        request.setAttribute("mess", "Your username doesn't exist!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    ArrayList<Customer> arr = dao1.getAllCustomer();
                    for (Customer cus : arr) {
                        if (cus.getUsername().equalsIgnoreCase(username)) {
                            if (cus.getPassword().equals(password)) {
                                session.setAttribute("username", username);
                                dispatch(request, response, "/HomePage.jsp");
                            } else {
                                out.println("Wrong Password!");
                                return;
                            }
                        }
                    }
                    out.println("Your username doesn't exist!");
                    return;
                }
            }

            if (service.equals("Customer Signup")) {
                dispatch(request, response, "/registerCustomer.jsp");
            }
            if (service.equals("Admin Signup")) {
                dispatch(request, response, "/registerAdmin.jsp");
            }
            if (service.equals("Change Password")) {
                dispatch(request, response, "/changePassword.jsp");
            }

            if (service.equals("passchanged")) {
                String username = request.getParameter("username");
                String oldpass = request.getParameter("oldpassword");
                String newpass = request.getParameter("newpassword");
                int status = Integer.parseInt(request.getParameter("status"));
                if (newpass.length() < 8) {
                    out.print("Your new password is too short!");
                    return;
                }
                if (status == 1) {
                    String sql = "Select * from admin where username ='" + username + "'";
                    ResultSet rs = dbconn.getData(sql);
                    if (rs.next()) {
                        if (rs.getString(3).equals(oldpass)) {
                            dao.changePassword(newpass, username);
                        } else {
                            out.print("Your old password isn't correct!");
                            return;
                        }
                    } else {
                        out.println("Your username doesn't exist!");
                        return;
                    }
                } else {
                    String sql = "Select * from Customer where username ='" + username + "'";
                    ResultSet rs = dbconn.getData(sql);
                    if (rs.next()) {
                        if (rs.getString(6).equals(oldpass)) {
                            dao1.changePassword(newpass, username);
                        } else {
                            out.print("Your old password isn't correct!");
                            return;
                        }
                    } else {
                        out.println("Your username doesn't exist!");
                        return;
                    }
                }
                response.sendRedirect("ControllerLogin");
            }

            if (service.equals("registeredAdmin")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String sql = "Select * from admin where username ='" + username + "'";
                ResultSet rs = dbconn.getData(sql);
                if (username.length() < 1) {
                    out.print("Enter username please!");
                    return;
                }
                if (rs.next()) {
                    out.print("username is existed!");
                    return;
                }
                if (password.length() < 8) {
                    out.print("password is too short!");
                    return;
                }
                if (!password.equals(repassword)) {
                    out.print("Repassword doesn't match with password!");
                    return;
                }
                Admin admin = new Admin(username, password);
                dao.addAdmin(admin);
                response.sendRedirect("ControllerLogin");
            }

            if (service.equals("registeredCustomer")) {
                String name = request.getParameter("cname");
                String phone = request.getParameter("cphone");
                String adress = request.getParameter("cAddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String sql = "Select * from customer where username ='" + username + "'";
                ResultSet rs = dbconn.getData(sql);
                if (name.length() < 1) {
                    out.print("Enter your Name please!");
                    return;
                }
                if (username.length() < 1) {
                    out.print("Enter username please!");
                    return;
                }
                if (rs.next()) {
                    out.print("username is existed!");
                    return;
                }
                if (password.length() < 8) {
                    out.print("password is too short!");
                    return;
                }
                if (!password.equals(repassword)) {
                    out.print("Repassword doesn't match with password!");
                    return;
                }
                Customer cus = new Customer(name, phone, adress, username, password, 1);
                dao1.addCustomer(cus);
                response.sendRedirect("ControllerLogin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

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
