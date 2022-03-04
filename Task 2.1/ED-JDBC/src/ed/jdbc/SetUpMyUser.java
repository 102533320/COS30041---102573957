/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ed.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class SetUpMyUser {

    public static ArrayList<MyUser> prepareMyuserData() {
        ArrayList<MyUser> myList = new ArrayList<MyUser>();
        MyUser myuser1 = new MyUser("000001", "Peter Smith", "123456","psmith@swin.edu.au", "9876543210", "Swinburne EN510f","What is my name?", "Peter");
        MyUser myuser2 = new MyUser("000002", "James T. Kirk", "234567","jkirk@swin.edu.au", "8765432109", "Swinburne EN511a","What is my name?", "James");
        MyUser myuser3 = new MyUser("000003", "Sheldon Cooper", "345678","scooper@swin.edu.au", "7654321098", "Swinburne EN512a","What is my last name?", "Cooper");
        MyUser myuser4 = new MyUser("000004", "Clark Kent", "456789","ckent@swin.edu.au", "6543210987", "Swinburne EN513a", "What is my last name?", "Kent");
        MyUser myuser5 = new MyUser("000005", "Harry Potter", "567890","hpotter@swin.edu.au", "6543210987", "Swinburne EN514a","What is my last name?", "Potter");
        myList.add(myuser1);
        myList.add(myuser2);
        myList.add(myuser3);
        myList.add(myuser4);
        myList.add(myuser5);
        return myList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyDB mydb = new MyDB();
        /*
            * drop table first for a clean start
            * may cause error if table does not exist
         */
        mydb.dropMyuserTable();
        mydb.createMyuserTable();
        ArrayList<MyUser> aList = prepareMyuserData();
        mydb.addRecords(aList);

        MyUser test = mydb.getRecord("0000awdwad03");
        MyUser test1 = mydb.getRecord("000004");

        
    }

}
