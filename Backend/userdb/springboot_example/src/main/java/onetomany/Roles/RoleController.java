package onetomany.Roles;


import java.util.List;


import onetomany.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    RoleRespository roleRespository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/roles")
    List<Role> getAllRole(){
        return roleRespository.findAll();
    }

    @GetMapping(path = "/roles/{id}")
    Role getRoleById( @PathVariable int id){
        return roleRespository.findById(id);
    }

    @PostMapping(path = "/roles")
    String createRole(@RequestBody Role role){
        if (role == null)
            return failure;
        roleRespository.save(role);
        return success;
    }

    @PutMapping("/roles/{id}")
    Role updateRole(@PathVariable int id, @RequestBody Role request){
        Role role = roleRespository.findById(id);
        if(role == null)
            return null;
        roleRespository.save(request);
        return roleRespository.findById(id);
    }


    @DeleteMapping(path = "/roles/{id}")
    String deleteRole(@PathVariable int id){
        roleRespository.deleteById(id);
        return success;
    }
}
