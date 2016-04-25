package code.control.jpa.dao;

import code.control.jpa.entity.Client;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientDAO {

    public void persist(Client client) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void remove(Client usuario) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Client temp = em.merge(usuario);
            em.remove(temp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
