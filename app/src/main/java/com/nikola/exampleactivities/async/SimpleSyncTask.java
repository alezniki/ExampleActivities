package com.nikola.exampleactivities.async;

import android.content.Context;
import android.content.Intent;
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

//    // Add Notification Manager
//    public void createNotification(String nTitle, String nText){
//        NotificationManager manager  = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        //Build the notification using Notification.Builder
//        Notification.Builder builder = new Notification.Builder(context);
//
//        builder.setSmallIcon(android.R.drawable.stat_notify_more);
//        builder.setAutoCancel(true);
//        builder.setContentTitle(nTitle);
//        builder.setContentText(nText);
//
//        //Show notification
//        manager.notify(1,builder.build());
//    }

    //2. Kada se posao koji se odvija u pozadini zavrsi, poziva se ova metoda
    @Override
    protected void onPostExecute(Integer type) {
        super.onPostExecute(type);
//        // Ako je potrebno osloboditi resurse ili obrisati elemente koji vise ne trebaju.

        Toast.makeText(context, "Sync Done", Toast.LENGTH_SHORT).show();
//        //fillProducts(); // Get data from ListView
//
        String text = ReviewerTools.getConnectionType(type);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
//
//        //Notification
//        String title =  "Notification Example";
//        createNotification(title,text);

        /**
        * Da bi poslali poruku BroadcastReceiver-u potrebno je da definisiemo Intent sa sadrzajem.
        * Definisemo intent i sa njim nasu akciju SYNC_DATA. Ovo radimo da bi BroadcastReceiver
        * znao kako da reaguje kada dobije poruku tog tipa.
        * Uz poruku mozemo vezati i neki sadrazaj RESULT_CODE u ovom slucaju.
        * Jedan BroadcastReceiver moze da prima vise poruka iz aplikacije i iz tog razloga definisanje
        * akcije je bitna stvar.
        *
        * Voditi racuna o tome da se naziv akcije kada korisnik salje Intent mora poklapati sa
        * nazivom akcije kada akciju proveravamo unutar BroadcastReceiver-a. Isto vazi i za podatke.
        * Dobra praksa je da se ovi nazivi izdvoje unutar neke staticke promenljive.
        * */

        Intent intent = new Intent("SYNC_DATA"); // SYNC_DATA JE TEMA NA KOJU SALJEMO RECEIVER
        //Intent intent = new Intent();
        //intent.setAction("SYNC_DATA");
        intent.putExtra("RESULT_CODE", type); //RESULT_CODE u onReceive(), type je Connection type
        context.sendBroadcast(intent); // BROADCAST SE IZVRSAVA UNUTAR FIRST ACTIVITY KLASE

    }


}
