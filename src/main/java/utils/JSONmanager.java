package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import deserializationObjects.ReviewData;
import entities.Chaza;
import entities.Review;
import structures.linkedLists.DoublyLinkedList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONmanager {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return defaultObjectMapper;
    }

    public static DoublyLinkedList<ReviewData> parseReviewJSONFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<ReviewData> reviews = objectMapper.readValue(file, new TypeReference<List<ReviewData>>() {});

        DoublyLinkedList<ReviewData> reviewList = new DoublyLinkedList<>();

        for (ReviewData review : reviews) {
            reviewList.pushBack(review);
        }

        return reviewList;
    }

    public static DoublyLinkedList<Chaza> parseChazaJSONFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<Chaza> chazas = objectMapper.readValue(file, new TypeReference<List<Chaza>>() {});

        DoublyLinkedList<Chaza> chazasList = new DoublyLinkedList<>();

        for (Chaza chaza : chazas) {
            chazasList.pushBack(chaza);
        }

        return chazasList;
    }


}