package Clases;

import Bases.Archivos;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import Bases.BinaryTree;
import Bases.NodeBinaryTree;
import Bases.Tema;

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
    private ArrayList<String> preguntas;
    private ArrayList<String> respuestas;    
    
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
                
                this.preguntas = lineasArrayList;
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
                
                this.respuestas = lineasArrayList;
                

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
    

    
    public void guardarTema(){
        Tema t = new Tema("titulo",preguntas,respuestas);        
        Archivos.guardarTema("titulo", t);        
    }
}