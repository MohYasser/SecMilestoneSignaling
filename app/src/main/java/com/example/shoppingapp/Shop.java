package com.example.shoppingapp;

public class Shop {
    private int id;
    private String shop_name;
    private float lattitude;
    private float longitude;

    public Shop(String shop_name, float lattitude, float longitude){
        this.shop_name = shop_name;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
