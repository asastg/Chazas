package structures.queues;
import structures.List;

public class ArrayQueueDynamic<T> extends List {
    private static final int DEFAULT_SIZE = 3;
    private int front;
    private int rear;
    private T[] queue;

    public ArrayQueueDynamic() {
        this(DEFAULT_SIZE);
    }

    public ArrayQueueDynamic(int n) {
        front = rear = count = 0;
        size = n;
        queue = (T[]) new Object[size];
    }

    public void enqueue(T item) {
        if (count == size) {
            resize(); // Aumentar el tamaño de la cola si está llena
        }
        queue[rear] = item;
        rear = (rear + 1) % size;
        count++;
    }

    public T dequeue() {
        T item = null;
        if (empty()) {
            throw new RuntimeException("Queue is empty: item not dequeued");
        }
        item = queue[front];
        front = (front + 1) % size;
        count--;
        return item;
    }

    private void resize() {
        int newSize = size * 2; // Duplicar el tamaño de la cola
        T[] newQueue = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % size];
        }
        front = 0;
        rear = size;
        size = newSize;
        queue = newQueue;
    }

    public T peek() {
        if (empty()) {
            throw new RuntimeException("Queue is empty: item not dequeued");
        }
        return queue[front];
    }
}