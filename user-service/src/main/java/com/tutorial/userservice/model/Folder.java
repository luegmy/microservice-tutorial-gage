package com.tutorial.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Folder implements Serializable {

    private static final long serialVersionUID = -6742104428183327682L;

    private String numCategories;
    private double moneyBoxBalance;
    private double categoryBalance;
    private double availableBalance;
    private String maxCategoriesNumber;
    private List<Category> categories;

}
