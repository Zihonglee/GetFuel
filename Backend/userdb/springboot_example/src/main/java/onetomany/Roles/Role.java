package onetomany.Roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import onetomany.Users.User;

import javax.persistence.*;

import java.util.List;



@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleType;

    @OneToMany
    @JsonIgnore
    private List<User> users;



    public Role(String roleType) {
        this.roleType = roleType;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
