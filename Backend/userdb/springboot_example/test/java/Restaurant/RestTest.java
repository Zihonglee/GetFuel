package Restaurant;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	}

//	@Test
//	public void getAllRestaurantTest() 
//	{
//		List<Restaurant> list = new ArrayList<Restaurant>();
//		Cuisine cs = new Cuisine("Asian");
//		Restaurant rest1 = new Restaurant("US Pizza", "$7.00", "8.5", cs, "https://uspizzaco.net/");
//		Restaurant rest2 = new Restaurant("sze chuan", "$11.00", "9.0", cs, "https://www.szechuanhouseames.com/");
//		Restaurant rest3 = new Restaurant("potbelly", "$6.00", "8.0", cs, "https://www.potbelly.com/");
//
//		list.add(rest1);
//		list.add(rest2);
//		list.add(rest3);
//
//		when(restService.getAllRestaurant()).thenReturn(list);
//
//		List<Restaurant> acctList = restService.getAllRestaurant();
//
//		assertEquals(3, acctList.size());
//	}

}
