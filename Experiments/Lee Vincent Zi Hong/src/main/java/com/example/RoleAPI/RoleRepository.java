package com.example.RoleAPI;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository  extends JpaRepository <Role, Long>{

    public Role findRoleById(Long id);

    @Transactional
    public void deleteRoleById(Long id);

}
