package com.nikola.exampleactivities.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Ingredients {
    private int id;
    private String name;
    List<Food> food; // jedan sastojak moze da ide u vise jelo

    public Ingredients(){

        food = new ArrayList<>();
    }

    public Ingredients(int id,String name) {
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

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public void addFood(Food f) {

        food.add(f);
    }

    public void removeFood(Food f) {

        food.remove(f);
    }

    public Food getFood(int position) {

        return food.get(position);
    }

    @Override
    public String toString() {
        return "Ingredients for " + food + " are: " + name;
    }
}
