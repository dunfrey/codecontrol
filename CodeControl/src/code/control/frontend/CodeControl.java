package code.control.frontend;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CodeControl extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {     
        Parent root = FXMLLoader.load(getClass().getResource("CodeControlFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle(" ---- Code Control / QR-Code ---- ");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
