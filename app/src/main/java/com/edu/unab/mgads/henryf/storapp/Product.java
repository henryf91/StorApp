package com.edu.unab.mgads.henryf.storapp;

public class Product {
    private String name;
    private double price;
    private String urlImage;
    private String description;

    public Product() {
    }

    public Product(String name, double price, String urlImage ) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, String urlImage, String description) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
