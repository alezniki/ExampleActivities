package com.nikola.exampleactivities.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.db.DataBaseHelper;
import com.nikola.exampleactivities.db.model.Meal;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 13.5.17..
 */

public class MasterFragment extends Fragment {

    OnItemSelectedListener listener; // Interface
    private DataBaseHelper dataBaseHelper;
    ListAdapter listAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //1. RETURNS FRAGMENT INTO GUI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // onCreateView returns GUI of the Fragment

        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_master, container, false);

        return view; // Inflate the layout for this fragment
    }

    //2. CALLED WHEN THE FRAGMENTS ACTIVITY HAS BEEN INITIALIZED (CREATED)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            List<Meal> meals = getDataBaseHelper().getmMealDao().queryForAll();
            listAdapter = new ArrayAdapter<Meal>(getActivity(),R.layout.list_item,meals);

            final ListView listView = (ListView)getActivity().findViewById(R.id.list_view); // fragment_master.xml

            //Assign ArrayAdapter to ListView
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Posto radimo sa bazom podataka, svaki element ima jedinstveni ID
                    // Potrebno je da vidimo na koji tacno element smo kliknuli - izvucemo meal iz liste i dobijemo njegov ID

                    Meal m = (Meal) listView.getItemAtPosition(position);
                    listener.onItemSelected(m.getmID());

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3. CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    public interface OnItemSelectedListener {
        public void onItemSelected(int position);
    }

    //4. ATTACH FRAGMENT TO ACTIIVTY
    @Override
    public void onAttach(Context context) { // Activity activity
        super.onAttach(context);

       try{
           listener = (OnItemSelectedListener) context;
       } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() + " must implement OnItemSelectedListener.");
       }
    }

    //5. DETACH FRAGMENT FROM ACTIVITY
    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public DataBaseHelper getDataBaseHelper() {
        if (dataBaseHelper == null) {
            dataBaseHelper = OpenHelperManager.getHelper(getActivity(),DataBaseHelper.class);
        }

        return dataBaseHelper;
    }
}