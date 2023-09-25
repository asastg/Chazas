package structures.linkedLists;

public class SinglyLinkedList<T> {
    private Node<T> head, tail;

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    public boolean empty() {
        return head == null;
    }
    public void PushFront(T  data){
        Node<T> node = new Node<>(data);
        node.setData(data);
        node.setNext(head);
        head = node;
        if (empty())
            tail = head;
    }
    public void PopFront(){
        if (empty())
            throw new RuntimeException("List is empty");
        head=head.getNext();
        if (head == null)
            tail = null;
    }
    public void PushBack(T data){
        Node<T> node = new Node<>(data);
        node.setData(data);
        node.setNext(null);
        if (empty()){
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }
    public void PopBack(){
        if (empty())
            throw new RuntimeException("List is empty");
        if (head == tail){
            head = null;
            tail = null;
        } else {
            Node<T> p = head;
            while (p.getNext().getNext() != null){
                p = p.getNext();
            }
            p.setNext(null);
            tail = p;
        }
    }
    public void AddBefore(Node node, T data){
        Node<T> newNode = new Node<>(data);
        if (empty()){
            head = newNode;
            tail = newNode;
        }
        if (node == head){
            newNode.setNext(node);
            head = newNode;
        } else {
            newNode.setNext(node);
            Node<T> p = head;
            while (p.getNext() != node){
                p = p.getNext();
            }
            p.setNext(newNode);
        }
    }
    public void AddAfter(Node node, T data){
        Node<T> node2 = new Node<>(data);
        node2.setNext(node.getNext());
        node.setNext(node2);
        if (tail == node){
            tail = node2;
        }
    }

}
