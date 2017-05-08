package com.nikola.exampleactivities.model;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Ingredients {
    private String ingredientsName;
    private Food food;

    public Ingredients(){

    }

    public Ingredients(String name) {
        this.ingredientsName = name;
    }

    public Ingredients(String name, Food food) {
        this.ingredientsName = name;
        this.food = food;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Ingredients for " + food + " are: " + ingredientsName;
    }
}
