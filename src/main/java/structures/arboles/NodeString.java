package structures.arboles;
import entities.Chaza;

public class NodeString {
    Chaza data;
    BSTnode pointerToScore;
    NodeString parent;
    NodeString left;
    NodeString right;

    public NodeString(Chaza data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public NodeString(Chaza data, NodeString parent, BSTnode node ) {
        this.data = data;
        this.parent = parent;
        this.pointerToScore=node;
        this.left = null;
        this.right = null;
    }

    public Chaza getData() {
        return data;
    }

    public void setData(Chaza data) {
        this.data = data;
    }

    public NodeString getParent() {
        return parent;
    }

    public void setParent(NodeString parent) {
        this.parent = parent;
    }

    public NodeString getLeft() {
        return left;
    }

    public void setLeft(NodeString left) {
        this.left = left;
    }

    public NodeString getRight() {
        return right;
    }

    public void setRight(NodeString right) {
        this.right = right;
    }

    public BSTnode getPointer(){
        return this.pointerToScore;
    }

    public void setPointer(BSTnode pointer){
        this.pointerToScore=pointer;
    }

    public void insertar(Chaza valor, BSTnode pointer){
        if (valor.getName().compareTo(this.data.getName())<0){
            if (this.left==null){
                NodeString NodoM = new NodeString(valor,this, pointer);
                this.left = NodoM;
            }
            else{
                this.left.insertar(valor, pointer);
            }
        }
        else{
            if (this.right==null){
                NodeString NodoM = new NodeString(valor,this, pointer);
                this.right = NodoM;
            }
            else{
                this.right.insertar(valor, pointer);
            }
        }
    }

    public NodeString getMax(){
        if(this.getRight()==null){
            return this;
        }
        return this.right.getMax();
    }
}
