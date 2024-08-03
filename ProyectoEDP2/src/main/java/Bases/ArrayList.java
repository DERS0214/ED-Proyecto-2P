package Bases;

import java.util.Iterator;

/**
 * Clase que representa una lista indexada.
 *
 * @param <E> el tipo de elementos contenidos en la lista
 */
public class ArrayList<E> implements List<E> {

    private E[] elements = null; // arreglo de elementos genericos
    private int capacity = 100;
    private int effectiveSize;
    private static final long serialVersionUID = 2004140222041502L;

    /**
     * Constructor crea una lista de genéricos con una capacidad maxima de 100
     */
    public ArrayList() {
        elements = (E[]) (new Object[capacity]);
        effectiveSize = 0;
    }

    /**
     * Verifica si la lista está llena
     * 
     * @return true si la lista está llena, false si no está llena
     */
    private boolean isFull() {
        return effectiveSize == capacity;
    }

    /**
     * Agrega un elemento al principio de la lista.
     *
     * @param e el elemento genérico que se va a agregar
     * @return true si se logró insertar el elemento, false si el objeto es nulo
     */
    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > 0; i--) {
            elements[i] = elements[i - 1];
        }
        elements[0] = e;
        effectiveSize++;
        return true;
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param e el elemento genérico a agregar
     * @return true si se logró insertar el elemento, false si el objeto es nulo
     */
    @Override
    public boolean addLast(E e) {
        if (e == null) {
            System.out.println("No agregado");
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize] = e;
        effectiveSize++;
        return true;
    }

    /**
     * Devuelve la longitud usada de la lista
     * 
     * @return effectiveSize, que es la longitud usada de la lista
     */
    @Override
    public int size() {
        return effectiveSize;
    }

    /**
     * Verifica si la lista está vacía
     * 
     * @return true si la lista está vacía, false si no está vacía
     */
    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    /**
     * Agrega un elemento en una posición dentro de la lista.
     *
     * @param index   la posición en la que se agregará el elemento
     * @param element el elemento genérico que se va a agregar en la lista
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la
     *                                   lista
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > effectiveSize) {
            throw new IndexOutOfBoundsException("No se pudo agregar el objeto en la posición: " + index);
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        effectiveSize++;
    }

    /**
     * Duplica la capacidad del arreglo de elementos.
     * Este método se llama cuando la lista está llena y necesita más espacio.
     */
    private void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }

    /**
     * Devuelve una String que contiene concatenados los elementos de la lista.
     *
     * @return una cadena que representa los elementos de la lista
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < effectiveSize; i++) {
            s += elements[i] + ", ";
        }
        return s;
    }

    /**
     * Devuelve un iterador sobre los elementos de la lista en el orden correcto.
     *
     * @return un iterador sobre los elementos de la lista
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;

            /**
             * Verifica si hay más elementos en la lista.
             *
             * @return true si hay más elementos, false si la lista ya no tiene más
             *         elementos
             */
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            /**
             * Devuelve el siguiente elemento en la lista.
             *
             * @return el siguiente elemento en la lista
             */
            @Override
            public E next() {
                E e = elements[cursor];
                cursor++;
                return e;
            }
        };
        return it;
    }

    /**
     * Devuelve el elemento en la posición especificada en la lista.
     *
     * @param index la posición del elemento que se quiere obtener
     * @return el elemento en la posición index
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la
     *                                   lista
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > effectiveSize) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        return elements[index - 1];
    }

    /**
     * Reemplaza el elemento en la posición especificada en la lista con el nuevo
     * elemento
     *
     * @param index   la posición del elemento que se va a reemplazar
     * @param element el nuevo elemento que va a reemplazar al anterior en su misma
     *                posición
     * @return el elemento que estaba antes de que se isnerte el nuevo
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la
     *                                   lista
     */
    @Override
    public E set(int index, E element) {
        if (index < 1 || index > effectiveSize) {
            throw new IndexOutOfBoundsException("No se pudo establecer el objeto en la posición: " + index);
        }
        E previousElement = elements[index - 1];
        elements[index - 1] = element;
        return previousElement;
    }

    /**
     * Elimina el elemento en la posición especificada en la lista.
     *
     * @param index la posición del elemento que se va a eliminar
     * @return el elemento eliminado
     * @throws IndexOutOfBoundsException si el índice está fuera del rango o la
     *                                   lista está vacía
     */
    @Override
    public E remove(int index) {
        if (index < 1 || index > effectiveSize || isEmpty()) {
            throw new IndexOutOfBoundsException("No se pudo establecer el objeto en la posición: " + index);
        }
        E removedElement = elements[index - 1];
        for (int i = index - 1; i < effectiveSize; i++) {
            elements[i] = elements[i + 1];
        }
        elements[effectiveSize - 1] = null;
        effectiveSize--;
        return removedElement;
    }

    public boolean removeObject(E e) {
        int ind = -1;
        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(e)) {
                ind = i;
            }
        }
        if (ind < 0 || ind >= effectiveSize || isEmpty()) {
            throw new IndexOutOfBoundsException("No se pudo encontrar el objeto");
        }
        for (int i = ind; i < effectiveSize; i++) {
            elements[i] = elements[i + 1];
        }
        elements[effectiveSize - 1] = null;
        effectiveSize--;
        return true;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<E> findIntersection(List<E> otherList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}