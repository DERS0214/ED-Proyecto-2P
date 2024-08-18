
package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class PantallaTemporalController implements Initializable {

    @FXML
    private Button btnSalir;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void switchToPartida() throws IOException {
        App.setRoot("partida");
    }
}
