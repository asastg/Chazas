package entities;

import structures.stacks.ArrayStack;

public class Chaza {
    private String name;
    private String location;
    private String foodType;
    private ArrayStack<Review>[] reviews;
    private float averageScore;

    public Chaza(String name, String location, String foodType) {
        this.name = name;
        this.location = location;
        this.foodType = foodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public ArrayStack<Review>[] getReviews() {
        return reviews;
    }

    public void setReviews(ArrayStack<Review>[] reviews) {
        this.reviews = reviews;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
}
