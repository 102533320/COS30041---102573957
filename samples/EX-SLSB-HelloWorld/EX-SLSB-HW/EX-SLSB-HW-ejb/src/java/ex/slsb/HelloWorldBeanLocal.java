/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.slsb;

import javax.ejb.Local;

/**
 * local bean class
 */
@Local
public interface HelloWorldBeanLocal {

    String getGreetingsLocal(String name);
    
}
