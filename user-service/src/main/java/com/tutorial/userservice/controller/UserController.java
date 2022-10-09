package com.tutorial.userservice.controller;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable int userId){
        User user= userService.getUserById(userId);
        if(user==null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getCars(userId));
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable int userId){
        User user= userService.getUserById(userId);

        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getBikes(userId));
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car>saveCar(@PathVariable int userId, @RequestBody Car car){
        if(userService.getUserById(userId)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.saveCar(userId,car));
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike>saveBike(@PathVariable int userId, @RequestBody Bike bike){
        if(userService.getUserById(userId)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.saveBike(userId,bike));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String,Object>>getCarsAndBikes(@PathVariable int userId){
        return ResponseEntity.ok(userService.getCarsAndBikes(userId));
    }

}
