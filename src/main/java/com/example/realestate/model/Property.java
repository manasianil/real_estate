package com.example.realestate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Entity  // Marks this class as a JPA entity to be mapped to a database table
@Table(name = "property")   // Specifies the table name in the database
public class Property {

    @Id   // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-generates the ID using the database identity column
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max=30, message = "Title must be at least 3 characters/ title exceed 30 characters")
    private String title;

    @Size(max = 1000, message = "Description can be up to 1000 characters")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(min = 2, max=30, message = "Location must be at least 3 characters")
    private String location;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Image URL is required")
    private String image;

    @NotBlank(message = "Type is required")
    @Size(min = 2, max=16, message = "Type must be at least 3 characters")
    private String type;

    @NotBlank(message = "Status is required")
    @Size(min = 2, max=16, message = "Status must be at least 3 characters")
    private String status;

    // Default constructor
    public Property() {
    }

    // Parameterized constructor
    public Property(Long id, String title, String description, String location, Double price, String image, String type, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.image = image;
        this.type = type;
        this.status = status;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //  Override equals and hashCode for proper comparison
    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
