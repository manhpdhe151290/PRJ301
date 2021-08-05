/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Category;
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
public class DAOCategory {

    Connection conn = null;
    DBConnect dbconn = null;

    public DAOCategory(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }
    
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> arr = new ArrayList<Category>();
        String sql = "select * from Category";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                Category cate = new Category(rs.getInt(1),rs.getString(2),rs.getInt(3));
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    //insert, update, delete -- return number of records
    public int addCategory(Category cate) {
        int n = 0;
        //preStatement insert
        String preSql = "insert into Category(cateName, status)"
                + "values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

//        //statement insert
//        String sql = "insert into Category(cateName, status) "
//                + "values ('" + cate.getCateName()
//                + "'," + cate.getStatus() + ")";
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }

    public int updateCategory(Category cate) {
        //preStatement update
        int n = 0;
        String Presql = "update Category set cateName=?,"
                + " status=? where cateID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(Presql);
            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());
            pre.setInt(3, cate.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(int cateID, int status) {
        int n = 0;
        String sql = "update Category set status=? where cateID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, cateID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCategory(int cateID) {
        int n = 0;
        String sql = "delete from Category where cateID=?";
        String s1 = "select * from Product where cateID="+cateID;
        try {
            ResultSet rs = dbconn.getData(s1);
            if (rs.next()) {
                //productID existed => change status
                changeStatus(cateID, 0);
            } else {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, cateID);
                n = pre.executeUpdate();
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void sort(String column) {
        String checksql = "select * from category order by " + column;
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                System.out.println(cate);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByCateName(String cateName1) {
        String checksql = "select * from Category where cateName like '%" + cateName1 + "%'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                System.out.println(cate);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAllCategory() {
        String sql = "select * from Category";

        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
            ResultSet rs = dbconn.getData(sql);
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                System.out.println(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOCategory dao = new DAOCategory(dbconn);
//        int n = dao.addCategory(new Category("C03", 0));
//        if (n > 0) {
//            System.out.println("inserted");
//        }

        //dao.changeStatus(1, 0);
        //dao.sort("status");
        //dao.searchByCateName("trung");
        dao.displayAllCategory();
    }

}
