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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
                    if (!linea.startsWith("¿") || !linea.endsWith("?")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Formato Incorrecto");
                        alert.setHeaderText("Una o más líneas no cumplen con el formato de pregunta.");
                        alert.setContentText("Cada línea debe comenzar con '¿' y terminar con '?'. Línea incorrecta: \n" + linea);
                        alert.showAndWait();
                        return; // Salir del método si se encuentra una línea incorrecta
                    }
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
                    if (linea.startsWith("¿") || linea.endsWith("?")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Formato Incorrecto");
                        alert.setHeaderText("Una o más líneas no cumplen con el formato de respuesta.");
                        alert.setContentText("Cada línea NO debe comenzar con '¿' y terminar con '?'. Línea incorrecta: \n" + linea);
                        alert.showAndWait();
                        return; // Salir del método si se encuentra una línea incorrecta
                    }
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
        //Si el nombre del tema esta en blanco o sea el textfield esta vacio no guarda, ni si los arraylist de preguntas ni respuestas estan vacios
        if(!(txtCategoria.getText().isEmpty()) && this.preguntas != null && this.respuestas != null){
            Tema t = new Tema(txtCategoria.getText(),preguntas,respuestas);        
            Archivos.guardarTema(t.getNombre(), t);    
            //el metodo archivos.guardartema no es booleano, la validacion se hace aqui
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Tema Guardado con Éxito");
            alerta.setHeaderText(null);
            alerta.setContentText("Tu tema con archivo de preguntas y respuestas fue guardado con éxito");

        alerta.showAndWait();
            this.limpiarCampos();
        }else if(txtCategoria.getText().isEmpty() ){
            System.out.println("El título del tema esta vacío");
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Título del tema vacío");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor coloque un título al tema");
            alerta.showAndWait();
            return;
        } else{
            System.out.println("Las preguntas o respuestas estan vacías");
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Preguntas o Respuestas vácias");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor verifique los archivos de preguntas y respuestas");
            alerta.showAndWait();
        }        
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
                      
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Archivo eliminado con éxito");
            alerta.setHeaderText(null);
            alerta.setContentText("Archivo eliminado con éxito");
            alerta.showAndWait();
            
        }  else{
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Tema vacío");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor seleccione un tema para borrarlo");
            alerta.showAndWait();   
        }
        
    }
    
    @FXML
    private void seleccionarTema() throws IOException{
        String nombre = cbTema.getValue();
        if(nombre!=null){
            this.limpiarCampos();
            Archivos.escribirSeleccionado(nombre);
            this.switchToInicio();
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Tema cargado");
            alerta.setHeaderText(null);
            alerta.setContentText(" Su tema cargado ");
            alerta.showAndWait();
        }else{
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Tema vacío");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor seleccione un tema");
            alerta.showAndWait();
        }  
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