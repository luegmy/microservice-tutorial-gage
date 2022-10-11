package com.tutorial.userservice.feignclients;

import java.util.List;

import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "bike-service")
@RequestMapping("/bike")
public interface BikeFeignClients {

    @PostMapping()
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable int userId);
}
