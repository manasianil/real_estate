package com.example.realestate.controller;

import com.example.realestate.exception.ResourceNotFoundException;
import com.example.realestate.model.Property;
import com.example.realestate.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins="http://localhost:3000") //cross-origin requests useful for frontend-backend communication

public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * Create a new property.
     * @param property The property details from the request body.
     * @return The created property with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Property> createProperty(@Valid @RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    /**
     * Retrieve all properties.
     * @return A list of all properties with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<?> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        if (properties.isEmpty()) {
            return ResponseEntity.ok("No properties found in the database.");
        }
        return ResponseEntity.ok(properties);
    }


    /**
     * Retrieve a property by its ID.
     * @param id The ID of the property.
     * @return The property if found, or 404 Not Found if it doesn't exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long id) {
        try {
            Property property = propertyService.getPropertyById(id);
            return ResponseEntity.ok(property);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    /**
     * Update a property by its ID.
     * @param id The ID of the property to update.
     * @param propertyDetails The updated property details.
     * @return The updated property if successful, or 404 Not Found if the property doesn't exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id,@Valid @RequestBody Property propertyDetails) {
        return propertyService.updateProperty(id, propertyDetails)
                .map(updatedProperty -> new ResponseEntity<>(updatedProperty, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a property by its ID.
     * @param id The ID of the property to delete.
     * @return A success message if deleted, or 404 Not Found if the property doesn't exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        boolean deleted = propertyService.deletePropertyById(id);
        if (deleted) {
            return ResponseEntity.ok("Property deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
        }
    }



    /**
     * Search properties by type and optionally by status.
     * @param type The type of property (e.g., "apartment", "villa").
     * @param status (Optional) The status of the property (e.g., "available", "sold").
     * @return A list of matching properties with HTTP status 200 (OK).
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Property>> searchProperties(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status) {

        List<Property> properties = propertyService.searchProperties(type, status);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

}
