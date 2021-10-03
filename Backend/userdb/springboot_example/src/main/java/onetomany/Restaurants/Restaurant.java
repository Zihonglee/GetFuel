package onetomany.Restaurants;

import onetomany.Cuisine.Cuisine;
import onetomany.Reviews.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Restaurant {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String restName;
    private String price;
    private String rating;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @OneToMany
    @JoinColumn(name ="review_id")
    private List<Review> reviews;


    public Restaurant(String restName, String price, String rating, Cuisine cuisine) {
        this.restName = restName;
        this.price = price;
        this.rating = rating;
        this.cuisine = cuisine;
        reviews = new ArrayList<>();

    }


    public Restaurant() { reviews = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReviews(Review review){
        this.reviews.add(review);
    }


}
