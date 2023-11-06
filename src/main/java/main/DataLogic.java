package main;
import java.util.ArrayList;
import deserializationObjects.ReviewData;
import structures.arboles.BinarySearchTree;
import structures.lineales.linkedLists.DoublyLinkedList;
import structures.lineales.linkedLists.NodeD;
import structures.arboles.BSTnode;
import structures.arboles.BinarySearchTree;
import structures.arboles.BinarySearchTreeString;
import structures.arboles.NodeString;

import entities.Chaza;
import entities.User;

public class DataLogic {
    
    public NodeD<Chaza> FindChaza(String chazaName, DoublyLinkedList<Chaza> chazaList){
        if(chazaList.getHead().getData().getName().equals(chazaName)){
            return chazaList.getHead();
        }

        NodeD<Chaza> node = chazaList.getHead();
        

        while(!node.getNext().getData().getName().equals(chazaName)){
            if(node.getNext()==null){
                throw new RuntimeException("No se encontró una chaza con este nombre");
            }
            node = node.getNext();
        }
        return node.getNext();

    }

    public int FindChaza(String chazaName, Chaza[] chazas){
        int arraySize=chazas.length;
        for (int i=0; i<arraySize; i++){
            if(chazas[i].getName().equals(chazaName)){return i;}
        }
        throw new RuntimeException("No se encontró una chaza con este nombre");
    }

    public void updateChazaScore(NodeD<Chaza> node, User currentUser, DoublyLinkedList<Chaza> chazaList, ReviewData reviewData){
        float previousScore = node.getData().getAverageScore();
        node.getData().addReview(currentUser, reviewData);
        float newScore = node.getData().getAverageScore();
        if(previousScore<newScore){
            if(lookForHigherScore(node)==null){
                chazaList.pushFront(node.getData());
                chazaList.popNode(node);
            }
            else{
                NodeD<Chaza> addAfterNode = lookForHigherScore(node);
                chazaList.addAfter(node.getData(), addAfterNode);
                chazaList.popNode(node);
            }
        }
        else{
            if(lookForLowerScore(node)==null){
                chazaList.pushBack(node.getData());
                chazaList.popNode(node);
            }
            else{
                NodeD<Chaza> addBeforeNode = lookForLowerScore(node);
                chazaList.addBefore(node.getData(), addBeforeNode);
                chazaList.popNode(node);
            }
        }
    }

    public void updateChazaScore(int index, User currentUser, Chaza[] chazas, ReviewData reviewData){
        float previousScore = chazas[index].getAverageScore();
        chazas[index].addReview(currentUser, reviewData);
        float newScore = chazas[index].getAverageScore();
        Chaza tempChaza2, tempChaza1;
        if(previousScore<newScore){
           int indexOrganize = lookForHigherScore(index, chazas);
           tempChaza1=chazas[indexOrganize];
           chazas[indexOrganize]=chazas[index];
           for(int i=indexOrganize; i<index;i++){
                tempChaza2 = chazas[i+1];
                chazas[i+1] = tempChaza1;
                tempChaza1 = tempChaza2;
           }
        }
        else if(previousScore>newScore){
           int indexOrganize = lookForLowerScore(index, chazas);
           tempChaza1=chazas[indexOrganize];
           chazas[indexOrganize]=chazas[index];
           for(int i=indexOrganize; i>index ;i--){
                tempChaza2=chazas[i-1];
                chazas[i-1]=tempChaza1;
                tempChaza1=tempChaza2;
            }
        }
    }

    public int lookForHigherScore(int index,Chaza[] chazas){
        if(index==0){
            return 0;
        }
        for(int i=index-1;i>0;i--){
            if(chazas[i].getAverageScore()>chazas[index].getAverageScore()){
                return i+1;
            }
        }
        return 0;
    }

    public int lookForLowerScore(int index, Chaza[] chazas){
        
        int arraySize = chazas.length;
        if(index==arraySize-1){
            return arraySize-1;
        }
        for (int i=index+1;i<arraySize;i++){
            if(chazas[i].getAverageScore()<chazas[index].getAverageScore()){
                return i-1;
            }
        }
        return arraySize-1;
    }

    public NodeD<Chaza> lookForHigherScore (NodeD<Chaza> node){
        if(node.getPrevious()==null){
            return node.getPrevious();
        }
        NodeD<Chaza> nodeToCompare = node.getPrevious();
        while(nodeToCompare.getData().getAverageScore()<=node.getData().getAverageScore()){
            if(nodeToCompare.getPrevious()==null){
                return null;
            }
            nodeToCompare = nodeToCompare.getPrevious();
        }
        return nodeToCompare;
    }

    public NodeD<Chaza> lookForLowerScore (NodeD<Chaza> node){
        if(node.getNext()==null){
            return null;
        }
        NodeD<Chaza> nodeToCompare = node.getNext();
        while(nodeToCompare.getData().getAverageScore()>node.getData().getAverageScore()){
            if(nodeToCompare.getNext()==null){
                return null;
            }
            nodeToCompare = nodeToCompare.getNext();
        }
        return nodeToCompare;
    }

    public void printHundredBestChazas(DoublyLinkedList<Chaza> chazaList){
        if(!chazaList.empty()){
            int counter=1;
            boolean nextChazaNull = false;
            NodeD<Chaza> node = chazaList.getHead();
            Chaza chazaPrint = node.getData();
            while(!nextChazaNull&&counter<101){
                System.out.println("La chaza en el top "+counter+" es: "+chazaPrint.getName()+". Tipo de comida: "+ chazaPrint.getFoodType()+
                ". Puntaje: "+chazaPrint.getAverageScore());
                if(node.getNext()==null){
                    nextChazaNull=true;
                }
                else{
                    node=node.getNext();
                    chazaPrint = node.getData();
                    counter++;
                }
            }
        }
    }

    public void printHundredBestChazas(Chaza[] chazas){
        int arraySize = chazas.length;
        if(arraySize>=100){
            for(int i=0;i<100; i++){
                System.out.println("La chaza en el top "+(i+1)+" es: "+chazas[i].getName()+" Tipo de comida "+chazas[i].getFoodType()+
                ". Puntaje: "+chazas[i].getAverageScore());
            }
        }
        else{
            for(int i=0; i<arraySize;i++){
                System.out.println("Chaza: "+chazas[i].getName()+" score: "+ chazas[i].getAverageScore());
            }
        }
    }
    

    public DoublyLinkedList<Chaza> bestToWorse(DoublyLinkedList<Chaza> chazaList){
        //Si la lista solo tiene un elemento, se retorna la misma
        if(chazaList.getHead() == null || chazaList.getHead().getNext() == null){
            return chazaList;
        }
        //Se itera desde la cabeza de la lista
        NodeD<Chaza> actual = chazaList.getHead();
        while(actual != null){
            //El nodo máximo se halla desde el nodo actual
            NodeD<Chaza> maximo = findMax(actual);
            //Se intercambian las posiciones de la chaza actual y la chaza con máxima calificación
            exchange(actual, maximo);
            //El actual ahora es el nodo siguiente, para hacer la comparación con el resto de Chazas
            actual = actual.getNext();
        }
        //Al final del ciclo se retorna la lista completa ordenada
        return chazaList;
    }

    //Encuentra una chaza con la calificación máxima
    public NodeD<Chaza> findMax(NodeD<Chaza> inicio){

        NodeD<Chaza> maximo = inicio;
        NodeD<Chaza> actual = inicio.getNext();   
        while(actual!=null){
            //Si la chaza tiene una reseña con 5.0 no se busca más y se retorna esa chaza
            if(Float.compare(actual.getData().getAverageScore(), 5.0f) == 0){
                return actual;
            }
            //En caso de que la puntuacion actual sea mayor a la maxima, la calificación máxima se hará la actual
            if(actual.getData().getAverageScore()>maximo.getData().getAverageScore()){
                maximo = actual;
            }
            //Al final de cada ciclo se sigue con el siguiente nodo
            actual = actual.getNext();
        }
        return maximo;
    }

    //Intercambia los valores (en este caso chazas) de un nodo a otro
    public void exchange(NodeD<Chaza> Nchaza1, NodeD<Chaza> Nchaza2){
        Chaza temp = Nchaza1.getData();
        Nchaza1.setData(Nchaza2.getData());
        Nchaza2.setData(temp);
    }

    public void organizeChazaArray(Chaza[] chazas){
        int size = chazas.length;
        Chaza temp;
        for(int i=0;i<size;i++){
            for(int j=i+1; j<size; j++){
                if(chazas[i].getAverageScore()<chazas[j].getAverageScore()){
                    temp = chazas[i];
                    chazas[i]=chazas[j];
                    chazas[j]=temp;
                }
            }

        }
    }

    public void updateChazaScore(NodeString node, User currentUser, BinarySearchTree scoreTree, BinarySearchTreeString nameTree, ReviewData reviewData){
        node.getData().addReview(currentUser, reviewData);
        scoreTree.delete(node.getPointer());
        node.setPointer(scoreTree.insert(node.getData()));
    }
    
}
