package com.nikola.exampleactivities.providers;

import com.nikola.exampleactivities.model.Category;
import com.nikola.exampleactivities.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 9.5.17..
 */

public class FoodProvider {
    public List<Food> getFood (){

    Category steak = new Category("Steak");
    Category seafood = new Category("Seafood");
    Category salad = new Category("Salad");

    List<Food> foodList = new ArrayList<>();
        Food tbone = new Food("steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);
        foodList.add(tbone);
        Food beef = new Food("steak.jpg","Beef Stake","Grilled Beef steak", steak, 243.75, 36.99);
        foodList.add(beef);
        Food caesar = new Food("steak.jpg", "Caesar Salad", "Fresh chicken salad", salad, 150.05, 15.00);
        foodList.add(caesar);
        Food salmon = new Food("steak.jpg", "Salmon", "Fresh Salmon", seafood, 150.05, 15.00);
        foodList.add(salmon);
        Food tuna = new Food("steak.jpg","Tuna Tartare", "Sushi-grade tuna", seafood, 130.12, 25.00);
        foodList.add(tuna);

        return foodList;

    }

}
