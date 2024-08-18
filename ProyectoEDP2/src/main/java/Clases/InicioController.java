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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("partida.fxml"));
        Parent root = loader.load();

        // Obtener el controlador y pasar el dato
        PartidaController partidaController = loader.getController();
        partidaController.setNumPreguntas(numero);

        // Cambiar la escena
        Stage stage = (Stage) btnIniciar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
                } else {
                    System.out.println("El valor de preguntas debe ser menor o igual al numero de preguntas del tema");                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR AL CONVERTIR A ENTERO: "+e.getMessage());
            }
            
        }else{
            //logica para indicar que no hay un tema cargado
        }
        
    }
    
    private void mostrarPantallaTemporal() throws IOException {
        // Cargar el FXML de la pantalla temporal
        Stage tempStage = new Stage();
        Parent tempRoot = FXMLLoader.load(getClass().getResource("pantallaTemporal.fxml"));
        Scene tempScene = new Scene(tempRoot);
        
        switchToPartida(Integer.parseInt(txtNumero.getText())); // Cambiar a la pantalla de partida
        tempStage.setScene(tempScene);
        tempStage.show();
        
        // Crear un hilo para esperar 5 segundos y luego cambiar la escena
        new Thread(() -> {
            try {
                // Esperar 5 segundos
                Thread.sleep(5000);

                // Volver al hilo de JavaFX para cambiar la escena
                javafx.application.Platform.runLater(() -> {
                    tempStage.close(); // Cerrar la pantalla temporal
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
