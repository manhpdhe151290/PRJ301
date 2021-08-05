/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
import entity.Customer;
import java.sql.CallableStatement;
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
public class DAOCustomer {

    Connection conn = null;
    DBConnect dbconn = null;

    public DAOCustomer(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }

    //insert, update, delete: number of records
    public int addCustomer(Customer cus) {
        int n = 0;
        //preStatement insert
        String preSql = "insert into Customer(cname, cphone, cAddress, username, password)"
                + " values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            //? has index start 1
            //pre.setDataType(index of ?, value);
            //DataType data id of ?
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

//        //statement insert
//        String sql = "insert into Customer(cname, cphone, cAddress, username, password)"
//                + " values('" + cus.getCname() + "', '" + cus.getCphone() + "',"
//                + " '" + cus.getcAddress() + "', '" + cus.getUsername() + "',"
//                + " '" + cus.getPassword() + "')";
//
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }
    
    public void changePassword(String password, String username) {
        String sql = "update customer set password=? where username=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setString(2, username);
            pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int updateCustomer(Customer cus) {
        //preStatement update
        int n = 0;
        String Presql = "update Customer set cname=?, cphone=?,"
                + " cAddress=?, username=?, password=?, status=? where cid=?";

        try {
            PreparedStatement pre = conn.prepareStatement(Presql);
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            pre.setInt(7, cus.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(int cid, int status) {
        int n = 0;
        String preSql = "update Customer set status=? where cid=?";
        //String sql="update Customer set status="+status+" where cid ="+cid; (cau lenh statement)
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setInt(1, status);
            pre.setInt(2, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changePassword(int cid, String username, String oldpass, String newPass) {
        int n = 0;
        // check pass and repass -- javascript
        // check account (username, oldpass)
        String checksql = "select * from Customer where username='" + username + "'"
                + " and password='" + oldpass + "'";
        java.sql.ResultSet rs = dbconn.getData(checksql);
        try {
            if (!rs.next()) {
                System.out.println("account don't exist");
                return n;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        // check OK

        String sql = "update Customer set password=? where cid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setInt(2, cid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Updated");
        return n;
    }

    public int deleteCustomer(String cid) {
        int n = 0;
        String sql = "delete from Customer where cid=?";
        //Customer ben 1 cua Bill
        //check foreign key
        String sql1 = "select * from Bill where cid='" + cid + "'";
        try {
            ResultSet rs = dbconn.getData(sql1);
            if (rs.next()) {
                //CustomerID existed => change status
                changeStatus(Integer.parseInt(cid), 0);
            } else {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, cid);
                n = pre.executeUpdate();
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int login(String username, String password) {
        int n = 0;
        String checksql = "select * from Customer where username='" + username + "'"
                + " and password='" + password + "'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            if (!rs.next()) {
                System.out.println("account don't exist");
                return n;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Login success, welcome " + username);
        return n;
    }

    public void sort(String column) {
        String checksql = "select * from Customer order by " + column;
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                int cid = rs.getInt("cid");//rs.getInt(1);
                String cname = rs.getString(2),//rs.getString("cphone");
                        cphone = rs.getString("cphone"),
                        cAddress = rs.getString(4),
                        username = rs.getString(5),
                        password = rs.getString(6);
                int status = rs.getInt(7);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByCname(String cname) {
        String checksql = "select * from Customer where cname like '%" + cname + "%'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                int cid = rs.getInt("cid");//rs.getInt(1);
                cname = rs.getString(2);//rs.getString("cphone");
                String cphone = rs.getString("cphone"),
                        cAddress = rs.getString(4),
                        username = rs.getString(5),
                        password = rs.getString(6);
                int status = rs.getInt(7);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAllCustomer() {
        String sql = "select * from Customer";
        try {
            //Statement state = conn.createStatement();  //default

            //TYPE_FORWARD_ONLY: pointer top -> down
            //TYPE_SCROLL_SENSITIVE: top <--> down, thread safe
            //CONCUR_READ_ONLY: not modify resultset
            //CONCUR_UPDATABLE: modify resultset            
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            //ResultSet rs = dbconn.getData();
            while (rs.next()) {
                //rs.DataType(index || fieldName);
                //DataType is data type of field name;
                int cid = rs.getInt("cid"); //rs.getInt(1);
                String cname = rs.getString(2);
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                System.out.println(cus);
            }
            //PreparedStatement pre = conn.prepareStatement(sql, 0, 0);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOCustomer dao = new DAOCustomer(dbconn);
//        int n = dao.addCustomer(new Customer("Hai", "094343", "Hai Phong", "Hai123", "abc123321"));
//        if (n > 0) {
//            System.out.println("inserted");
//            }

        //dao.changeStatus(2, 0);
        dao.changePassword(2, "Hai123", "abc123321", "newpasss");
        //dao.login("Hai123", "abc123321");
        //dao.sort("status");
        //dao.searchByCname("Hai");
        dao.displayAllCustomer();

    }

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        String sql = "select * from Customer";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}
