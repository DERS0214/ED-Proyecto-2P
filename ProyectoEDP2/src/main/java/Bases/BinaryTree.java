package Bases;

import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree<E> {
    private NodeBinaryTree<E> root;

    public BinaryTree() {
        this.root=null;
    }
    
    public BinaryTree(NodeBinaryTree<E> root) {
        this.root=root;
    }
    
    public void recorrerPreorden(){
        
        // 1. Imprimir a la raiz
        // 2. recorrer preorden en hijo izquierdo
        // 3. recorrer preorden en hijo derecho

        if (!this.isEmpty()) {
            
            // 1. Imprimir a la raiz
            System.out.println(this.root.getContent());
            
            // 2. recorrer preorden en hijo izquierdo
            if (root.getLeft()!=null) {
                root.getLeft().recorrerPreorden(); 
            }
        
            // 3. recorrer preorden en hijo derecho
            if (root.getRight()!=null) {
                root.getRight().recorrerPreorden(); 
            }
        }
    }
    
    public void recorrerPostorden(){
        
        if (!this.isEmpty()){
            
            // 1. recorrer postorden en hijo izquierdo
            if (root.getLeft()!=null){
                root.getLeft().recorrerPostorden(); 
            }
        
            // 2. recorrer postorden en hijo derecho
            if (root.getRight()!=null){
                root.getRight().recorrerPostorden(); 
            }
            
            // 3. Imprimir a la raiz
            System.out.println(this.root.getContent());
        }
    }
    
    public void recorrerEnorden(){
        
        if (!this.isEmpty()){
            
            // 1. recorrer enorden en hijo izquierdo
            if (root.getLeft()!=null){
                root.getLeft().recorrerEnorden(); 
            }
        
            // 2. Imprimir a la raiz
            System.out.println(this.root.getContent());
            
            // 3. recorrer enorden en hijo derecho
            if (root.getRight()!=null){
                root.getRight().recorrerEnorden(); 
            }
            
        }
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean isLeaf(){
        // root no esta vacio
        if (!this.isEmpty()){
            return root.getLeft() == null && root.getRight() == null;
            // return root.getLeft().isEmpty() && root.getRight().isEmpty();
        }
        return false;
    }

    public NodeBinaryTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeBinaryTree<E> root) {
        this.root = root;
    }
    
    public int contarNodos(){
        if(!this.isEmpty()){
            int i =0;
            if(root.getLeft()!=null){
                i=i+root.getLeft().contarNodos();
            }
            if(root.getRight()!=null){
                i=i+root.getRight().contarNodos();
            }
            i=i+1;
            return i;
        } else{
            return 0;
        }    
    }
    
    public int countLeavesRecursive(){
        if(this.isEmpty()){
            return 0;
        }else if(this.isLeaf()){
            return 1;
        }else{
            int leftLeaves =0;
            int rightLeaves=0;
            
            if (this.root.getLeft()!=null){
                leftLeaves=this.root.getLeft().countLeavesRecursive();
            }
            
            
            if (this.root.getRight()!=null){
                rightLeaves=this.root.getRight().countLeavesRecursive();
            }
            
        
        return leftLeaves+rightLeaves;
        }
    }
    
    public int countLeavesIterative(){
        int leave=0;
        
        Stack<BinaryTree<E>> s = new Stack();
        
        if(!this.isEmpty()){
            s.push(this);
        }
        
        while (!s.isEmpty()){
            BinaryTree<E> tree=s.pop();
            
            if(tree.isLeaf()){
                leave++;
            }
            
            if(tree.getRoot().getLeft() != null){
                s.push(tree.getRoot().getLeft());
            }
            
            if(tree.getRoot().getRight() != null){
                s.push(tree.getRoot().getRight());
            }
        }
        return leave;
    }
    
    public int countDescendantsIterative(){
        //stacks para nodos
        Stack<BinaryTree<E>> s = new Stack();
        int descendientes =0;
        if(this.isEmpty() || this.isLeaf()){
            return 0;            
        }
        //condiciones por si no existe o es una hoja

        s.add(this);
        //añade el raiz al Stack
        while(!s.isEmpty()){
            //obtenemos el ultimo elemento
            BinaryTree<E> tree=s.pop();
            
            if(tree.getRoot().getLeft() != null){
                //añadimos al stack el nodo izquierdo y aumetamos los descendientes 
                s.push(tree.getRoot().getLeft());
                descendientes++;
            }
            
            if(tree.getRoot().getRight() != null){
                //añadimos al stack el nodo derecho y aumetamos los descendientes 
                s.push(tree.getRoot().getRight());
                descendientes++;
            }
        }
        
        //retorna descendientes
        return descendientes;
    }
    
    public int countDescendantsRecursive(){
        //Este metodo cuenta todos los nodos que hay exceptuando el nodo raiz, si es una 
        //si es una hoja o esta vacio retorna 0
        int descendientes =0;
        if(this.isEmpty() || this.isLeaf()){
            return 0;     
            //condicion si es que no hay hijos
        }else{
            if(this.getRoot().getLeft() != null){
                //aumento el contador y pasa el metodo al siguiente nodo izq
                descendientes++;
                descendientes+=this.getRoot().getLeft().countDescendantsRecursive();
            }
            
            if(this.getRoot().getRight() != null){
                //aumento el contador y pasa el metodo al siguiente nodo der
                descendientes++;
                descendientes+=this.getRoot().getRight().countDescendantsRecursive();
            }
        }
        //retorna el contador
        return descendientes;
    }
    
    
    public NodeBinaryTree<E> findParentIterative(NodeBinaryTree<E> tree){
        Stack<NodeBinaryTree<E>> s = new Stack();
        if(this.isEmpty() || this.isLeaf()){
            return null;            
        }
        //retorno null si el arbol es una hoja o esta vacio
        s.add(this.root);
        //añade al stack el NODO de este ARBOL
        
        while(!s.isEmpty()){
            NodeBinaryTree<E> t = s.pop();
            //obtenemos el ultimo nodo
            if(t.getLeft() != null){
                //si el nodo izquierdo es igual al parametro quiere decir que el nodo actual es el padre
                //por lo que retornamos el nodo actual
                if(t.getLeft().getRoot().equals(tree)){
                    return t;
                }else{
                    //caso contrario ponemos el nodo izquierdo en el Stack
                    s.push(t.getLeft().getRoot());
                }
            }
            
            if(t.getRight() != null){
                //si el nodo derecho es igual al parametro quiere decir que el nodo actual es el padre
                //por lo que retornamos el nodo actual
                if(t.getRight().getRoot().equals(tree)){
                    return t;
                }else{
                    //caso contrario ponemos el nodo derecho en el Stack
                    s.push(t.getRight().getRoot());
                }
            }
        }
        //si al terminar la pila no se encontraron coincidencias no existe el padre en este arbol
        
        return null;
        
    }
    
    public NodeBinaryTree<E> findParentRecursive(NodeBinaryTree<E> tree){
        
        if(this.isEmpty() || this.isLeaf()){
            return null;          
        }
        //si es una hoja retornara null porque quiere decir que los nodos que evaluo no son el padre que necesita encontrar
        
        if (root.getLeft()!=null){
            //si existe el nodo izquierdo 
            if(root.getLeft().root.equals(tree)){
                //evalua que el nodo izquierdo sea igual al parametro
                return this.getRoot();
            }else{
                NodeBinaryTree<E> nodo = root.getLeft().findParentRecursive(tree);
                //si el nodo no es igual al parametro se crea un nodo el cual buscara si el nodo izquierdo es el nodo que necesitamos hasta llegar a una hoja
                //si llego a encontrarlo el nodo sera diferente de null por lo que lo retornaremos, si no encuentra sera null
                if(nodo!=null){
                    return nodo;
                }
            }
        }
        
        if (root.getRight()!=null){
            //si existe el nodo derecho
            if(root.getRight().getRoot().equals(tree)){
                //evalua que el nodo derecho sea igual al parametro
                return this.getRoot();
            }else{
                NodeBinaryTree<E> nodo = root.getRight().findParentRecursive(tree);
                 //si el nodo no es igual al parametro se crea un nodo el cual buscara si el nodo derecho es el nodo que necesitamos hasta llegar a una hoja
                //si llego a encontrarlo el nodo sera diferente de null por lo que lo retornaremos, si no encuentra sera null
                if(nodo!=null){
                    return nodo;
                }
            }
        }
        
        return null;
        
    }
    
    public int countLevelsIterative(){
        
        if(this.isEmpty()){
            return 0;
        }
        //si esta vacio retornamos 0
        if(this.isLeaf()){
            return 1;
        }
        //si es una hoja retornamos 1
        
        int nivel = 0;
        //contador de nivel
        Queue<BinaryTree<E>> q = new LinkedList<>();
        //creamos la cola y agregamos el nodo raiz
        q.add(this);
        
        while(!q.isEmpty()){
            int size = q.size();
            //creamos la variable de size que servira para iterar todos los nodos del mismo nivel
                for(int i = 0; i<size;i++){
                    //quitamos el ultimo nodo
                    BinaryTree<E> t = q.poll();
                    //añadimos nodos izq y der en caso de existir
                    if(t.getRoot().getLeft()!=null){
                        q.add(t.getRoot().getLeft());
                    }

                    if(t.getRoot().getRight()!=null){
                        q.add(t.getRoot().getRight());
                    }
                }
            //cuando termine la iteracion de nivel aumenta el contador de nivel
            nivel++;
        }
        return nivel;
        /*
        dando un ejemplo en el nodo raiz añadiremos 2 nodos de existir, en el  
        la 2da iteracion pasara por los 2 nodos y los iterara a la vez sin aumentar el nivel
        hasta que termine los 2 y asi contaremos todos los nodos sin repetir niveles!
        */
        
    }
    
    public int countLevelsRecursive(){
        int nivelIqz = 0;
        int nivelDer = 0;
        
        if(this.isEmpty()){
            return 0;
        }//retorna 0 si esta vacio
        
        if(this.isLeaf()){
            return 1;
        }//retorna 1 si es una hoja
        
        if(root.getLeft()!=null){
            nivelIqz++;
            nivelIqz = nivelIqz+root.getLeft().countLevelsRecursive();
        }//aumenta el contador izq
        
        if(root.getRight()!=null){
            nivelDer++;
            nivelDer = nivelDer+root.getRight().countLevelsRecursive();
        }//aumenta el contador der
            
        return max(nivelIqz,nivelDer);
        //retorna el max entre los 2 contadores
        /*
        Hara la llamada recursiva hasta que pueda determinar una hoja, cuando lo haga
        retornara 1, y se iran resolviendo las llamadas recursivas hasta obtener 
        el nivel del arbol
        */
        
    }
    
    public boolean isLeftyIterative (){
        if(this.isEmpty()){
            return true;
        }
        //si es vacio retorna true
        if(this.isLeaf()){
            return true;
        }
        //si es hoja retorna true
        Queue<BinaryTree<E>> q = new LinkedList<>();
        //creamos la cola y agregamos el nodo
        q.add(this);
        
        while(!q.isEmpty()){
        //hacemos un while mientras la cola no este vacia
            BinaryTree<E> t = q.poll();
            if(t.getRoot().getLeft()!=null){
                q.add(t.getRoot().getLeft());
                //Agregamos el nodo izq si existe
            }

            if(t.getRoot().getRight()!=null){
                q.add(t.getRoot().getRight());
                //Agregamos el nodo der si existe
            }
            
            if(!t.isEmpty() && !t.isLeaf()){
                //Verificaremos si no cumple la condicion, para esto tiene que ser diferente de una hoja
                int left =0;
                if(t.getRoot().getLeft()!=null){
                    left=t.getRoot().getLeft().contarNodos();
                }
                int right =0;
                if(t.getRoot().getRight()!=null){
                    right=t.getRoot().getRight().contarNodos();
                }
                //contamos los nodos de ambos nodos hijos en caso de existir
                boolean hijos=left>(left+right)/2;//creamos la condicion
                    if(!hijos){
                        //si no cumple la condicion retorna false, caso contrario
                        // seguira iterando
                        return false;
                    }
                }
                    
                }
        return true;
    }
    
    public boolean isLeftyRecursive (){
        if(this.isEmpty()){
            return true;
        }//retorna true si esta vacio
        
        if(this.isLeaf()){
            return true;
        }//retorna true si es una hoja
        
        boolean lefty=true;
        if(this.root.getLeft()!=null){
            lefty=this.root.getLeft().isLeftyRecursive();
        }
        //verifica si el nodo izq es Lefty en caso de existir
        boolean righty=true;
        if(this.root.getRight()!=null){
            righty=this.root.getRight().isLeftyRecursive();
        }
        //verifica si el nodo der es Lefty en caso de existir
        int left =0;
        if(this.root.getLeft()!=null){
            left=this.root.getLeft().contarNodos();
        }
        //cuenta los nodos del nodo der
        int right =0;
        if(this.root.getRight()!=null){
            right=this.root.getRight().contarNodos();
        }
        //cuenta los nodos del nodo izq
        
        boolean hijos=left>(left+right)/2;//creamos la condicion
        
         
        
        if( lefty && righty && hijos){
            //si todo se cumple se retorna true
            return true;
        }else{
            //si no cumple retorna false
            return false;
        }
        
    }
    
    public boolean isIdenticalIterative(BinaryTree<E> tree){
        Stack<BinaryTree<E>> s1 = new Stack();
        Stack<BinaryTree<E>> s2 = new Stack();
        if(this.isEmpty() && tree.isEmpty()){
            return true;
        }
        //si ambos son vacios retorna true
        
        if(this.isLeaf()&& tree.isLeaf()){
            return true;
        }
        //si ambos son hojas retorna true
        
        if(this.isEmpty() || tree.isEmpty()){
            return false;
        }
        //si uno esta vacio y otro no retorna false
        
        if(this.isLeaf() || tree.isLeaf()){
            return false;
        }
        //si uno es una hoja y otro no retorna false
        
        s1.add(this);
        s2.add(tree);
        //añado al s1 este nodo y al s2 el arbol a comparar
        
        while(!s1.isEmpty() && !s2.isEmpty()){
            BinaryTree<E> n1 = s1.pop();
            BinaryTree<E> n2 = s2.pop();
            //obtenemos los primeros nodos
            
            if (!n1.getRoot().getContent().equals(n2.getRoot().getContent())) {
                //se hizo con el contenido porque no detectaba que fuera igual, solo cuando era la misma instancia.
            return false;
            }
            
            if (n1.getRoot().getLeft() != null && n2.getRoot().getLeft() != null) {
                //si ambos son diferentes de null agregamos a ambos stacks los nodos 
                s1.add(n1.getRoot().getLeft());
                s2.add(n2.getRoot().getLeft());
            } else if (n1.getRoot().getLeft() != null || n2.getRoot().getLeft() != null) {
                //si uno solo de los 2 es nulo retorna false porque el arbol no es igual
                return false;
            }
            
            if (n1.getRoot().getRight() != null && n2.getRoot().getRight() != null) {
                //si ambos son diferentes de null agregamos a ambos stacks los nodos 
                s1.add(n1.getRoot().getRight());
                s2.add(n2.getRoot().getRight());
            } else if (n1.getRoot().getRight() != null || n2.getRoot().getRight() != null) {
                //si uno solo de los 2 es nulo retorna false porque el arbol no es igual
            return false;
            }
        }
        //retornamos si es que ambos Stacks estan vacios, quiere decir que tienen los mismos nodos y tienen mismo contenido
        return s1.isEmpty() && s2.isEmpty();
    }
    
    public boolean isIdenticalRecursive(BinaryTree<E> tree){
        
        if(this.isEmpty() && tree.isEmpty()){
            return true;
        }//si ambos son vacios retorna true
        
        if(this.isEmpty() || tree.isEmpty()){
            return false;
        }//si uno esta vacio y otro no retorna false
        
        if (!this.getRoot().getContent().equals(tree.getRoot().getContent())) {
            return false;
        }//si el contenido no es igual retorna false
        
        if(this.isLeaf()&& tree.isLeaf()){
            return true;
        }//si ambos son hojas retorna true
        if(this.isLeaf() || tree.isLeaf()){
            return false;
        }//si uno es una hoja y otro no retorna false
        
        boolean left=false;
        boolean right=false;
        //boleanos
        if (this.getRoot().getLeft() != null && tree.getRoot().getLeft() != null) {
            //si en ambos los nodos izquierdos son diferentes de null verificamos que sean identicos con el mismo metodo
                left = this.getRoot().getLeft().isIdenticalRecursive(tree.getRoot().getLeft());
            } else if (this.getRoot().getLeft() != null || tree.getRoot().getLeft() != null) {
                //si uno es nulo y otro no la condicion sera falsa!
                left = false;
            }
        
        if (this.getRoot().getRight() != null && tree.getRoot().getRight() != null) {
            //si en ambos los nodos derechos son diferentes de null verificamos que sean identicos con el mismo metodo
                right = this.getRoot().getRight().isIdenticalRecursive(tree.getRoot().getRight());
            } else if (this.getRoot().getRight() != null || tree.getRoot().getRight() != null) {
                //si uno es nulo y otro no la condicion sera falsa!
                right = false;
            }
                //retornamos que ambos booleanos
        return  left && right;
    }
    
    public void largestValueOfEachLevelIterative(){
                
        if(this.isEmpty()){
            System.out.println("Arbol vacio!");
        }//si es vacio imprimimos que el arbol esta vacio
                       
        Queue<BinaryTree<E>> q = new LinkedList<>();
        //stack para los binary tree
        q.add(this);
        while(!q.isEmpty()){
            LinkedList<Integer> elementos=new LinkedList<>();
            //creamos un linkedlis para los elementos del mismo nivel
            int size = q.size();
                for(int i = 0; i<size;i++){
                    //creamos el for para iterar todos los nodos del mismo nivel
                    BinaryTree<E> t = q.poll();
                    elementos.add((Integer)t.getRoot().getContent());
                    //REALIZAMOS EL CASTING PORQUE EL EJERCICIO DICE ESPECIFICAMENTE QUE:
                    //un árbol binario cuyos nodos contienen números enteros
                    //Por lo que este metodo esta hecho para arboles que SOLO tienen nodos de enteros
                    if(t.getRoot().getLeft()!=null){
                        q.add(t.getRoot().getLeft());
                        //agregamos el nodo izquierdo
                    }

                    if(t.getRoot().getRight()!=null){
                        q.add(t.getRoot().getRight());
                        //agregamos el nodo derecho
                    }
                }
            System.out.print(Collections.max(elementos)+" ");
            //imprimimos por cada nivel el maximo de la coleccion
        }
    }
    
    public void largestValueOfEachLevelRecursive(){
        //pregunte a la miss y dijo que de ser necesario podemos usar otro metodo, la verdad no encontraba otra forma de hacerlo
        TreeMap<Integer,Integer> m=  new TreeMap<>();
        //las claves son los niveles entonces como es un TreeMap las claves se mostraran en orden y al imprimirlo tambien estara en orden
        largestValueOfEachLevelRecursive(0,this,m);
        System.out.println(m.values());
    }
    
    public void largestValueOfEachLevelRecursive(int level,BinaryTree<E> t,TreeMap<Integer,Integer> m){
        
        if(m.containsKey(level)){
            if((Integer)t.getRoot().getContent()>m.get(level)){
                m.put(level, (Integer)t.getRoot().getContent());
            }
            //si la clave existe compara que el que vayamos a insertar sea mayor, si no, no lo inserta
        }else{
            m.put(level, (Integer)t.getRoot().getContent());
            //si no existe la clave la creamos en el diccionario
        }
        
        if(t.getRoot().getLeft()!=null){
            largestValueOfEachLevelRecursive(level+1,t.getRoot().getLeft(),m);
            //aumentamoos el nivel y enviamos el nodo izquierdo
        }

        if(t.getRoot().getRight()!=null){
            largestValueOfEachLevelRecursive(level+1,t.getRoot().getRight(),m);
            //aumentamoos el nivel y enviamos el nodo derecho
        }
    }
    
    public int countNodesWithOnlyChildIterative(){
         if(this.isEmpty()){
            return 0;
        }
         // si esta vacio retornamos 0
        int nodos = 0;
        
        Queue<BinaryTree<E>> q = new LinkedList<>();
        //creamos el stack y añadimos este nodo
        q.add(this);
        while(!q.isEmpty()){
            BinaryTree<E> t = q.poll();
            if(t.getRoot().getLeft()!=null){
                q.add(t.getRoot().getLeft());
            }

            if(t.getRoot().getRight()!=null){
                q.add(t.getRoot().getRight());
            }
            //añadimos ambos nodos al stack
            
            if(t.getRoot().getLeft()!=null && t.getRoot().getRight()==null){
                nodos++;
            }else if(t.getRoot().getLeft()==null && t.getRoot().getRight()!=null){
                nodos++;
            }
            //si solo tiene un hijo aumenta el contador
                
        }
        return nodos;
    }
    
    public int countNodesWithOnlyChildRecursive(){
         if(this.isEmpty()){
            return 0;
        }
         
        int nodos = 0;
        
        if(this.getRoot().getLeft()!=null && this.getRoot().getRight()==null){
        nodos++;
        }else if(this.getRoot().getLeft()==null && this.getRoot().getRight()!=null){
        nodos++;
        }
        //si solo tiene un hijo aumenta el contador
        
        if(this.getRoot().getLeft()!=null){
            nodos=nodos+this.getRoot().getLeft().countNodesWithOnlyChildRecursive();
        }
        if(this.getRoot().getRight()!=null){
            nodos=nodos+this.getRoot().getRight().countNodesWithOnlyChildRecursive();
        }
        //invocamos el metodo en ambos nodos en caso de existir
        return nodos;
    }
    
    public boolean isHeightBalancedIterative(){
        if(this.isEmpty()){
            return true;
        }
        
        if(this.isLeaf()){
            return true;
        }
        //retorna true si es una hoja o esta vacio
        
        Queue<BinaryTree<E>> q = new LinkedList<>();
        q.add(this);
        //añadimos el roor y creamos enteros para comprobaciones
        int izq=0;
        int der=0;
        int residuo=0;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size;i++){
                
                BinaryTree<E> t = q.poll();
                izq=0;
                der=0;
                //iteramos por nivel y comprobamos existan los nodos hijos
                if(t.getRoot().getLeft()!=null){
                    q.add(t.getRoot().getLeft());
                    izq=t.getRoot().getLeft().countLevelsIterative();
                }
                if(t.getRoot().getRight()!=null){
                    q.add(t.getRoot().getRight());
                    der=t.getRoot().getRight().countLevelsIterative();
                }
                //si existe el nodo contamos los niveles
                residuo=izq-der;
                //despues restamos el izquierdo con derecha
                //si esta entre 1 y -1 esta balanceado
                if(residuo>1 || residuo <-1){
                    return false;
                }
            }
        }
        
        return true;
        
        
    }
    public boolean isHeightBalancedRecursive(){
        if(this.isEmpty()){
            return true;
        }

        if(this.isLeaf()){
            return true;
        }
        //si esta vacio o es una hoja retorna true
        
        boolean lefty=true;
        if(getRoot().getLeft()!=null){
            lefty=getRoot().getLeft().isHeightBalancedRecursive();
        }
        //invocamos el metodo si existe un nodo izquierdo
        boolean righty=true;
        if(getRoot().getRight()!=null){
            righty=getRoot().getRight().isHeightBalancedRecursive();
        }
        //invocamos el metodo si existe un nodo derecho
        int left =0;
        if(getRoot().getLeft()!=null){
            left=getRoot().getLeft().countLevelsRecursive();
        }
        //contamos los niveles de existir el nodo izquierdo
        int right =0;
        if(getRoot().getRight()!=null){
            right=getRoot().getRight().countLevelsRecursive();
        }
        //contamos los niveles de existir nodo derecho
        int residuo=left-right;
        //calculamos el residuo, si esta entre -1 y 1 esta balanceado
        boolean res=residuo<=1 && residuo >=-1;
        
        if( lefty && righty && res){
            //si se cumple todo retorna true
            return true;
        }else{
            return false;
        }

    }
    
    public ArrayList<E> obtenerHojas(){
        ArrayList<E> lista = new ArrayList<>();
        
        Stack<BinaryTree<E>> s = new Stack();
        
        if(!this.isEmpty()){
            s.push(this);
        }
        
        while (!s.isEmpty()){
            BinaryTree<E> tree=s.pop();
            
            if(tree.isLeaf()){
                lista.add(tree.getRoot().getContent());
            }
            
            if(tree.getRoot().getLeft() != null){
                s.push(tree.getRoot().getLeft());
            }
            
            if(tree.getRoot().getRight() != null){
                s.push(tree.getRoot().getRight());
            }
        }
        return lista;
    }
}
