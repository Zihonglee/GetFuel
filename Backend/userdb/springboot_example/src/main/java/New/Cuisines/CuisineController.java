package New.Cuisines;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuisineController {

    @Autowired
    CuisineRepository cuisineRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/cuisines")
    List<Cuisine> getAllCuisine(){
        return cuisineRepository.findAll();
    }

    @GetMapping(path = "/cuisines/{id}")
    Cuisine getCuisineById( @PathVariable int id){
        return cuisineRepository.findById(id);
    }

    @PostMapping(path = "/cuisines")
    String createCuisine(@RequestBody Cuisine review){
        if (review == null)
            return failure;
        cuisineRepository.save(review);
        return success;
    }

    @PutMapping("/cuisines/{id}")
    Cuisine updateCuisine(@PathVariable int id, @RequestBody Cuisine request){
        Cuisine cuisine = cuisineRepository.findById(id);
        if(cuisine == null)
            return null;
        cuisineRepository.save(request);
        return cuisineRepository.findById(id);
    }


    @DeleteMapping(path = "/cuisines/{id}")
    String deleteCuisine(@PathVariable int id){
        cuisineRepository.deleteById(id);
        return success;
    }
}
