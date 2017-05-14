package com.nikola.exampleactivities.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.providers.FoodProvider;

import java.util.List;

/**
 * Created by androiddevelopment on 13.5.17..
 */

public class MasterFragment extends Fragment {

    OnItemSelectedListener listener; // Interface

    // onCreate method is a life-cycle method that is called when creating the fragment.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "MasterFragment.onCreate()", Toast.LENGTH_SHORT).show();
    }

    //1. RETURNS FRAGMENT INTO GUI
    // onCreateView method is a life-cycle method that is called  to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "MasterFragment.onCreateView()", Toast.LENGTH_SHORT).show();

        // onCreateView returns GUI of the Fragment

        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_master, container, false);

        //Button btn  = (Button) view.findViewById(R.id.btn);

        return view; // Inflate the layout for this fragment
    }

    //2. CALLED WHEN THE FRAGMENTS ACTIVITY HAS BEEN INITIALIZED (CREATED)
    // onActivityCreated method is a life-cycle method that is called when the fragment's activity has been created
    // and this fragment's view hierarchy instantiated.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "MasterFragment.onActivityCreated()", Toast.LENGTH_SHORT).show();

        // MOVED FROM FIRST ACTIVITY CLASS

        // Loads food names from array resource
        final List<String> foodNames = FoodProvider.getFoodNames();

        // Creates an ArrayAdaptar from the array of String
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item, foodNames);
        ListView listView = (ListView) getActivity().findViewById(R.id.list_view);

        // Assigns ArrayAdaptar to ListView
        listView.setAdapter(adapter);

        // Update FirstActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemSelected(position); // OnItemSelectedListener
            }
        });
    }


    // onDestroyView method is a life-cycle method that is called when the view previously created by
    // onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "MasterFragment.onDestroy()", Toast.LENGTH_SHORT).show();
    }


    //3. CONTAINER ACTIVITY MUST IMPLEMENT THIS INTERFACE
    // The easiest way to communicate between your activity and fragments is using interfaces.
    // The idea is basically to define an interface inside a given Fragment A and let the activity implement that interface.

    public interface OnItemSelectedListener {
        public void onItemSelected(int position);
    }

    //4. ATTACH FRAGMENT TO ACTIIVTY
    // onAttach method is a life-cycle method that is called when a fragment is first attached to its context
    @Override
    public void onAttach(Context context) { // Activity activity
        super.onAttach(context);

        Toast.makeText(getActivity(), "MasterFragment.onAttach()", Toast.LENGTH_SHORT).show();

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

        Toast.makeText(getActivity(), "MasterFragment.onDetach()", Toast.LENGTH_SHORT).show();

        listener = null;
    }

}