package com.example.asus.ayojoon;

import com.example.asus.ayojoon.ViewHolder.ProductViewHolder;

public class Products {

    private String title, description, image;


    public Products ()
    {

    }


    public Products(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
