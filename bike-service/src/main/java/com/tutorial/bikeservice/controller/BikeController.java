package com.tutorial.bikeservice.controller;

import java.util.List;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.service.BikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bike> getAll() {
        return bikeService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bike getById(@PathVariable("id") String id) {
        return bikeService.getBikeById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Bike save(@RequestBody Bike bike) {
        return bikeService.save(bike);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.byUserId(userId);
/*        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();*/
        return ResponseEntity.ok(bikes);
    }

}
