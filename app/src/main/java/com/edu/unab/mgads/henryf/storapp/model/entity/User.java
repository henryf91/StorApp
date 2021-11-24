package com.edu.unab.mgads.henryf.storapp.model.entity;

public class User {

    private String urlPicture;
    private String email;
    private String document;
    private String name;

    public User( String name, String document, String email,  String urlPicture) {
        this.urlPicture = urlPicture;
        this.email = email;
        this.document = document;
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public User() {
    }
}
