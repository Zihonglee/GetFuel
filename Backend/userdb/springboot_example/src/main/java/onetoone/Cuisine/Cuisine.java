package onetoone.Cuisine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Cuisine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cuisineType;



    public Cuisine(String cuisineType) {
        this.cuisineType = cuisineType;

    }

    public Cuisine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

}

// =============================== Getters and Setters for each field ================================== //

