package com.nikola.exampleactivities.providers;

import com.nikola.exampleactivities.model.Category;
import com.nikola.exampleactivities.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 9.5.17..
 */

public class FoodProvider {

    public static List<Food> getFoodList (){

        Category steak = new Category(0,"Steak");
        Category seafood = new Category(1,"Seafood");
        Category salad = new Category(2,"Salad");

        List<Food> foodList = new ArrayList<>();
        Food tbone = new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);
        foodList.add(tbone);
        Food beef = new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, 243.75, 36.99);
        foodList.add(beef);
        Food caesar = new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, 150.05, 15.00);
        foodList.add(caesar);
        Food salmon = new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, 150.05, 15.00);
        foodList.add(salmon);
        Food tuna = new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood, 130.12, 25.00);
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
        Category seafood = new Category(1,"Seafood");
        Category salad = new Category(2,"Salad");

        switch (id){
            case 0:
                return new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);
            case 1:
                return new Food(1,"steak.jpg","Beef","Grilled Beef steak", steak, 243.75, 36.99);
            case 2:
                return new Food(2,"salad.jpg", "Caesar", "Fresh chicken salad", salad, 150.05, 15.00);
            case 3:
                return new Food(3,"seafood.jpg", "Salmon", "Oven-baked Salmon", seafood, 150.05, 15.00);
            case 4:
                return new Food(4,"seafood.jpg","Tuna", "Sushi-grade Tuna Tartare", seafood, 130.12, 25.00);
            default:
                return null;
        }
    }

}
