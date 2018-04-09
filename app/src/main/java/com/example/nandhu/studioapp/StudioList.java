package com.example.nandhu.studioapp;

/**
 * Created by Nandhu on 14-03-2018.
 */

class StudioList {
    private String caption,image;
    public StudioList(String caption, String image){
        this.caption=caption;
        this.image=image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
