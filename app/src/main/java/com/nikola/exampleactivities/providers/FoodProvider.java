package com.nikola.exampleactivities.providers;

import com.nikola.exampleactivities.model.Category;
import com.nikola.exampleactivities.model.Food;
import com.nikola.exampleactivities.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 9.5.17..
 */

public class FoodProvider {

    public static List<Food> getFoodList (){

        Category steak = new Category(0,"Steak");
        Category salad = new Category(1,"Salad");
        Category seafood = new Category(2,"Seafood");

        Ingredients fillet = new Ingredients(0,"Fillet");
//        ingredientsList.add(fillet);
        Ingredients pepper = new Ingredients(1,"Black Pepper");
//        ingredientsList.add(pepper);
        Ingredients oil = new Ingredients(2,"Olive Oil");
//        ingredientsList.add(oil);
        Ingredients garlic = new Ingredients(3,"Onion Garlic");
//        ingredientsList.add(garlic);
        Ingredients breasts = new Ingredients(4,"Chicken Breasts");
//        ingredientsList.add(breasts);
        Ingredients ciabatta = new Ingredients(5,"Ciabatta Loaf Bread");
//        ingredientsList.add(ciabatta);
        Ingredients lemon = new Ingredients(6,"Lemon Juice");
//        ingredientsList.add(lemon);

//        List<Ingredients> steakList = new ArrayList<>();
//        steakList.add(fillet);
//        steakList.add(pepper);
//        steakList.add(garlic);
//
//        List<Ingredients> saladList = new ArrayList<>();
//        saladList.add(oil);
//        saladList.add(breasts);
//        saladList.add(ciabatta);
//
//        List<Ingredients> seafoodList = new ArrayList<>();
//        seafoodList.add(pepper);
//        seafoodList.add(oil);
//        seafoodList.add(garlic);
//        seafoodList.add(lemon);

        List<Food> foodList = new ArrayList<>();
        Food tbone = new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, pepper, 243.75, 36.99);
        foodList.add(tbone);
        Food beef = new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, fillet, 243.75, 36.99);
        foodList.add(beef);
        Food caesar = new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, breasts, 150.05, 15.00);
        foodList.add(caesar);
        Food salmon = new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, lemon,150.05, 15.00);
        foodList.add(salmon);
        Food tuna = new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood, oil,130.12, 25.00);
        foodList.add(tuna);

        return foodList;

    }

    public static List<String> getFoodNames(){

        List<String> foodNames = new ArrayList<>();

        foodNames.add("T-bone");
        foodNames.add("Beef");
        foodNames.add("Caesar");
        foodNames.add("Salmon");
        foodNames.add("Tuna");

        return foodNames;
    }

    public static Food getFoodById(int id){

        Category steak = new Category(0,"Steak");
        Category salad = new Category(1,"Salad");
        Category seafood = new Category(2,"Seafood");


        Ingredients fillet = new Ingredients(0,"Fillet");
//        ingredientsList.add(fillet);
        Ingredients pepper = new Ingredients(1,"Black Pepper");
//        ingredientsList.add(pepper);
        Ingredients oil = new Ingredients(2,"Olive Oil");
//        ingredientsList.add(oil);
        Ingredients garlic = new Ingredients(3,"Onion Garlic");
//        ingredientsList.add(garlic);
        Ingredients breasts = new Ingredients(4,"Chicken Breasts");
//        ingredientsList.add(breasts);
        Ingredients ciabatta = new Ingredients(5,"Ciabatta Loaf Bread");
//        ingredientsList.add(ciabatta);
        Ingredients lemon = new Ingredients(6,"Lemon Juice");
//        ingredientsList.add(lemon);

//        List<Ingredients> steakList = new ArrayList<>();
//        steakList.add(fillet);
//        steakList.add(pepper);
//        steakList.add(garlic);
//
//        List<Ingredients> saladList = new ArrayList<>();
//        saladList.add(oil);
//        saladList.add(breasts);
//        saladList.add(ciabatta);
//
//        List<Ingredients> seafoodList = new ArrayList<>();
//        seafoodList.add(pepper);
//        seafoodList.add(oil);
//        seafoodList.add(garlic);
//        seafoodList.add(lemon);



        switch (id){
            case 0:
                return new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, pepper, 243.75, 36.99);
            case 1:
                return new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, fillet,243.75, 36.99);
            case 2:
                return new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, breasts,150.05, 15.00);
            case 3:
                return new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, lemon,150.05, 15.00);
            case 4:
                return new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood, oil,130.12, 25.00);
            default:
                return null;
        }
    }

}
