package Clases;

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
                this.preguntas=lineasArrayList;
                
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
                this.respuestas=lineasArrayList;
                

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
    
    @FXML
    private void cargarArbol()  {
        BinaryTree<String> arbol= new BinaryTree<>(new NodeBinaryTree<String>(preguntas.get(0)));
        
        
        Queue<BinaryTree<String>> q = new LinkedList<>();
        q.add(arbol);
        
        int nivel = 0;
        while(!q.isEmpty()){
            int size = q.size();
                for(int i = 0; i<size;i++){
                    BinaryTree<String> t = q.poll();
                    System.out.println("nivel:" +nivel);
                    System.out.println("SIZE: "+preguntas.size());
                    if(nivel<preguntas.size()-1){
                        t.getRoot().setLeft(new BinaryTree<String>(new NodeBinaryTree<String>(preguntas.get(nivel+1))));
                        q.add(t.getRoot().getLeft());
                        
                        t.getRoot().setRight(new BinaryTree<String>(new NodeBinaryTree<String>(preguntas.get(nivel+1))));
                        q.add(t.getRoot().getRight());
                        
                    }
                }
            nivel++;
        }
        arbol.recorrerEnorden();
    }
    
    
}