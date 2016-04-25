package code.control.frontend.search;

import code.control.jpa.entity.Client;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SearchClientController implements Initializable {

    @FXML
    private Button btRemove;
    @FXML
    private TextField tfIdClient;
    @FXML
    private TextField tfRetornError;

    @FXML
    private Button btnAddUser;
    @FXML
    private TableView<TableModel> tvConsult;
    @FXML
    private TableColumn tcName;
    @FXML
    private TextField tfCode;
    @FXML
    private TableColumn tcCode;
    @FXML
    private TextField tfName;
    @FXML
    private Button btnRemove;

    @FXML
    private void remove(ActionEvent evento) {
        String num = tfIdClient.getText();

        if (tfIdClient == null) {
            tfRetornError.setText("Type ID");
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            //procurar saber no banco qual cliente tem esse id
            Query consulta = em.createQuery("select client from Client client where client.id= " + num);
            List<Client> clientes = consulta.getResultList();

            clientes.stream().forEach((cliente) -> {
                em.remove(cliente);
            });
            em.getTransaction().commit();
            em.close();
            tfRetornError.setText("User removed");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query consulta = em.createQuery("select cliente from Cliente cliente");
        
        List<Client> clients = consulta.getResultList();
        em.getTransaction().commit();
        em.close();

        ObservableList<TableModel> dates = FXCollections.observableArrayList();

        for (Client client : clients) {

            //inserir a consulta do banco aqui
            dates.add(new TableModel(client.getId().intValue(), client.getName()));

            tcCode.setCellValueFactory(new PropertyValueFactory("codigo"));
            tcName.setCellValueFactory(new PropertyValueFactory("nome"));

            tvConsult.setItems(dates);
        }
    }

}
