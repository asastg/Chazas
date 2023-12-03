package entities;

import deserializationObjects.ReviewData;
import structures.lineales.linkedLists.stacks.ArrayStack;



public class Chaza {
    private String name;
    private String location;
    private String foodType;
    private ArrayStack<Review> reviews;
    private float averageScore;
    private Admin admin;
    public  Chaza(){
        this.reviews = new ArrayStack<>();
    }
    public Chaza(String name, String location, String foodType, Admin admin) {
        this.name = name;
        this.location = location;
        this.foodType = foodType;
        this.admin = admin;
        this.reviews = new ArrayStack<Review>(5);
        this.averageScore = 0;
    }
    
    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public Admin getAdmin(){
        return admin;
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

    public ArrayStack<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayStack<Review> reviews) {
        this.reviews = reviews;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }


    public void addReview(User user, ReviewData reviewData){
//        String description = reviewData.getDescription();
//        String title = reviewData.getTitle();
        String description = "";
        String title = "";
        float score= reviewData.getScore();



        user.addReview(description, title, score, this);
        this.reviews.push(new Review(score, this, user, description, title));
        this.udapteScore(score);
        
    }
    
    public void udapteScore(float score){
        this.averageScore = (this.averageScore+ score)/(this.reviews.getCount()+1);
    }

    
}
