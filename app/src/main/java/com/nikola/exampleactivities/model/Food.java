package com.nikola.exampleactivities.model;

/**
 * Created by Dzoni on 5/8/2017.
 */

public class Food {
    private int id;
    private String image;
    private String name;
    private String description;
    private Category category; // jedno jelo pripada jednoj kategoriji
    private Ingredients ingredients;// jedno jelo ima vise sastojak
    double calories;
    double price;

    public Food(){
        //ingredients = new ArrayList<>();
    }

    public Food(int id, String image, String name, String description, Category category, Ingredients ingredients,double calories, double price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.calories = calories;
        this.price = price;

       // ingredients = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
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
