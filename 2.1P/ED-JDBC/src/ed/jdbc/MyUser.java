/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class MyUser {

    private String userid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String secQn;
    private String secAns;

    public MyUser(String userid, String name, String password, String email, String phone, String address, String secQn, String secAns) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.secQn = secQn;
        this.secAns = secAns;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecQn() {
        return secQn;
    }

    public void setSecQn(String secQn) {
        this.secQn = secQn;
    }

    public String getSecAns() {
        return secAns;
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    public static MyUser fromResultSet(ResultSet rs){
        try {
            return new MyUser(
                rs.getString("userid"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("secQn"),
                rs.getString("secAns")
            );
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<MyUser> fromResultSetCollection(ResultSet rs){
        ArrayList<MyUser> mapped = new ArrayList<MyUser>();
        try {
            while (rs.next()) {
                mapped.add(fromResultSet(rs));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return mapped;
    }
}
