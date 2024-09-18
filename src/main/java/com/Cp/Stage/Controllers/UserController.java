package com.Cp.Stage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.Models.User;
import com.Cp.Stage.Services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @PutMapping("/activate/{username}")
    public ResponseEntity<User> activateUser(@PathVariable String username) {
        User activatedUser = userService.activateUser(username);
        return ResponseEntity.ok(activatedUser);
    }

    @PutMapping("/deactivate/{username}")
    public ResponseEntity<User> deactivateUser(@PathVariable String username ) {
        User deactivatedUser = userService.deactivateUser(username);
        return ResponseEntity.ok(deactivatedUser);
    }
}
