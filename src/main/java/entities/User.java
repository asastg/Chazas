package entities;

import structures.queues.ArrayQueueDynamic;

import java.util.Calendar;

public class User {
    private String name;
    private Calendar birthdate;
    private String username;
    private String password;
    private ArrayQueueDynamic<Review>[] reviews;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayQueueDynamic<Review>[] getReviews() {
        return reviews;
    }

    public void setReviews(ArrayQueueDynamic<Review>[] reviews) {
        this.reviews = reviews;
    }
}
