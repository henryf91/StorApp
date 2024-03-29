package com.edu.unab.mgads.henryf.storapp.model.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "products")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    private int key;
    private String name;
    private double price;
    @ColumnInfo(name = "url_image")
    @SerializedName("url_image")
    private String urlImage;
    private String description;
    private String id;
    @SerializedName("owner")
    private String ownerId;
    @Ignore
    @Expose(serialize = false, deserialize = false)
    @SerializedName("ownerObject")
    private User owner;

    @Ignore
    public Product(){

        this.key = 0;
        this.id = "";
    }

    @Ignore
    public Product(String name, double price){
        this.name = name;
        this.price = price;
        this.key = 0;
        this.id = "";
    }

    @Exclude
    public int getKey() {
        return key;
    }

    @Exclude
    public void setKey(int key) {
        this.key = key;
    }

    @Exclude
    public String getId(){
        return id;
    }

    @Exclude
    public void setId(String id){
        this.id = id;
    }

    @Ignore
    public Product(String name, double price, String urlImage){
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.key = 0;
        this.id = "";
    }

    @PropertyName("owner")
    public String getOwnerId() {
        return ownerId;
    }

    @PropertyName("owner")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    @Exclude
    public User getOwner() {
        return owner;
    }
    @Exclude
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Product(int key, String name, double price, String urlImage, String description) {
        this.key = key;
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.description = description;
        this.id = "";
    }

    @Ignore
    public Product(String id) {
        this.id = id;
    }

    public String getName() {
        if(owner != null){
            return name+" de "+owner.getName();
        }
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

    @PropertyName("url_image")
    public String getUrlImage() {
        return urlImage;
    }

    @PropertyName("url_image")
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", urlImage='" + urlImage + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
