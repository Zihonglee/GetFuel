package Restaurant;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import onetoone.Cuisine.Cuisine;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantController;
import onetoone.Restaurants.RestaurantRepository;

public class RestTest
{
	@InjectMocks
	RestaurantController restService;

	@Mock
	RestaurantRepository repo;

	@SuppressWarnings("deprecation") //not needed
	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRestaurantTest()
	{
		assertNull(repo.getRestaurantById(Long.valueOf(1)));
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", new Cuisine("Japanese"), "https://www.google.com/search?client=firefox-b-1-d&q=pizza"));

		Restaurant rest = restService.getRestaurantById(Long.valueOf(1));

		assertEquals("Pizza", rest.getName());
		assertEquals("$10.00", rest.getPrice());
		assertEquals("7.5", rest.getRating());
		assertEquals("https://www.google.com/search?client=firefox-b-1-d&q=pizza", rest.getUrl());
		
		verify(repo, times(2)).getRestaurantById(Long.valueOf(1));
		verify(repo, times(2)).getRestaurantById(anyLong());
	}
	
	@Test
	public void deleteRestaurantTest()
	{
		verify(repo, never()).deleteRestaurantById(anyLong());
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", new Cuisine("Japanese"), "https://www.google.com/search?client=firefox-b-1-d&q=pizza"));
		
		doNothing().when(repo).deleteRestaurantById(Long.valueOf(1));
		String rest = restService.deleteRestaurantById(Long.valueOf(1));
		
		assertEquals("Restaurant deleted", rest);
		
		verify(repo, atMost(1)).getRestaurantById(anyLong()); //since there is only one line of getrestaurantbyid that i mocked
		verify(repo, atMost(1)).deleteRestaurantById(anyLong());
	}

	@Test
	public void getAllRestaurantTest() 
	{
		List<Restaurant> list = new ArrayList<Restaurant>();
		
		Cuisine cs = new Cuisine("Asian");
		Restaurant rest1 = new Restaurant("US Pizza", "$7.00", "8.5", cs, "https://uspizzaco.net/");
		Restaurant rest2 = new Restaurant("sze chuan", "$11.00", "9.0", cs, "https://www.szechuanhouseames.com/");
		Restaurant rest3 = new Restaurant("potbelly", "$6.00", "8.0", cs, "https://www.potbelly.com/");

		list.add(rest1);
		list.add(rest2);
		list.add(rest3);

		when(restService.getAllRestaurant()).thenReturn(list);

		List<Restaurant> restList = restService.getAllRestaurant();

		assertEquals(3, restList.size());
		
		verify(repo, never()).getRestaurantById(anyLong());
	}

}