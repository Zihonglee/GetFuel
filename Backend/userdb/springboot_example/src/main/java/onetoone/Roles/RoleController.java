<<<<<<< HEAD
//package onetoone.Roles;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/roles")
//public class RoleController
//{
//    @Autowired
//    public RoleRepository roleRespository;
//
//    @GetMapping
//    public List<Role> getAllRole()
//    {
//        return roleRespository.findAll();
//    }
//
//    @PostMapping
//    public String createRole(@RequestBody Role role)
//    {
//        if (role == null)
//        {
//        	return "Failure";
//        }
//        else
//        {
//        	roleRespository.save(role);
//        	return "Success";
//        }
//    }
//
//    @GetMapping("/{id}")
//    public Role getRoleById( @PathVariable Long id)
//    {
//        return roleRespository.findRoleById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Role updateRole(@PathVariable Long id, @RequestBody Role request)
//    {
//        Role role = roleRespository.findRoleById(id);
//        if(role == null)
//        {
//        	return null;
//        }
//        else
//        {
//        	roleRespository.save(request);
//        	return roleRespository.findRoleById(id);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteRole(@PathVariable Long id)
//    {
//        roleRespository.deleteRoleById(id);
//        return "Role deleted";
//    }
//}
=======
package onetoone.Roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleController
{
    @Autowired
    public RoleRepository roleRespository;
    
    @GetMapping
    public List<Role> getAllRole()
    {
        return roleRespository.findAll();
    }

    @PostMapping
    public String createRole(@RequestBody Role role)
    {
        if (role == null)
        {
        	return "Failure";
        }
        else
        {
        	roleRespository.save(role);
        	return "Success";
        }
    }

    @GetMapping("/{id}")
    public Role getRoleById( @PathVariable Long id)
    {
        return roleRespository.getRoleById(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role request)
    {
        Role role = roleRespository.getRoleById(id);
        if(role == null || request == null)
        {
        	return null;
        }
        else
        {
        	roleRespository.getRoleById(id).setRoleType(request.getRoleType());
        	roleRespository.getRoleById(id).setUsers(request.getUsers());
        	return roleRespository.getRoleById(id);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id)
    {
        roleRespository.deleteRoleById(id);
        return "Role deleted";
    }
}
>>>>>>> 137d015e2fe519f21b1f3ce63c21e551f91d1864
