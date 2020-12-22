package com.boutique.Boutique.Angular.Spring.repository;


import com.boutique.Boutique.Angular.Spring.model.ERole;
import com.boutique.Boutique.Angular.Spring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}