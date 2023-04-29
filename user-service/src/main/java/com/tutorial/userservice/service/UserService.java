package com.tutorial.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.feignclients.BikeFeignClients;
import com.tutorial.userservice.feignclients.CarFeignClients;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final CarFeignClients carFeignClients;
    private final BikeFeignClients bikeFeignClients;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List getCars(String userId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
        ResponseEntity<List> bikes = restTemplate.exchange("http://car-service/cars/byuser/" + userId, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
        return bikes.getBody();
    }

    public List getBikes(String userId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
        ResponseEntity<List> cars = restTemplate.exchange("http://bike-service/bikes/byuser/" + userId, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
        return cars.getBody();
    }

    public Car saveCar(String userId, Car car) {
        car.setUserId(userId);
        return carFeignClients.save(car);
    }

    public Bike saveBike(String userId, Bike bike) {
        bike.setUserId(userId);
        return bikeFeignClients.save(bike);
    }

    public Map<String, Object> getCarsAndBikes(String userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "No existe usuario");
            return result;
        }
        result.put("User", user);

        List<Car> cars = carFeignClients.getCars(userId);

        if (cars.isEmpty())
            result.put("Cars", "No tiene carros");
        else
            result.put("Cars", cars);
        List<Bike> bikes = bikeFeignClients.getBikes(userId);
        if (bikes.isEmpty())
            result.put("Bikes", "No tiene motos");
        else
            result.put("Bikes", bikes);

        return result;
    }

}
