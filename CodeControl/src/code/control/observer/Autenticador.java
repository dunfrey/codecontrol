package code.control.observer;

import code.control.jpa.entidade.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import code.control.observer.arduino.Desbloqueio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Autenticador implements Observador {

    public Autenticador(Observado obs) {
        obs.incluirObservador(this);
    }

    @Override
    public void atualizar(String retorno) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query consulta = em.createQuery("select cliente from Cliente cliente");
        List<Cliente> clientes = consulta.getResultList();
        em.getTransaction().commit();
        em.close();

        clientes.stream().forEach((cliente) -> {
            if (!retorno.equals(cliente.getNome())) {
            } else {
                try {
                    Desbloqueio d = new Desbloqueio();
                    d.executa();
                } catch (Exception ex) {
                    Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
