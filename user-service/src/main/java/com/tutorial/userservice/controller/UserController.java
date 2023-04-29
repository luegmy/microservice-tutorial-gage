package com.tutorial.userservice.controller;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
       return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/cars/{userId}")
    @ResponseStatus(HttpStatus.OK)
    //@CircuitBreaker(name = "carsCB",fallbackMethod = "fallbackGetCars")
    public List<Car> getCars(@PathVariable String userId){
        return userService.getCars(userId);
    }

    @GetMapping("/bikes/{userId}")
    @ResponseStatus(HttpStatus.OK)
    //@CircuitBreaker(name = "bikesCB",fallbackMethod = "fallbackGetBikes")
    public List<Bike> getBikes(@PathVariable String userId){
        return userService.getBikes(userId);
    }

    @PostMapping("/savecar/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    //@CircuitBreaker(name = "carsCB",fallbackMethod = "fallbackSaveCar")
    public Car saveCar(@PathVariable String userId, @RequestBody Car car){
        return userService.saveCar(userId,car);
    }

    @PostMapping("/savebike/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    //@CircuitBreaker(name = "bikesCB",fallbackMethod = "fallbackSaveBike")
    public Bike saveBike(@PathVariable String userId, @RequestBody Bike bike){

        return userService.saveBike(userId,bike);
    }

    @GetMapping("/all/{userId}")
    //@CircuitBreaker(name = "allCB",fallbackMethod = "fallbackGetCarsAndBikes")
    public ResponseEntity<Map<String,Object>>getCarsAndBikes(@PathVariable String userId){
        return ResponseEntity.ok(userService.getCarsAndBikes(userId));
    }

    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable("userId") String userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene los coches en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Car> fallbackSaveCar(@PathVariable("userId") String userId, @RequestBody Car car, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para coches", HttpStatus.OK);
    }

    private ResponseEntity<List<Bike>> fallbackGetBikes(@PathVariable("userId") String userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Car> fallbackSaveBike(@PathVariable("userId") String userId, @RequestBody Bike bike, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + "  no tiene dinero para motos", HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> fallbackGetCarsAndBikes(@PathVariable("userId") String userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene los vehículos en el taller", HttpStatus.OK);
    }

}
