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
import javax.persistence.Query;

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
        if (getRecord(myuserDTO.getUserid()) != null) {
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
        Myuser myuser = find(userId);
        if(myuser == null) return null;
        
        return myDAO2DTO(myuser);
    }

    @Override
    public boolean updateRecord(MyuserDTO myuserDTO) {
        if (getRecord(myuserDTO.getUserid()) == null) return false;
        
        Myuser myuser = myDTO2DAO(myuserDTO);
        try {
            edit(myuser);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteRecord(String userId) {
        MyuserDTO myuserDTO = getRecord(userId);
        if (myuserDTO != null) return false;
        
        Myuser myuser = myDTO2DAO(myuserDTO);
        try {
            remove(myuser);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
        Query query = em.createNamedQuery("Myuser.findByAddress").setParameter("address", address);
        ArrayList<Myuser> daoList = (ArrayList<Myuser>)query.getResultList();
        
        ArrayList<MyuserDTO> dtoList = new ArrayList<MyuserDTO>();
        for (Myuser myuser : daoList){
            dtoList.add(myDAO2DTO(myuser));
        }
        
        return dtoList;
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
