package com.boutique.Boutique.Angular.Spring.controller;


import com.boutique.Boutique.Angular.Spring.message.request.LoginRequest;
import com.boutique.Boutique.Angular.Spring.message.request.SignupRequest;
import com.boutique.Boutique.Angular.Spring.message.response.JwtResponse;
import com.boutique.Boutique.Angular.Spring.message.response.MessageResponse;
import com.boutique.Boutique.Angular.Spring.model.ERole;
import com.boutique.Boutique.Angular.Spring.model.Role;
import com.boutique.Boutique.Angular.Spring.model.User;
import com.boutique.Boutique.Angular.Spring.repository.RoleRepository;
import com.boutique.Boutique.Angular.Spring.repository.UserRepository;
import com.boutique.Boutique.Angular.Spring.security.jwt.JwtUtils;
import com.boutique.Boutique.Angular.Spring.security.services.UserDetailsImpl;
import com.boutique.Boutique.Angular.Spring.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
     UtilisateurService utilisateurService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.debug("Authentification {} :", loginRequest);

        return ResponseEntity.ok(utilisateurService.authenticateUser(loginRequest).getBody());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        return ResponseEntity.ok( utilisateurService.registerUser(signUpRequest));
    }

    @PostMapping("/signupClient")
    public ResponseEntity<?> registerClient(@Valid @RequestBody SignupRequest signUpRequest) {


        return ResponseEntity.ok(utilisateurService.registerClient(signUpRequest));
    }




}
