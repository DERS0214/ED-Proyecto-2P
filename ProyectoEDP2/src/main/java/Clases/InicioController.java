package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Bases.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class InicioController implements Initializable {

    @FXML
    private Button btnIniciar;
    @FXML
    private Button btnCargar;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPreguntas;
    @FXML
    private Label lblRespuestas;
    @FXML
    private TextField txtNumero;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarSeleccionado();
    }

    private void switchToPartida(int numero) throws IOException {
        App.setRoot("partida");
    }

    @FXML
    private void switchToCargar() throws IOException{
        App.setRoot("cargar");
    }

    @FXML
    private void cerrar() throws IOException{
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void cargarSeleccionado(){
        Tema t = Archivos.leerSeleccionado();
        if (t!=null){
            String nombre = t.getNombre();            
            String preguntas = Integer.toString(t.cantPreguntas()) ;
            String respuestas = Integer.toString(t.cantRespuestas());
            lblNombre.setText(nombre);
            lblPreguntas.setText(preguntas);
            lblRespuestas.setText(respuestas);
        }
        
    }
    
    @FXML
    public void iniciarPartida() throws IOException{
        Tema t = Archivos.leerSeleccionado();
        String num = txtNumero.getText();
        
        if (!(txtNumero.getText().isEmpty()) && t!=null ){
            try {
                int value = Integer.parseInt(num);
                if (value > 0 && value <= t.cantPreguntas()) {
                    mostrarPantallaTemporal();
                    Archivos.escribirCantPreg(num);
                } else {
                    System.out.println("El valor de preguntas debe ser menor o igual al número de preguntas del tema");      
                    Alert alerta = new Alert(AlertType.INFORMATION);
                    alerta.setTitle("Error cantidad de preguntas");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Por favor, digite un número entre 1 y "+ t.cantPreguntas());
                    alerta.showAndWait();
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR AL CONVERTIR A ENTERO: "+e.getMessage());
            }
            
        }else{
            //logica para indicar que no hay un tema cargado
            if(t==null  || lblNombre.getText().isEmpty() ){
                Alert alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Error tema vacío");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, seleccione primero un tema");
                alerta.showAndWait();                
            }
            
            if(!lblNombre.getText().isEmpty() && txtNumero.getText().isEmpty()){
                Alert alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Error cantidad de preguntas vacío");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor digite, un numero entre 1 y "+ t.cantPreguntas());
                alerta.showAndWait();
            }
        }
        
    }
    
    private void mostrarPantallaTemporal() throws IOException {
        App.setRoot("pantallaTemporal");
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                javafx.application.Platform.runLater(() -> {
                    try {
                        App.setRoot("partida"); 
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
