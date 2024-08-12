package Bases;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * Profesora: Adriana Collaguazo Jaramillo
 * Estudiante: Katherine Forero Villota
 */
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
    
    // EJERCICIO 1
    
    public int countDescendantsRecursive(){
        if(!this.isEmpty() && !this.isLeaf()){ // Verificamos si el árbol no está vacío o no es una hoja, ya que de ser el caso, no tendría descendientes.
            int descendants = 0; // Inicializamos la variable en 0, que nos servirá como un contador de descendientes.            
            if(this.root.getLeft()!=null){
                descendants += 1 + this.root.getLeft().countDescendantsRecursive(); // Aquí empezamos la llamada recursiva, por cada que detecte un nodo (izquierdo o derecho), aumentará en 1 hasta contar todos los nodos (menos la raíz).
            }
            if(this.root.getRight()!=null){
                descendants += 1 + this.root.getRight().countDescendantsRecursive();
            }
            return descendants; // Retornamos el número de descendientes.
        } else{
            return 0; // Retornamos 0 al estar vacío el árbol o ser una hoja (nodo único).
        }
    }
    
    public int countDescendantsIterative(){
        int descendants = 0; // Creamos una variable para contar los descendientes y la inicializamos con 0.
        Stack<BinaryTree<E>> s = new Stack(); // Creamos un stack para ir almacenando el árbol y los respectivos subárboles.
        if(!this.isEmpty() && !this.isLeaf()){ // Verificamos si el árbol no está vacío o no es una hoja, ya que de ser el caso, no tendría descendientes.
            s.push(this); // Si el árbol actual no está vacío, lo insertamos.
            while(!s.isEmpty()){ // Mientras sigamos añadiendo subárboles, seguimos corriendo nuestro algoritmo.
                BinaryTree<E> tree = s.pop(); // Sacamos el árbol del stack.
                descendants +=1; // Aumentamos en 1 descendientes, y esto será así hasta que acabemos con todos los subárboles.
                if(tree.getRoot().getLeft()!=null){ // Si son distintos de null, añadimos los subárboles, así hasta acabar con todo el árbol.
                    s.push(tree.getRoot().getLeft());
                }                
                if(tree.getRoot().getRight()!=null){
                    s.push(tree.getRoot().getRight());
                }
            }
            return descendants-1; // Retornamos los descendientes menos 1, ya que si nos fijamos el algoritmo antes realizado también cuenta la raíz. Por lo tanto, retornamos el número menos 1.
        } else{
            return 0; // Retornamos 0 al estar vacío el árbol o ser una hoja (nodo único).
        }        
    }
    
    // EJERCICIO 2
    
    public E findParentRecursive(NodeBinaryTree<E> n){
        if(this.isEmpty() || this.isLeaf()){ // Verificamos si el árbol no está vacío o no es una hoja, ya que de ser así, no pueden existir hijos que puedan encontrar un padre.
            return null;
        } else{            
            if(this.getRoot().getLeft()!=null){ // Verificamos si el subárbol izquierdo no es nulo para proceder.
                if(this.getRoot().getLeft().getRoot().getContent().equals(n.getContent())){ //Comparamos el hijo izquierdo del nodo actual con el nodo puesto en el parámetro.                   
                    return this.getRoot().getContent(); // Si son iguales, significa que el nodo actual es su padre, y retornamos el nodo actual.
                } else{                
                    E left = this.getRoot().getLeft().findParentRecursive(n); // Caso contrario, creamos una variable que almacenará al padre del nodo en caso de existir y invocamos al método de forma recursiva que nos devolverá este elemento E.
                    if(left!=null){
                        return left; // Si nos da un resultado, verificamos que este no sea nulo y retornamos el padre.
                    }
                }
            }
            if(this.getRoot().getRight()!=null){ // Verificamos si el subárbol derecho no es nulo para proceder.
                if(this.getRoot().getRight().getRoot().getContent().equals(n.getContent())){ //Comparamos el hijo derecho del nodo actual con el nodo puesto en el parámetro.                  
                    return this.getRoot().getContent(); // Si son iguales, significa que el nodo actual es su padre, y retornamos el nodo actual.
                } else{                
                    E right = this.getRoot().getRight().findParentRecursive(n); // Caso contrario, creamos una variable que almacenará al padre del nodo en caso de existir y invocamos al método de forma recursiva que nos devolverá este elemento E.
                    if(right!=null){
                        return right; // Si nos da un resultado, verificamos que este no sea nulo y retornamos el padre.
                    }
                }
            }
            return null; // Si no retornamos nada en todo el algoritmo, significa que no se encontró un padre, así que retornamos null.
        }        
    }
    
    public E findParentIterative(NodeBinaryTree<E> n){        
        Stack<BinaryTree<E>> s = new Stack(); // Creamos un stack para ir almacenando el árbol y los respectivos subárboles.
        if(!this.isEmpty() || !this.isLeaf()){ // Verificamos si el árbol no está vacío o no es una hoja, ya que de ser así, no pueden existir hijos que puedan encontrar un padre.
            s.push(this); // Si el árbol actual no está vacío, lo insertamos.
            while(!s.isEmpty()){ // Mientras sigamos añadiendo subárboles, seguimos corriendo nuestro algoritmo.
                BinaryTree<E> tree = s.pop(); // Sacamos el árbol del stack.
                if(tree!=null){ // Verificamos si el árbol que sacamos del stack no es nulo (Sinceramente nunca debería ser nulo, pero me botó un error de eso así que lo puse por si acaso jajaj).
                    if(tree.getRoot().getLeft()!=null){ // Si son el subárbol izquierdo es distinto de null, añadimos el subárbol, así hasta acabar con todo el árbol.
                        if(tree.getRoot().getLeft().getRoot().getContent().equals(n.getContent())){ //Comparamos el hijo izquierdo del nodo actual con el nodo puesto en el parámetro.                       
                            return tree.getRoot().getContent(); // Si son iguales, significa que el nodo actual es su padre, y retornamos el nodo actual.
                        } else{                
                            s.push(tree.getRoot().getLeft()); // Caso contrario, añadimos el subárbol a la pila para seguir analizando sus hijos.       
                        }
                    }
                }
                if(tree!=null){
                    if(tree.getRoot().getRight()!=null){ // Si son el subárbol derecho es distinto de null, añadimos el subárbol, así hasta acabar con todo el árbol.
                        if(tree.getRoot().getRight().getRoot().getContent().equals(n.getContent())){ //Comparamos el hijo izquierdo del nodo actual con el nodo puesto en el parámetro.                        
                            return tree.getRoot().getContent(); // Si son iguales, significa que el nodo actual es su padre, y retornamos el nodo actual.
                        } else{                
                            s.push(tree.getRoot().getRight()); // Caso contrario, añadimos el subárbol a la pila para seguir analizando sus hijos.                        
                        }
                    }            
                }
            }
        
            return null; // Si no retornamos nada en todo el algoritmo, significa que no se encontró un padre, así que retornamos null.            
        } else{
            return null; // El árbol está vacío o es una hoja, por lo tanto no puede existir un padre al no existir hijos.
        }    
    }    
    
    // EJERCICIO 3
    
    public int countLevelsRecursive(){        
        if(!this.isEmpty()){
            int leftLvl = 0; // Inicializamos variables para contar los niveles de ambos subárboles (izquierdo y derecho)
            int rightLvl = 0;

            if (this.getRoot().getLeft() != null) {
                leftLvl = this.getRoot().getLeft().countLevelsRecursive(); // Llamamos a la función recursivamente para el subárbol izquierdo.
            }

            if (this.getRoot().getRight() != null) {
                rightLvl = this.getRoot().getRight().countLevelsRecursive(); // Llamamos a la función recursivamente para el subárbol derecho.
            }
            
            return 1 + Math.max(leftLvl, rightLvl); // Retornamos 1 + el nivel mayor entre el subárbol izquierdo y derecho, ya que al casi finalizar el algoritmo obtendremos los valores de los niveles de cada subárbol, por lo que retornamos el mayor más el nodo actual (raíz). Así funciona para cada subárbol igualmente.
            
        } else{
            return 0; // Si el árbol está vacío decimos que no tiene niveles.
        }
    }
    
    public int countLevelsIterative(){
        int maxLvl = 0; // Creamos una variable que almacenará el valor de los niveles.
        Queue<BinaryTree<E>> q = new ArrayDeque(); // Creamos una cola para almacenar los subárboles y el árbol actual.
        if(!this.isEmpty()){ // Si el árbol actual no está vacío, lo añadimos a la cola.
            q.offer(this);
            while(!q.isEmpty()){ // Mientras la cola no esté vacía corremos el algoritmo respectivo.
                maxLvl++; // Añadimos al contador de niveles 1, ya que empezamos por el árbol en general, desde el primer nodo (raíz) que ya cuenta como un nivel.
                int tamañoNivel = q.size(); // Obtenemos el size de la cola.
                for(int i = 0; i <tamañoNivel; i++){ // Utilizamos el size de la cola para añadir los subárboles (hijos) que corresponden al siguiente nivel hasta terminar con los de este nivel, y así sabemos que ya completamos un nivel.
                    BinaryTree<E> tree = q.poll();
                    if(tree.getRoot().getLeft()!= null) {
                        q.offer(tree.getRoot().getLeft());                    
                    }

                    if(tree.getRoot().getRight() != null) {
                        q.offer(tree.getRoot().getRight());                   
                    }
                }
                
                /* Un ejemplo más explicativo, sería que por ejemplo, al almacenar el primer árbol (todo el árbol), la cola tendría tamaño 1, y al entrar al for, 
                   eliminamos el árbol de la cola, lo almacenamos en una variable y almacenamos el subárbol izquierdo y derecho de dicho árbol. El for finaliza pues el
                   size era de 1. Ahora que se agregaron el subárbol izquierdo y derecho, el tamaño sería dos, y ejecuta el mismo algoritmo anteriormente descrito. 
                   El for termina cuando ya se recorrieron los dos árboles añadidos, y la cola nueva sería con los hijos de ambos árboles, conformando el siguiente
                   nivel. El for correrá hasta que se termine de añadir todos los hijos de los árboles que hay en la cola, que conformarán el siguiente nivel. Por ello,
                   cuando acabe el for y se le sume 1 al maxLvl, estará sumando los niveles del árbol.
                */
            }            
            return maxLvl; // Retornamos el número de niveles.
        } else{
            return 0; // Si el árbol está vacío, no tiene niveles. Retornamos 0.
        }
    }
    
    // EJERCICIO 4
    
    // Creamos un método contar nodos para usarlo en el método que vamos a realizar a continuación. Hecho con propósito de ahorro de código. El método retorna el número de nodos que tiene un árbol.
    public int contarNodos(){
        int leftCount=0; 
        int rightCount=0;
        if(!this.isEmpty()){
            if(this.root.getLeft()!=null){
                leftCount = this.root.getLeft().contarNodos();
            }
            if(this.root.getRight()!=null){
                rightCount = this.root.getRight().contarNodos();
            }
            return 1 + leftCount + rightCount;
        } else{
            return 0;
        }        
    }
    
    public boolean isLeftyRecursive(){       
        if(this.isEmpty() || this.isLeaf()){ // Si un árbol está vacío o es una hoja, podemos considerar que es zurdo, retornamos true.
            return true;
        } else{ // Caso contrario, analizamos el árbol sumando los nodos de cada subárbol, y si sus descendientes están más en la izquierda, decimos que es surdo. Sus subárboles deben ser zurdos también.                      
            boolean leftLefty; // Creamos un boolean que nos dirá si el subárbol izquierdo es zurdo o no.
            if (this.getRoot().getLeft() != null) {
                leftLefty = this.getRoot().getLeft().isLeftyRecursive(); // Si el subárbol izquierdo no es nulo, ejecutamos el método recursivamente y lo almacenamos en la variable boolean creada.
            } else {
                leftLefty = true; // Si es nulo, ponemos la variable en true.
            }
            
            boolean rightLefty; // Creamos un boolean que nos dirá si el subárbol derecho es zurdo o no.            
            if (this.getRoot().getRight() != null) {
                rightLefty = this.getRoot().getRight().isLeftyRecursive(); // Si el subárbol derecho no es nulo, ejecutamos el método recursivamente y lo almacenamos en la variable boolean creada.
            } else {
                rightLefty = true; // Si es nulo, ponemos la variable en true.
            }           
            
            int leftCount = 0; // Creamos una variable para contar los descendientes del lado izquierdo.
            int rightCount = 0; // Creamos una variable para contar los descendientes del lado derecho.
            if(this.getRoot().getLeft()!=null){ // Si los subárboles no son nulos, almacenamos el número de nodos que hay en cada uno.
                leftCount = this.getRoot().getLeft().contarNodos();
            }
            if(this.getRoot().getRight()!=null){
                rightCount = this.getRoot().getRight().contarNodos();
            }                
            
            boolean isLefty = leftCount > rightCount; // Creamos una variable que comprueba si un árbol es zurdo, que retornará true si la cantidad de hijos del árbol está mayormente en la izquierda que en la derecha.            
            return leftLefty && rightLefty && isLefty; // Retornamos las 3 variables, así se hará recursivamente, y basta con que una sea falsa para que retorne false y podemos decir que el árbol no es zurdo.
                        
        }        
    }
    
    public boolean isLeftyIterative(){       
        Stack<BinaryTree<E>> sLeft = new Stack(); // Creamos dos stack, uno para almacenar el subárbol izquierdo y sus hijos, y para el subárbol derecho y sus hijos, y así analizarlos a ambos.
        Stack<BinaryTree<E>> sRight = new Stack();
        if(this.isEmpty() || this.isLeaf()){ // Si un árbol está vacío o es una hoja, podemos considerar que es zurdo, retornamos true.
            return true;
        } else{
            boolean leftLefty = true; // Creamos las variables que dirán si el subárbol izquierdo, derecho y en general es zurdo. 
            boolean rightLefty = true;
            boolean isLefty;
            if(this.getRoot().getLeft()!=null){ // Hacemos un algoritmo con while como lo hemos hecho anteriormente, siguiendo la misma dinámica.
                sLeft.push(this.getRoot().getLeft());
                while(!sLeft.isEmpty()){
                    BinaryTree<E> tree = sLeft.pop();     
                    int leftCount = 0; // Hacemos variables para contar los descendientes del lado izquierdo y derecho.
                    int rightCount = 0;
                    
                    if(tree.getRoot().getLeft() != null) { // Vamos almacenando los hijos del subárbol para analizarlos así mismo después con el mismo algoritmo.
                        leftCount = tree.getRoot().getLeft().contarNodos(); // Contamos los nodos del árbol izquierdo para almacenarlos en el contador izquierdo.
                        sLeft.push(tree.getRoot().getLeft());
                    }
                              
                    if(tree.getRoot().getRight() != null) {
                        rightCount = tree.getRoot().getRight().contarNodos(); // Contamos los nodos del árbol derecho para almacenarlos en el contador derecho.
                        sLeft.push(tree.getRoot().getRight());
                    }        
                    
                    if(!(tree.getRoot().getLeft() == null || tree.getRoot().getRight() == null)){ // Si uno de los subárboles no son nulos, comparamos la cuenta de hijos. Si ambos son nulos, recordando que inicializamos ambos contadores como 0, nos dará false innecesariamente y dará un resultado equivocado.
                        leftLefty = leftLefty && leftCount>rightCount; // La variable de si el subárbol izquierdo la comparamos con el valor inicializado. Basta con que uno salga false, para que la variable siempre sea false.
                    }                     
                }
            }
            if(this.getRoot().getRight()!=null){ // La misma lógica aplicada arriba pero para el SUBÁRBOL DERECHO.
                sRight.push(this.getRoot().getRight());
                while(!sRight.isEmpty()){
                    BinaryTree<E> tree = sRight.pop();     
                    int leftCount = 0;
                    int rightCount = 0;
                    
                    if(tree.getRoot().getLeft() != null) {
                        leftCount = tree.getRoot().getLeft().contarNodos();
                        sRight.push(tree.getRoot().getLeft());
                    }
                              
                    if(tree.getRoot().getRight() != null) {
                        rightCount = tree.getRoot().getRight().contarNodos();
                        sRight.push(tree.getRoot().getRight());
                    }         
                    if(!(tree.getRoot().getLeft() == null && tree.getRoot().getRight() == null)){
                        rightLefty = rightLefty && leftCount>rightCount;
                    } 
                }
            }
            
            isLefty = this.getRoot().getLeft().contarNodos() > this.getRoot().getRight().contarNodos(); // Le designamos un valor a isLefty que comprueba si el árbol en general es zurdo.            
            
            return leftLefty && rightLefty && isLefty; // Retornamos las tres variables, si el subárbol derecho, o izquierdo, o en general no es zurdo, la misma nos retornará false. Caso contrario, retornará true.
        }    
    }
    
    // EJERCICIO 5
    
    public boolean isIdenticalRecursive(BinaryTree<E> tree){
          
        if(this.isEmpty() && tree.isEmpty()){ // Si ambos árboles están vacío, entonces son iguales. Retornamos true.
            return true;
        } else if(this.isEmpty() || tree.isEmpty()){ // Si uno está vacío y otro no, no son iguales. Retornamos false.
            return false;
        } else if(this == null && tree == null){ // Si ambos son nulos, son iguales. Retornamos true.
            return true;
        }        
       
        boolean rootIdentical = this.getRoot().getContent().equals(tree.getRoot().getContent()); // Comprobamos si las raices son iguales en ambos árboles.
        boolean leftIdentical;
        if (this.getRoot().getLeft() != null && tree.getRoot().getLeft() != null) { // Si los subárboles izquierdos en ambos árboles no son nulos, inicializamos la variable que nos dirá si el subárbol izquierdo es igual al derecho llamando al método recursivamente.
            leftIdentical = this.getRoot().getLeft().isIdenticalRecursive(tree.getRoot().getLeft()); // Así cuando entren van a comparar ambas raíces con el primer boolean, y así sucesivamente.
        } else {
            leftIdentical = this.getRoot().getLeft() == null && tree.getRoot().getLeft() == null; // Si ambos no son diferente de nulo, verificamos si ambos son nulos para saber si son iguales, o retornamos false en caso de que uno sea nulo y otro no. Ya con eso sabemos que el árbol es diferente.
        }
        
        boolean rightIdentical; // La misma lógica usada para el subárbol izquierdo pero esta vez para el derecho.
        if (this.getRoot().getRight() != null && tree.getRoot().getRight() != null) {
            rightIdentical = this.getRoot().getRight().isIdenticalRecursive(tree.getRoot().getRight());
        } else {
            rightIdentical = this.getRoot().getRight() == null && tree.getRoot().getRight() == null;
        }
        
        return rootIdentical && leftIdentical && rightIdentical; // Retornamos los tres booleanos, basta que uno no sea igual para que todo nos de false y podemos saber si son idénticos o no.
    }
    
    public boolean isIdenticalIterative(BinaryTree<E> tree){ 
        if(this.isEmpty() && tree.isEmpty()){ // Si ambos árboles están vacío, entonces son iguales. Retornamos true.
            return true;
        } else if(this.isEmpty() || tree.isEmpty()){ // Si uno está vacío y otro no, no son iguales. Retornamos false.
            return false;
        } else if(this == null && tree == null){ // Si ambos son nulos, son iguales. Retornamos true.
            return true;
        }    
        
        Stack<BinaryTree<E>> s1 = new Stack(); // Creamos un stack para el árbol que invoca el método y para el que va a ser comparado.
        Stack<BinaryTree<E>> s2 = new Stack();
        
        s1.push(this); // Ingresamos ambos árboles a los stacks.
        s2.push(tree);        

        while(!s1.isEmpty() && !s2.isEmpty()){ // Usamos el mismo ciclo como hemos usado anteriormente, pero esta vez para ambos stacks.
            BinaryTree<E> treeOriginal = s1.pop(); // Sacamos ambos árboles y los almacenamos en sus respectivas variables.
            BinaryTree<E> treeComparar = s2.pop();        
            
            if (!treeOriginal.getRoot().getContent().equals(treeComparar.getRoot().getContent())) { // Hacemos la comparación, si no son iguales retornamos falso.
                return false;
            }
            
            if (treeOriginal.getRoot().getLeft() != null && treeComparar.getRoot().getLeft() != null) { // Si ambos subárboles izquierdos son diferentes de nulo, almacenamos los subárboles en los respectivos stacks.
                s1.push(treeOriginal.getRoot().getLeft()); // Esto con el propósito de comparar con la misma condición de arriba todos los subárboles hasta llegar al final.
                s2.push(treeComparar.getRoot().getLeft());
            } else if (treeOriginal.getRoot().getLeft() != null || treeComparar.getRoot().getLeft() != null) { // Si uno de los dos es nulo y el otro no, no son iguales, retornamos false.
                return false;
            }

            if (treeOriginal.getRoot().getRight() != null && treeComparar.getRoot().getRight() != null) { // Si ambos subárboles derechos son diferentes de nulo, almacenamos los subárboles en los respectivos stacks.
                s1.push(treeOriginal.getRoot().getRight()); // Esto con el propósito de comparar con la misma condición de arriba todos los subárboles hasta llegar al final.
                s2.push(treeComparar.getRoot().getRight());
            } else if (treeOriginal.getRoot().getRight() != null || treeComparar.getRoot().getRight() != null) { // Si uno de los dos es nulo y el otro no, no son iguales, retornamos false.
                return false;
            }             
        }
        return s1.isEmpty() && s2.isEmpty(); // Si salimos del while, comprobamos que ambos stacks están vacíos. Si lo están y no retornamos false antes con las condiciones, sabemos que son iguales, y retornamos true. En caso de que en un stack haya elementos y otro no, no son iguales. Retornamos false.      
    } 
    
    
    public void largestValueOfEachLevelRecursive(){        
        ArrayList<Integer> lValues = new ArrayList(); // ArrayList que almacenará los valores mayores.
        largestValueOfEachLevelRecursive(0, lValues); // Iniciamos el método abajo creado desde el nivel 0, enviando la lista también para el almacenamiento respectivo de valores.
        System.out.println(lValues); // Imprimimos la lista para su visualización.
    }
    
    public void largestValueOfEachLevelRecursive(int level, ArrayList<Integer> l){ // Este método será invocado iniciado en el nivel 0, y con una lista que almacenará los valores más grandes de cada nivel.        
        if(!this.isEmpty()){ // Si el árbol que invoca el método no está vacío, procedemos.
           if(level==0){ // Si estamos en el "nivel 0", o sea el primer nivel, sabemos que solo tenemos a la raíz, y la añadimos a la lista que tenemos como parámetro.
               l.addLast((Integer) this.getRoot().getContent()); // Hacemos un casting asegurando que es un Integer. Recordando que este método solo está hecho para árboles de tipo Integer, especificado en las instrucciones.
           } else{ // Si no estamos en el nivel uno, hacemos la comparación.
               if(l.size()-1>=level){ // Comprobamos que el size menos 1 de la lista es mayor o igual al nivel actual, ya que si no existe aún un número del nivel 1 por ejemplo (ya que recién empezamos a recorrer el nivel), la lista solo tendrá al valor de la raíz y no podremos setear nada. 
                   if(l.get(level)<(Integer)this.getRoot().getContent()){ // En caso de ya haber agregado un valor para ese nivel, y ese valor es menor al valor actual correspondiente al mismo nivel, seteamos el valor nuevo en la posición del nivel (recordando que el index es igual al nivel. Es decir, en el index 0 se almacenará el valor más alto del primer nivel, en el 1 el del segundo nivel, etc.)
                       l.set(level, (Integer) this.getRoot().getContent());
                   }
               } else{
                   l.add(level, (Integer) this.getRoot().getContent()); // Si aún no existe un valor en la posición del nivel, significa que recién empezamos a recorrer el nivel y agregamos el valor actual para que posteriormente sea reemplazado en caso de que haya un valor mayor al valor actual introducido del mismo nivel.
               }
           }
           if(this.getRoot().getLeft()!=null){ // Si los subárboles no son nulos, llamamos a la función recursiva añadiendole 1 al nivel, así asegurandonos que todos se mantendrán en el mismo nivel, usando la misma lista para almacenar los valores.
            this.getRoot().getLeft().largestValueOfEachLevelRecursive(level + 1, l);
           }
           if(this.getRoot().getRight()!=null){
            this.getRoot().getRight().largestValueOfEachLevelRecursive(level + 1, l); 
           }
            
        } else{
            System.out.println("El arbol esta vacio!!"); // Si está vacío el árbol, no hay nada que imprimir.
        }        
    }
    
    public void largestValueOfEachLevelIterative(){
        Queue<BinaryTree<E>> q = new ArrayDeque(); // Creamos una cola para almacenar el árbol actual y sus subárboles.
        ArrayList<Integer> lValues = new ArrayList(); // Creamos el ArrayList de enteros para almacenar los valores.
        if(!this.isEmpty()){ // Si el árbol actual no está vacío, lo añadimos a la cola.
            q.offer(this);
            while(!q.isEmpty()){ // Usamos el mismo ciclo que hemos usado para analizar el árbol y cada subárbol que se vaya añadiendo.               
                int size = q.size(); // Obtenemos el size de la cola.
                Integer lValue = 0; // Crearemos una variable para almacenar los valores de un mismo nivel, que podría ser reemplazado por posteriores valores de ser mayores, dejando así como valor final el valor más grande del respectivo nivel.
                for(int i = 0; i<size; i++){ // Este for como implementamos arriba, (y se explicó con más detalles), nos permitirá recorrer los elementos de la cola manteniéndonos en un unico nivel.
                    BinaryTree<E> tree = q.poll(); // Sacamos el árbol y lo almacenamos en una variable.
                    if(i==0){ // Si estamos en el primer ciclo, inicializamos la variable con el valor, que posteriormente será comparado y será reemplazado en caso de que sea mayor que el valor ya existente (perteneciente al mismo nivel, corroborandolo con el for).
                        lValue = (Integer)tree.getRoot().getContent(); // Hacemos casting necesario para añadir el elemento a la lista, que será de tipo Integer.
                    } else if(lValue<(Integer)tree.getRoot().getContent()){ // Si no estamos en el primer ciclo, empezamos la comparación. Si es menor el valor de lValue, reemplazamos su valor por el valor actual.
                        lValue = (Integer)tree.getRoot().getContent(); // Hacemos casting necesario para añadir el elemento a la lista, que será de tipo Integer.
                    }
                    if(tree.getRoot().getLeft()!=null){ // Si ambos subárboles son diferente de null, los agregamos a la cola.
                        q.offer(tree.getRoot().getLeft());
                    }                    
                    if(tree.getRoot().getRight()!=null){
                        q.offer(tree.getRoot().getRight());
                    }
                }
                lValues.addLast(lValue); // Finalmente, agregamos el valor a la lista, una vez ya pasado por todas las condicionales que nos permitieron saber si el valor es el mayor de todos.
            }
            System.out.println(lValues); // Imprimimos la lista para su visualización.
        } else if(this.isLeaf()){
            System.out.println(this.getRoot().getContent()); // Si es una hoja, simplemente imprimimos el nodo raíz (único valor existente).
        } else{
            System.out.println("El arbol esta vacio!!"); // Si está vacío el árbol, no hay nada que imprimir.
        }
    }
    
    public int countNodesWithOnlyChildRecursive(){
        if(!this.isEmpty() || !this.isLeaf()){ // Verificamos que el árbol no esté vacío ni sea una hoja para proceder.
            int onlyChild = 0; // Iniciamos contador para contar los hijos únicos.
            if(this.getRoot().getLeft()!=null && this.getRoot().getRight()==null || this.getRoot().getLeft()==null && this.getRoot().getRight()!=null){
                onlyChild+=1; // Hacemos la condición para validar la cantidad de hijos únicos. Si el hijo izquierdo es nulo y el derecho no, o viceversa, sabemos que es hijo único al no tener hermano. Agregamos 1 al contador.
            }
            if(this.getRoot().getLeft()!=null){ // Si ambos subárboles no son nulos, llamamos recursivamente a la función en los respectivod árboles. 
                onlyChild += this.getRoot().getLeft().countNodesWithOnlyChildRecursive();
            }
            if(this.getRoot().getRight()!=null){
                onlyChild += this.getRoot().getRight().countNodesWithOnlyChildRecursive();
            }
            
            return onlyChild; // Retornará el valor del hijo único que será sumado hasta acabar la recursión, dándonos la cantidad de hijos únicos.
        } else{
            return 0; // Si el árbol está vacío o es una hoja, no existen hijos. Retornamos 0.
        }
    }
    
    public int countNodesWithOnlyChildIterative(){
        Stack<BinaryTree<E>> s = new Stack(); // Creamos un stack para almacenar el árbol y sus subárboles.        
        if(!this.isEmpty() || !this.isLeaf()){ // Verificamos que el árbol no esté vacío ni sea una hoja para proceder.
            int onlyChild = 0; // Iniciamos contador para contar los hijos únicos.
            s.push(this); // Añadimos el árbol al stack.
            while(!s.isEmpty()){ // Usamos el mismo ciclo que hemos usado para analizar el árbol y cada subárbol que se vaya añadiendo.
                BinaryTree<E> tree = s.pop(); // Sacamos el árbol y lo almacenamos en una variable.
                if(tree.getRoot().getLeft()!=null && tree.getRoot().getRight()==null || tree.getRoot().getLeft()==null && tree.getRoot().getRight()!=null){ 
                    onlyChild+=1; // Hacemos la condición para validar la cantidad de hijos únicos. Si el hijo izquierdo es nulo y el derecho no, o viceversa, sabemos que es hijo único al no tener hermano. Agregamos 1 al contador.
                }
                if(tree.getRoot().getLeft()!=null){ // Añadimos los respectivos subárboles si son diferente de null para su respectivo análisis, así hasta llegar al final del árbol y finalizar el ciclo while.
                    s.push(tree.getRoot().getLeft());
                }
                if(tree.getRoot().getRight()!=null){
                    s.push(tree.getRoot().getRight());
                }
            }
            return onlyChild; // Retornamos el número de hijos únicos.
        } else{
            return 0; // Si el árbol está vacío o es una hoja, no existen hijos. Retornamos 0.
        }
    }
    
    public boolean isHeightBalancedRecursive(){
        if(!this.isEmpty() && !this.isLeaf()){ // Si el árbol no está vacío y no es una hoja, procedemos.  
            boolean leftBalanced; // Hacemos un boolean para comprobar si el subárbol izquierdo está balanceado.
            if (this.getRoot().getLeft() != null) { // Si el árbol es diferente de nulo, llamamos a la función recursivamente que nos dará el respectivo valor boolean.
                leftBalanced = this.getRoot().getLeft().isHeightBalancedRecursive();
            } else {
                leftBalanced = true; // Si es nulo, lo seteamos en true.
            }
            
            boolean rightBalanced; // Hacemos un boolean para comprobar si el subárbol derecho está balanceado.             
            if (this.getRoot().getRight() != null) { // Si el árbol es diferente de nulo, llamamos a la función recursivamente que nos dará el respectivo valor boolean.
                rightBalanced = this.getRoot().getRight().isHeightBalancedRecursive();
            } else {
                rightBalanced = true; // Si es nulo, lo seteamos en true.
            }           
            
            int leftHeight = 0; // Creamos variables para almacenar la altura del subárbol izquierdo y del subárbol derecho.
            int rightHeight = 0;
            if(this.getRoot().getLeft()!=null){
                leftHeight = this.getRoot().getLeft().countLevelsRecursive(); // Usamos la función creada en la misma tarea de contar niveles, ya que técnicamente el número de niveles es igual a la altura.
            }
            if(this.getRoot().getRight()!=null){
                rightHeight = this.getRoot().getRight().countLevelsRecursive();
            }                
            
            boolean isHBalanced = (leftHeight - rightHeight<=1 && leftHeight - rightHeight>=0)|| (rightHeight - leftHeight<=1 && rightHeight - leftHeight>=0); // Hacemos la validación, si la resta de ambas alturas da un valor de 0 o 1, está balanceado. Si es mayor, retorno false.            
            return leftBalanced && rightBalanced && isHBalanced; // Retornamos todos los booleanos. Basta que uno no cumpla para que todo sea false.
        } else{
            return true; // Si el árbol está vacío  o es una hoja, decimos que está balanceado. Retornamos true.
        }
    }
    
    public boolean isHeightBalancedIterative(){
        Stack<BinaryTree<E>> sLeft = new Stack(); // Creamos dos stack, uno para almacenar el subárbol izquierdo y sus hijos, y para el subárbol derecho y sus hijos, y así analizarlos a ambos.
        Stack<BinaryTree<E>> sRight = new Stack();
        if(this.isEmpty() || this.isLeaf()){ // Si un árbol está vacío o es una hoja, podemos considerar que está balanceado, retornamos true.
            return true;
        } else{
            boolean leftBalanced = true; // Creamos las variables que dirán si el subárbol izquierdo, derecho y en general está balanceado. 
            boolean rightBalanced = true;
            boolean isHBalanced;
            if(this.getRoot().getLeft()!=null){ // Para subárbol izquierdo.
                sLeft.push(this.getRoot().getLeft());
                while(!sLeft.isEmpty()){ // Hacemos un algoritmo con while como lo hemos hecho anteriormente, siguiendo la misma dinámica.
                    BinaryTree<E> tree = sLeft.pop();     
                    int leftHeight = 0; // Hacemos variables para contar las alturas del lado izquierdo y derecho.
                    int rightHeight = 0;
                    
                    if(tree.getRoot().getLeft() != null) { // Vamos almacenando los hijos del subárbol para analizarlos así mismo después con el mismo algoritmo.
                        leftHeight = tree.getRoot().getLeft().countLevelsIterative(); // Sacamos la altura del árbol izquierdo para almacenarla en el contador izquierdo.
                        sLeft.push(tree.getRoot().getLeft());
                    }
                              
                    if(tree.getRoot().getRight() != null) {
                        rightHeight = tree.getRoot().getRight().countLevelsIterative(); // Sacamos la altura del árbol derecho para almacenarla en el contador derecho.
                        sLeft.push(tree.getRoot().getRight());
                    }      
                    
                    leftBalanced = leftBalanced && ((leftHeight - rightHeight<=1 && leftHeight - rightHeight>=0)|| (rightHeight - leftHeight<=1 && rightHeight - leftHeight>=0)); // Hacemos la validación, si la resta de ambas alturas da un valor de 0 o 1, está balanceado. Si es mayor, retorno false. 
                    
                }
            }
            if(this.getRoot().getRight()!=null){ // La misma lógica aplicada arriba pero para el SUBÁRBOL DERECHO.
                sRight.push(this.getRoot().getRight());
                while(!sRight.isEmpty()){
                    BinaryTree<E> tree = sRight.pop();     
                    int leftHeight = 0;
                    int rightHeight = 0;
                    
                    if(tree.getRoot().getLeft() != null) {
                        leftHeight = tree.getRoot().getLeft().countLevelsIterative();
                        sRight.push(tree.getRoot().getLeft());
                    }
                              
                    if(tree.getRoot().getRight() != null) {
                        rightHeight = tree.getRoot().getRight().countLevelsIterative();
                        sRight.push(tree.getRoot().getRight());
                    }         
                    
                    rightBalanced = rightBalanced && ((leftHeight - rightHeight<=1 && leftHeight - rightHeight>=0)|| (rightHeight - leftHeight<=1 && rightHeight - leftHeight>=0));                                      
                }
            }
            int lHeight = this.getRoot().getLeft().countLevelsIterative(); // Alturas de los subárboles izquierdo y derecho almacenadas en variables.
            int rHeight = this.getRoot().getRight().countLevelsIterative();
            isHBalanced = (lHeight - rHeight<=1 && lHeight - rHeight>=0)|| (rHeight - lHeight<=1 && rHeight - lHeight>=0); // Le designamos un valor al boolean isHBalanced haciendo la respectiva validación para el árbol en general.                
            
            return leftBalanced && rightBalanced && isHBalanced; // Retornamos las tres variables, si el subárbol derecho, o izquierdo, o en general no está balanceado, la misma nos retornará false. Caso contrario, retornará true.
        }
    }
        
}
