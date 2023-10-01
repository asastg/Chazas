package main;

import deserializationObjects.ReviewData;
import entities.Admin;
import entities.Chaza;
import entities.Review;
import entities.User;
import structures.linkedLists.DoublyLinkedList;
import structures.linkedLists.NodeD;

import java.io.IOException;

import static utils.JSONmanager.parseChazaJSONFile;
import static utils.JSONmanager.parseReviewJSONFile;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Proyecto time");
        DataLogic chazaProcessing = new DataLogic();

        DoublyLinkedList<Chaza> existingChazas = chazaProcessing.bestToWorse(initializeChazas());
        DoublyLinkedList<ReviewData> existingReviews = initializeReviews();


        User reviewer = new User("testUser" , "user", "1234");
        Admin chacero = new Admin("testChacero", "user1", "5678");



        chazaProcessing.printHundredBestChazas(existingChazas);

        NodeD<Chaza> busqueda = chazaProcessing.FindChaza("40SwOhFUeARmwhECc3S", existingChazas);
        System.out.println("given name: 40SwOhFUeARmwhECc3S\n " + "found chaza name: "+ busqueda.getData().getName());
        System.out.println("score for the chaza"+busqueda.getData().getAverageScore());

        chazaProcessing.updateChazaScore(busqueda, reviewer, existingChazas, existingReviews.getHead().getData());

        System.out.println("new score for the chaza"+busqueda.getData().getAverageScore());
        System.out.println(busqueda.getData().getReviews().empty());
        System.out.println(reviewer.getReviews().empty());





        System.out.println();




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
}

