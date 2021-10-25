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

public class CuisineTest
{
	@InjectMocks
	CuisineController cuisineService;

	@Mock
	CuisineRepository repo;

	@SuppressWarnings("deprecation") //not needed
	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRestaurantTest()
	{
		assertNull(repo.getCuisineById(Long.valueOf(1)));
		when(repo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Japanese"));

		Cuisine cuisine = cuisineService.getCuisineById(Long.valueOf(1));

		assertEquals("Japanese", cuisine.getCuisineType());
		
		verify(repo, times(2)).getCuisineById(anyLong());
	}
	
	@Test
	public void deleteRestaurantTest()
	{
		verify(repo, never()).deleteCuisineById(anyLong());
		when(repo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Asian"));
		
		doNothing().when(repo).deleteCuisineById(Long.valueOf(1));;
		String cuisine = cuisineService.deleteCuisine(Long.valueOf(1));
		
		assertEquals("Deleted successfully", cuisine);
		
		verify(repo, atMost(1)).getCuisineById(anyLong()); //since there is only one line of getrestaurantbyid that i mocked
		verify(repo, atMost(1)).deleteCuisineById(anyLong());
	}

	@Test
	public void getAllRestaurantTest() 
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
}
