package onetoone.Users;

import javax.persistence.*;


import java.time.LocalDateTime;



@Entity
public class User {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    //id name cannot be change ?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime timecreated = LocalDateTime.now();


    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }



    public LocalDateTime getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(LocalDateTime timecreated) {
        this.timecreated = timecreated;
    }
// =============================== Getters and Setters for each field ================================== //

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return username;
    }

    public void setName(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }




}
