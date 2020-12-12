package com.example.shoppingapp;

public class Link {
    private int id;
    private int shop_id;
    private int product_id;
    private double price;
    private String sp_offers;

    public Link(int shop_id, int product_id, double price, String sp_offers){
        this.shop_id = shop_id;
        this.product_id = product_id;
        this.price = price;
        this.sp_offers = sp_offers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSp_offers() {
        return sp_offers;
    }

    public void setSp_offers(String sp_offers) {
        this.sp_offers = sp_offers;
    }
}
