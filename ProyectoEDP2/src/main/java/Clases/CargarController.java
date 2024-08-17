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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CargarController implements Initializable{

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
    private ComboBox<String> cbTema;
    @FXML
    private Label lblPreguntas;
    @FXML
    private Label lblRespuestas;
    
        @FXML
    private void cargarPreguntas(){
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cargarTemas();
    }
    
    @FXML
    private void guardarTema(){
        Tema t = new Tema(txtCategoria.getText(),preguntas,respuestas);        
        Archivos.guardarTema(t.getNombre(), t);    
        this.limpiarCampos();
        
    }
    
    private void cargarTemas(){
        List<Tema> temas = Archivos.leerTemasDesdeDirectorio();
        for (int i =0; i<temas.size(); i++){
            cbTema.getItems().add(temas.get(i).getNombre());
        }
    }
    
    
    
    @FXML
    private void eliminarTema(){
        String nombre = cbTema.getValue();
        if(nombre!=null){
            this.limpiarCampos();
            Archivos.eliminarTema(nombre);
        }  
    }
    
    @FXML
    private void seleccionarTema() throws IOException{
        String nombre = cbTema.getValue();
        if(nombre!=null){
            this.limpiarCampos();
            Archivos.escribirSeleccionado(nombre);
        }  
        this.switchToInicio();
    }
    
    private void limpiarCampos(){
        txtCategoria.setText("");
        lblPreguntas.setText("----------");
        lblRespuestas.setText("----------");
        cbTema();
        
        
    }
    
        public void cbTema() {
        // Crear un nuevo hilo
        Thread hilo = new Thread(() -> {
            try {
                // Esperar 100 ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Asegurarse de que la actualización de la UI se realice en el hilo de la aplicación JavaFX
            Platform.runLater(() -> {
                cbTema.getItems().clear();
                cbTema.setEditable(true);
                cbTema.setPromptText("Tema");
                cbTema.setEditable(false);
                cargarTemas(); 
            });
        });

        // Que vuelva a poner la palabra tema en el combo box, solo funciona cuando se ejecuta 2 veces
        cbTema.getItems().clear();
        cbTema.setEditable(true);
        cbTema.setPromptText("Tema");
        cbTema.setEditable(false);
        hilo.start();
    }
    
    @FXML
    private void actualizarStats(){
        String nombre = cbTema.getValue();
        if(nombre!=null){
            Tema t = Archivos.leerTema(nombre);
            lblPreguntas.setText(Integer.toString(t.cantPreguntas()));
            lblRespuestas.setText(Integer.toString(t.cantRespuestas()));
        }
    }
}