package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(String id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        return bikeRepository.insert(bike);
    }

    public List<Bike> byUserId(String userId) {
        return bikeRepository.findByUserId(userId);
    }
}
