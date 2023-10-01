package main;
import structures.linkedLists.DoublyLinkedList;
import structures.linkedLists.NodeD;
import entities.Chaza;
import entities.User;

public class DataLogic {
    
    public NodeD<Chaza> FindChaza(String chazaName, DoublyLinkedList<Chaza> chazaList){
        if(chazaList.getHead().getData().getName()==chazaName){
            return chazaList.getHead();
        }
        NodeD<Chaza> node = chazaList.getHead();
        while(node.getNext().getData().getName()!=chazaName){
            if(node.getNext()==null){
                throw new RuntimeException("No se encontró una chaza con este nombre");
            }
            node = node.getNext();
        }
        return node.getNext();
    }

    public void updateChazaScore(NodeD<Chaza> node, User currentUser, DoublyLinkedList<Chaza> chazaList){
        float previousScore = node.getData().getAverageScore();
        node.getData().addReview(currentUser);
        float newScore = node.getData().getAverageScore();
        if(previousScore<newScore){
            chazaList.popNode(node);
            chazaList.addAfter(node.getData(), lookForHigherScore(node));
        }
        else{
            chazaList.popNode(node);
            chazaList.addBefore(node.getData(), lookForLowerScore(node));
        }
    }

    public NodeD<Chaza> lookForHigherScore (NodeD<Chaza> node){
        if(node.getPrevious()==null){
            return node.getPrevious();
        }
        NodeD<Chaza> nodeToCompare = node.getPrevious();
        while(nodeToCompare.getData().getAverageScore()<=node.getData().getAverageScore()){
            if(nodeToCompare.getPrevious()==null){
                return nodeToCompare;
            }
            nodeToCompare = nodeToCompare.getPrevious();
        }
        return nodeToCompare;
    }

    public NodeD<Chaza> lookForLowerScore (NodeD<Chaza> node){
        if(node.getNext()==null){
            return node.getPrevious();
        }
        NodeD<Chaza> nodeToCompare = node.getNext();
        while(nodeToCompare.getData().getAverageScore()>node.getData().getAverageScore()){
            if(nodeToCompare.getNext()==null){
                return nodeToCompare;
            }
            nodeToCompare = nodeToCompare.getNext();
        }
        return nodeToCompare;
    }

    public void printHundredBestChazas(DoublyLinkedList<Chaza> chazaList){
        if(!chazaList.empty()){
            int counter=0;
            boolean nextChazaNull = false;
            NodeD<Chaza> node = chazaList.getHead();
            Chaza chazaPrint = node.getData();
            while(!nextChazaNull&&counter<100){
                System.out.println("La chaza en el top "+counter+1+" es: "+chazaPrint.getName()+". Tipo de comida: "+ chazaPrint.getFoodType());
                if(node.getNext()==null){
                    nextChazaNull=true;
                }
                else{
                    node=node.getNext();
                    chazaPrint = node.getData();
                }
            }
        }
    }
}
