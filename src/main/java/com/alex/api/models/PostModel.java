package com.alex.api.models;


public class PostModel {
    private String title;
    private String body;
    private int userId;
    
    
    //Constructor
    public PostModel(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    // getters opcionales si REST Assured los necesita
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public int getUserId() { return userId; }
}