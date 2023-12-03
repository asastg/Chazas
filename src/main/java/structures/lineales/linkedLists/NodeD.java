package structures.lineales.linkedLists;

public class NodeD<T> {
    private T data;
    private NodeD<T> next;
    private NodeD<T> previous;

    public NodeD(T data, NodeD<T> next, NodeD<T> previous){
        this.data=data;
        this.next=next;
        this.previous=previous;
    }

    public NodeD(T data){
        this.data=data;
        this.next=null;
        this.previous=null;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public NodeD<T> getNext() {
        return next;
    }
    public void setNext(NodeD<T> next) {
        this.next = next;
    }
    public void setPrevious(NodeD<T> previous){
        this.previous=previous;
    }
    public NodeD<T> getPrevious(){
        return previous;
    }
}
