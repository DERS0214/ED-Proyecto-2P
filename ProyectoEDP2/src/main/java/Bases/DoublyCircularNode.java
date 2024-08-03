package Bases;

import java.io.Serializable;

/**
 * Clase que representa un nodo de DoublyCircularList
 * 
 * @param <E> el tipo de elemento contenido en el nodo
 */
public class DoublyCircularNode<E> implements Serializable {
    private E content;
    private DoublyCircularNode<E> next;
    private DoublyCircularNode<E> previous;
    private static final long serialVersionUID = 2004140222041502L;

    /**
     * Constructor que crea un nodo con el contenido especificado.
     * El siguiente y anterior nodo se apuntan a sí mismos.
     * 
     * @param content el contenido del nodo
     */
    public DoublyCircularNode(E content) {
        this.content = content;
        this.next = this; // Instancia de un nodo el next ya no apunta a null sino a si mismo
        this.previous = this;
    }

    /**
     * Devuelve el contenido del nodo.
     * 
     * @return el contenido del nodo
     */
    public E getContent() {
        return content;
    }

    /**
     * Establece el contenido del nodo.
     * 
     * @param content el contenido del nodo
     */
    public void setContent(E content) {
        this.content = content;
    }

    /**
     * Devuelve el siguiente nodo.
     * 
     * @return el siguiente nodo
     */
    public DoublyCircularNode<E> getNext() {
        return next;
    }

    /**
     * Establece el siguiente nodo.
     * 
     * @param next el nuevo siguiente nodo
     */
    public void setNext(DoublyCircularNode<E> next) {
        this.next = next;
    }

    /**
     * Devuelve el nodo anterior.
     * 
     * @return el nodo anterior
     */
    public DoublyCircularNode<E> getPrevious() {
        return previous;
    }

    /**
     * Establece el nodo anterior.
     * 
     * @param previous el siguiente nodo
     */
    public void setPrevious(DoublyCircularNode<E> next) {
        this.previous = next;
    }
}