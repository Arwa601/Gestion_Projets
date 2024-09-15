package com.Cp.Stage.Controllers;

import java.util.HashSet;
import java.util.Set;

//Le terme "Rest" fait référence à RESTful (Representational State Transfer),
// un style d'architecture utilisé pour créer des services web qui communiquent via HTTP.


// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// //Le terme "Rest" fait référence à RESTful
// // un style d'architecture utilisé pour créer des services web qui communiquent via HTTP
// @CrossOrigin(origins = "http://localhost:4200")
// @RequestMapping("/api")
// public class AuthController {

// }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.LoginRequest;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.DTOs.SignupRequest;
import com.Cp.Stage.Models.ERole;
import com.Cp.Stage.Models.Role;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.RoleRepo;
import com.Cp.Stage.Repositories.UserRepo;
import com.Cp.Stage.Security.jwt.JwtUtils;
import com.Cp.Stage.Services.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
     UserRepo userRepository;

     @Autowired
     RoleRepo roleRepository;

     @Autowired
     PasswordEncoder encoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest authenticationRequest) throws Exception {
        try {
            // Authentification de l'utilisateur
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        // Charger les détails de l'utilisateur après authentification
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        // Générer le token JWT
        final String jwt = jwtUtil.generateJwtToken(userDetails);

        return ResponseEntity.ok("Token : " + jwt);
  

    }
    
    
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    System.out.println(signUpRequest.getUserName());
    if (userRepository.existsByUserName(signUpRequest.getUserName())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    User user = new User(signUpRequest.getUserName(), signUpRequest.getEmail(),
    encoder.encode(signUpRequest.getPassword()));
    // System.out.println(user.getUserName());
    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.EMPLOYEE)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "ADMIN":
          Role adminRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "MANAGER":
          Role modRole = roleRepository.findByName(ERole.MANAGER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role EmployeeRole = roleRepository.findByName(ERole.EMPLOYEE)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(EmployeeRole);
        }
      });
    }
    user.setRoles(roles);
    System.out.println("Role : " + user.getRoles()); 
    try {
    userRepository.save(user);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("An error occurred while saving the user"));
    }

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }



}

