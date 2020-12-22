package com.boutique.Boutique.Angular.Spring.controller;



import com.boutique.Boutique.Angular.Spring.exception.ResourceNotFoundException;
import com.boutique.Boutique.Angular.Spring.model.Fournisseur;
import com.boutique.Boutique.Angular.Spring.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FournisseurController {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping("/fournisseurs")
    public List<Fournisseur> getAllFournisseurs()
    {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurRepository.findAll().forEach(fournisseurs::add);
        return fournisseurs;
    }

    @GetMapping("fournisseurs/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(value = "id") long fournisseurId) throws ResourceNotFoundException
    {
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new ResourceNotFoundException("fournisseur non trouvé"));
        return  ResponseEntity.ok().body(fournisseur);

    }


    @PostMapping("/fournisseurs")
    public Fournisseur createFournisseur(@Validated @RequestBody Fournisseur fournisseur)
    {
        Random rand = new Random();
        String codeFour = String.format("Four_"+rand.nextInt(100));

        fournisseur.setCode(codeFour);
        return fournisseurRepository.save(fournisseur);
    }

    @DeleteMapping("fournisseurs/{id}")
    public Map<String, Boolean> deleteFournisseur(@PathVariable(value = "id") long fournisseurId) throws ResourceNotFoundException
    {
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new ResourceNotFoundException("fournisseur non trouvé"));

        fournisseurRepository.delete(fournisseur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    @PutMapping("fournisseurs/{id}")
    public  ResponseEntity<Fournisseur> updateFournisseur(@PathVariable(value = "id") long id, @RequestBody Fournisseur fournisseur)
    {
        Optional<Fournisseur> fournisseurInfo = fournisseurRepository.findById(id);

        if (fournisseurInfo.isPresent())
        {
            Fournisseur fournisseur1= fournisseurInfo.get();
            fournisseur1.setCode(fournisseur.getCode());
            fournisseur1.setAdresse(fournisseur.getAdresse());
            fournisseur1.setTel(fournisseur.getTel());
            fournisseur1.setEmail(fournisseur.getEmail());
            fournisseur1.setPrenom(fournisseur.getPrenom());
            fournisseur1.setNom(fournisseur.getNom());


            return new ResponseEntity<>(fournisseurRepository.save(fournisseur1), HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/nombreFournisseurs")
    public long getNombreFours()
    {
        return  fournisseurRepository.count();

    }
}
