package com.example.realestate.service;

import com.example.realestate.model.User;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service  // Marks this class as a Spring service component
public class UserService {

    @Autowired   // Injects the PropertyRepository dependency
    private UserRepository userRepository;

    @Autowired  // Injects the JwtUtil dependency to handle JWT token generation
    private JwtUtil jwtUtil;

    /**
     * Registers a new user by saving their details to the database.
     * @param user The user object containing registration details.
     * @return The saved user object.
     */
    public User registerUser(User user) {
        return userRepository.save(user);
    }


    /**
     * Authenticates a user using email and password.
     * If authentication is successful, generates a JWT token and returns user info.
     * @param email The user's email.
     * @param password The user's password.
     * @return A map containing user details and JWT token if authentication succeeds; otherwise, null.
     */
    public Map<String, Object> authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            String token = jwtUtil.generateToken(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("name", user.getName());
            response.put("token", token);

            return response;
        }
        return null;
    }


    /**
     * Retrieves all users from the database.
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * Updates a user's details by their ID.
     * @param id The ID of the user to update.
     * @param userDetails The new user details.
     * @return An Optional containing the updated user if found; otherwise, an empty Optional.
     */
    public Optional<User> updateUserById(Long id, User userDetails) {
        // Find user by ID and update fields if present
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user); // Save updated user
        });
    }


    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     * @return true if the user was deleted; false if the user was not found.
     */
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;  // Return false if user does not exist
    }

}
