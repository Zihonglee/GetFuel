package Cuisine;

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
import onetoone.Cuisine.CuisineController;
import onetoone.Cuisine.CuisineRepository;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantController;
import onetoone.Restaurants.RestaurantRepository;

public class CuisineTest
{
	@InjectMocks
	CuisineController cuisineService;
	
	@InjectMocks
	RestaurantController restService;

	@Mock
	CuisineRepository repo;
	
	@Mock
	RestaurantRepository restRepo;

	@SuppressWarnings("deprecation") //not needed
	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getCuisineTest()
	{
		assertNull(repo.getCuisineById(Long.valueOf(1)));
		when(repo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Japanese"));

		Cuisine cuisine = cuisineService.getCuisineById(Long.valueOf(1));

		assertEquals("Japanese", cuisine.getCuisineType());
		
		verify(repo, times(2)).getCuisineById(anyLong());
	}
	
	@Test
	public void deleteCuisineTest()
	{
		verify(repo, never()).deleteCuisineById(anyLong());
		when(repo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Asian"));
		doNothing().when(repo).deleteCuisineById(Long.valueOf(1));
		List<Restaurant> emptylist = new ArrayList<>();
		assertEquals(emptylist, cuisineService.getAllCuisine());
		
		verify(repo, atMost(1)).getCuisineById(anyLong()); //since there is only one line of getrestaurantbyid that i mocked
		verify(repo, atMost(1)).deleteCuisineById(anyLong());
	}
	
	@Test
	public void deleteCuisineRestaurant() //delete cuisine, then all restaurants gone and delete any restaurant, the cuisine will still be there
	{
		//delete cuisine then all restaurants gone
		List<Restaurant> emptylist = new ArrayList<>();
		when(repo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Asian"));
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", null, "https://www.thaikitchenames.com/"));
		restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(1));
		doNothing().when(repo).deleteCuisineById(Long.valueOf(1));
		assertEquals(emptylist, restService.getAllRestaurant());		
		assertEquals(emptylist, cuisineService.getAllCuisine());

		//delete any restaurant then the restaurant will be gone in the cuisine but not the cuisine will not be deleted
		when(repo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Japanese"));
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", null, "https://www.thaikitchenames.com/"));
		restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(1));
		restService.deleteRestaurantById(Long.valueOf(1));
		assertEquals("Japanese", repo.getCuisineById(Long.valueOf(1)).getCuisineType());
		assertEquals(emptylist, repo.getCuisineById(Long.valueOf(1)).getRestaurants());
		
		//verify need to do
	}

	@Test
	public void getAllCuisineTest() 
	{
		List<Cuisine> list = new ArrayList<Cuisine>();
		
		Cuisine cs1 = new Cuisine("Japanese");
		Cuisine cs2 = new Cuisine("Asian");
		Cuisine cs3 = new Cuisine("Korean");

		list.add(cs1);
		list.add(cs2);
		list.add(cs3);

		when(cuisineService.getAllCuisine()).thenReturn(list);

		List<Cuisine> restList = cuisineService.getAllCuisine();

		assertEquals(3, restList.size());
		
		verify(repo, never()).getCuisineById(anyLong());
	}
	
	@Test
	public void createCuisineTest()
	{		
		Cuisine cs = null;
		String cuisineTest = cuisineService.createCuisine(cs);
		assertEquals("failure", cuisineTest);

		cs = new Cuisine("Japanese");
		cuisineTest = cuisineService.createCuisine(cs);
		assertEquals("success", cuisineTest);		
	}
	
	@Test
	public void updateTest()
	{
		Cuisine test = new Cuisine("Asian");
		when(repo.getCuisineById(Long.valueOf(2))).thenReturn(test);
		assertEquals(test, cuisineService.getCuisineById(Long.valueOf(2)));
		
		Cuisine newtest = new Cuisine("Japanese");
		cuisineService.updateCuisine(Long.valueOf(2), newtest);
		assertEquals(newtest.getCuisineType(), repo.getCuisineById(Long.valueOf(2)).getCuisineType());	
		
		verify(repo, times(3)).getCuisineById(anyLong());//running in class method as well
	}
}
