package com.example.shoppingapp;

public class Product {
    private int id;
    private final String product_name;
    private String description;
    private String image_URL;

    public Product (String product_name, String description, String image_URL){

        this.product_name = product_name;
        this.description = description;
        this.image_URL = image_URL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }
}