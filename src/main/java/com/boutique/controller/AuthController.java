package com.boutique.controller;


import com.boutique.message.request.LoginRequest;
import com.boutique.message.request.SignupRequest;
import com.boutique.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
