package code.control.jpa.dao;

import code.control.jpa.entidade.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteDAO {

    public void persistir(Cliente usuario) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void remover(Cliente usuario) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Cliente temp = em.merge(usuario);
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
