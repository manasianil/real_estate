package com.example.realestate.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity  // Marks this class as a JPA entity to be mapped to a database table
@Table(name = "users")   // Specifies the table name in the database
public class User {

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-generates the ID using the database identity column
    private Long id;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 3, max=16, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max=16, message = "Name must be at least 6 characters")
    private String name;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Override equals and hashCode for proper comparison
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
