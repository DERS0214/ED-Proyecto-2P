package Clases;

import java.io.IOException;
import javafx.fxml.FXML;

public class CargarController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}