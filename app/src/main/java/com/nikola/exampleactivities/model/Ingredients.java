package com.nikola.exampleactivities.model;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Ingredients {
    private int id;
    private String name;
    private Food food; // jedan sastojak ide u jedno jelo

    public Ingredients(){

    }

    public Ingredients(String name, Food food) {
        this.name = name;
        this.food = food;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Ingredients for " + food + " are: " + name;
    }
}
