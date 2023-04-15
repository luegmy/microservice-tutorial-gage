package com.tutorial.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cars")
@Getter
@Setter
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private String userId;
}
