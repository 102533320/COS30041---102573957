/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.slsb;

import javax.ejb.EJB;

/**
 * Client App
 */
public class HelloWorldAppClient {

    @EJB
    private static HelloWorldBeanRemote helloWorldBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HelloWorldAppClient appClient = new HelloWorldAppClient();
        
        String result = appClient.sayHello("David");
        
        System.out.println(result);
    }
    
    public String sayHello(String name) {
        return helloWorldBean.getGreetings(name);
    }
    
}
