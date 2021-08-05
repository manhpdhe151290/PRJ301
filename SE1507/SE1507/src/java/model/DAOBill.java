/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
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
public class DAOBill {

    Connection conn = null;
    DBConnect dbconn = null;

    public DAOBill(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn = dbconn;
    }

    //insert, update, delete: number of records
    public int addBill(Bill bil) {
        int n = 0;
        //preStatement insert
        String preSql = "insert into Bill(oID, cname, cphone, cAddress, total, cid)"
                + " values(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, bil.getoID());
            pre.setString(2, bil.getCname());
            pre.setString(3, bil.getCphone());
            pre.setString(4, bil.getcAddress());
            pre.setDouble(5, bil.getTotal());
            pre.setInt(6, bil.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

//        //statment insert
//        String sql = "insert into Bill(oID, cname, cphone, cAddress, total, cid)"
//                + " values('" + bil.getoID() + "', '" + bil.getCname() + "',"
//                + " '" + bil.getCphone() + "', '" + bil.getcAddress() + "',"
//                + " '" + bil.getTotal() + "', '" + bil.getCid() + "')";
//
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }

    public int updateBill(Bill bil) {
        //preStatement update
        int n = 0;
        String Presql = "update Bill set oID=?, dateCreate=?,"
                + " cname=?, cphone=?, cAddress=?, total=?, status=? where cid=?";

        try {
            PreparedStatement pre = conn.prepareStatement(Presql);
            pre.setString(1, bil.getoID());
            pre.setString(2, bil.getDateCreate());
            pre.setString(3, bil.getCname());
            pre.setString(4, bil.getCphone());
            pre.setString(5, bil.getcAddress());
            pre.setDouble(6, bil.getTotal());
            pre.setInt(7, bil.getStatus());
            pre.setInt(8, bil.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteBill(int oID) {
        int n = 0;
        String sql = "delete from Bill where oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, oID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void sort(String column) {
        String checksql = "select * from bill order by " + column;
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1),
                        dateCreate = rs.getString(2),
                        cname = rs.getString(3),
                        cphone = rs.getString(4),
                        cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7),
                        cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByCname(String cname1) {
        String checksql = "select * from bill where cname like '%" + cname1 + "%'";
        ResultSet rs = dbconn.getData(checksql);
        try {
            while (rs.next()) {
                String oID = rs.getString(1), dateCreate = rs.getString(2), cname = rs.getString(3),
                        cphone = rs.getString(4), cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7), cid = rs.getInt(8);
                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bill);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAllBill() {
        String sql = "select * from Bill";

        try {
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
            ResultSet rs = dbconn.getData(sql);
            while (rs.next()) {
                String oID = rs.getString(1),
                        dateCreate = rs.getString(2),
                        cname = rs.getString(3),
                        cphone = rs.getString(4),
                        cAddress = rs.getString(5);
                double total = rs.getDouble(6);
                int status = rs.getInt(7),
                        cid = rs.getInt(8);
                Bill bil = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                System.out.println(bil);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOBill dao = new DAOBill(dbconn);
//        int n = dao.addBill(new Bill("123", "Trung", "01234423", "Ha Noi", 10.5, 1));
//        int n = dao.addBill(new Bill("456", "Trung2", "038703785", "Ha Noi 2", 8.5, 2));
//        if (n > 0) {
//            System.out.println("inserted");
//        }

//        dao.sort("total");
//        dao.searchByCname("trun");
        dao.displayAllBill();
    }

    public ArrayList<Bill> getAllBill() {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "select * from Bill";
        ResultSet rs = dbconn.getData(sql);
        try {
            while(rs.next()){
                Bill bill = new Bill(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getInt(8));
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}
