/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edjee;

import entity.MyuserDTO;
import session.MyuserFacadeRemote;

/**
 *
 * @author Matthew Coulter
 */
public class MyuserAppClient {

    @javax.ejb.EJB
    private static MyuserFacadeRemote myuserFacade;

    public static void main(String[] args) {
        MyuserAppClient client = new MyuserAppClient();
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = myuserFacade.createRecord(myuserDTO);
        client.showCreateResult(result, myuserDTO);
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO2 = new MyuserDTO("000007", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = myuserFacade.createRecord(myuserDTO2);
        client.showCreateResult(result, myuserDTO2);
        
        // 1. Test getRecord
        MyuserDTO myuserDTO2dup = myuserFacade.getRecord(myuserDTO2.getUserid());
        showGetResult(myuserDTO2.getUserid(),myuserDTO2dup);
        
        // 2. Test updateRecord
        MyuserDTO myuserDTO2updated = new MyuserDTO("000006", "David Lee UPDATED", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David"); // need to instantiate a new DTO as they are read only, cannot modify
        MyuserDTO myuserDTO2updatedRefetched = myuserFacade.getRecord(myuserDTO2updated.getUserid());
        showUpdateResult(myuserDTO2updated,myuserDTO2updatedRefetched);
        
        // 3. Test deleteRecord
        String userIdToDelete = myuserDTO2updated.getUserid();
        boolean deletedResult = myuserFacade.deleteRecord(userIdToDelete);
        showDeleteResult(userIdToDelete,deletedResult);
        
    }
    
    public static void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }
    
    public static void showGetResult(String userid,MyuserDTO getResult){
        if (getResult == null){
            System.out.println("Record with primary key " + userid + " could not be found in the database table!");
        } else {
            if (getResult.getUserid().equals(userid)){
                System.out.println("Record with primary key " + userid + " has been found in the database table!");
            } else {
                System.out.println("Record with primary key " + userid + " somehow returned " + getResult.getUserid() + " instead!");
            }
        }
    }
    
    public static void showUpdateResult(MyuserDTO updatedObject,MyuserDTO reFetchedUpdatedObject){
        // assums the name was updated
        if (updatedObject.getName().equals(reFetchedUpdatedObject.getName())){
            System.out.println("Record with primary key " + updatedObject.getUserid()
                    + " could not be updated in the database table.");
        } else {
            System.out.println("Record with primary key " + updatedObject.getUserid()
                    + " has been updated in the database table.");
        }
    }
    public static void showDeleteResult(String userid,boolean deletedResult){
        // assums the name was updated
        if (deletedResult == true){
            System.out.println("Record with primary key " + userid
                    + " has been deleted from the database table.");
        } else {
            System.out.println("Record with primary key " + userid
                    + " could not be deleted from the database table.");
        }
    }
}
