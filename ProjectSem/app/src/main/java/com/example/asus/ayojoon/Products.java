package com.example.asus.ayojoon;

import com.example.asus.ayojoon.ViewHolder.ProductViewHolder;

public class Products {

    private String title, description, image,payment,pid;


    public Products ()
    {

    }

    public Products(String title, String description, String image, String payment, String pid) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.payment = payment;
        this.pid = pid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
