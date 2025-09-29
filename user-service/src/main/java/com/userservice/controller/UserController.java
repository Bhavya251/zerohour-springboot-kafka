package com.userservice.controller;

import com.userservice.dto.UserDTO;
import com.userservice.manager.UserManager;
import com.userservice.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserManager userManager;
    
    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable @NotNull String username) {
        return ResponseEntity.ok(userManager.getUser(username));
    }
    
    @GetMapping("/{id}/username")
    public ResponseEntity<String> getUsernameById(@PathVariable Long id) {
        return ResponseEntity.ok(userManager.getUsernameById(id));
    }
    
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody @NotNull User user) {
        boolean created = userManager.createUser(user);
        return created ?
                ResponseEntity.ok().body("User created successfully") :
                ResponseEntity.badRequest().body("User already exists");
    }
    
}
