/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DO THANH TRUNG
 */
public class DAOProduct {

    Connection conn = null;
    DBConnect dbconn = null;

    public DAOProduct(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    public Product getProduct(String pid) {
        
        String sql = "select * from Product where pid='"+pid+"'";
        ResultSet rs = dbconn.getData(sql);
        Product pro = null;
        
         try {
             if(rs.next()) {
                 pro = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                 return pro;
                 
             }} catch (SQLException ex) {
             Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return pro;
    }

    public int addProduct(Product product) {
        int n = 0;
        //preStatement insert
        String preSql = "insert into Product(pid, pname, quantity, price, image,"
                + " description, status, cateID) "
                + "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, product.getPid());
            pre.setString(2, product.getPname());
            pre.setInt(3, product.getQuantity());
            pre.setDouble(4, product.getPrice());
            pre.setString(5, product.getImage());
            pre.setString(6, product.getDescription());
            pre.setInt(7, product.getStatus());
            pre.setInt(8, product.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

//        //statement insert
//        String sql = "insert into Product(pid, pname, quantity, price, image, description, status, cateID) "
//                + "values ('" + product.getPid()
//                + "','" + product.getPname()
//                + "'," + product.getQuantity()
//                + "," + product.getPrice()
//                + ",'" + product.getImage()
//                + "','" + product.getDescription()
//                + "'," + product.getStatus()
//                + "," + product.getCateID() + ")";
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }

    public int updateProduct(Product pro) {
        //statement update
        int n = 0;
        String sql = "update Product set pname='" + pro.getPname()
                + "',quantity=" + pro.getQuantity() + ","
                + "price=" + pro.getPrice() + ",image='" + pro.getImage()
                + "',description='" + pro.getDescription() + "',"
                + "status=" + pro.getStatus() + ",cateID=" + pro.getCateID() + " "
                + "where pid='" + pro.getPid() + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateQuantity(String pid, int quantity) {
        int n = 0;
        String sql = "update Product set "
                + "quantity=quantity + " + quantity + "  "
                + "where pid='" + pid + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updatePrice(String pid, double price) {
        int n = 0;
        String sql = "update Product set price=? where pid=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, price);
            pre.setString(2, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(String pid, int status) {
        int n = 0;
        String sql = "update Product set status=? where pid=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProductAll(Product pro) {
        //preStatement update
        int n = 0;
        String Presql = "update Product set pid=?, pname=?,"
                + " quantity=?, price=?, image=?, description=?, status=? where cateID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(Presql);
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteProduct(String pid) {
        int n = 0;
        String sql = "delete from Product where pid=?";
        //Product ben 1 cua BillDetail
        //check foreign key
        String s1 = "select * from BillDetail where pid='" + pid + "'";
        try {
            //get data
            ResultSet rs = dbconn.getData(s1);
            if (rs.next()) {
                //productID existed => change status
                changeStatus(pid, 0);
            } else {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, pid);
                n = pre.executeUpdate();
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void sort(String column) {
        String checksql = "select * from Product order by " + column;
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1), pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product product = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(product);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByPname(String pname1) {
        String checksql = "select * from product where pname like '%" + pname1 + "%'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1), pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product product = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(product);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAllProduct() {
        String sql = "select * from Product";

        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
            ResultSet rs = dbconn.getData(sql);
            while (rs.next()) {
                String pid = rs.getString(1),
                        pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5),
                        description = rs.getString(6);
                int status = rs.getInt(7),
                        cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOProduct dao = new DAOProduct(dbconn);
//        int n = dao.addProduct(new Product("1", "1", 1, 1, "demo", "demo", 1, 1));
//        int n = dao.addProduct(new Product("3", "trung2", 3, 5, "demo2", "demo2", 3, 2));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }
//        dao.updateQuantity("3", 10);
//        dao.updatePrice("3", 10);
//        dao.changeStatus("3", 0);
//        dao.sort("status");
        dao.displayAllProduct();
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                Product pro = new Product(rs.getString(1), rs.getString(2), rs.getInt(3), 
                        rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

}
