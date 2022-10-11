package com.tutorial.userservice.feignclients;

import java.util.List;

import com.tutorial.userservice.model.Car;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarFeignClients {

    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping("/byuser/{userId}")
    List<Car>getCars(@PathVariable int userId);
}
