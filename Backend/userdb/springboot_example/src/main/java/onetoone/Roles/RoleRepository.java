<<<<<<< HEAD
//package onetoone.Roles;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//public interface RoleRepository  extends JpaRepository <Role, Long>
//{
//    public Role findRoleById(Long id);
//
//    @Transactional
//    public void deleteRoleById(Long id);
//
//}
=======
package onetoone.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository  extends JpaRepository <Role, Long>
{
    public Role getRoleById(Long id);

    @Transactional
    public void deleteRoleById(Long id);

}
>>>>>>> 137d015e2fe519f21b1f3ce63c21e551f91d1864
