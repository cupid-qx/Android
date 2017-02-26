package com.cupido.recyclerviewdemo;

/**
 * @author: qcq
 * @data: 2017/2/26.
 * @content:
 */

public class Fruit {
    private String name;
    private int imageId ;

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit(String name,int imageId){
        this.name = name ;
        this.imageId = imageId ;
    }

}
