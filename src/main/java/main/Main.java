package main;

import deserializationObjects.ReviewData;
import entities.Chaza;
import entities.User;
import structures.lineales.linkedLists.DoublyLinkedList;
import structures.lineales.linkedLists.NodeD;
import java.io.IOException;
import java.util.Random;
import static utils.JSONmanager.parseChazaJSONFile;
import static utils.JSONmanager.parseReviewJSONFile;
import static utils.JSONmanager.parseChazaJSONFileArray;
import static utils.JSONmanager.parseReviewJSONFileArray;
import java.util.ArrayList; 
import java.util.Collections; 
import structures.arboles.BSTnode;
import structures.arboles.BinarySearchTree;
import structures.arboles.BinarySearchTreeString;
import structures.arboles.NodeString;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Proyecto time");
        DataLogic chazaProcessing = new DataLogic();
        ArrayList<Integer> integerList = new ArrayList<Integer>();
        int numberOfChazas = 1000;
        for(int i=0; i<numberOfChazas;i++){
            integerList.add(i);
        }
        Collections.shuffle(integerList);

        BinarySearchTree scoreTree = new BinarySearchTree();
        BinarySearchTreeString nameTree = new BinarySearchTreeString();

        Chaza[] existingChazasArray = initializeChazaArray();
        ReviewData[] existingReviewsArray = initializeReviewsArray();        
        DoublyLinkedList<Chaza> existingChazas = chazaProcessing.bestToWorse(initializeChazas());
        DoublyLinkedList<ReviewData> existingReviews = initializeReviews();

        //chazaProcessing.organizeChazaArray(existingChazasArray);
        
        //chazaProcessing.printHundredBestChazas(existingChazasArray);
        for(Integer integer : integerList){
            nameTree.insert(existingChazasArray[integer], scoreTree.insert(existingChazasArray[integer]));
        }

        System.out.println();

        // Esta parte imprime las chazas por orden descendente según su puntaje
        BSTnode node = scoreTree.getMax();
        Chaza chazaImprimir = scoreTree.getMax().getData();
        System.out.println("Chaza top 1: "+ chazaImprimir.getName()+" puntaje: "+ chazaImprimir.getAverageScore());
        for(int k=0; k<10; k++){
            node = scoreTree.previous(node);
            chazaImprimir = node.getData();
            System.out.println("Chaza top: "+ (k+2) +": "+chazaImprimir.getName()+" puntaje: "+chazaImprimir.getAverageScore());
        }
        User[] reviewUsers = new User[100];
        // Esta parte imprime las chazas por orden descendente según su nombre
        NodeString nodeM = nameTree.getMax();
        Chaza chazaImprimirNombre = nameTree.getMax().getData();
        System.out.println("Chaza top 1: "+ chazaImprimirNombre.getName()+" puntaje: "+ chazaImprimirNombre.getAverageScore());
        for(int k=0; k<10; k++){
            nodeM = nameTree.previous(nodeM);
            chazaImprimirNombre = nodeM.getData();
            System.out.println("Chaza top: "+ (k+2)+": "+chazaImprimirNombre.getName()+" puntaje: "+chazaImprimirNombre.getAverageScore());
        }
        // Acá se prepara todo para hacer la prueba de añadir reviews
        for(int i=0; i<100; i++){
            String name = "Usuario de nombre"+i;
            String username = "user"+i;
            String password = Integer.toString(i);
            reviewUsers[i] = new User(name,username,password);
        }
        Random randomGeneretor = new Random();
        String[] nameOfDifferentChazas = new String[100];
        for(int i=0; i<numberOfChazas/10; i++){
            int randomInteger = randomGeneretor.nextInt(999);
            nameOfDifferentChazas[i]=existingChazasArray[randomInteger].getName();
        }
        
        //Ciclo para buscar Chazas
        for(int i=0; i<numberOfChazas/10; i++){
            nameTree.find(nameOfDifferentChazas[i], nameTree.getRoot());
        }
     
        //Se añaden las reviews a las chazas
        
        for (int j= 0; j<numberOfChazas/10; j++){
            int randomNumber = randomGeneretor.nextInt(99);
            for(int k=0; k<10;k++){
                chazaProcessing.updateChazaScore(nameTree.find(nameOfDifferentChazas[j], nameTree.getRoot()),reviewUsers[randomNumber], scoreTree, nameTree, existingReviewsArray[(j*10)+k]);
            }
        }
        
        
        


        /*chazaProcessing.printHundredBestChazas(existingChazas);
        NodeD<Chaza> chazaForReviewNode = existingChazas.getHead();
        String[] nameOfDifferentChazas = new String[1000];
        for(int i=0; i<1000; i++){
            nameOfDifferentChazas[i]=chazaForReviewNode.getData().getName();
            chazaForReviewNode=chazaForReviewNode.getNext();
        }

        chazaForReviewNode = existingChazas.getHead();
        NodeD<ReviewData> reviewToAddNode = existingReviews.getHead(); 
        Random randomGenerator = new Random();
        while(chazaForReviewNode.getNext()!=null&&reviewToAddNode.getNext()!=null){
            NodeD<Chaza> search = chazaProcessing.FindChaza(nameOfDifferentChazas[i],existingChazas);
            int randomNumber = randomGenerator.nextInt(99);
            chazaProcessing.updateChazaScore(search, reviewUsers[randomNumber], existingChazas, reviewToAddNode.getData());

            chazaForReviewNode=chazaForReviewNode.getNext();
            reviewToAddNode=reviewToAddNode.getNext();
        }
        System.out.println("Las mejores 10 chazas han sido actualizadas.");
        System.out.println();
        chazaProcessing.printHundredBestChazas(existingChazas);
       
        NodeD<Chaza> busqueda = chazaProcessing.FindChaza("i23nAaQC2SLpudYt99S", existingChazas);

        chazaProcessing.updateChazaScore(busqueda, reviewer, existingChazas, existingReviews.getHead().getData());
        System.out.println("Reseña agregada. ");


        System.out.println("Nuevo puntaje de la chaza: "+busqueda.getData().getAverageScore());
        

        System.out.println();*/
        




    }
    public static ReviewData[] initializeReviewsArray() throws IOException{
        String filePath = "src/main/java/testdata/reviewsData/reseñas1K.json";
        return parseReviewJSONFileArray(filePath);
    }
    public static DoublyLinkedList<Chaza> initializeChazas() throws IOException {
        String filePath = "src/main/java/testdata/chazasData/Chazas1K.json";
        return parseChazaJSONFile(filePath);
    }
    public static DoublyLinkedList<ReviewData> initializeReviews() throws IOException {
        String filePath = "src/main/java/testdata/reviewsData/reseñas1K.json";
        DoublyLinkedList<ReviewData> reviewDataList = parseReviewJSONFile(filePath);
        return reviewDataList;
    }
    public static Chaza[] initializeChazaArray() throws IOException{
        String filePath = "src/main/java/testdata/chazasData/Chazas1K.json";
        return parseChazaJSONFileArray(filePath);
    }

    public ArrayList<Chaza> searchByRating(BSTnode node, int targetRating) {
        ArrayList<Chaza> result = new ArrayList<>();
        searchByRating(node, targetRating, result);
        return result;
    }

    private void searchByRating(BSTnode node, int targetRating, ArrayList<Chaza> result) {
        if (node != null) {
            if (node.getData().getAverageScore() == targetRating){
                result.add(node.getData());
            }
            if (targetRating <= node.getData().getAverageScore()){
                searchByRating(node.getLeft(), targetRating, result);
            }
            else if (targetRating >= node.getData().getAverageScore()){
                searchByRating(node.getRight(), targetRating, result);
            }
            else{
                // Si la calificación del nodo actual coincide, busca en ambas ramas
                searchByRating(node.getLeft(), targetRating, result);
                searchByRating(node.getRight(), targetRating, result);
            }
        }
    }
    // int targetRating = 4;
    // ArrayList<Chaza> result = searchByRating(root, targetRating); //Es necesario tener el root del arbol
    // for (Chaza chaza : result) {
    //     System.out.println(chaza.name + " - Calificación: " + chaza.rating);
    // }
}

