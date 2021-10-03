package com.example.RestMODEL;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Restaurant
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="price")
	private double price;
	@Column(name="rating")
	private double rating;
	@Column(name="cusineID")
	private int cuisineID;
	@Column(name="reviewID")
	private int reviewID;
	
	public Restaurant(){
	}
	
	public Restaurant(String id, String name, double price, double rating, int cuisineID, int reviewID)
	{
		UUID idUUID = UUID.randomUUID();
		this.id = idUUID.toString();
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cuisineID = cuisineID;
		this.reviewID = reviewID;
	}

	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public double getPrice() 
	{
		return price;
	}

	public double getRating() 
	{
		return rating;
	}

	public int getCuisineID()
	{
		return cuisineID;
	}

	public int getReviewID() 
	{
		return reviewID;
	}

	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n price: " + getPrice()
        + "rating: " + getRating() + ",/n cuisineID: " + getCuisineID() + "/n reviewID: " + getReviewID();  
    }
}