package structures.queues;
import structures.List;

public class ArrayQueue<T> extends List {
    private static final int fixed = 3;
    private int front;
    private int rear;
    private T[] queue;

    public ArrayQueue() {
        this(fixed);
    }
    public  ArrayQueue(int n) {
        front = rear = count = 0;
        size = n;
        queue = (T[]) new Object[size];
    }

    public void enqueue(T item) {
        if(full())
            throw new RuntimeException("Queue is full: item not enqueued");
        queue[rear] = item;
        rear = (rear + 1) % size;
        count++;
    }

    public T dequeue() {
        T item = null;
        if(empty())
            throw new RuntimeException("Queue is empty: item not dequeued");
        item = queue[front];
        front = (front + 1) % size;
        count--;
        return item;
    }

    public T peek() {
        if(empty())
            throw new RuntimeException("Queue is empty: item not dequeued");
        return queue[front];
    }

    public void clear() {
        front = 0;
        rear = 0;
        count = 0;
    }

}
