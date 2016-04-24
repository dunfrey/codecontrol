package code.control.frontend.pesquisa;

import code.control.jpa.entidade.Cliente;
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

public class PesquisaUsuarioController implements Initializable {

    @FXML
    private Button btRemover;
    @FXML
    private TextField tfIdCliente;
    @FXML
    private TextField tfRetornoErro;

    @FXML
    private Button btnAdicionar;
    @FXML
    private TableView<TableModel> tvConsulta;
    @FXML
    private TableColumn tcNome;
    @FXML
    private TextField tfCod;
    @FXML
    private TableColumn tcCodigo;
    @FXML
    private TextField tfNome;
    @FXML
    private Button btnRemover;

    @FXML
    private void remover(ActionEvent evento) {
        String num = tfIdCliente.getText();

        if (tfIdCliente == null) {
            tfRetornoErro.setText("Informe o ID");
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            //procurar saber no banco qual cliente tem esse id
            Query consulta = em.createQuery("select cliente from Cliente cliente where cliente.id= " + num);
            List<Cliente> clientes = consulta.getResultList();

            clientes.stream().forEach((cliente) -> {
                em.remove(cliente);
            });
            em.getTransaction().commit();
            em.close();
            tfRetornoErro.setText("Usuario Removido");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeControlPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query consulta = em.createQuery("select cliente from Cliente cliente");
        
        List<Cliente> clientes = consulta.getResultList();
        em.getTransaction().commit();
        em.close();

        ObservableList<TableModel> dados = FXCollections.observableArrayList();

        for (Cliente cliente : clientes) {

            //inserir a consulta do banco aqui
            dados.add(new TableModel(cliente.getId().intValue(), cliente.getNome()));

            tcCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
            tcNome.setCellValueFactory(new PropertyValueFactory("nome"));

            tvConsulta.setItems(dados);
        }
    }

}
