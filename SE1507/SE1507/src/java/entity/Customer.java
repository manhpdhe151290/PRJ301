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
public class Customer {

    private int cid;
    private String cname, cphone, cAddress, username, password;
    private int status;

    public Customer() {
    }

    public Customer(int cid, String cname, String cphone, String cAddress, String username, String password, int status) {
        this.cid = cid;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Customer(String cname, String cphone, String cAddress, String username, String password) {
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
    }

    public Customer(String cname, String cphone, String cAddress, String username, String password, int status) {
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
    }
    
    

    public int getCid() {
        return cid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" + "cid=" + cid + ", cname=" + cname + ", cphone=" + cphone + ", cAddress=" + cAddress + ", username=" + username + ", password=" + password + ", status=" + status + '}';
    }

}
