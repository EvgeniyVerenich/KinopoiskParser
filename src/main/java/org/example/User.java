package org.example;

public class User {

    private int id;
    private String name;
    private String review;
    private String topic;
    private String reviewType;

    public User(int id, String name, String topic, String review, String reviewType){
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.review = review;
        this.reviewType = reviewType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
