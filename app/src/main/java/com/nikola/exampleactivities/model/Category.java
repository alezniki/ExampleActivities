package com.nikola.exampleactivities.model;

import java.util.List;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Category {
    private String name;
    private List<Food> food; // jedna kategorija ima vise jela

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

//    public Category(String categoryName, List<Food> food) {
//        this.categoryName = categoryName;
//        this.food = food;
//    }

    public String getName() {
        return name;
    }

    public void setName(String categoryName) {
        this.name = categoryName;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }





}
