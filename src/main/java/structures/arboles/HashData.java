package structures.arboles;
import entities.Chaza;
public class HashData {
    private HashNode head;
    public HashData(Chaza data, BSTnode chazaNode ){
        this.head=new HashNode(data, chazaNode);
    }
    public void linkHashing(Chaza data, BSTnode chazaNode){
        HashNode node = head;
        while(node.getNext()!=null){
            node = node.getNext();
        }
        HashNode newNode = new HashNode(data, chazaNode);
        node.setNext(newNode);
        newNode.setPrevious(node);
    }
    public HashNode returnData(String string){
        return this.head.searchNode(string);
    }
    
}
