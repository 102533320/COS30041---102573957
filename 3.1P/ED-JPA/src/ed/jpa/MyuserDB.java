package ed.jpa;

import entity.Myuser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Matthew Coulter
 */
public class MyuserDB {

    private EntityManager em = null;

    public MyuserDB() {
// using default persistence unit defined in the persistence.xml file
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ED-EntityPU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManger() {
        return em;
    }

    private Myuser findMyuser(String userid) {
        return em.find(Myuser.class, userid);
    }

    public MyuserDTO getRecord(String userId) {
        Myuser myuser = findMyuser(userId);
        return myDAO2DTO(myuser);
    }

    private boolean createMyuser(Myuser myuser) throws Exception {
        try {
            if (findMyuser(myuser.getUserid()) != null) {
// myuser exists already
                return false;
            }
// myuser does not exist in database
            em.getTransaction().begin();
            em.persist(myuser); // to add an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean createRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTO2DAO(myuserDTO);
        boolean result = false;
        try {
            result = this.createMyuser(myuser);
        } catch (Exception ex) {
        }
        return result;
    }

    private boolean updateMyuser(Myuser myuser) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(myuser); // to update an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean updateRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTO2DAO(myuserDTO);
        boolean result = false;
        try {
            result = this.updateMyuser(myuser);
        } catch (Exception ex) {
        }
        return result;
    }

    private boolean deleteMyuser(Myuser myuser) throws Exception {
        try {
            if (findMyuser(myuser.getUserid()) == null) {
                // myuser doesn't exist
                return false;
            }
            // myuser exists in database
            em.getTransaction().begin();
            em.remove(myuser); // to delete an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean deleteRecord(String userId) {
        Myuser myuser = findMyuser(userId);
        boolean result = false;
        if (myuser == null) {
            return false;
        }
        try {
            result = this.deleteMyuser(myuser);
        } catch (Exception ex) {
        }
        return result;
    }

    private Myuser myDTO2DAO(MyuserDTO myDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myDTO.getUserid());
        myuser.setName(myDTO.getName());
        myuser.setPassword(myDTO.getPassword());
        myuser.setEmail(myDTO.getEmail());
        myuser.setPhone(myDTO.getPhone());
        myuser.setAddress(myDTO.getAddress());
        myuser.setSecqn(myDTO.getSecQn());
        myuser.setSecans(myDTO.getSecAns());
        return myuser;
    }

    private MyuserDTO myDAO2DTO(Myuser myuser) {
        if (myuser == null) {
            return null;
        }
        return new MyuserDTO(myuser.getUserid(), myuser.getName(), myuser.getPassword(),
                myuser.getEmail(), myuser.getPhone(), myuser.getAddress(),
                myuser.getSecqn(), myuser.getSecans());
    }
}
