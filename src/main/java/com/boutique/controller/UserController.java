package com.boutique.controller;


import com.boutique.exception.ResourceNotFoundException;
import com.boutique.model.User;
import com.boutique.repository.RoleRepository;
import com.boutique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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




}
