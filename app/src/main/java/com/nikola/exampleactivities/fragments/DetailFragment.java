package com.nikola.exampleactivities.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.model.Ingredients;
import com.nikola.exampleactivities.providers.CategoryProvider;
import com.nikola.exampleactivities.providers.FoodProvider;
import com.nikola.exampleactivities.providers.IngredientsProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by androiddevelopment on 13.5.17..
 */

public class DetailFragment extends Fragment {

    // Position of the item to be displayed in the DetailFragment
    private int position = 0; // Initially T-Bone Steak

    // onCreate method is a life-cycle method that is called when creating the fragment.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "DetailFragment.onCreate()", Toast.LENGTH_SHORT).show();
    }

    // onActivityCreated method is a life-cycle method that is called when the fragment's activity has been created
    // and this fragment's view hierarchy instantiated.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "DetailFragment.onActivityCreated()", Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position", 0);
        }

        // MOVED FROM SECOND ACTIVITY CLASS
//        ImageView ivImage = (ImageView) findViewById(R.id.iv_image);
        ImageView ivImage = (ImageView) getView().findViewById(R.id.iv_image);
        InputStream inputStream = null;
        try {
//            inputStream = getAssets().open(FoodProvider.getFoodById(position).getImage());
            inputStream = getActivity().getAssets().open(FoodProvider.getFoodById(position).getImage());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        tvName.setText("Food Name: " + FoodProvider.getFoodById(position).getName());

//        TextView tvDescription = (TextView) findViewById(R.id.tv_description);
        TextView tvDescription = (TextView) getView().findViewById(R.id.tv_description);
        tvDescription.setText("Description: " + FoodProvider.getFoodById(position).getDescription());

//        TextView tvCalories = (TextView) findViewById(R.id.tv_calories);
        TextView tvCalories = (TextView) getView().findViewById(R.id.tv_calories);
        tvCalories.setText("Calories: " + String.valueOf(FoodProvider.getFoodById(position).getCalories()));

//        TextView tvPrice = (TextView) findViewById(R.id.tv_price);
        TextView tvPrice = (TextView) getView().findViewById(R.id.tv_price);
        tvPrice.setText("Price: $" + String.valueOf(FoodProvider.getFoodById(position).getPrice()));

        // Finds "spCategory" Spinner and sets "selection" property
//        Spinner spCategory = (Spinner) findViewById(R.id.sp_category);
        Spinner spCategory = (Spinner) getView().findViewById(R.id.sp_category);

        List<String> categories = CategoryProvider.getCategoryNames();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        spCategory.setAdapter(adapter);
        spCategory.setSelection(FoodProvider.getFoodById(position).getCategory().getId());

        // Loads ingredients from array resource
        final List<Ingredients> ingredientsNames = FoodProvider.getFoodById(position).getIngredients();

        // Creates ArrayAdapter from the array of Strings
//        ArrayAdapter<Ingredients> arrayAdapter = new ArrayAdapter<Ingredients>(this,R.layout.list_item, ingredientsNames);
        ArrayAdapter<Ingredients> arrayAdapter = new ArrayAdapter<Ingredients>(getActivity(),R.layout.list_item, ingredientsNames);

//        ListView lvIngredients = (ListView) findViewById(R.id.lv_ingredients);
        ListView lvIngredients = (ListView) getView().findViewById(R.id.lv_ingredients);

        lvIngredients.setSelection(IngredientsProvider.getIngredientsById(position).getId());
        lvIngredients.setAdapter(arrayAdapter);

    }

    // onSaveInstanceState method is a life-cycle method that is called to ask the fragment to save its current dynamic state,
    // so it can later be reconstructed in a new instance of its process is restarted.
    @Override
    public void onSaveInstanceState(Bundle outState) { // savedInstanceState
        super.onSaveInstanceState(outState); // savedInstanceState
        Toast.makeText(getActivity(), "DetailFragment.onSaveInstanceState()", Toast.LENGTH_SHORT).show();

        // savedInstanceState
        outState.putInt("position", position); // CUVA STANJE
    }

    // onCreateView method is a life-cycle method that is called  to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "DetailFragment.onCreateView()", Toast.LENGTH_SHORT).show();

        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    // onDestroyView method is a life-cycle method that is called when the view previously created by
    // onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "DetailFragment.onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
