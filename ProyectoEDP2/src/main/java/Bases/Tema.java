package Bases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.fxml.FXML;

public class Tema implements Serializable{
    private String nombre;
    private ArrayList<String> preguntas;
    private ArrayList<String> respuestas;
    
    public Tema(String n,ArrayList<String> p,ArrayList<String> r){
        this.nombre=n;
        this.preguntas=p;
        this.respuestas=r;
    }
    
    @FXML
    private BinaryTree<String> cargarArbolPreguntas(ArrayList<String> preguntas)  {
        BinaryTree<String> arbol= new BinaryTree<>(new NodeBinaryTree<String>(preguntas.get(0)));


        Queue<BinaryTree<String>> q = new LinkedList<>();
        q.add(arbol);

        int nivel = 0;
        while(!q.isEmpty()){
            int size = q.size();
                for(int i = 0; i<size;i++){
                    BinaryTree<String> t = q.poll();
                    if(nivel<preguntas.size()-1){
                        t.getRoot().setLeft(new BinaryTree<String>(new NodeBinaryTree<String>(preguntas.get(nivel+1))));
                        q.add(t.getRoot().getLeft());

                        t.getRoot().setRight(new BinaryTree<String>(new NodeBinaryTree<String>(preguntas.get(nivel+1))));
                        q.add(t.getRoot().getRight());

                    }
                }
            nivel++;
        }
        return arbol;
    }
    
    @FXML
    private void cargarArbolRespuestas(ArrayList<String> respuestas,BinaryTree<String> arbol)  {
                
        for(String linea:respuestas){
            
            String[] espacios = linea.split(" ");
            String animal = espacios[0];
            int cantidad=espacios.length;
            BinaryTree<String> p = arbol;
            
            for(int i=1;i<cantidad-1;i++){
                if(espacios[i].equalsIgnoreCase("SI")){
                    
                    p=p.getRoot().getLeft();
                    
                }else{
                    p=p.getRoot().getRight();
                }
            }
            
            if(espacios[cantidad-1].equalsIgnoreCase("SI")){
                if(p.getRoot().getLeft()!=null){
                    String ant = p.getRoot().getLeft().getRoot().getContent();
                    p.getRoot().setLeft(new BinaryTree<String>(new NodeBinaryTree<String>(ant+", "+animal)));
                }else{
                    p.getRoot().setLeft(new BinaryTree<String>(new NodeBinaryTree<String>(animal)));
                }
            }else{
                if(p.getRoot().getRight()!=null){
                    String ant = p.getRoot().getRight().getRoot().getContent();
                    p.getRoot().setRight(new BinaryTree<String>(new NodeBinaryTree<String>(ant+", "+animal)));
                }else{
                    p.getRoot().setRight(new BinaryTree<String>(new NodeBinaryTree<String>(animal)));
                }
                   
            }
            
        }
        arbol.recorrerEnorden();
    }
    
   public BinaryTree<String> crearArbol(){
        BinaryTree<String> arbol= this.cargarArbolPreguntas(this.preguntas);
        this.cargarArbolRespuestas(respuestas, arbol);
        return arbol;
   } 
   
    public String toString(){
       return "El tema: "+this.nombre+" tiene "+preguntas.size()+" preguntas y "+respuestas.size()+" respuestas";
       
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<String> preguntas) {
        this.preguntas = preguntas;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }
   
    public int cantPreguntas(){
        return this.preguntas.size();
    }
    
    public int cantRespuestas(){
        return this.respuestas.size();
    }
}
