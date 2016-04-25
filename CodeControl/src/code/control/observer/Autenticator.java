package code.control.observer;

import code.control.jpa.entity.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import code.control.observer.arduino.UnlockDoor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Autenticator implements Observer {

    public Autenticator(Observable obs) {
        obs.incluirObservador(this);
    }

    @Override
    public void atualizar(String retorno) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query consulta = em.createQuery("select cliente from Cliente cliente");
        List<Client> clientes = consulta.getResultList();
        em.getTransaction().commit();
        em.close();

        clientes.stream().forEach((cliente) -> {
            if (!retorno.equals(cliente.getName())) {
            } else {
                try {
                    UnlockDoor d = new UnlockDoor();
                    d.executa();
                } catch (Exception ex) {
                    Logger.getLogger(Autenticator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
