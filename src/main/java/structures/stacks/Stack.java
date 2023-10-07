package structures.stacks;

import structures.List;

public interface Stack<T>{
    public void push(T item);
    public T pop();
    public T peek();
    public boolean full();
    public boolean empty();
}
