package com.tutorial.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Bike {
    private String id;
    private String brand;
    private String model;
    private String userId;
}
