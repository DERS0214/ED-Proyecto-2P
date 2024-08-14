package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class InicioController implements Initializable {

    @FXML
    private Button primaryButton;
    @FXML
    private Button primaryButton1;
    @FXML
    private Button primaryButton11;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("PrimaryController initialized");
    }
}
