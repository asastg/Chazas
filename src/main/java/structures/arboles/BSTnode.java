package structures.arboles;
import entities.Chaza;

public class BSTnode  {
    private Chaza data;
    private BSTnode parent;
    private BSTnode left;
    private BSTnode right;

    public BSTnode(Chaza data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public BSTnode(Chaza data, BSTnode parent) {
        this.data = data;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public Chaza getData() {
        return data;
    }

    public void setData(Chaza data) {
        this.data = data;
    }

    public BSTnode getParent() {
        return parent;
    }

    public void setParent(BSTnode parent) {
        this.parent = parent;
    }

    public BSTnode getLeft() {
        return left;
    }

    public void setLeft(BSTnode left) {
        this.left = left;
    }

    public BSTnode getRight() {
        return right;
    }

    public void setRight(BSTnode right) {
        this.right = right;
    }

    public BSTnode insertar(Chaza valor){
        if (valor.getAverageScore() < this.data.getAverageScore()){
            if (this.left==null){
                BSTnode NodoM = new BSTnode(valor,this);
                this.left = NodoM;
                return NodoM;
            }
            else{
                return this.left.insertar(valor);
            }
        }
        else{
            if (this.right==null){
                BSTnode NodoM = new BSTnode(valor,this);
                this.right = NodoM;
                return NodoM;
            }
            else{
                return this.right.insertar(valor);
            }
        }
    }

    public BSTnode getMax(){
        if(this.right==null){
            return this;
        }
        return this.right.getMax();
    }
}
