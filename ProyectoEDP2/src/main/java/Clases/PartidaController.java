package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class PartidaController implements Initializable {
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnPregunta;
    @FXML
    private Button btnNo1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void switchToInicio() throws IOException{
        App.setRoot("inicio");
    }
}
