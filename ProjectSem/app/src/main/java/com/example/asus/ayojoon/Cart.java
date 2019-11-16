package com.example.asus.ayojoon;

public class Cart {

    String pid,title ,payment ;
    int quantity ;

    public Cart ()
    {

    }

    public Cart(String pid, String title, String payment, int quantity) {
        this.pid = pid;
        this.title = title;
        this.payment = payment;
        this.quantity = quantity;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
