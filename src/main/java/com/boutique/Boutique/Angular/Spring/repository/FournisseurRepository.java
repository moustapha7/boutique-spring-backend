package com.boutique.Boutique.Angular.Spring.repository;


import com.boutique.Boutique.Angular.Spring.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
