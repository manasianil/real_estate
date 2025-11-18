package com.example.realestate.repository;

import com.example.realestate.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByTypeAndStatus(String type, String status);

    List<Property> findByType(String type);

    List<Property> findByStatus(String status);
}
