package com.helmercapassola.dog;

public class Dog {


    private String title;
    private  String desc;
    private  String image;

    public Dog(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }
}
