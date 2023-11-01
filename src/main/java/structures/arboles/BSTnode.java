package structures.arboles;

public class BSTnode<T extends Comparable<T>> {
    private T data;
    private BSTnode<T> parent;
    private BSTnode<T> left;
    private BSTnode<T> right;

    public BSTnode(T data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public BSTnode(T data, BSTnode<T> parent) {
        this.data = data;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BSTnode<T> getParent() {
        return parent;
    }

    public void setParent(BSTnode<T> parent) {
        this.parent = parent;
    }

    public BSTnode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTnode<T> left) {
        this.left = left;
    }

    public BSTnode<T> getRight() {
        return right;
    }

    public void setRight(BSTnode<T> right) {
        this.right = right;
    }
}
