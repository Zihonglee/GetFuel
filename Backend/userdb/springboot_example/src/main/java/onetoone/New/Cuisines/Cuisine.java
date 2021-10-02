package New.Cuisines;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String CuisineType;


    public Cuisine(String cuisineType) {
        CuisineType = cuisineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuisineType() {
        return CuisineType;
    }

    public void setCuisineType(String cuisineType) {
        CuisineType = cuisineType;
    }
}
