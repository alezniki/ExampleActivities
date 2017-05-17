package com.nikola.exampleactivities.fragments;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private static int NOTIFICATION_ID = 1;

    // Position of the item to be displayed in the DetailFragment
    private int position = 0; // Initially T-Bone Steak

    // onCreate method is a life-cycle method that is called when creating the fragment.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "DetailFragment.onCreate()", Toast.LENGTH_SHORT).show();
    }

    //1. RETURNS FRAGMENT INTO GUI
    // onCreateView method is a life-cycle method that is called  to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "DetailFragment.onCreateView()", Toast.LENGTH_SHORT).show();

        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    //2. CALLED WHEN THE FRAGMENTS ACTIVITY HAS BEEN INITIALIZED (CREATED)
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
        updateFragmentContent(position);
    }


    //3. SAVE THE STATE INFORMATIONS OF YOUR FRAGMENT: It used for the scenario where Android kills off your activity to reclaim memory.
    // onSaveInstanceState method is a life-cycle method that is called to ask the fragment to save its current dynamic state,
    // so it can later be reconstructed in a new instance of its process is restarted.
    @Override
    public void onSaveInstanceState(Bundle outState) { // savedInstanceState
        super.onSaveInstanceState(outState); // savedInstanceState
        Toast.makeText(getActivity(), "DetailFragment.onSaveInstanceState()", Toast.LENGTH_SHORT).show();

        // savedInstanceState
        outState.putInt("position", position); // CUVA STANJE
    }

    // onDestroyView method is a life-cycle method that is called when the view previously created by
    // onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "DetailFragment.onDestroy()", Toast.LENGTH_SHORT).show();
    }


    //4. DEFINE METHODS TO COMMUNICATE TO FIRST ACTIVITY VIA onItemSelected()
    //4-A. Called to set Fragment's content in FirstActivity overrided onItemSelected() method
    public void setFragmentContent(final int position) {

        this.position = position;

        Log.v("DetailFragment", "setFragmentContent() sets position to " + position);
    }


    //4-B. Called to update Fragment's content in FirstActivity overrided onItemSelected() method
    public void updateFragmentContent(final int position) {

        this.position = position;
        Log.v("DetailFragment", "updateFragmentContent() sets position to " + position);

        //ImageView ivImage = (ImageView) findViewById(R.id.iv_image);
        ImageView ivImage = (ImageView) getView().findViewById(R.id.iv_image);
        InputStream inputStream = null;
        try {
            //inputStream = getAssets().open(FoodProvider.getFoodById(position).getImage());
            inputStream = getActivity().getAssets().open(FoodProvider.getFoodById(position).getImage());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        tvName.setText("Food Name: " + FoodProvider.getFoodById(position).getName());

        // TextView tvDescription = (TextView) findViewById(R.id.tv_description);
        TextView tvDescription = (TextView) getView().findViewById(R.id.tv_description);
        tvDescription.setText("Description: " + FoodProvider.getFoodById(position).getDescription());

        // TextView tvCalories = (TextView) findViewById(R.id.tv_calories);
        TextView tvCalories = (TextView) getView().findViewById(R.id.tv_calories);
        tvCalories.setText("Calories: " + String.valueOf(FoodProvider.getFoodById(position).getCalories()));

        // TextView tvPrice = (TextView) findViewById(R.id.tv_price);
        TextView tvPrice = (TextView) getView().findViewById(R.id.tv_price);
        tvPrice.setText("Price: $" + String.valueOf(FoodProvider.getFoodById(position).getPrice()));

        // Finds "spCategory" Spinner and sets "selection" property
        //Spinner spCategory = (Spinner) findViewById(R.id.sp_category);
        Spinner spCategory = (Spinner) getView().findViewById(R.id.sp_category);

        List<String> categories = CategoryProvider.getCategoryNames();
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        spCategory.setAdapter(adapter);
        spCategory.setSelection(FoodProvider.getFoodById(position).getCategory().getId());

        // Loads ingredients from array resource
        final List<Ingredients> ingredientsNames = FoodProvider.getFoodById(position).getIngredients();

        // Creates ArrayAdapter from the array of Strings
        //ArrayAdapter<Ingredients> arrayAdapter = new ArrayAdapter<Ingredients>(this,R.layout.list_item, ingredientsNames);
        ArrayAdapter<Ingredients> arrayAdapter = new ArrayAdapter<Ingredients>(getActivity(),R.layout.list_item, ingredientsNames);

        // ListView lvIngredients = (ListView) findViewById(R.id.lv_ingredients);
        ListView lvIngredients = (ListView) getView().findViewById(R.id.lv_ingredients);

        lvIngredients.setSelection(IngredientsProvider.getIngredientsById(position).getId());
        lvIngredients.setAdapter(arrayAdapter);

        // Add odred button
        Button btnOrder = (Button)getView().findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }


    public void showNotification(){
        // Create Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources() ,R.drawable.ic_stat_order);

        builder.setSmallIcon(R.drawable.ic_action_order);
        builder.setContentTitle(getString(R.string.notification_title));
        builder.setContentText(getString(R.string.notification_text));

        builder.setLargeIcon(bitmap);
        // Manage Notification
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

}
