/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
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
public class DAOBillDetail {

    Connection conn = null;
    DBConnect dbconn = null;

    public DAOBillDetail(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }

    public int addBillDetail(BillDetail billDetail) {
        int n = 0;
        //preStatement insert
        String preSql = "insert into BillDetail(pid, oID, quantity, price, total) "
                + "values (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, billDetail.getPid());
            pre.setString(2, billDetail.getoID());
            pre.setInt(3, billDetail.getQuantity());
            pre.setDouble(4, billDetail.getMoney());
            pre.setDouble(5, billDetail.getTotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

//        //statement insert
//        String sql = "insert into BillDetail(pid, oID, quantity, price, total) "
//                + "values ('" + billDetail.getPid()
//                + "','" + billDetail.getoID()
//                + "'," + billDetail.getQuantity()
//                + "," + billDetail.getMoney()
//                + "," + billDetail.getTotal() + ")";
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }

    public int updateBillDetail(BillDetail billdetail) {
        //preStatement update
        int n = 0;
        String Presql = "update BillDetail set pid=?,"
                + " quantity=?, money=?, total=? where oID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(Presql);
            pre.setString(1, billdetail.getPid());
            pre.setInt(2, billdetail.getQuantity());
            pre.setDouble(3, billdetail.getMoney());
            pre.setDouble(4, billdetail.getTotal());
            pre.setString(5, billdetail.getoID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteBillDetail(String oID, String pid) {
        int n = 0;
        String sql = "delete from BillDetail where pid=? and oid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pid);
            pre.setString(2, oID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void sort(String column) {
        String checksql = "select * from BillDetail order by " + column;
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1), oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double money = rs.getDouble(4), total = rs.getDouble(5);
                BillDetail bd = new BillDetail(pid, oID, quantity, money, total);
                System.out.println(bd);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByPid(String pid1) {
        String checksql = "select * from BillDetail where pid like '%" + pid1 + "%'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1), oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double money = rs.getDouble(4), total = rs.getDouble(5);
                BillDetail bd = new BillDetail(pid, oID, quantity, money, total);
                System.out.println(bd);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAllBillDetail() {
        String sql = "select * from BillDetail";

        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
            ResultSet rs = dbconn.getData(sql);
            while (rs.next()) {
                String pid = rs.getString(1),
                        oID = rs.getString(2);
                int quantity = rs.getInt(3);
                double money = rs.getDouble(4),
                        total = rs.getDouble(5);
                BillDetail bilde = new BillDetail(pid, oID, quantity, money, total);
                System.out.println(bilde);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOBillDetail dao = new DAOBillDetail(dbconn);
//        int n = dao.addBillDetail(new BillDetail("1", "123", 1, 1000, 1));
//        int n = dao.addBillDetail(new BillDetail("3", "123", 5, 500, 2));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }

        //dao.sort("price");
        //dao.searchByPid("3");
        dao.displayAllBillDetail();
    }

    public ArrayList<BillDetail> getAllBillDetail() {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        String sql = "select * from BillDetail";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                BillDetail billDetail = new BillDetail(rs.getString(1), 
                        rs.getString(2), rs.getInt(3), 
                        rs.getDouble(4), rs.getDouble(5));
                arr.add(billDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public ArrayList<BillDetail> getBillDetail(String oid) {
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        String sql = "select * from BillDetail where oid='"+oid+"'";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                BillDetail billDetail = new BillDetail(rs.getString(1), 
                        rs.getString(2), rs.getInt(3), 
                        rs.getDouble(4), rs.getDouble(5));
                arr.add(billDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}
