package com.example.RestAPI;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.CuisineAPI.Cuisine;
import com.example.ReviewsAPI.Review;


@Entity
public class Restaurant
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String price;
	private String rating;
	
    @ManyToOne(targetEntity = Cuisine.class)
    private Cuisine cuisine;
    
    @OneToMany(mappedBy = "id")
    private List<Review> reviews;
    
	public Restaurant(){
	}
	
	public Restaurant(String name, String price, String rating, Cuisine cuisine)
	{
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cuisine = cuisine;
		reviews = new ArrayList<>();
	}

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

    public void setName(String restName) 
    {
        this.name = restName;
    }

	public String getPrice() 
	{
		return price;
	}

    public void setPrice(String price) {
        this.price = price;
    }

	public String getRating() 
	{
		return rating;
	}
	

    public void setRating(String rating) {
        this.rating = rating;
    }

	public Cuisine getCuisine()
	{
		return cuisine;
	}

	public List<Review> getReviews() 
	{
		return reviews;
	}

    public void setReviews(List<Review> reviews) 
    {
        this.reviews = reviews;
    }

    public void addReviews(Review review)
    {
        this.reviews.add(review);
    }

	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n price: " + getPrice()
        + "rating: " + getRating() + ",/n cuisineID: " + getCuisine() + "/n reviewID: " + getReviews();  
    }
}
