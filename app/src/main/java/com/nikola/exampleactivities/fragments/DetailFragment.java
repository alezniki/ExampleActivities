package com.nikola.exampleactivities.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.activities.FirstActivity;
import com.nikola.exampleactivities.db.model.Category;
import com.nikola.exampleactivities.db.model.Meal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 13.5.17..
 */

public class DetailFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    // Position of the item to be displayed in the detail fragment
    private int position = 0;
    private Meal meal = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //2. CALLED WHEN THE FRAGMENTS ACTIVITY HAS BEEN INITIALIZED (CREATED)
    //https://stackoverflow.com/questions/15313598/once-for-all-how-to-correctly-save-instance-state-of-fragments-in-back-stack
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position", 0);

            meal = new Meal();
            meal.setmID(savedInstanceState.getInt("id"));
            meal.setmName(savedInstanceState.getString("name"));
            meal.setmDescription(savedInstanceState.getString("description"));
            meal.setmCalories(savedInstanceState.getDouble("calories"));
            meal.setmPrice(savedInstanceState.getDouble("price"));
            meal.setmImage(savedInstanceState.getString("image"));

        }
//
//        // MOVED FROM SECOND ACTIVITY CLASS
//        updateFragmentContent(position);
    }


    //3. SAVE THE STATE INFORMATIONS OF YOUR FRAGMENT: It used for the scenario where Android kills off your activity to reclaim memory.
    @Override
    public void onSaveInstanceState(Bundle outState) { // savedInstanceState
        super.onSaveInstanceState(outState); // savedInstanceState

        if (outState !=null) {
            outState.putInt("id",meal.getmID());
            outState.putString("name",meal.getmName());
            outState.putString("description", meal.getmDescription());
            outState.putDouble("calories", meal.getmCalories());
            outState.putDouble("price",meal.getmPrice());
            outState.putString("image",meal.getmImage());
        }


        // savedInstanceState
        outState.putInt("position", position); // CUVA STANJE
    }

    //1. RETURNS FRAGMENT INTO GUI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(meal.getmName());

        TextView description = (TextView)view.findViewById(R.id.description);
        description.setText(meal.getmDescription());

        TextView calories = (TextView)view.findViewById(R.id.calories);
        calories.setText("Calories: " +  meal.getmCalories());

        TextView price = (TextView)view.findViewById(R.id.price);
        price.setText("Price: $" + meal.getmPrice());

        ImageView imageView = (ImageView)view.findViewById(R.id.iv_image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(meal.getmImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Spinner spinner = (Spinner)view.findViewById(R.id.category);
        try {
            List<Category> categoryList = ((FirstActivity)getActivity()).getDataBaseHelper().getmCategoryDao().queryForAll();
            ArrayAdapter<Category> categoryAdapter =
                    new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,categoryList);
            spinner.setAdapter(categoryAdapter);

            for (int i = 0; i < categoryList.size() ; i++) {
                if (categoryList.get(i).getId() == meal.getmCategory().getId()) {
                    spinner.setSelection(i);
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void updateMeal(Meal meal) {
        this.meal = meal;

        EditText name = (EditText)getActivity().findViewById(R.id.name);
        name.setText("Meal Name: " + meal.getmName());

        EditText description = (EditText)getActivity().findViewById(R.id.description);
        description.setText("Description: " + meal.getmDescription());

        EditText calories = (EditText)getActivity().findViewById(R.id.calories);
        calories.setText("Calories: " +  meal.getmCalories());

        EditText price = (EditText)getActivity().findViewById(R.id.price);
        price.setText("Price: $" + meal.getmPrice());

        ImageView imageView = (ImageView)getActivity().findViewById(R.id.image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(meal.getmImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Kada dodajemo novi element u toolbar potrebno je da oobrisemo prethodne elemente
     * Zato pozivamo menu.clear() i dodajemo nove toolbar elemente
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detail_fragment_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.remove:
                doRemoveElement();
                break;
            case R.id.update:
                doUpdateElement();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doUpdateElement() {
        if (meal != null) {
            EditText name = (EditText)getActivity().findViewById(R.id.name);
            meal.setmImage(name.getText().toString());
            EditText description = (EditText)getActivity().findViewById(R.id.description);
            meal.setmDescription(description.getText().toString());
            EditText calories = (EditText)getActivity().findViewById(R.id.calories);
            //meal.setmCalories(Double.parseDouble(calories.getText().toString()));
            EditText price = (EditText)getActivity().findViewById(R.id.price);
            //meal.setmPrice(Double.parseDouble(price.getText().toString());
            Spinner category = (Spinner)getActivity().findViewById(R.id.category);
            Category c = (Category) category.getSelectedItem();
            meal.setmCategory(c);

            try {
                ( (FirstActivity)getActivity()).getDataBaseHelper().getmMealDao().update(meal);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            getActivity().onBackPressed();
        }
    }

    private void doRemoveElement() {
        if (meal !=null) {
            try {
                ((FirstActivity)getActivity()).getDataBaseHelper().getmMealDao().delete(meal);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getActivity().onBackPressed();

        }

    }


}
