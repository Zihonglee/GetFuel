package onetoone.Restaurants;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RestaurantRepository extends JpaRepository <Restaurant,Long>{

    Restaurant findById(int id);

    @Transactional
    void deleteById(int id);

}
