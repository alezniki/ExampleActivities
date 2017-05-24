package com.nikola.exampleactivities.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.fragments.MasterFragment;
import com.nikola.exampleactivities.providers.FoodProvider;

import java.util.List;

/**
 * Created by Dzoni on 5/24/2017.
 */

public class SimpleSyncTask  extends AsyncTask<Void,Void,Void> {


    private Activity activity;
    private MasterFragment.OnItemSelectedListener listener; // Interface

    public SimpleSyncTask(Activity activity) {
        this.activity = activity;
        listener = (MasterFragment.OnItemSelectedListener) activity;
    }

    // Metoda koja se poziva pre samog starta pozadinskog zadatka
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Sve pripreme odraditi u ovoj metodi - ako ih ima
    }

    //1. Posao koji se odvija u pozadini, ne blokira glavnu nit aplikacije.
    @Override
    protected Void doInBackground(Void... params) {
        // Sav posao koji dugo traje izvrsavati unutar ove metode.
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

     //2. Kada se posao koji se odvija u pozadini zavrsi, poziva se ova metoda
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Ako je potrebno osloboditi resurse ili obrisati elemente koji vise ne trebaju.
        Toast.makeText(activity, "Sync Done!", Toast.LENGTH_SHORT).show();

        fillProducts();
    }

    private void fillProducts() {
        // Loads food names from array resource
        // Moved from MasterFragment onActivityCreated method

        final List<String> foodNames = FoodProvider.getFoodNames();

        // Creates an ArrayAdaptar from the array of String
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.list_item, foodNames);
        ListView listView = (ListView) activity.findViewById(R.id.list_view);

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
}
