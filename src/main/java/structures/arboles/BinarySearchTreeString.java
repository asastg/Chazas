package structures.arboles;
import entities.Chaza;

public class BinarySearchTreeString {
    NodeString root;

    public NodeString getRoot(){
        return root;
    }

    public void insert(Chaza chaza, BSTnode pointer){
        if (this.root==null){
            this.root = new NodeString(chaza, null, pointer);
        }
        else{
            this.root.insertar(chaza, pointer);
        }
    }

    public NodeString find(String name, NodeString root){
        if (this.root == null) {
            return null; 
        }
        
        if (name == root.getData().getName()) {
            return root; 
        }
        if (name.compareTo(root.getData().getName()) < 0) {
            if (root.getLeft() != null) {
                return find(name, root.getLeft());
            } else {
                return root; 
            }
        } 
        else {
            if(root.getRight()!=null){
                return find(name, root.getRight());
            }
            else{
                return root;
            }
        }
    }
    
    public NodeString previous(NodeString node){
        if (node.getLeft() != null){
            return rightDescendant(node.getLeft());
        }
        else{
            return leftAncestor(node);
        }
    }
    
    public NodeString rightDescendant(NodeString node){
        if(node.getRight()==null){
            return node;
        }
        return rightDescendant(node.getRight());
    }

    public NodeString leftAncestor(NodeString node){
        if (node.getParent() == null) {
            return null; 
        }

        if (node==node.getParent().getRight())  {
            return node.getParent();
        } else {
            return leftAncestor(node.getParent());
        }
    }
    

    public NodeString next(NodeString node) {
        if (node.getRight() != null) {
            return leftDescendant(node.getRight());
        } else {
            return rightAncestor(node);
        }
    }

    private NodeString leftDescendant(NodeString node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return leftDescendant(node.getLeft());
        }
    }

    private NodeString rightAncestor(NodeString node) {
        if (node.getParent() == null) {
            return null; 
        }

        if (node.getData().getName().compareTo(node.getParent().getData().getName()) < 0)  {
            return node.getParent();
        } else {
            return rightAncestor(node.getParent());
        }
    }
    


    public void delete(NodeString N) {
        if (N == null) {
            return; 
        }

        if (N.getRight() == null) {
            
            promote(N, N.getLeft());
        } else {
            
            NodeString X = next(N);

            if (X.getLeft() == null) {
                
                replace(N, X);
            } else {
                
                promote(X, X.getLeft());
                
                replace(N, X);
            }
        }
    }

    public int getTreeSizeFromNode(NodeString node){
        if (node == null) {
            return 0;
        }
        return 1 + getTreeSizeFromNode(node.getLeft()) + getTreeSizeFromNode(node.getRight());
    }

    public void promote(NodeString oldNode, NodeString newNode) {
        if (oldNode.getParent() == null) {
            
            root = newNode;
            if (newNode != null) {
                newNode.setParent(null);
            }
        } else {
            NodeString parent = oldNode.getParent();
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

    public void replace(NodeString oldNode, NodeString newNode) {
        NodeString parent = oldNode.getParent();

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

    public NodeString getMax(){
        if(root==null||root.getRight()==null){
            return root;
        }
        return root.getMax();
    }

}
