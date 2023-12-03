package structures.arboles;
import entities.Chaza;
public class HashNode {
    private Chaza data;
    private BSTnode node;
    private HashNode next, previous;
    public HashNode(Chaza data, BSTnode node){
        this.data = data;
        this.node = node;
        this.next = null;
        this.previous = null;
    }
    public Chaza getData(){
        return this.data;
    }
    public BSTnode getNode(){
        return this.node;
    }
    public void setData(Chaza data){
        this.data =  data;
    }
    public void setNode(BSTnode node){
        this.node = node;
    }
    public void setNext(HashNode next){
        this.next = next;
    }
    public HashNode getNext(){
        return next;
    }
    public void setPrevious(HashNode previous){
        this.previous = previous;
    }
    public HashNode getPrevious(){
        return previous;
    }
    public HashNode searchNode(String string){
        if(this.data.getName().equals(string)||this.next==null){
            return this;
        }
        else{
            return this.next.searchNode(string);
        }
    }

}
