package com.boutique.Boutique.Angular.Spring.repository;




import com.boutique.Boutique.Angular.Spring.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
