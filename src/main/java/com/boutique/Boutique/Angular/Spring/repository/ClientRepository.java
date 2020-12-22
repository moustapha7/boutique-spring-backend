package com.boutique.Boutique.Angular.Spring.repository;




import com.boutique.Boutique.Angular.Spring.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
