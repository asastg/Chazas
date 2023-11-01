package structures.lineales.linkedLists.stacks;

import structures.lineales.linkedLists.List;

public class ArrayStack<T> extends List {
    private static final int fixed = 3;
    private T top;

    public ArrayStack() {
        this(fixed);
    }

    public ArrayStack(int n) {
        count = 0;
        array = (T[]) new Object[n];
        size = n;
        top = null;
    }
    public void push(T item) {
        if(full())
            this.duplicateSize();
        array[count]=item;
        top = item;
        count++;
    }
    public void pop() {
        if(empty())
            throw new RuntimeException("Stack is empty");
        if(count ==1){
            top = null;
        } else {
            top = (T) array[count -2];
        }
        array[count -1]=null;
        count--;
    }
    public T peek() {
        if(empty())
            throw new RuntimeException("Stack is empty");
        return top;
    }
    public void duplicateSize(){
        
        int newSize = this.size*2;
        T[] newStack = (T[]) new Object[newSize];
        for (int i=0; i<this.size;i++){
            newStack[i] = (T) this.array[i];
        }
        this.size=newSize;
        this.top=newStack[count];
        this.array=newStack;    
    }
    public int getCount(){
        return this.count;
    }
}
