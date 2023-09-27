package structures.queues;

public interface Queue<T> {
    public void enqueue(T item);
    public T dequeue();
    public boolean full();
    public boolean empty();
}
