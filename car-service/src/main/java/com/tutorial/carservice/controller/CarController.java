package com.tutorial.carservice.controller;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car getById(@PathVariable("id") String id) {
       return carService.getCarById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Car save(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> cars = carService.byUserId(userId);
/*        if(cars.isEmpty())
            return ResponseEntity.noContent().build();*/
        return ResponseEntity.ok(cars);
    }

}
