/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DO THANH TRUNG
 */
public class Bill {

    private String oID, dateCreate, cname, cphone, cAddress;
    private double total;
    private int status, cid;

    public Bill() {
    }

    public Bill(String oID, String dateCreate, String cname, String cphone, String cAddress, double total, int status, int cid) {
        this.oID = oID;
        this.dateCreate = dateCreate;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.total = total;
        this.status = status;
        this.cid = cid;
    }

    public Bill(String oID, String cname, String cphone, String cAddress, double total, int cid) {
        this.oID = oID;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.total = total;
        this.cid = cid;
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Bill{" + "oID=" + oID + ", dateCreate=" + dateCreate + ", cname=" + cname + ", cphone=" + cphone + ", cAddress=" + cAddress + ", total=" + total + ", status=" + status + ", cid=" + cid + '}';
    }

}
