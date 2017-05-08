package com.nikola.exampleactivities.model;

import java.util.List;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Food {
    private String image;
    private String name;
    private String description;
    private Category category;
    private List<Ingredients> ingredientses;
    double calories;
    double price;

    public Food(){

    }

    public Food(String image, String name, String description, Category category, List<Ingredients> ingredientses, double calories, double price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.category = category;
        this.ingredientses = ingredientses;
        this.calories = calories;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredients> getIngredientses() {
        return ingredientses;
    }

    public void setIngredientses(List<Ingredients> ingredientses) {
        this.ingredientses = ingredientses;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
