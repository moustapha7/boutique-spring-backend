package com.boutique.Boutique.Angular.Spring.controller;

import com.boutique.Boutique.Angular.Spring.exception.ResourceNotFoundException;
import com.boutique.Boutique.Angular.Spring.model.Produit;
import com.boutique.Boutique.Angular.Spring.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/produits")
    public List<Produit> getAllProduitss()
    {
        List<Produit> produits = new ArrayList<>();
        produitRepository.findAll().forEach(produits::add);
        return produits;
    }

    @GetMapping("produits/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable(value = "id") long produitId) throws ResourceNotFoundException
    {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé"));
        return  ResponseEntity.ok().body(produit);

    }


    @PostMapping("/produits")
    public Produit createProduit(@Validated @RequestBody Produit produit)
    {
        Random rand = new Random();
        String codeProd = String.format("Pro_"+rand.nextInt(100));

        produit.setCode(codeProd);

        /*Produit produit1 = new Produit();
        produit1.setCode(produit.getCode());
        produit1.setLibelle(produit.getLibelle());
        produit1.setPrixUnitaire(produit.getPrixUnitaire());
        produit1.setQuantite(produit.getQuantite());
        
        int total = produit.getPrixUnitaire() * produit.getQuantite();

        produit1.setPrixTotal(total);
        produit1.setCategorie(produit.getCategorie());
        produit1.setFournisseur(produit.getFournisseur());*/

        return produitRepository.save(produit);
    }

    @DeleteMapping("produits/{id}")
    public Map<String, Boolean> deleteProduit(@PathVariable(value = "id") long produitId) throws ResourceNotFoundException
    {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé"));

        produitRepository.delete(produit);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    @PutMapping("produits/{id}")
    public  ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") long id, @RequestBody Produit produit)
    {
        Optional<Produit> produitInfo = produitRepository.findById(id);

        if (produitInfo.isPresent())
        {

            Produit produit1 = produitInfo.get();
            produit1.setCode(produit.getCode());
            produit1.setLibelle(produit.getLibelle());
            produit1.setPrixUnitaire(produit.getPrixUnitaire());
             produit1.setCategorie(produit.getCategorie());


            return new ResponseEntity<>(produitRepository.save(produit1), HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/nombreProduits")
    public long getNombreProduits()
    {
        return  produitRepository.count();

    }




}
