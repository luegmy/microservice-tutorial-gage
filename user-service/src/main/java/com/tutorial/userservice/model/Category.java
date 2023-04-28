package com.tutorial.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = -6742104428183327682L;


    private String id;
    private String name;
    private String order;
    private String logo;
    private double balance;
    private Date registerDate;
    private Date updatedDate;
}
