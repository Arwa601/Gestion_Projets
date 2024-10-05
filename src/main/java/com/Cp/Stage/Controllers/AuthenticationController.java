package com.Cp.Stage.Controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.JwtResponse;
import com.Cp.Stage.DTOs.LoginRequest;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.DTOs.SignupRequest;
import com.Cp.Stage.Models.ERole;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.Role;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.RoleRepo;
import com.Cp.Stage.Repositories.UserRepo;
import com.Cp.Stage.Security.jwt.JwtUtils;
import com.Cp.Stage.Services.UserDetailsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Tag(name = "Authentication", description = "API for authentication operations")
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

  @Operation(summary = "Sign in a user", description = "Authenticate a user and return a JWT token.")
  @PostMapping("/auth/signin")
  public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest authenticationRequest) throws Exception {
      try {
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
          );
      } catch (BadCredentialsException e) {
          throw new Exception("Incorrect username or password", e);
      }

      final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());


      final String jwt = jwtUtil.generateJwtToken(userDetails);

      Set<ERole> roles = new HashSet<>();
      for (GrantedAuthority authority  : userDetails.getAuthorities()) {
        try {
          roles.add(ERole.valueOf(authority.getAuthority()));
        } catch (IllegalArgumentException  e) {
          throw new Exception("Role not recognized: " + authority.getAuthority(), e);
        }
      }
      JwtResponse jwtResponse = new JwtResponse(jwt,userDetails.getUsername(), roles);
      return ResponseEntity.ok(jwtResponse);

  }
  
  @Operation(summary = "Create a user account", description = "Register a new user account with specified roles.")
  @PostMapping("/auth/createUserAccount")
  public ResponseEntity<?> createrUserAccoutn(@Valid @RequestBody SignupRequest signUpRequest) {
    System.out.println(signUpRequest.getUserName());
    if (userRepository.existsByUserName(signUpRequest.getUserName())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    User user = new User(signUpRequest.getUserName(), signUpRequest.getEmail(),
    encoder.encode(signUpRequest.getPassword()));
    user.setNom(signUpRequest.getNom());
    user.setPrenom(signUpRequest.getPrenom());
    user.setDateIntegration(signUpRequest.getDateIntegration());

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "ADMIN":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "MANAGER":
          Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role EmployeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(EmployeeRole);
        }
      });
    }
    user.setRoles(roles);
    System.out.println("Role : " + user.getRoles());

    Profile profile = new Profile();
    profile.setBrefDescription("DEFAULT Desc"); 
    profile.setUser(user);
    user.setProfile(profile);
    try {
    userRepository.save(user);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("An error occurred while saving the user"));
    }

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }



}

