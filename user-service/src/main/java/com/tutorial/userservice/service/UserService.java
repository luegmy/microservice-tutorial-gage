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

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        return restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
    }

    public List<Bike> getBikes(int userId) {
        return restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
    }

    public Car saveCar(int userId,Car car){
        car.setUserId(userId);
        return carFeignClients.save(car);
    }

    public Bike saveBike(int userId,Bike bike){
        bike.setUserId(userId);
        return bikeFeignClients.save(bike);
    }

    public Map<String,Object> getCarsAndBikes(int userId){
        Map<String,Object> result=new HashMap<>();
        User user=userRepository.findById(userId).orElse(null);
        if(user==null){
            result.put("Mensaje","No existe usuario");
            return result;
        }
        result.put("User",user);

        List<Car> cars=carFeignClients.getCars(userId);
        List<Bike> bikes=bikeFeignClients.getBikes(userId);
        if(cars.isEmpty())
            result.put("Cars","No tiene carros");
        else
            result.put("Cars",cars);

        if(bikes.isEmpty())
            result.put("Bikes","No tiene motos");
        else
            result.put("Bikes",bikes);

        return result;
    }
}
