package com.nikola.exampleactivities.model;

import java.util.List;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Category {
    private String categoryName;
    private List<Food> food;

    public Category() {

    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName, List<Food> food) {
        this.categoryName = categoryName;
        this.food = food;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }





}
