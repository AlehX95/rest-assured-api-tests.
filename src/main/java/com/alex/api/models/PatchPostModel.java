package com.alex.api.models;

public class PatchPostModel {

    private String title;
    private String body;

    public PatchPostModel(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public PatchPostModel(String title) {  // PATCH solo title
        this.title = title;
    }

    public PatchPostModel() {}  // opcional si quieres campos sueltos

    public String getTitle() { return title; }
    public String getBody() { return body; }
}
