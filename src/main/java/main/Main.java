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
import structures.arboles.Hash;
import structures.arboles.HashData;
import structures.arboles.HashNode;


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
        
        //elegir un primo con relativa cercania para la cantidad de datos.
        Hash hash = new Hash(641);


        Chaza[] existingChazasArray = initializeChazaArray();
        ReviewData[] existingReviewsArray = initializeReviewsArray();        
        //DoublyLinkedList<Chaza> existingChazas = chazaProcessing.bestToWorse(initializeChazas());
        //DoublyLinkedList<ReviewData> existingReviews = initializeReviews();

        //chazaProcessing.organizeChazaArray(existingChazasArray);
        
        long organizeHashStart = System.currentTimeMillis();
        
        for(Integer integer : integerList){
            hash.hashChaza(existingChazasArray[integer], scoreTree.insert(existingChazasArray[integer]));
        }
        long organizeHashFinish = System.currentTimeMillis();

        System.out.println(" El tiempo de organizar "+numberOfChazas+" en hashes es: "+(organizeHashFinish-organizeHashStart)+" Con colisiones= "+hash.getCollisions());

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
        HashNode hashnode;
        int number=0;
        //Ciclo para buscar Chazas
        long searchStart = System.currentTimeMillis();
        for(int i=0; i<numberOfChazas/10; i++){
            hashnode = hash.findChaza(nameOfDifferentChazas[i]);
            if(hashnode==null){
               number++;
            }
        }
        long searchFinish = System.currentTimeMillis();
        System.out.println(" Tiempo de buscar "+numberOfChazas+" en hashes es: "+(searchFinish-searchStart)+" nodos nulos "+ number);    
        //Se añaden las reviews a las chazas
        
        long addReviewsStart = System.currentTimeMillis();
        for (int j=0; j<numberOfChazas/10; j++){
            int randomNumber = randomGeneretor.nextInt(99);
            for(int k=0; k<10;k++){
                HashNode nodeHash = hash.findChaza(nameOfDifferentChazas[j]);
                chazaProcessing.updateChazaScore(nodeHash,reviewUsers[randomNumber], scoreTree, existingReviewsArray[j*10+k]);
            }
        }
        long addReviewsFinish = System.currentTimeMillis();
        System.out.println(" Tiempo de añadir 10 reviews a "+ numberOfChazas+" en hashes es: "+ (addReviewsFinish-addReviewsStart));
        

        


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
}

