package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Bases.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView ivCharacter;
    ArrayList<String> rutas = new ArrayList<>();
    @FXML
    private Label lblTotal;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargar();
        this.lblPregunta.setText(arbol.getRoot().getContent());
        this.numActuales = 1;
        lblPregunta.setWrapText(true);
        lblPregunta.setAlignment(Pos.CENTER);
        lblPregunta.setTextAlignment(TextAlignment.CENTER);
        rutas.add("Character1.png");
        rutas.add("Character2.png");
        rutas.add("Character3.png");
        rutas.add("Character4.png");
        imgAleatoria();
        this.setLblPreguntas(Integer.toString(numActuales), Integer.toString(numPreguntas));
        
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
                Thread.sleep(5000);
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
        imgAleatoria();
        System.out.println("PREGUNTA: "+numActuales);
        if(arbol.getRoot().getLeft()==null){
            System.out.println("ARBOL NULO");
            mostrarNoRespuestas();
            return;
        }
        
        if(!arbol.getRoot().getLeft().getRoot().getContent().contains("?")){
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
        this.setLblPreguntas(Integer.toString(numActuales), Integer.toString(numPreguntas));
    }
    
    @FXML
    public void presionoNo(){
        this.numActuales++;
        this.imgAleatoria();
        
        if(arbol.getRoot().getRight()==null){
            System.out.println("ARBOL NULO");
            mostrarNoRespuestas();
            return;
        }
        
        if(!arbol.getRoot().getRight().getRoot().getContent().contains("?")){
            this.setArbol(arbol.getRoot().getRight());
            System.out.println("ARBOL RESPUESTA");
            mostrarRespuesta();
            return;
        }
        
        if(numActuales>numPreguntas){
            this.setArbol(arbol.getRoot().getRight());
            System.out.println("PREGUNTAS AGOTADAS");
            mostrarPosiblesRespuestas();
            return;
        }
        this.setLblPreguntas(Integer.toString(numActuales), Integer.toString(numPreguntas));
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
                if (!posibles.get(i).contains("?")) {
                        System.out.println(posibles.get(i));
                        linea = linea+posibles.get(i);
                    if(i!=(posibles.size()-1)){
                        linea=linea+", ";
                    }else{
                        linea=linea+".";
                    }
                }
            }
            this.cambiarLabel("Se agotaron las preguntas :(, pero las posibles respuestas son: "+linea);
            this.juegoTerminado();
            this.cambiarImage("Character2.png");
        }
                
    }
    
    public void mostrarNoRespuestas(){
        this.cambiarLabel("No pudimos encontrar lo que estas pensando :(");
        juegoTerminado();
        this.cambiarImage("Character5.png");
    }
    
    public void mostrarRespuesta(){
        if(!arbol.getRoot().getContent().contains("?")){
            this.cambiarLabel("Estas pensando en: "+arbol.getRoot().getContent());
            juegoTerminado();
            this.cambiarImage("Character4.png");
        }else{
            mostrarNoRespuestas();
        }
        

    }
    
    public void juegoTerminado(){
        this.btnVolverJugar.setVisible(true);
        this.btnEscogerTema.setVisible(true);
        this.btnVolverInicio.setVisible(true);
        this.btnSi.setVisible(false);
        this.btnNo.setVisible(false);
        this.btnRegresar.setVisible(false);
    }
    
    public void cambiarImage(String rutaImg){
        String ruta = "/Clases/"+rutaImg;
        Image image = new Image(getClass().getResourceAsStream(ruta));
        ivCharacter.setImage(image);
    }
    
    public void imgAleatoria(){
        int numeroAleatorio = (int) (Math.random() * 4);
        this.cambiarImage(rutas.get(numeroAleatorio));
    }
    
    public void setLblPreguntas(String a1, String a2){
        lblTotal.setText("Pregunta "+a1+" de "+ a2);
    }
}
