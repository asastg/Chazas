package structures.arboles;

public class MinHeap {

        private int[] Heap;
        private int size;
        private int maxsize;
        private static final int FRONT = 1;

        public MinHeap(int maxsize)
        {

            this.maxsize = maxsize;
            this.size = 0;

            Heap = new int[this.maxsize + 1];
            Heap[0] = Integer.MIN_VALUE;
        }

        public int getSize() {return this.size;}

        // Method 1
        // Returning the position of
        // the parent for the node currently
        // at pos
        private int parent(int pos) { return pos / 2; }

        // Method 2
        // Returning the position of the
        // left child for the node currently at pos
        private int leftChild(int pos) { return (2 * pos); }

        // Method 3
        // Returning the position of
        // the right child for the node currently
        // at pos
        private int rightChild(int pos)
        {
            return (2 * pos) + 1;
        }

        // Method 4
        // Returning true if the passed
        // node is a leaf node
        private boolean isLeaf(int pos)
        {
            if (pos > (size / 2)) {
                return true;
            }
            return false;
        }

        // Method 5
        // To swap two nodes of the heap
        private void swap(int fpos, int spos)
        {

            int tmp;
            tmp = Heap[fpos];

            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        // Method 6
        // To heapify the node at pos
        private void siftDown(int pos)
        {
            if(!isLeaf(pos)){
                int swapPos= pos;
                // swap with the minimum of the two children
                // to check if right child exists. Otherwise default value will be '0'
                // and that will be swapped with parent node.
                if(rightChild(pos)<=size)
                    swapPos = Heap[leftChild(pos)]<Heap[rightChild(pos)]?leftChild(pos):rightChild(pos);
                else
                    swapPos= leftChild(pos);

                if(Heap[pos]>Heap[leftChild(pos)] || Heap[pos]> Heap[rightChild(pos)]){
                    swap(pos,swapPos);
                    siftDown(swapPos);
                }
            }
        }

        // Method 7
        // To insert a node into the heap
        public void insert(int element)
        {
            if (size >= maxsize) {
                return;
            }
            Heap[++size] = element;
            int current = size;

            while (Heap[current] < Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
        }


        // Method 8
        // To remove and return the minimum
        // element from the heap
        public int remove()
        {
            int popped = Heap[FRONT];
            Heap[FRONT] = Heap[size--];
            siftDown(FRONT);

            return popped;
        }

    public int peek()
    { return Heap[FRONT];    }

}




