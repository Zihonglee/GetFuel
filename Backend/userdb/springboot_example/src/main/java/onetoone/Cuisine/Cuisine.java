package onetoone.Cuisine;

import io.swagger.annotations.*;
import onetoone.Restaurants.Restaurant;

import javax.persistence.*;

import java.util.List;

@Entity
public class Cuisine
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identification of this cuisine", name = "id", required = true, value = "test id")
	private Long id;
	
	@ApiModelProperty(notes = "The name of the cuisine", name = "cuisineType", required = true, value = "test cuisineType")
	private String cuisineType;

	@OneToMany(targetEntity = Restaurant.class)
	@ApiModelProperty(notes = "The list of restaurant in this specific category of cuisine", name = "List of restaurant", required = true, value = "test restaurant")
	private List<Restaurant> restaurants;

	public Cuisine(String cuisineType) 
	{
		this.cuisineType = cuisineType;
	}

	public Cuisine(){
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


