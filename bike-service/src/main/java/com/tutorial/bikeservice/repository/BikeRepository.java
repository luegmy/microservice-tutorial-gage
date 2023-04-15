package com.tutorial.bikeservice.repository;

import com.tutorial.bikeservice.entity.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends MongoRepository<Bike, String> {

    List<Bike> findByUserId(String userId);
}
