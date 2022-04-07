/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.slsb;

import javax.ejb.Stateless;

/**
 * EJB class
 */
@Stateless
public class HelloWorldBean implements HelloWorldBeanRemote, HelloWorldBeanLocal {

    @Override
    public String getGreetings(String name) {
        return "Hello, " + name + "!";
    }

    @Override
    public String getGreetingsLocal(String name) {
        return "Local Interface - Hello, " + name + "!";
    }

}
