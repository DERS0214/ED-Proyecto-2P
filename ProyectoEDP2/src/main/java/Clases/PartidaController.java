package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Bases.*;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;


public class PartidaController implements Initializable {
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;
    private int numPreguntas;
    private int numActuales;
    private BinaryTree<String> arbol;
    @FXML
    private Label lblPregunta;
    @FXML
    private Button btnVolverJugar;
    @FXML
    private Button btnEscogerTema;
    @FXML
    private Button btnVolverInicio;
    @FXML
    private Button btnRegresar;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargar();
        this.lblPregunta.setText(arbol.getRoot().getContent());
        this.numActuales = 1;
        lblPregunta.setWrapText(true);
        lblPregunta.setAlignment(Pos.CENTER);
        lblPregunta.setTextAlignment(TextAlignment.CENTER);

    }    
    
    @FXML
    private void switchToInicio() throws IOException{
        App.setRoot("inicio");
    }
    
    @FXML
    private void switchToCargar() throws IOException{
        App.setRoot("cargar");
    }
    
    @FXML
    private void volverAJugar() throws IOException {
        App.setRoot("pantallaTemporal");
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                javafx.application.Platform.runLater(() -> {
                    try {
                        App.setRoot("partida"); // Cerrar la pantalla temporal
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public void setNumPreguntas(int numPreguntas){
        this.numPreguntas=numPreguntas;    
    }
    
    public void cargar(){
        this.setNumPreguntas( Archivos.leerPreguntas());
        Tema t = Archivos.leerSeleccionado();
        this.setArbol(t.crearArbol());
    }

    public void setArbol(BinaryTree<String> arbol) {
        this.arbol = arbol;
    }
    
    public void cambiarLabel(String pregunta){
        lblPregunta.setText(pregunta);
    }
    
    public void cambiarPregunta(){
        lblPregunta.setText(arbol.getRoot().getContent());
    }
    
    @FXML
    public void presionoSi(){
        this.numActuales++;
        System.out.println("PREGUNTA: "+numActuales);
        if(arbol.getRoot().getLeft()==null){
            System.out.println("ARBOL NULO");
            mostrarNoRespuestas();
            return;
        }
        
        if(arbol.getRoot().getLeft().isLeaf()){
            this.setArbol(arbol.getRoot().getLeft());
            System.out.println("ARBOL HOJA");
            mostrarRespuesta();
            return;
        }
        
        if(numActuales>numPreguntas){
            this.setArbol(arbol.getRoot().getLeft());
            System.out.println("PREGUNTAS AGOTADAS");
            mostrarPosiblesRespuestas();
            return;
        }
        
        this.setArbol(arbol.getRoot().getLeft());
        this.cambiarPregunta();
    }
    
    @FXML
    public void presionoNo(){
        this.numActuales++;
        if(arbol.getRoot().getRight()==null){
            System.out.println("ARBOL NULO");
            mostrarNoRespuestas();
            return;
        }
        
        if(arbol.getRoot().getRight().isLeaf()){
            this.setArbol(arbol.getRoot().getRight());
            System.out.println("ARBOL HOJA");
            mostrarRespuesta();
            return;
        }
        
        if(numActuales>numPreguntas){
            this.setArbol(arbol.getRoot().getRight());
            System.out.println("PREGUNTAS AGOTADAS");
            mostrarPosiblesRespuestas();
            return;
        }
        
        this.setArbol(arbol.getRoot().getRight());
        this.cambiarPregunta();
    }
    
    public void mostrarPosiblesRespuestas(){
        ArrayList<String> posibles=arbol.obtenerHojas();
        if(posibles.size()==0){
            this.mostrarNoRespuestas();
        }else{
            String linea = "";
            for (int i = 0; i<posibles.size();i++){
                linea = linea+posibles.get(i);
                if(i!=(posibles.size()-1)){
                    linea=linea+", ";
                }else{
                    linea=linea+".";
                }
            }
            this.cambiarLabel("Se agotaron las preguntas :(, pero los posibles animales son: "+linea);
            this.juegoTerminado();
        }
                
    }
    
    public void mostrarNoRespuestas(){
        this.cambiarLabel("No pudimos encontrar el animal en el que piensas :(");
        juegoTerminado();
    }
    
    public void mostrarRespuesta(){
        this.cambiarLabel("El animal en el que estas pensando es: "+arbol.getRoot().getContent());
        juegoTerminado();
    }
    
    public void juegoTerminado(){
        this.btnVolverJugar.setVisible(true);
        this.btnEscogerTema.setVisible(true);
        this.btnVolverInicio.setVisible(true);
        this.btnSi.setVisible(false);
        this.btnNo.setVisible(false);
        this.btnRegresar.setVisible(false);
    }
}
