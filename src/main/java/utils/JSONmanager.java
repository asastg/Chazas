package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import entities.Chaza;
import entities.Review;

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

    public static List<Review> parseReviewJSONFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<Review> reviews = objectMapper.readValue(file, new TypeReference<List<Review>>() {});

        return reviews;
    }

    public static List<Chaza> parseChazaJSONFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<Chaza> chazas = objectMapper.readValue(file, new TypeReference<List<Chaza>>() {});

        return chazas;
    }


}