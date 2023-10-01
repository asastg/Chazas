package deserializationObjects;

public class ReviewData {
    String title;
    String description;
    float score;

    public ReviewData(){
    }
    public ReviewData(String title, String description, float score) {
        this.title = title;
        this.description = description;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
