package com.project.Online_real_estate_application.service.impl;

import com.project.Online_real_estate_application.dto.Propertydata;
import com.project.Online_real_estate_application.entity.PropertyEntity;
import com.project.Online_real_estate_application.mapper.PropertyMapper;
import com.project.Online_real_estate_application.repository.PropertyRepository;
import com.project.Online_real_estate_application.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceimpl implements PropertyService {

    private PropertyRepository propertyRepository;
    @Autowired
    public PropertyServiceimpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Propertydata createProperty(Propertydata propertydata) {
        PropertyEntity propertyEntity = PropertyMapper.mapToAccount(propertydata);
        PropertyEntity savedUser = propertyRepository.save(propertyEntity);
        return PropertyMapper.mapToAccountData(savedUser);
    }

    @Override
    public Propertydata getAccountById(Long id) {
        PropertyEntity propertyEntity = propertyRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        return PropertyMapper.mapToAccountData(propertyEntity);
    }

    @Override
    public Optional<Propertydata> updateProperty(Long id, Propertydata propertydata) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(id);
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyEntity.setTitle(propertydata.getTitle());
            propertyEntity.setType(propertydata.getType());
            propertyEntity.setBhk(propertydata.getBhk());
            propertyEntity.setDescription(propertydata.getDescription());
            propertyEntity.setArea(propertydata.getArea());
            propertyEntity.setCity(propertydata.getCity());
            propertyEntity.setLocationUrl(propertyEntity.getLocationUrl());
            propertyEntity.setPrice(propertydata.getPrice());
            propertyEntity.setImageUrl(propertydata.getImageUrl());
            PropertyEntity updatedProperty = propertyRepository.save(propertyEntity);
            return Optional.of(PropertyMapper.mapToAccountData(updatedProperty));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Propertydata> partialUpdateProperty(Long id, Propertydata propertydata) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(id);
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();

            if (propertydata.getTitle() != null) {
                propertyEntity.setTitle(propertydata.getTitle());
            }
            if (propertydata.getType() != null){
                propertyEntity.setType(propertydata.getType());
            }
            if (propertydata.getBhk() != 0){
                propertyEntity.setBhk(propertydata.getBhk());
            }
            if (propertydata.getDescription() != null) {
                propertyEntity.setDescription(propertydata.getDescription());
            }
            if (propertydata.getArea() != null) {
                propertyEntity.setArea(propertydata.getArea());
            }
            if (propertydata.getCity() != null){
                propertyEntity.setCity(propertydata.getCity());
            }
            if (propertydata.getLocationUrl() != null){
                propertyEntity.setLocationUrl(propertydata.getLocationUrl());
            }
            if (propertydata.getPrice() != 0) {
                propertyEntity.setPrice(propertydata.getPrice());
            }

            if (propertydata.getImageUrl() != null) {
                propertyEntity.setImageUrl(propertydata.getImageUrl());
            }
            PropertyEntity updatedProperty = propertyRepository.save(propertyEntity);
            return Optional.of(PropertyMapper.mapToAccountData(updatedProperty));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<Propertydata> getPropertiesByCity(String city) {
        List<PropertyEntity> properties = propertyRepository.findByCityContainingIgnoreCase(city);
        List<Propertydata> propertyDataList = new ArrayList<>();
        for (PropertyEntity property : properties) {
            propertyDataList.add(PropertyMapper.mapToAccountData(property));
        }
        return propertyDataList;
    }

    @Override
    public List<Propertydata> getPropertiesByPriceRange(double minPrice, double maxPrice) {
        List<PropertyEntity> properties = propertyRepository.findByPriceBetween(minPrice, maxPrice);
        List<Propertydata> propertyDataList = new ArrayList<>();
        for (PropertyEntity property : properties) {
            propertyDataList.add(PropertyMapper.mapToAccountData(property));
        }
        return propertyDataList;
    }

    @Override
    public List<Propertydata> getPropertiesByCityAndPriceRange(String city, double minPrice, double maxPrice) {
        List<PropertyEntity> properties = propertyRepository.findByCityContainingIgnoreCaseAndPriceBetween(city, minPrice, maxPrice);
        List<Propertydata> propertyDataList = new ArrayList<>();
        for (PropertyEntity property : properties) {
            propertyDataList.add(PropertyMapper.mapToAccountData(property));
        }
        return propertyDataList;
    }

}



