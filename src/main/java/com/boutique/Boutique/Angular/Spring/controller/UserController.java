package com.boutique.Boutique.Angular.Spring.controller;



import com.boutique.Boutique.Angular.Spring.exception.ResourceNotFoundException;
import com.boutique.Boutique.Angular.Spring.model.ERole;
import com.boutique.Boutique.Angular.Spring.model.Role;
import com.boutique.Boutique.Angular.Spring.model.User;
import com.boutique.Boutique.Angular.Spring.repository.RoleRepository;
import com.boutique.Boutique.Angular.Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/nombreUsers")
    public long getNombreUsers()
    {
        return  userRepository.count();

    }


    @GetMapping("/listUsers")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<User> getAllUsers()
    {
        return  userRepository.findAll();

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User>  getUserById(@PathVariable long id) throws ResourceNotFoundException
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user non trouvé"));
        return  ResponseEntity.ok().body(user);


    }

    @GetMapping("/listRoles")
    public Optional<Role> geNameRole(ERole name)
    {
        return  roleRepository.findByName(name);
    }

   /* @GetMapping("/users/{id}")
    public ResponseEntity<User>  getUserByUsernameAndRole(@PathVariable long id) throws ResourceNotFoundException
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user non trouvé"));
        return  ResponseEntity.ok().body(user);


    }*/

}
