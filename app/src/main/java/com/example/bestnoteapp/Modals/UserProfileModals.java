package com.example.bestnoteapp.Modals;

public class UserProfileModals {
    int image;
    String name;
    String desc;
    String date;

    public UserProfileModals(int image, String name, String desc, String date) {
        this.image = image;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public UserProfileModals(){}

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
