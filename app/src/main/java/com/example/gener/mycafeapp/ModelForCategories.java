package com.example.gener.mycafeapp;

public class ModelForCategories {

    public String name;
    public String image;
    public String price;
    public String id;
    public String description;

    public ModelForCategories(){

    }

    public ModelForCategories(String name, String image, String price, String id, String description) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
