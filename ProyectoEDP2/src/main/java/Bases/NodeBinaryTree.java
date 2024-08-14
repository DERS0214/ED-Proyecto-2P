package tree;

/**
 *
 * @author adi
 */
public class NodeBinaryTree<E> {
    private E content;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public NodeBinaryTree(E content) {
        this.content = content;
        this.left=null;
        this.right=null;
        
        // this.left = new BinaryTree<>();
        // this.right = new BinaryTree<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }
    
    public String toString(){
        return content.toString();
    }
    
}
