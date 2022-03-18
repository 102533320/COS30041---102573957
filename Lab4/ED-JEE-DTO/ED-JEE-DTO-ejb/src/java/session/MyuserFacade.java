/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package session;
import entity.Myuser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.MyuserDTO;
import java.util.ArrayList;

/**
 *
 * @author Matthew Coulter
 */
@Stateless
public class MyuserFacade implements MyuserFacadeRemote {

    @PersistenceContext(unitName = "ED-JEE-DTO-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Myuser myuser) {
        em.persist(myuser);
    }

    private void edit(Myuser myuser) {
        em.merge(myuser);
    }

    private void remove(Myuser myuser) {
        em.remove(em.merge(myuser));
    }

    private Myuser find(Object id) {
        return em.find(Myuser.class, id);
    }

    @Override
    public boolean createRecord(MyuserDTO myuserDTO) {
        if (find(myuserDTO.getUserid()) != null) {
// user whose userid can be found
            return false;
        }
// user whose userid could not be found
        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            this.create(myuser); // add to database
            return true;
        } catch (Exception ex) {
            return false; // something is wrong, should not be here though
        }
    }

    @Override
    public MyuserDTO getRecord(String userId) {
        return null;
    }

    @Override
    public boolean updateRecord(MyuserDTO myuserDTO) {
        return false;
    }

    @Override
    public boolean deleteRecord(String userId) {
        return false;
    }

    @Override
    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
        return null;
    }
    
    private Myuser myDTO2DAO(MyuserDTO myuserDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myuserDTO.getUserid());
        myuser.setName(myuserDTO.getName());
        myuser.setPassword(myuserDTO.getPassword());
        myuser.setEmail(myuserDTO.getEmail());
        myuser.setPhone(myuserDTO.getPhone());
        myuser.setAddress(myuserDTO.getAddress());
        myuser.setSecqn(myuserDTO.getSecQn());
        myuser.setSecans(myuserDTO.getSecAns());
        return myuser;
    }
    private MyuserDTO myDAO2DTO(Myuser myuser){
        return new MyuserDTO(
            myuser.getUserid(), 
            myuser.getName(), 
            myuser.getPassword(), 
            myuser.getEmail(), 
            myuser.getPhone(), 
            myuser.getAddress(), 
            myuser.getSecqn(), 
            myuser.getSecans()
        );
    }
}
