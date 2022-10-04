package com.example.hunger;

public class UserHelperClass2 {

    String mealStatus, vegStatus;

    public UserHelperClass2() {
    }

    public UserHelperClass2(String mealStatus, String vegStatus) {
        this.mealStatus = mealStatus;
        this.vegStatus = vegStatus;
    }

    public String getMealStatus() {
        return mealStatus;
    }

    public void setMealStatus(String mealStatus) {
        this.mealStatus = mealStatus;
    }

    public String getVegStatus() {
        return vegStatus;
    }

    public void setVegStatus(String vegStatus) {
        this.vegStatus = vegStatus;
    }

}
