package com.nikola.exampleactivities.providers;

import com.nikola.exampleactivities.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 9.5.17..
 */

public class CategoryProvider {

    public static List<Category> getCategoryList(){

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category(0, "Steak"));
        categoryList.add(new Category(1, "Salad"));
        categoryList.add(new Category(2, "Seafood"));

        return categoryList;
    }

    public static List<String> getCategoryNames(){
        List<String> categoryNames = new ArrayList<>();

        categoryNames.add("Steak");
        categoryNames.add("Salad");
        categoryNames.add("Seafood");

        return categoryNames;
    }

    public static Category getCategoryById(int id) {
        switch (id) {
            case 0:
                return new Category(0, "Steak");
            case 1:
                return new Category(1, "Salad");
            case 2:
                return new Category(2, "Seafood");
            default:
                return null;
        }
    }
}
