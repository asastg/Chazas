package main;

import deserializationObjects.ReviewData;
import entities.Admin;
import entities.Chaza;
import entities.Review;
import entities.User;
import structures.linkedLists.DoublyLinkedList;
import structures.linkedLists.NodeD;


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


import static utils.JSONmanager.parseChazaJSONFile;
import static utils.JSONmanager.parseReviewJSONFile;
import static utils.JSONmanager.parseChazaJSONFileArray;
import static utils.JSONmanager.parseReviewJSONFileArray;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Proyecto time");
        DataLogic chazaProcessing = new DataLogic();

        Chaza[] existingChazasArray = initializeChazaArray();
        ReviewData[] existingReviewsArray = initializeReviewsArray();        
        DoublyLinkedList<Chaza> existingChazas = chazaProcessing.bestToWorse(initializeChazas());
        DoublyLinkedList<ReviewData> existingReviews = initializeReviews();

        chazaProcessing.organizeChazaArray(existingChazasArray);
        
        chazaProcessing.printHundredBestChazas(existingChazasArray);
        System.out.println();
        
        User[] reviewUsers = new User[100];

        for(int i=0; i<100; i++){
            String name = "Usuario de nombre"+i;
            String username = "user"+i;
            String password = Integer.toString(i);
            reviewUsers[i] = new User(name,username,password);
        }
        String[] nameOfDifferentChazas = new String[1000];
        for(int i=0; i<1000; i++){
            nameOfDifferentChazas[i]=existingChazasArray[i].getName();
        }
        Random randomGeneretor = new Random();
        NodeD<ReviewData> reviewToAddNode = existingReviews.getHead();
        int i=0;
        while(reviewToAddNode.getNext()!=null){
            int index = chazaProcessing.FindChaza(nameOfDifferentChazas[i], existingChazasArray);
            int randomNumber = randomGeneretor.nextInt(99);
            chazaProcessing.udapteChazaScore(index, reviewUsers[randomNumber], existingChazasArray, reviewToAddNode.getData());
            reviewToAddNode=reviewToAddNode.getNext();
            i +=1;
        }
        System.out.println("Se actualizaron las 100 mejores chazas");
        System.out.println();
        chazaProcessing.printHundredBestChazas(existingChazasArray);

        


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
        String filePath = "src/main/java/testdata/MOCK_DATA(review).json";
        return parseReviewJSONFileArray(filePath);
    }
    public static DoublyLinkedList<Chaza> initializeChazas() throws IOException {
        String filePath = "src/main/java/testdata/MOCK_DATA(Chaza).json";
        return parseChazaJSONFile(filePath);
    }
    public static DoublyLinkedList<ReviewData> initializeReviews() throws IOException {
        String filePath = "src/main/java/testdata/MOCK_DATA(review).json";
        DoublyLinkedList<ReviewData> reviewDataList = parseReviewJSONFile(filePath);
        return reviewDataList;
    }
    public static Chaza[] initializeChazaArray() throws IOException{
        String filePath = "src/main/java/testdata/MOCK_DATA(Chaza).json";
        return parseChazaJSONFileArray(filePath);
    }
}

