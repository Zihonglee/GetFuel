package com.example.jsontest;

public class Restaurant {
    private String name;
    private String id;
    private String price;
    private String rating;
    private String url;

    public Restaurant(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
