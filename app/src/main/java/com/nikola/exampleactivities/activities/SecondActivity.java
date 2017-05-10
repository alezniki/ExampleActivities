package com.nikola.exampleactivities.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.model.Category;
import com.nikola.exampleactivities.model.Food;
import com.nikola.exampleactivities.model.Ingredients;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dzoni on 5/5/2017.
 */

public class SecondActivity extends Activity {

    Category steak = new Category(0,"Steak");
    Food tbone = new Food(0,"steak.jpg","T-bone","Chargrilled T-bone steak",  steak, 243.75, 36.99);

    Ingredients fillet = new Ingredients(0,"Fillet");
    Ingredients pepper = new Ingredients(0,"Black Pepper");
    Ingredients oil = new Ingredients(0,"Olive Oil");

    List<Ingredients> list = new ArrayList<>();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast toast = Toast.makeText(this, "SecondActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();

         ImageView ivImage = (ImageView) findViewById(R.id.iv_image);
         InputStream inputStream = null;
         try {
             inputStream = getAssets().open(tbone.getImage());
             Drawable drawable = Drawable.createFromStream(inputStream, null);
             ivImage.setImageDrawable(drawable);
         } catch (IOException e) {
             e.printStackTrace();
         }
         TextView tvName = (TextView) findViewById(R.id.tv_name);
         tvName.setText("Food Name: " + tbone.getName());
         TextView tvDescription = (TextView) findViewById(R.id.tv_description);
         tvDescription.setText("Description: " + tbone.getDescription());

         TextView tvCategory = (TextView) findViewById(R.id.tv_category);
         tvCategory.setText("Food Category: " + steak.getName());

         list.add(fillet);
         list.add(pepper);
         list.add(oil);

         TextView tvIngredients = (TextView) findViewById(R.id.tv_ingredients);
         tvIngredients.setText("Ingredients: ");
         for (Ingredients i : list){
             Log.v("TAG",i.getName());
             tvIngredients.append(i.getName() + ", ".replaceAll(",$", ""));
         }
         
         TextView tvCalories = (TextView) findViewById(R.id.tv_calories);
         tvCalories.setText("Calories: " + String.valueOf(tbone.getCalories()));
         TextView tvPrice = (TextView) findViewById(R.id.tv_price);
         tvPrice.setText("Price: $" + String.valueOf(tbone.getPrice()));
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
