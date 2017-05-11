package com.nikola.exampleactivities.providers;

import com.nikola.exampleactivities.model.Category;
import com.nikola.exampleactivities.model.Food;
import com.nikola.exampleactivities.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 9.5.17..
 */

public class IngredientsProvider {
        public static List<Ingredients> getIngredintsList(){
            Category steak = new Category(0,"Steak");
            Category salad = new Category(1,"Salad");
            Category seafood = new Category(2,"Seafood");


            List<Ingredients> ingredientsList = new ArrayList<>();

//            Ingredients fillet = new Ingredients(0,"Fillet");
//            ingredientsList.add(fillet);
//            Ingredients pepper = new Ingredients(1,"Black Pepper");
//            ingredientsList.add(pepper);
//            Ingredients oil = new Ingredients(2,"Olive Oil");
//            ingredientsList.add(oil);
//            Ingredients garlic = new Ingredients(3,"Onion Garlic");
//            ingredientsList.add(garlic);
//            Ingredients breasts = new Ingredients(4,"Chicken Breasts");
//            ingredientsList.add(breasts);
//            Ingredients ciabatta = new Ingredients(5,"Ciabatta Loaf Bread");
//            ingredientsList.add(ciabatta);
//            Ingredients lemon = new Ingredients(6,"Lemon Juice");
//            ingredientsList.add(lemon);
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
            tbone.setIngredients(tboneIngredients);
            foodList.add(tbone);

            Food beef = new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, 243.75, 36.99);
            beef.setIngredients(beefIngredients);
            foodList.add(beef);

            Food caesar = new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, 150.05, 15.00);
            caesar.setIngredients(caesarIngredients);
            foodList.add(caesar);

            Food salmon = new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, 150.05, 15.00);
            salmon.setIngredients(salmonIngredients);
            foodList.add(salmon);

            Food tuna = new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood,130.12, 25.00);
            tuna.setIngredients(tunaIngredients);
            foodList.add(tuna);

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
