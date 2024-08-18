package Clases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Bases.*;


public class PartidaController implements Initializable {
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;
    private int numPreguntas;
    private int numActuales;
    private BinaryTree<String> arbol;
    @FXML
    private Button lblPregunta;
    @FXML
    private Button btnNo1;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargar();
        this.lblPregunta.setText(arbol.getRoot().getContent());
        this.numActuales = 1;
    }    
    
    @FXML
    private void switchToInicio() throws IOException{
        App.setRoot("inicio");
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
        if(arbol==null){
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
        if(arbol==null){
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
            System.out.println("PREGUNTAS AGOTADAS");
            mostrarPosiblesRespuestas();
            return;
        }
        
        this.setArbol(arbol.getRoot().getRight());
        this.cambiarPregunta();
    }
    
    public void mostrarPosiblesRespuestas(){
        
    }
    
    public void mostrarNoRespuestas(){
        this.cambiarLabel("No pudimos encontrar el animal en el que piensas :(");
    }
    
    public void mostrarRespuesta(){
        this.cambiarLabel("El animal en el que estas pensando es: "+arbol.getRoot().getContent());
    }
}
