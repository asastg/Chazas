package structures.arboles;

public class BinarySearchTree<T extends Comparable<T>> {
    private BSTnode<T> root;
    private int size =0;

    public BinarySearchTree() {
        this.root = null;
    }

    public BSTnode<T> getRoot(){
        return root;
    }

    public void setRoot(BSTnode<T> root) {
        this.root = root;
    }

    public BSTnode insert(BSTnode node, T key) {
        // If the tree is empty, return a new node
        if (node == null) {
            node = new BSTnode<>(key);
            return node;
        }

        // Otherwise, recur down the tree
        if (key.compareTo((T) node.getData()) < 0)
            node.setLeft(insert(node.getLeft(), key));
        else if (key.compareTo((T) node.getData()) > 0)
            node.setRight(insert(node.getRight(), key));

        // Return the (unchanged) node pointer
        return node;
    }

    BSTnode search(BSTnode root, T key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.getData() == key)
            return root;

        // Key is greater than root's key
        if (key.compareTo((T) root.getData()) > 0)
            return search(root.getRight(), key);

        // Key is smaller than root's key
        return search(root.getLeft(), key);
    }

    public BSTnode<T> find(T key, BSTnode<T> root) {
        if (root == null) {
            return null; // Key not found in the tree
        }
        if (key.compareTo(root.getData()) == 0) {
            return root; // Key found at the current node
        }
        if (key.compareTo(root.getData()) < 0) {
            if (root.getLeft() != null) {
                return find(key, root.getLeft());
            } else {
                return root; // Key not found, return the current node
            }
        } else {
            return find(key, root.getRight());
        }
    }

    public BSTnode<T> next(BSTnode<T> node) {
        if (node.getRight() != null) {
            return leftDescendant(node.getRight());
        } else {
            return rightAncestor(node);
        }
    }

    private BSTnode<T> leftDescendant(BSTnode<T> node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return leftDescendant(node.getLeft());
        }
    }

    private BSTnode<T> rightAncestor(BSTnode<T> node) {
        if (node.getParent() == null) {
            return null; // node is the root, and there is no right ancestor
        }

        if (node.getData().compareTo(node.getParent().getData()) < 0) {
            return node.getParent();
        } else {
            return rightAncestor(node.getParent());
        }
    }

    public BSTnode<T>[] rangeSearch(T x, T y, BSTnode<T> root) {
        BSTnode<T>[] result = (BSTnode<T>[]) new BSTnode[1]; // Initialize with an array of size 1
        int count = 0;

        BSTnode<T> node = find(x, root);

        while (node != null && node.getData().compareTo(y) <= 0) {
            if (node.getData().compareTo(x) >= 0) {
                if (count >= result.length) {
                    // If the result array is full, double its size
                    result = resizeArray(result, result.length * 2);
                }
                result[count++] = node;
            }
            node = next(node);
        }

        // Resize the result array to fit the actual number of elements
        result = resizeArray(result, count);

        return result;
    }

    public void delete(BSTnode<T> N) {
        if (N == null) {
            return; // Node is null, nothing to delete
        }

        if (N.getRight() == null) {
            // Node N has no right child, so we promote the left child
            promote(N, N.getLeft());
        } else {
            // Node N has a right child, find the next node (X)
            BSTnode<T> X = next(N);

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

    public int getTreeSizeFromNode(BSTnode<T> node){
        if (node == null) {
            return 0;
        }
        return 1 + getTreeSizeFromNode(node.getLeft()) + getTreeSizeFromNode(node.getRight());
    }

    public void promote(BSTnode<T> oldNode, BSTnode<T> newNode) {
        if (oldNode.getParent() == null) {
            // oldNode is the root node, update the root to newNode
            root = newNode;
            if (newNode != null) {
                newNode.setParent(null);
            }
        } else {
            BSTnode<T> parent = oldNode.getParent();
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

    public void replace(BSTnode<T> oldNode, BSTnode<T> newNode) {
        BSTnode<T> parent = oldNode.getParent();

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

    private BSTnode<T>[] resizeArray(BSTnode<T>[] array, int newSize) {
        BSTnode<T>[] newArray = (BSTnode<T>[]) new BSTnode[newSize];
        System.arraycopy(array, 0, newArray, 0, Math.min(array.length, newSize));
        return newArray;
    }
}
