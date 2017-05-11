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

        List<Ingredients> tboneIngredients = new ArrayList<>();
        List<Ingredients> beefIngredients = new ArrayList<>();
        List<Ingredients> caesarIngredients = new ArrayList<>();
        List<Ingredients> salmonIngredients = new ArrayList<>();
        List<Ingredients> tunaIngredients = new ArrayList<>();


        Ingredients fillet = new Ingredients(0,"Fillet");
        Ingredients pepper = new Ingredients(1,"Black Pepper");
        Ingredients oil = new Ingredients(2,"Olive Oil");
        Ingredients garlic = new Ingredients(3,"Onion Garlic");
        Ingredients breasts = new Ingredients(4,"Chicken Breasts");
        Ingredients ciabatta = new Ingredients(5,"Ciabatta Loaf Bread");
        Ingredients lemon = new Ingredients(6,"Lemon Juice");

        tboneIngredients.add(fillet); tboneIngredients.add(pepper); tboneIngredients.add(garlic);
        beefIngredients.add(fillet); beefIngredients.add(pepper); beefIngredients.add(garlic);
        caesarIngredients.add(oil); caesarIngredients.add(breasts); caesarIngredients.add(ciabatta);
        salmonIngredients.add(fillet); salmonIngredients.add(pepper); salmonIngredients.add(lemon);
        tunaIngredients.add(fillet); tunaIngredients.add(oil); tunaIngredients.add(lemon);

        List<Food> foodList = new ArrayList<>();
        Food tbone = new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);
        foodList.add(tbone);
        Food beef = new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, 243.75, 36.99);
        foodList.add(beef);
        Food caesar = new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, 150.05, 15.00);
        foodList.add(caesar);
        Food salmon = new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, 150.05, 15.00);
        foodList.add(salmon);
        Food tuna = new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood,130.12, 25.00);
        foodList.add(tuna);

        tbone.setIngredients(tboneIngredients);
        beef.setIngredients(beefIngredients);
        caesar.setIngredients(caesarIngredients);
        salmon.setIngredients(salmonIngredients);
        tuna.setIngredients(tunaIngredients);

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

        List<Ingredients> tboneIngredients = new ArrayList<>();
        List<Ingredients> beefIngredients = new ArrayList<>();
        List<Ingredients> caesarIngredients = new ArrayList<>();
        List<Ingredients> salmonIngredients = new ArrayList<>();
        List<Ingredients> tunaIngredients = new ArrayList<>();


        Ingredients fillet = new Ingredients(0,"Fillet");
        Ingredients pepper = new Ingredients(1,"Black Pepper");
        Ingredients oil = new Ingredients(2,"Olive Oil");
        Ingredients garlic = new Ingredients(3,"Onion Garlic");
        Ingredients breasts = new Ingredients(4,"Chicken Breasts");
        Ingredients ciabatta = new Ingredients(5,"Ciabatta Loaf Bread");
        Ingredients lemon = new Ingredients(6,"Lemon Juice");

        tboneIngredients.add(fillet); tboneIngredients.add(pepper); tboneIngredients.add(garlic);
        beefIngredients.add(fillet); beefIngredients.add(pepper); beefIngredients.add(garlic);
        caesarIngredients.add(oil); caesarIngredients.add(breasts); caesarIngredients.add(ciabatta);
        salmonIngredients.add(fillet); salmonIngredients.add(pepper); salmonIngredients.add(lemon);
        tunaIngredients.add(fillet); tunaIngredients.add(oil); tunaIngredients.add(lemon);

        List<Food> foodList = new ArrayList<>();
        Food tbone = new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);
        foodList.add(tbone);
        Food beef = new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, 243.75, 36.99);
        foodList.add(beef);
        Food caesar = new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, 150.05, 15.00);
        foodList.add(caesar);
        Food salmon = new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, 150.05, 15.00);
        foodList.add(salmon);
        Food tuna = new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood,130.12, 25.00);
        foodList.add(tuna);

        tbone.setIngredients(tboneIngredients);
        beef.setIngredients(beefIngredients);
        caesar.setIngredients(caesarIngredients);
        salmon.setIngredients(salmonIngredients);
        tuna.setIngredients(tunaIngredients);

        switch (id){
            case 0:
//                return new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);
                return tbone;
            case 1:
//                return new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, 243.75, 36.99);
                return beef;
            case 2:
//                return new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, 150.05, 15.00);
                return caesar;
            case 3:
//                return new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, 150.05, 15.00);
                return salmon;
            case 4:
//                return new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood, 130.12, 25.00);
                return tuna;
            default:
                return null;
        }
    }

}
