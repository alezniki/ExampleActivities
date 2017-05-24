package com.nikola.exampleactivities.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Dzoni on 5/24/2017.
 */

public class SimpleSyncTask  extends AsyncTask<Void,Void,Void> {


    private Activity activity;

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
            Thread.sleep(5000);
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
        // Load product names from array resource
    }
}
