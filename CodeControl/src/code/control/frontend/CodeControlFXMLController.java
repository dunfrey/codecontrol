package code.control.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import code.control.observer.Autenticator;
import code.control.observer.SystemObservable;
import code.control.qr.action.GeneratorQR;
import code.control.qr.action.ReaderQR;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CodeControlFXMLController implements Initializable {

    @FXML
    private Button btAdicionarUsuario;
    @FXML
    private Button btListarUsuarios;
    @FXML
    private Button btRemoverUsuario;
    @FXML
    private Button btAutenticarUsuario;

    @FXML
    private TextField tfNome_cadastrar;
    @FXML
    private TextField tfRetornoErro;

    private int controle = 1;

    @FXML
    private void cadastrarUsuario(ActionEvent evento) {
        GeneratorQR spynQR = new GeneratorQR();
        String nome = tfNome_cadastrar.getText();
        if (!nome.equals("")) {
            spynQR.GeradorQR(nome);
            tfRetornoErro.setText("QR-Code gerado. Verifique a pasta files/");
        } else {
            tfRetornoErro.setText("É necessário inserir um nome.");
        }
    }

    @FXML
    private void listarUsuarios(ActionEvent evento) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./search/SearchClient.fxml"));
        
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setScene(scene);
        secondStage.setTitle("Remove User");
        secondStage.show();
    }

    @FXML
    private void autenticarUsuario(ActionEvent evento) throws InterruptedException {
        if (controle == 1) {
            tfRetornoErro.setText("Apresente o QR-Code a qualquer momento.");
            SystemObservable notificator = new SystemObservable();
            ReaderQR sl = new ReaderQR(notificator);
            Autenticator u = new Autenticator(notificator);

            controle = 0;
        } else {
            tfRetornoErro.setText("A janela de captura já se encontra aberta.");
        }

    }
    
    public void retornoErro(){
        tfRetornoErro.setText("Permissão Negada. Tente outro QR-Code. Obrigado!");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      
    }

}

    
//    @FXML
//    private void lerQR(ActionEvent evento) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("./leitura/Leitura.fxml"));
//        
//        Scene scene = new Scene(root);
//        Stage secondStage = new Stage();
//        secondStage.setScene(scene);
//        secondStage.setTitle("Autenticator");
//        secondStage.show();
//    }