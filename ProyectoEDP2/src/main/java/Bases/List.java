
package Bases;

import java.io.Serializable;

/**
 * Interfaz para una lista genérica.
 * 
 * @param <E> Tipo de elemento en la lista.
 */
public interface List<E> extends Serializable, Iterable<E> {

    /**
     * Inserta el elemento al inicio de la lista.
     * 
     * @param e elemento a insertar.
     * @return true si se puso bien, false si no fue así.
     */
    public boolean addFirst(E e);

    /**
     * Inserta el elemento al final de la lista.
     * 
     * @param e elemento a insertar.
     * @return true si se puso bien, false si no fue así.
     */
    public boolean addLast(E e);

    /**
     * Remueve y retorna el elemento que estaba al principio de la lista.
     * 
     * @return Elemento removido.
     */
    public E removeFirst();

    /**
     * Remueve y retorna el elemento que estaba al final de la lista.
     * 
     * @return Elemento removido.
     */
    public E removeLast();

    /**
     * Obtiene el tamaño de la lista.
     * 
     * @return el tamaño de la lista.
     */
    public int size();

    /**
     * Verifica si la lista está vacía.
     * 
     * @return true si la lista está vacía, false si noe stá vacía.
     */
    public boolean isEmpty();

    /**
     * Elimina todos los elementos de la lista dejándola vacía.
     */
    public void clear();

    /**
     * Inserta el elemento en la posición index.
     * 
     * @param index   Índice donde se pondrá el elemento.
     * @param element Elemento que se pondrá.
     */
    public void add(int index, E element);

    /**
     * Remueve y retorna el elemento que estaba en la posición index.
     * 
     * @param index Índice del elemento que se va a remover.
     * @return Elemento removido.
     */
    public E remove(int index);

    /**
     * Obtiene el elemento en la posición index.
     * 
     * @param index Índice del elemento a que se quiere tener.
     * @return Elemento en la posición index.
     */
    public E get(int index);

    /**
     * Reemplaza el elemento en la posición index y devuelve dicho objeto.
     * 
     * @param index   Índice del elemento que se va a reemplazar.
     * @param element Elemento que va a reemplazar al anterior.
     * @return Elemento que estaba en la posición index.
     */
    public E set(int index, E element);

    /**
     * Encuentra la intersección (dada por algún comparador o aglo así) entre 2
     * listas.
     * 
     * @param another Otra lista para comparar.
     * @return Lista que contiene la intersección.
     */
    public List<E> findIntersection(List<E> another);

    // public List<E> findIntersection(List<E> another, Comparator<E> cmp);
}
