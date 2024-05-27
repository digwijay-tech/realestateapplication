package com.project.Online_real_estate_application.controller;

import com.project.Online_real_estate_application.dto.Propertydata;
import com.project.Online_real_estate_application.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<Propertydata> addProperty(@RequestBody Propertydata propertydata) {
        return new ResponseEntity<>(propertyService.createProperty(propertydata), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propertydata> updateProperty(@PathVariable Long id, @RequestBody Propertydata propertydata) {
        Optional<Propertydata> updatedProperty = propertyService.updateProperty(id, propertydata);
        return updatedProperty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Propertydata> partialUpdateProperty(@PathVariable Long id, @RequestBody Propertydata propertydata) {
        Optional<Propertydata> updatedProperty = propertyService.partialUpdateProperty(id, propertydata);
        return updatedProperty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Propertydata>> searchProperties(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        if (location != null && minPrice != null && maxPrice != null) {
            return ResponseEntity.ok(propertyService.getPropertiesByLocationAndPriceRange(location, minPrice, maxPrice));
        } else if (location != null) {
            return ResponseEntity.ok(propertyService.getPropertiesByLocation(location));
        } else if (minPrice != null && maxPrice != null) {
            return ResponseEntity.ok(propertyService.getPropertiesByPriceRange(minPrice, maxPrice));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
