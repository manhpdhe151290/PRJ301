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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCategory;
import model.DAOCustomer;
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author Grimmy
 */
public class ControllerBill extends HttpServlet {

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
        DAOBill dao = new DAOBill(dbconn);
        DAOCustomer daoCus = new DAOCustomer(dbconn);
        DAOCategory daoCate = new DAOCategory(dbconn);
        DAOBillDetail daoBD = new DAOBillDetail(dbconn);
        DAOProduct daoPro = new DAOProduct(dbconn);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");

            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                //Model: entity, model
                //Controller (Servlet): get action from view --> model --> select view
                //View: JSP
                //Controller call Model
                ResultSet rs = dbconn.getData("select * from Bill");
                ArrayList<Bill> arr = dao.getAllBill();
                //Controller add other item
                String titleTable = "List of Bill";
                //set information for view: setAttribute
                request.setAttribute("ketQua", rs);
                request.setAttribute("danhSach", arr);
                request.setAttribute("tieuDe", titleTable);
                
                dispatch(request, response, "/bill.jsp");
            }
            if (service.equals("detail")) {
                String title = "BillDetail";
                request.setAttribute("tieuDe", title);
                String oid = request.getParameter("oid");
//              ArrayList<Bill> bl = dao.getAllBill();
//                Bill b = null;
//                for (Bill bill : bl) {
//                    if (bill.getoID().equals(oid)) {
//                        b = bill;
//                    }
//                }
//                request.setAttribute("billdetail", b);
//                ArrayList<BillDetail> bdl = daoBD.getBillDetail(oid);
//                request.setAttribute("billdetaillist", bdl);
//                ArrayList<Product> pro = daoPro.getAllProduct();
//                request.setAttribute("product", pro);
//                ArrayList<Customer> cusl = daoCus.getAllCustomer();
//                Customer cus = null;
//                for (Customer customer : cusl) {
//                    if (customer.getCid() == b.getCid()) {
//                        cus = customer;
//                    }
//                }
//                request.setAttribute("customer", cus);
//                ArrayList<Category> cl = daoCate.getAllCategory();
//                request.setAttribute("categorylist", cl);
//                dispatch(request, response, "/billDetail.jsp");
//            }
//        }
String sql = "select b.oID, b.dateCreate, b.status, c.cid, c.cname, c.cphone, c.cAddress, c.username,\n"
                        + "b.cname, b.cphone, b.cAddress, p.pid, p.pname, p.price, bd.quantity, bd.total, ct.cateName\n"
                        + "from Bill b inner join Customer c on b.cid = c.cid \n"
                        + "join BillDetail bd on bd.oID = b.oID\n"
                        + "join Product p on p.pid = bd.pid\n"
                        + "join Category ct on ct.cateID = p.cateID\n"
                        + "where b.oid = "+oid;
                ResultSet rs1 = dbconn.getData(sql);
                ResultSet rs2 = dbconn.getData(sql);
                request.setAttribute("ketQua1", rs1);
                request.setAttribute("ketQua2", rs2);
                dispatch(request, response, "/billDetail2.jsp");

            }
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
