package com.nikola.exampleactivities.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.model.Category;
import com.nikola.exampleactivities.model.Food;
import com.nikola.exampleactivities.model.Ingredients;


/**
 * Created by Dzoni on 5/5/2017.
 */

public class SecondActivity extends Activity {
    Food food = new Food();
    Category category = new Category();
    Ingredients ingredients = new Ingredients();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast toast = Toast.makeText(this, "SecondActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();

        ImageView ivImage = (ImageView) findViewById(R.id.iv_image);
//        ivImage.setText
        TextView tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setText(food.getName());
        TextView tvDescription = (TextView) findViewById(R.id.tv_description);
        tvDescription.setText(food.getDescription());
        TextView tvCategory = (TextView) findViewById(R.id.tv_category);
        tvCategory.setText(category.getCategoryName());
        TextView tvIngredients = (TextView) findViewById(R.id.tv_ingredients);
        tvIngredients.setText(ingredients.getIngredientsName());
        TextView tvCalories = (TextView) findViewById(R.id.tv_calories);
        tvCalories.setText(String.valueOf(food.getCalories()));
        TextView tvPrice = (TextView) findViewById(R.id.tv_price);
        tvPrice.setText(String.valueOf(food.getPrice()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onStart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onRestart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onResume()", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onPause()", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onStop()", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onDestroy()", Toast.LENGTH_SHORT);
        toast.show();
    }

}
