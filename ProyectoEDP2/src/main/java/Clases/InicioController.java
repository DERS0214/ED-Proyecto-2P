package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Bases.*;

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

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarSeleccionado();
    }

    @FXML
    private void switchToPartida() throws IOException{
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
}
