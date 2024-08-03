package Bases;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Clase que representa una lista doblemente enlazada y que es circular; ideal
 * para carrusel de imágenes.
 *
 * @param <E> el tipo de elementos contenidos en la lista
 */
public class DoublyCircularList<E> implements List<E>, Serializable {
    private DoublyCircularNode<E> last;
    private static final long serialVersionUID = 2004140222041502L;

    /**
     * Devuelve el último nodo de la lista.
     *
     * @return el último nodo de la lista
     */
    public DoublyCircularNode<E> getLast() {
        return last;
    }

    /**
     * Devuelve el nodo cabecera de la lista.
     *
     * @return el nodo cabecera de la lista
     */
    public DoublyCircularNode<E> getHeader() {
        return last.getNext();
    }

    /**
     * Establece el último nodo de la lista.
     *
     * @param last el nuevo último nodo de la lista
     */
    public void setLast(DoublyCircularNode<E> last) {
        this.last = last;
    }

    /**
     * Establece el nodo cabecera de la lista.
     *
     * @param last el nuevo nodo cabecera de la lista
     */
    public void setHeader(DoublyCircularNode<E> last) {
        this.last.setNext(last);
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param e el elemento a agregar
     * @return true si se logró insertar el elemento, false si el objeto es nulo
     */
    @Override
    public boolean addLast(E e) {
        if (e != null) { // No añadimos nulos.
            DoublyCircularNode node = new DoublyCircularNode<>(e);
            if (last == null) { // En caso de que la lista esté vacía, el primer elemento será last y apuntará a
                                // sí mismo.
                last = node;
                last.setNext(last);
                last.setPrevious(last);
                return true;
            } else if (last.getNext() == last && last.getPrevious() == last) { // En caso de que solo haya un elemento.
                last.setNext(node);
                last.setPrevious(node);
                node.setNext(last);
                node.setPrevious(last);
                last = node;
                return true;
            } else if (last.getNext() != last) { // En caso de que la lista esté vacía, el primer elemento será last y
                                                 // apuntará a sí mismo.
                node.setNext(last.getNext());
                node.setPrevious(last);
                last.getNext().setPrevious(node);
                last.setNext(node);
                last = node;
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina el último elemento de la lista.
     *
     * @return el elemento eliminado, o null si la lista está vacía
     */
    @Override
    public E removeLast() {
        if (last.getNext() != null) { // Comprobar si hay más de un elemento.
            DoublyCircularNode<E> n = last;
            E e = last.getContent();
            for (n = last.getNext(); n != last; n = n.getNext()) {
                if (n.getNext() == last) {
                    n.setNext(last.getNext());
                    this.last = n;
                    break;
                }
            }
            return e;
        } else if (last.getNext() == null) { // Si solo tenemos last, hay un elemento nada más, creamos otro caso.
            E e = last.getContent();
            this.last = null;
            return e;
        }
        return null; // Si no se cumple ningún caso, quiere decir que no hay elementos, retornamos
                     // null.
    }

    /**
     * Devuelve un iterador sobre los elementos de la lista en el orden correcto.
     *
     * @return un iterador sobre los elementos de la lista
     */
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            DoublyCircularNode<E> cursor = last.getNext();
            boolean firstPass = true;

            /**
             * Verifica si hay más elementos en la lista.
             *
             * @return true si hay más elementos, false en caso contrario
             */
            @Override
            public boolean hasNext() {
                return cursor != last.getNext() || firstPass;
            }

            /**
             * Devuelve el siguiente elemento en la lista.
             *
             * @return el siguiente elemento en la lista
             */
            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext();
                if (cursor == last.getNext()) {
                    firstPass = false;
                }
                return e;
            }
        };
        return it;
    }

    /**
     * Devuelve la cantidad de elementos en la lista.
     *
     * @return la cantidad de elementos en la lista
     */
    @Override
    public int size() {
        if (last == null) {
            return 0;
        } else {
            DoublyCircularNode<E> cursor = last.getNext();
            int i = 0;
            boolean firstPass = true;

            while (cursor != last.getNext() || firstPass) {
                i++;
                cursor = cursor.getNext();
                if (cursor == last.getNext()) {
                    firstPass = false;
                }
            }
            return i;
        }

    }

    @Override
    public boolean addFirst(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        DoublyCircularNode<E> actual = last.getNext(); // Comenzar desde el primer nodo
        while (actual != last) {
            DoublyCircularNode<E> next = actual.getNext();
            actual.setPrevious(null);
            actual.setNext(null);
            actual = next;
        }
        last.setPrevious(null);
        last.setNext(null);
        last = null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public E removeNode(DoublyCircularNode<E> n) {
        if (n != null) {
            if (this.size() > 2) {
                n.getNext().setPrevious(n.getPrevious());
                n.getPrevious().setNext(n.getNext());
                if (n == last) {
                    last = n.getPrevious();
                }
            } else if (this.size() == 2) {
                n.getNext().setNext(n.getNext());
                n.getPrevious().setPrevious(n.getPrevious());
                last = n.getNext();
            } else if (this.size() == 1) {
                last = null;
            }
            System.out.println("DCCL: ELIMINO NODO");
            return n.getContent();
        } else {
            System.out.println("DCCL: NO ELIMINO NODO");
            return null;
        }
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findIntersection(List<E> another) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIndex(DoublyCircularNode<E> n) {
        int indice = 0;
        for (DoublyCircularNode<E> cursor = last.getNext(); cursor != n; cursor = cursor.getNext()) {
            indice++;
        }
        return indice;
    }

    public DoublyCircularNode<E> getNodo(int index) {
        if (!(index < 0 || index >= size())) {
            DoublyCircularNode<E> currentNode = last.getNext(); // Comenzar desde el nodo cabecera
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext(); // Avanzar al siguiente nodo
            }
            return currentNode;
        } else {
            return null;
        }

    }

    public boolean nodeExists(E e) {
        DoublyCircularNode<E> currentNode = last.getNext();
        for (int i = 0; i < size(); i++) {
            System.out.println(e);
            System.out.println(currentNode.getContent());
            if (currentNode == e) {
                return true;
            }
            currentNode = currentNode.getNext(); // Avanzar al siguiente nodo
        }
        return false;
    }

    public String toString() {
        String s = "";
        for (DoublyCircularNode<E> n = last.getNext();; n = n.getNext()) {
            s += n.getContent() + ", ";
            if (n == last) {
                break;
            }
        }
        return s;
    }
}
