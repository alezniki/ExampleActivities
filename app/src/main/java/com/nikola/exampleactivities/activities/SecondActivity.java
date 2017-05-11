package com.nikola.exampleactivities.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.providers.CategoryProvider;
import com.nikola.exampleactivities.providers.FoodProvider;
import com.nikola.exampleactivities.providers.IngredientsProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * Created by Dzoni on 5/5/2017.
 */

public class SecondActivity extends Activity {
    // private int position = 0;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast toast = Toast.makeText(this, "SecondActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();

         // Loads an URL into the WebView from FirstActivity intent
         final int position = getIntent().getIntExtra("position",0);

         ImageView ivImage = (ImageView) findViewById(R.id.iv_image);
         InputStream inputStream = null;
         try {
             inputStream = getAssets().open(FoodProvider.getFoodById(position).getImage());
             Drawable drawable = Drawable.createFromStream(inputStream, null);
             ivImage.setImageDrawable(drawable);
         } catch (IOException e) {
             e.printStackTrace();
         }
         TextView tvName = (TextView) findViewById(R.id.tv_name);
         tvName.setText("Food Name: " + FoodProvider.getFoodById(position).getName());
         TextView tvDescription = (TextView) findViewById(R.id.tv_description);
         tvDescription.setText("Description: " + FoodProvider.getFoodById(position).getDescription());

//         TextView tvCategory = (TextView) findViewById(R.id.tv_category);
//         tvCategory.setText("Food Category: " + CategoryProvider.getCategoryById(position).getName());

//         list.add(fillet);
//         list.add(pepper);
//         list.add(oil);

//         TextView tvIngredients = (TextView) findViewById(R.id.tv_ingredients);
//         tvIngredients.setText("Ingredients: ");
//         for (Ingredients i : list){
//             Log.v("TAG",i.getName());
//             tvIngredients.append(IngredientsProvider.getIngredientsById(position).getName() + ", ");
//         }
         TextView tvCalories = (TextView) findViewById(R.id.tv_calories);
         tvCalories.setText("Calories: " + String.valueOf(FoodProvider.getFoodById(position).getCalories()));
         TextView tvPrice = (TextView) findViewById(R.id.tv_price);
         tvPrice.setText("Price: $" + String.valueOf(FoodProvider.getFoodById(position).getPrice()));

         // Finds "spCategory" Spinner and sets "selection" property
         Spinner spCategory = (Spinner) findViewById(R.id.sp_category);
         List<String> categories = CategoryProvider.getCategoryNames();
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
         spCategory.setAdapter(adapter);
         spCategory.setSelection(FoodProvider.getFoodById(position).getCategory().getId());

         // Loads ingredients from array resource
         final List<String> ingredientsNames = IngredientsProvider.getIngredientsNames();

         // Creates ArrayAdapter from the array of Strings
         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item, ingredientsNames);
         ListView lvIngredients = (ListView) findViewById(R.id.lv_ingredients);
//         lvIngredients.setSelection(FoodProvider.getFoodById(position).getIngredients().get());

         lvIngredients.setSelection(IngredientsProvider.getIngredientsById(position).getId());
         lvIngredients.setAdapter(arrayAdapter);


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
