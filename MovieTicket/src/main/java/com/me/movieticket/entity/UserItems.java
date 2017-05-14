package com.me.movieticket.entity;

/**
 * Created by MANITO on 2017/3/27.
 */

public class UserItems {
    private String itemname;
    private int imageId;

    public UserItems(String itemname, int imageId){
        this.itemname=itemname;
        this.imageId=imageId;
    }

    public String getItemname(){
        return itemname;
    }

    public int getImageId(){
        return imageId;
    }

}
