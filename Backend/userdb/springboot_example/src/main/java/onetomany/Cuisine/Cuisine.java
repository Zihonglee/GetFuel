package onetomany.Cuisine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import onetomany.Restaurants.Restaurant;
import onetomany.Restaurants.RestaurantRepository;


import javax.persistence.*;
import java.util.List;


@Entity
public class Cuisine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cuisineType;

    @OneToMany
    @JsonIgnore
    private List<Restaurant> restaurants;


    public Cuisine(String cuisineType) {
        this.cuisineType = cuisineType;

    }

    public Cuisine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}


