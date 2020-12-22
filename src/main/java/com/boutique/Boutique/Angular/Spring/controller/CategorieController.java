package com.boutique.Boutique.Angular.Spring.controller;



import com.boutique.Boutique.Angular.Spring.exception.ResourceNotFoundException;
import com.boutique.Boutique.Angular.Spring.model.Categorie;
import com.boutique.Boutique.Angular.Spring.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;


    @GetMapping("/categories")
    public List<Categorie> getAllCategories()
    {
        List<Categorie> categories = new ArrayList<>();
        categorieRepository.findAll().forEach(categories::add);
        return categories;
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable(value = "id") long categorieId) throws ResourceNotFoundException
    {
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie non trouvé"));
        return  ResponseEntity.ok().body(categorie);

    }


    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public Categorie createCategorie(@Validated @RequestBody Categorie categorie)
    {
        Random rand = new Random();
        String codeCat = String.format("Cat_"+rand.nextInt(100));
        categorie.setCode(codeCat);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication(). getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal). getUsername();
        } else {
             username = principal. toString();
        }

        categorie.setUsername(username);
        return categorieRepository.save(categorie);
    }

    @DeleteMapping("categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteCategorie(@PathVariable(value = "id") long categorieId) throws ResourceNotFoundException
    {
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie non trouvé"));

            categorieRepository.delete(categorie);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;

    }

    @PutMapping("categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<Categorie> updateCategorie(@PathVariable(value = "id") long id, @RequestBody Categorie categorie)
    {
        Optional<Categorie> categorieInfo = categorieRepository.findById(id);

        if (categorieInfo.isPresent())
        {
            Categorie categorie1= categorieInfo.get();
            categorie1.setCode(categorie.getCode());
            categorie1.setLibelle(categorie.getLibelle());

            categorie.setUsername(categorie.getUsername());

            return new ResponseEntity<>(categorieRepository.save(categorie1), HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/nombreCategories")
    public long getNombreCategories()
    {
        return  categorieRepository.count();

    }

}
