package com.helmercapassola.dog;

public class Dog {


    private String title;
    private  String desc;
    private  int image;

    public Dog(String title, String desc, int image) {
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

    public int getImage() {
        return image;
    }
}
