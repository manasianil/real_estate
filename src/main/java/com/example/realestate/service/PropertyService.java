package com.example.realestate.service;

import com.example.realestate.exception.ResourceNotFoundException;
import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import com.example.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Marks this class as a Spring service component
public class PropertyService {

    @Autowired  // Injects the PropertyRepository dependency
    private PropertyRepository propertyRepository;

    /**
     * Creates a new property and saves it to the database.
     * @param property The property object to be created.
     * @return The saved property object.
     */
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * Retrieves all properties from the database.
     * @return A list of all properties.
     */
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Retrieves a property by its ID.
     * @param id The ID of the property.
     * @return An Optional containing the property if found, or empty if not.
     */
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property with ID " + id + " not found"));
    }

    /**
     * Updates an existing property by its ID.
     * @param id The ID of the property to update.
     * @param propertyDetails The new property details.
     * @return An Optional containing the updated property if found, or empty if not.
     */
    public Optional<Property> updateProperty(Long id, Property propertyDetails) {
        // Find the property by ID and update its fields if present
        return propertyRepository.findById(id).map(property -> {
            property.setTitle(propertyDetails.getTitle());
            property.setDescription(propertyDetails.getDescription());
            property.setLocation(propertyDetails.getLocation());
            property.setPrice(propertyDetails.getPrice());
            property.setImage(propertyDetails.getImage());
            return propertyRepository.save(property);  // Save the updated property
        });
    }

    /**
     * Deletes a property by its ID.
     * @param id The ID of the property to delete.
     * @return true if the property was deleted; false if it was not found.
     */
    public boolean deletePropertyById(Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;  // Return false if the property does not exist

    }


    /**
     * Fetch properties based on type and status.
     *
     * @param type   the type of property (e.g., "Apartment", "House")
     * @param status the status of property (e.g., "For Sale", "For Rent")
     * @return a list of properties matching the criteria
     */
    // Fetch filtered properties with exception handling
    public List<Property> searchProperties(String type, String status) {
        if (type != null && status != null) {
            return propertyRepository.findByTypeAndStatus(type, status);
        } else if (type != null) {
            return propertyRepository.findByType(type);
        } else if (status != null) {
            return propertyRepository.findByStatus(status);
        } else {
            return propertyRepository.findAll();
        }
    }

}
