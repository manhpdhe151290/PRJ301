/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
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
public class DAOAdmin {

    Connection conn = null;
    DBConnect dbconn = null;

    public DAOAdmin(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }

    //insert, update, delete: number of records
    public int addAdmin(Admin ad) {
        int n = 0;
        //preStatement insert
        String preSql = "insert into admin(username, password)"
                + " values(?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, ad.getUsername());
            pre.setString(2, ad.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

//        //statement insert
//        String sql = "insert into admin(username, password)"
//                + " values('" + ad.getUsername() + "', '" + ad.getPassword() + "')";
//
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }

    public int updateAdmin(Admin ad) {
        //preStatement update
        int n = 0;
        String Presql = "update admin set username=?,"
                + " password=? where adminID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(Presql);
            pre.setString(1, ad.getUsername());
            pre.setString(2, ad.getPassword());
            pre.setInt(3, ad.getAdminID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeUsername(Admin admin) {
        int n = 0;
        String sql = "update admin set username=? where adminID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, admin.getUsername());
            pre.setInt(2, admin.getAdminID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public void changePassword(String password, String username) {
        String sql = "update admin set password=? where username=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setString(2, username);
            pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int deleteAdmin(int adminID) {
        int n = 0;
        String sql = "delete from admin where adminID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, adminID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int login(String username, String password) {
        int n = 0;
        String checksql = "select * from admin where username='" + username + "'"
                + " and password='" + password + "'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            if (!rs.next()) {
                System.out.println("account don't exist");
                return n;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Login success, welcome " + username);
        return n;
    }

    public void sort(String column) {
        String checksql = "select * from admin order by " + column;
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                int adminID = rs.getInt("adminID");//rs.getInt(1);
                String username = rs.getString(2),
                        password = rs.getString(3);
                Admin ad = new Admin(adminID, username, password);
                System.out.println(ad);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByUsername(String username) {
        String checksql = "select * from admin where username like '%" + username + "%'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                int adminID = rs.getInt("adminID");//rs.getInt(1);
                username = rs.getString(2);
                String password = rs.getString(3);
                Admin ad = new Admin(adminID, username, password);
                System.out.println(ad);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAllAdmin() {
        String sql = "select * from admin";

        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
            ResultSet rs = dbconn.getData(sql);
            while (rs.next()) {
                int adminID = rs.getInt("adminID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Admin ad = new Admin(adminID, username, password);
                System.out.println(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOAdmin dao = new DAOAdmin(dbconn);
//        int n = dao.addAdmin(new Admin("Hai123", "abc123321"));
//        int n = dao.addAdmin(new Admin("Trung", "12345678"));
//        if (n > 0) {
//            System.out.println("inserted");
//        }

        dao.changeUsername(new Admin(3, "Trung 3", "new username"));
//        dao.login("Trung", "12345678");
//        dao.sort("username");
//        dao.searchByUsername("trung");
        dao.displayAllAdmin();
    }

    public ArrayList<Admin> getAllAdmin(){
        ArrayList<Admin> arr = new ArrayList<Admin>();
        String sql = "select * from Admin";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                Admin admin = new Admin(rs.getInt(1),rs.getString(2),rs.getString(3));
                arr.add(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}
