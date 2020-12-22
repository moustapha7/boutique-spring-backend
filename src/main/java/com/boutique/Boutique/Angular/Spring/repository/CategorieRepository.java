package com.boutique.Boutique.Angular.Spring.repository;



import com.boutique.Boutique.Angular.Spring.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
