/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package web;

import email.EmailSender;
import entity.MyuserDTO;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import session.MyuserFacadeRemote;

/**
 *
 * @author Matt
 */
@Named(value = "myuserManagedBean")
@RequestScoped
public class MyuserManagedBean {

    @javax.ejb.EJB
    private MyuserFacadeRemote myuserFacade;

    /**
     * Creates a new instance of MyuserManagedBean
     */
    private String userid;
    private String name;
    private String password;
    private String cPassword; // for confirmed password field
    private String email;
    private String phone;
    private String address;
    private String secQn;
    private String secAns;

    public MyuserManagedBean() {
    }

    public MyuserFacadeRemote getMyuserFacade() {
        return myuserFacade;
    }

    public void setMyuserFacade(MyuserFacadeRemote myuserFacade) {
        this.myuserFacade = myuserFacade;
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

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
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

    public String addUser() {
        String result = "failure";
        /*
            * are all data entered valid?
            * and password the same as cPassword (case sensitive)
            * before calling the façade’s createRecord() method
         */
        if (isValidUserid(userid) && isValidName(name)
                && isValidPassword(password) && isValidPassword(cPassword)
                && isValidEmail(email) && isValidPhone(phone)
                && isValidAddress(address) && isValidSecQn(secQn)
                && isValidSecAns(secAns) && password.equals(cPassword)) {
            MyuserDTO myuserDTO = new MyuserDTO(userid, name,
                    password, email, phone, address, secQn, secAns);
            if (myuserFacade.createRecord(myuserDTO)) {
                result = "success";
            }
        }
        return result;
    }

    public String editUser() {
        String result = "failure";
        if (isValidUserid(userid) && isValidName(name)
                && isValidPassword(password) && isValidPassword(cPassword)
                && isValidEmail(email) && isValidPhone(phone)
                && isValidAddress(address) && isValidSecQn(secQn)
                && isValidSecAns(secAns) && password.equals(cPassword)) {
            MyuserDTO myuserDTO = new MyuserDTO(userid, name,
                    password, email, phone, address, secQn, secAns);
            if (myuserFacade.updateRecord(myuserDTO)) {
                result = "success";

                // send an email informing the information has been updated
                EmailSender.SendEmail(email,
                        "Your information has been changed.",
                        "In case this is not done by you, please contact us immediately at xyz@swin.com"
                );
            }
        }
        return result;
    }

    public String lookupUser() {
        String result = "failure";
        if (isValidUserid(userid)) {
            MyuserDTO myuser = myuserFacade.getRecord(userid);
            if (myuser != null) {
                // load all the user values into the bean
                setUserid(myuser.getUserid());
                setName(myuser.getName());
                setPassword(myuser.getPassword());
                setcPassword(myuser.getPassword());
                setEmail(myuser.getEmail());
                setPhone(myuser.getPhone());
                setAddress(myuser.getAddress());
                setSecQn(myuser.getSecQn());
                setSecAns(myuser.getSecAns());

                result = "success";
            }
        }
        return result;
    }

    /* 
        * Some basic checking, complicated checking can be done later
        * not a good way of doing this
        * Should use JSF’s validator method to do this – left as C task
     */
    public boolean isValidUserid(String userid) {
        return (userid != null);
    }

    public boolean isValidName(String name) {
        return (name != null);
    }

    public boolean isValidPassword(String password) {
        return (password != null);
    }

    public boolean isValidEmail(String email) {
        return (email != null);
    }

    public boolean isValidPhone(String phone) {
        return (phone != null);
    }

    public boolean isValidAddress(String address) {
        return (address != null);
    }

    public boolean isValidSecQn(String secQn) {
        return (secQn != null);
    }

    public boolean isValidSecAns(String secAns) {
        return (secAns != null);
    }
}
