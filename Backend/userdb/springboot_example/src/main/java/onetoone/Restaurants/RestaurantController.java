package onetoone.Restaurants;

import java.util.List;


import onetoone.Cuisine.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/restaurants")
    List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }

    @GetMapping(path = "/restaurants/{id}")
    Restaurant getRestaurantById( @PathVariable int id){
        return restaurantRepository.findById(id);
    }

    @PostMapping(path = "/restaurants")
    String createRestaurant(@RequestBody Restaurant restaurant){
        if (restaurant == null)
            return failure;
        restaurantRepository.save(restaurant);
        return success;
    }

    @PutMapping("/restaurants/{id}")
    Restaurant updateRestaurant(@PathVariable int id, @RequestBody Restaurant request){
        Restaurant restaurant = restaurantRepository.findById(id);
        if(restaurant == null)
            return null;
        restaurantRepository.save(request);
        return restaurantRepository.findById(id);
    }


    @DeleteMapping(path = "/restaurants/{id}")
    String deleteRestaurant(@PathVariable int id){
        restaurantRepository.deleteById(id);
        return success;
    }

}
