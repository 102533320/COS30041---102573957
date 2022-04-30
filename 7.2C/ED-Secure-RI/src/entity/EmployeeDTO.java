/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;


public class EmployeeDTO implements Serializable {
    
    String empid;
    String name;
    String phone;
    String address;
    String email;
    String password;
    String appGroup;
    String bnkAccId;
    Double salary;
    Boolean active;
    
    public EmployeeDTO(String empid, String name, String phone, String address,
            String email, String password, String appGroup, 
            String bnkAccId, Double salary, Boolean active) {
        this.empid = empid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.appGroup = appGroup;
        this.bnkAccId = bnkAccId;
        this.salary = salary;
        this.active = active;
    }

    public String getEmpid() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }

    public void setBnkAccId(String bnkAccId) {
        this.bnkAccId = bnkAccId;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAppGroup() {
        return appGroup;
    }
    
    public String getBnkAccId() {
        return bnkAccId;
    }

    public Double getSalary() {
        return salary;
    }

    public Boolean isActive() {
        return active;
    }
    
}
