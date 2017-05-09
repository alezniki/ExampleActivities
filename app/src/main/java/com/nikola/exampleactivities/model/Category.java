package com.nikola.exampleactivities.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Category {
    private int id;
    private String name;
    private List<Food> food; // jedna kategorija ima vise jela

    public Category() {
        food = new ArrayList<>();

    }

    public Category(int id,String name) {
        this.id = id;
        this.name = name;

        food = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
