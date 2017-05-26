package com.nikola.exampleactivities.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.nikola.exampleactivities.tools.ReviewerTools;

/**
 * Created by Dzoni on 5/24/2017.
 */

public class SimpleSyncTask extends AsyncTask<Integer,Void,Integer> {


    private Context context;
    //private Activity activity;
    //private MasterFragment.OnItemSelectedListener listener; // Interface

    public SimpleSyncTask(Context context) {
        this.context = context;
    }

//    public SimpleSyncTask(Activity activity) {
//        this.activity = activity;
//        listener = (MasterFragment.OnItemSelectedListener) activity;
//    }


    // Metoda koja se poziva pre samog starta pozadinskog zadatka
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Sve pripreme odraditi u ovoj metodi - ako ih ima
    }

    //1. Posao koji se odvija u pozadini, ne blokira glavnu nit aplikacije.
    @Override
    protected Integer doInBackground(Integer... params) {
        try {
            //simulacija posla koji se obavlja u pozadini i traje duze vreme
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return params[0];
    }

    //2. Kada se posao koji se odvija u pozadini zavrsi, poziva se ova metoda
    @Override
    protected void onPostExecute(Integer type) {
        super.onPostExecute(type);
        // Ako je potrebno osloboditi resurse ili obrisati elemente koji vise ne trebaju.
        Toast.makeText(context, "Sync Done", Toast.LENGTH_SHORT).show();
        //fillProducts(); // Get data from ListView

        String text = ReviewerTools.getConnectionType(type);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }

//    private void fillProducts() {
//        // Loads food names from array resource
//        // Moved from MasterFragment onActivityCreated method
//
//        final List<String> foodNames = FoodProvider.getFoodNames();
//
//        // Creates an ArrayAdaptar from the array of String
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.list_item, foodNames);
//        ListView listView = (ListView) activity.findViewById(R.id.list_view);
//
//        // Assigns ArrayAdaptar to ListView
//        listView.setAdapter(adapter);
//
//        // Update FirstActivity
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listener.onItemSelected(position); // OnItemSelectedListener
//            }
//        });
//    }
}
