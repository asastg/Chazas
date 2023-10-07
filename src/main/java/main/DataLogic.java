package main;
import structures.linkedLists.DoublyLinkedList;
import structures.linkedLists.NodeD;
import structures.stacks.Stack;
import structures.stacks.ArrayStack;
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
//--------------------------Float---------------------------------------------------//
    public void exchangeFloat(NodeD<Float> numero1, NodeD<Float> numero2){
        Float temp = numero1.getData();
        numero1.setData(numero2.getData());
        numero2.setData(temp);
    }

    public NodeD<Float> findMaxFloat(NodeD<Float> inicio){

        NodeD<Float> maximo = inicio;
        NodeD<Float> actual = inicio.getNext();   
        while(actual!=null){
            //En caso de que la puntuacion actual sea mayor a la maxima, la calificación máxima se hará la actual
            if(actual.getData()>maximo.getData()){
                maximo = actual;
            }
            //Al final de cada ciclo se sigue con el siguiente nodo
            actual = actual.getNext();
        }
        return maximo;
    }

    public DoublyLinkedList<Float> bestToWorseFloat(DoublyLinkedList<Float> floatList){
        //Si la lista solo tiene un elemento, se retorna la misma
        if(floatList.getHead() == null || floatList.getHead().getNext() == null){
            return floatList;
        }
        //Se itera desde la cabeza de la lista
        NodeD<Float> actual = floatList.getHead();
        while(actual != null){
            //El nodo máximo se halla desde el nodo actual
            NodeD<Float> maximo = findMaxFloat(actual);
            //Se intercambian las posiciones de la chaza actual y la chaza con máxima calificación
            exchangeFloat(actual, maximo);
            //El actual ahora es el nodo siguiente, para hacer la comparación con el resto de Chazas
            actual = actual.getNext();
        }
        //Al final del ciclo se retorna la lista completa ordenada
        return floatList;
    }
    //Float organizar chazas maximo a minimo (todo arriba)

    public NodeD<Float> FindFloat(float number, DoublyLinkedList<Float> floatList){
        if(floatList.getHead().getData()==number){
            return floatList.getHead();
        }
        NodeD<Float> node = floatList.getHead();
        while(node.getNext().getData()!=number){
            if(node.getNext()==null){
                throw new RuntimeException("No se encontró una chaza con este nombre");
            }
            node = node.getNext();
        }
        return node.getNext();
    } 

    public static ArrayStack<Float> StackBestWorse(ArrayStack<Float> stack) {
        ArrayStack<Float> tempStack = new ArrayStack<>();
        while (!stack.empty()) {
            float temp = stack.pop();
            //Mientras que el stack no esté vacio se compara el peek del stacktemporal con
            //el ultimo elemento popeado de stack, si es mayor el elemento del stack, se popea el ultimo del 
            //stacktemporal y se coloca nuevamente en el stack desordenado
            //el ciclo termina colocando el elemento temporal en el stacktemporal
            while (!tempStack.empty() && tempStack.peek() < temp) {
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        return tempStack;
    }
}

