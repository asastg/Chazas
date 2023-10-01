package structures.linkedLists;

public class DoublyLinkedList <T>{
    NodeD<T> head, tail;

    public DoublyLinkedList(){
        head=null;
        tail=null;
    }

    public boolean empty(){
        return this.head==null;
    }
    
    public void pushFront(T data){
        NodeD<T> node = new NodeD<T>(data);
        
        if(empty()){
            this.tail=node;
            this.head=node;
        }
        else{
            node.setNext(this.head);
            this.head.setPrevious(node);
            this.head=node;
        }
    }

    public void pushBack(T data){
        NodeD<T> node = new NodeD<T>(data);
        if(empty()){
            this.tail=node;
            this.head=node;
        }
        else{
            node.setPrevious(this.tail);
            this.tail.setNext(node);
            this.tail=node;
        }
    }
    
    public void addAfter(T data, NodeD<T> node){
        NodeD<T> newNode = new NodeD<T>(data);
        newNode.setNext(node.getNext());
        newNode.setPrevious(node);
        node.setNext(newNode);
        if(node==this.tail){
            this.tail=newNode;
        }
        else{
            node.getNext().setPrevious(newNode);
        }   
    }

    public NodeD<T> getHead(){
        return head;
    }

    public NodeD<T> getTail(){
        return tail;
    }

    public void addBefore(T data, NodeD<T> node){
        NodeD<T> newNode = new NodeD<T>(data);
        newNode.setPrevious(node.getPrevious());
        newNode.setNext(node);
        node.setPrevious(newNode);
        if(node==this.head){
            this.head=newNode;
        }
        else{
            node.getPrevious().setNext(newNode);
        }
    }

    public void popNode(NodeD<T> node){
        boolean head=true, tail=true;
        if(node!=this.head){
           node.getPrevious().setNext(node.getNext());
           head=false;
        }
        if(node!=this.tail){
           node.getNext().setPrevious(node.getPrevious());
           tail=false;
        }
        popHeadAndTail(tail, head);
    }

    public void popHeadAndTail(boolean tail, boolean head){
        if(tail){
           this.tail=null;
        }
        if(head){
            this.head=null;
        }
    }



}
