package com.example.realestate.controller;

import com.example.realestate.dto.LoginRequestDto;
import com.example.realestate.model.User;
import com.example.realestate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")//cross-origin requests useful for frontend-backend communication

public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to register a new user.
     * @param user The user details from the request body.
     * @return The created user with HTTP status 201 (Created).
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User createdUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    /**
     * Endpoint to authenticate a user and return user info along with a JWT token.
     * @param loginRequestDto Contains email and password.
     * @return A map with user info and token if successful, or 401 Unauthorized if failed.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Map<String, Object> response = userService.authenticateUser(
                loginRequestDto.getEmail(), loginRequestDto.getPassword()
        );

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    /**
     * Endpoint to retrieve all users.
     * @return A list of all users.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    /**
     * Endpoint to update a user by their ID.
     * @param id The ID of the user to update.
     * @param userDetails The new user details.
     * @return The updated user if found, or 404 Not Found if the user doesn't exist.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User userDetails) {
        Optional<User> updatedUser = userService.updateUserById(id, userDetails);
        return updatedUser
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    /**
     * Endpoint to delete a user by their ID.
     * @param id The ID of the user to delete.
     * @return A success message if deleted, or 404 Not Found if the user doesn't exist.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
