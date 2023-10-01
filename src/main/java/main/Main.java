package main;

import entities.Admin;
import entities.Chaza;
import entities.Review;
import entities.User;
import structures.linkedLists.DoublyLinkedList;

import java.io.IOException;
import java.util.List;

import static utils.JSONmanager.parseChazaJSONFile;
import static utils.JSONmanager.parseReviewJSONFile;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Proyecto time");

        DoublyLinkedList<Chaza> existingChazas = initializeChazas();
        DoublyLinkedList<Review> existingReviews = initializeReviews();

        User reviewer = new User("testUser" , "user", "1234");
        Admin chacero = new Admin("testChacero", "user1", "5678");

        System.out.println(existingChazas.empty());


    }

    public static DoublyLinkedList<Chaza> initializeChazas() throws IOException {
        String filePath = "src/main/java/testdata/MOCK_DATA(Chaza).json";
        return parseChazaJSONFile(filePath);
    }
    public static DoublyLinkedList<Review> initializeReviews() throws IOException {
        String filePath = "src/main/java/testdata/MOCK_DATA(review).json";
        DoublyLinkedList<Review> reviewDataList = parseReviewJSONFile(filePath);
        return reviewDataList;
    }
}

