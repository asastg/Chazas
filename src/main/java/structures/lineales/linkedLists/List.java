package structures.lineales.linkedLists;

public class List<T> {
    protected int size, count;
    protected T array[];

    public boolean full() {
        return count >= size;
    }
    public boolean empty() {
        return count <= 0;
    }
}
