package onetoone.Cuisine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    String createCuisine(@RequestBody Cuisine cuisine){
        if (cuisine == null)
            return failure;
        cuisineRepository.save(cuisine);
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
