package onetoone.Roles;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRespository  extends JpaRepository <Role,Long>{

    Role findById(int id);

    @Transactional
    void deleteById(int id);

}
