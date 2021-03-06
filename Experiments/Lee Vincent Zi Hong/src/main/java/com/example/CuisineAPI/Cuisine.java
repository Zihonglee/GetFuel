package com.example.CuisineAPI;

import com.example.RestAPI.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Cuisine
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cuisineType;

    @OneToMany(targetEntity = Restaurant.class)
    @JsonIgnore
    private List<Restaurant> restaurants;

    public Cuisine() {
    }
    
    public Cuisine(String cuisineType) 
    {
        this.cuisineType = cuisineType;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCuisineType()
    {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) 
    {
        this.cuisineType = cuisineType;
    }

    public List<Restaurant> getRestaurants()
    {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) 
    {
        this.restaurants = restaurants;
    }
}


