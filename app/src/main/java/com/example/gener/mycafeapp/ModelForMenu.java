package com.example.gener.mycafeapp;

public class ModelForMenu {

    public String image;
    public String name;
    public String id;

    public ModelForMenu(){

    }

    public ModelForMenu(String image, String name, String id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
