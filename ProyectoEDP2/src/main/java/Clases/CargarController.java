package Clases;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class CargarController {

    @FXML
    private Button btnPreguntas;
    @FXML
    private Button btnRespuestas;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtCategoria;

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToSecondary(ActionEvent event) {
    }
    
        @FXML
    private void cargarPreguntas() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Preguntas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(txtCategoria.getScene().getWindow());

        if (selectedFile != null) {
            try {
                List<String> lineas = (List<String>) Files.readAllLines(selectedFile.toPath(), StandardCharsets.UTF_8);
                ArrayList<String> lineasArrayList = new ArrayList(lineas);
                
                for(String linea : lineas){
                    System.out.println(linea);
                }

                // Aquí puedes trabajar con la lista `lineasArrayList`
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        }
    }
    
    @FXML
    private void cargarRespuestas() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Respuestas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(txtCategoria.getScene().getWindow());

        if (selectedFile != null) {
            try {
                List<String> lineas = (List<String>) Files.readAllLines(selectedFile.toPath(), StandardCharsets.UTF_8);
                ArrayList<String> lineasArrayList = new ArrayList(lineas);
                
                for(String linea : lineas){
                    System.out.println(linea);
                }

                // Aquí puedes trabajar con la lista `lineasArrayList`
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("inicio");
    }
}