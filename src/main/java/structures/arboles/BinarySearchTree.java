package structures.arboles;

import entities.Chaza;

public class BinarySearchTree  {
    private BSTnode root;
    private int size =0;

    public BinarySearchTree() {
        this.root = null;
    }

    public BSTnode getRoot(){
        return root;
    }

    public void setRoot(BSTnode root) {
        this.root = root;
    }

    public BSTnode insert(Chaza key) {
        if (this.root==null){
            this.root = new BSTnode(key, null);
            return root;
        }
        else{
            return this.root.insertar(key);
        }
    }

    public BSTnode find(float key, BSTnode root) {
        if (root == null) {
            return null; // Key not found in the tree
        }
        if (key == root.getData().getAverageScore()) {
            return root; // Key found at the current node
        }
        if (key < root.getData().getAverageScore()) {
            if (root.getLeft() != null) {
                return find(key, root.getLeft());
            } else {
                return root; // Key not found, return the current node
            }
        } else {
            return find(key, root.getRight());
        }
    }

    public BSTnode next(BSTnode node) {
        if (node.getRight() != null) {
            return leftDescendant(node.getRight());
        } else {
            return rightAncestor(node);
        }
    }

    private BSTnode leftDescendant(BSTnode node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return leftDescendant(node.getLeft());
        }
    }

    private BSTnode rightAncestor(BSTnode node) {
        if (node.getParent() == null) {
            return null; // node is the root, and there is no right ancestor
        }

        if (node.getData().getAverageScore() < node.getParent().getData().getAverageScore())  {
            return node.getParent();
        } else {
            return rightAncestor(node.getParent());
        }
    }

    public BSTnode[] rangeSearch(float min, float max, BSTnode root) {
        BSTnode[] result = new BSTnode[1]; // Initialize with an array of size 1
        int count = 0;

        BSTnode node = find(min, root);

        while (node != null && node.getData().getAverageScore() <= max) {
            if (node.getData().getAverageScore() >= min) {
                if (count >= result.length) {
                    result = resizeArray(result, result.length * 2);
                }
                result[count++] = node;
            }
            node = next(node);
        }

        

        result = resizeArray(result, count);

        return result;
    }

    public void delete(BSTnode N) {
        if (N == null) {
            return; // Node is null, nothing to delete
        }

        if (N.getRight() == null) {
            // Node N has no right child, so we promote the left child
            promote(N, N.getLeft());
        } else {
            // Node N has a right child, find the next node (X)
            BSTnode X = next(N);

            if (X.getLeft() == null) {
                // X has no left child, simply replace N with X
                replace(N, X);
            } else {
                // X has a left child, promote the left child of X
                promote(X, X.getLeft());
                // Replace N with X
                replace(N, X);
            }
        }
    }

    public int getTreeSizeFromNode(BSTnode node){
        if (node == null) {
            return 0;
        }
        return 1 + getTreeSizeFromNode(node.getLeft()) + getTreeSizeFromNode(node.getRight());
    }

    public void promote(BSTnode oldNode, BSTnode newNode) {
        if (oldNode.getParent() == null) {
            // oldNode is the root node, update the root to newNode
            root = newNode;
            if (newNode != null) {
                newNode.setParent(null);
            }
        } else {
            BSTnode parent = oldNode.getParent();
            if (parent.getLeft() == oldNode) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }

            if (newNode != null) {
                newNode.setParent(parent);
            }
        }
    }

    public void replace(BSTnode oldNode, BSTnode newNode) {
        BSTnode parent = oldNode.getParent();

        if (parent == null) {
            // oldNode is the root node
            root = newNode;
            if (newNode != null) {
                newNode.setParent(null);
            }
        } else {
            if (parent.getLeft() == oldNode) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }

            if (newNode != null) {
                newNode.setParent(parent);
            }
        }

        // Clear the parent and child references for the oldNode
        oldNode.setParent(null);
        oldNode.setLeft(null);
        oldNode.setRight(null);
    }

    private BSTnode[] resizeArray(BSTnode[] array, int newSize) {
        BSTnode[] newArray = (BSTnode[]) new BSTnode[newSize];
        System.arraycopy(array, 0, newArray, 0, Math.min(array.length, newSize));
        return newArray;
    }

    public BSTnode getMax(){
        if(root==null||root.getRight()==null){
            return root;
        }
        return root.getMax();
    }

    public BSTnode previous(BSTnode node){
        if (node.getLeft() != null){
            return rightDescendant(node.getLeft());
        }
        else{
            return leftAncestor(node);
        }
    }
    
    public BSTnode rightDescendant(BSTnode node){
        if(node.getRight()==null){
            return node;
        }
        return rightDescendant(node.getRight());
    }

    public BSTnode leftAncestor(BSTnode node){
        if (node.getParent() == null) {
            return null; 
        }

        if (node == node.getParent().getRight()) {
            return node.getParent();
        } else {
            return leftAncestor(node.getParent());
        }
    }
}
