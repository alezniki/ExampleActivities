package com.nikola.exampleactivities.providers;

import com.nikola.exampleactivities.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 9.5.17..
 */

public class IngredientsProvider {
        public static List<Ingredients> getIngredintsList(){

            List<Ingredients> ingredientsList = new ArrayList<>();

            Ingredients fillet = new Ingredients(0,"Fillet");
            ingredientsList.add(fillet);
            Ingredients pepper = new Ingredients(1,"Black Pepper");
            ingredientsList.add(pepper);
            Ingredients oil = new Ingredients(2,"Olive Oil");
            ingredientsList.add(oil);
            Ingredients garlic = new Ingredients(3,"Onion Garlic");
            ingredientsList.add(garlic);
            Ingredients breasts = new Ingredients(4,"Chicken Breasts");
            ingredientsList.add(breasts);
            Ingredients ciabatta = new Ingredients(5,"Ciabatta Loaf Bread");
            ingredientsList.add(ciabatta);
            Ingredients lemon = new Ingredients(6,"Lemon Juice");
            ingredientsList.add(lemon);

            return ingredientsList;
        }

        public static List<String> getIngredientsNames(){

            List<String> ingredientsNames = new ArrayList<>();

            ingredientsNames.add("Fillet");
            ingredientsNames.add("Black Pepper");
            ingredientsNames.add("Olive Oil");
            ingredientsNames.add("Onion Garlic");
            ingredientsNames.add("Chicken Breasts");
            ingredientsNames.add("Ciabatta Loaf Bread");
            ingredientsNames.add("Lemon Juice");

            return ingredientsNames;
        }

        public static Ingredients getIngredientsById(int id){

            switch (id) {
                case 0:
                    return new Ingredients(0,"Fillet");
                case 1:
                    return new Ingredients(1,"Black Pepper");
                case 2:
                    return new Ingredients(2,"Olive Oil");
                case 3:
                    return new Ingredients(3,"Onion Garlic");
                case 4:
                    return  new Ingredients(4,"Chicken Breasts");
                case 5:
                    return new Ingredients(5,"Ciabatta Loaf Bread");
                case 6:
                    return new Ingredients(6,"Lemon Juice");
                default:
                    return null;
            }
        }
}
