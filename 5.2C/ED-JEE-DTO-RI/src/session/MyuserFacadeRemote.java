/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package session;

import entity.MyuserDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Matthew Coulter
 */
@Remote
public interface MyuserFacadeRemote {

    boolean createRecord(MyuserDTO myuserDTO);

    MyuserDTO getRecord(String userId);

    boolean updateRecord(MyuserDTO myuserDTO);

    boolean deleteRecord(String userId);

    ArrayList<MyuserDTO> getRecordsByAddress(String address);

}
