package com.example.gener.mycafeapp;

public class ModelForOrderHistory {

    public String timeoforder;

    public ModelForOrderHistory(){

    }

    public ModelForOrderHistory(String timeoforder) {

        this.timeoforder = timeoforder;
    }

    public String getTimeoforder() {
        return timeoforder;
    }

    public void setTimeoforder(String timeoforder) {
        this.timeoforder = timeoforder;
    }
}
