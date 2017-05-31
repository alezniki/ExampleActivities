package com.nikola.exampleactivities.async;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.tools.ReviewerTools;

/**
 * Created by nikola on 5/28/17.
 *
 * BroadcastReceiver je komponenta koja moze da reaguje na poruke drugih delova
 * samog sistema kao i korisnicki definisanih. Cesto se koristi u sprezi sa
 * servisima i asinhronim zadacima.
 *
 * Pored toga on moze da reaguje i na neke sistemske dogadjaje prispece sms poruke
 * paljenje uredjaja, novi poziv isl.
 */

public class SimpleReceiver extends BroadcastReceiver {

    //private static int notificationID = 1;

    /**
     * Intent je bitan parametar za BroadcastReceiver. Kada posaljemo neku poruku,
     * ovaj Intent cuva akciju i podatke koje smo mu poslali.
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("My_Android_App", "RECEIVE");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        /**
         * Posto nas BroadcastReceiver reaguje samo na jednu akciju koju smo definisali
         * dobro je da proverimo da li smo dobili bas tu akciju. Ako jesmo onda mozemo
         * preuzeti i sadrzaj ako ga ima.
         *
         * Voditi racuna o tome da se naziv akcije kada korisnik salje Intent mora poklapati sa
         * nazivom akcije kada akciju proveravamo unutar BroadcastReceiver-a. Isto vazi i za podatke.
         * Dobra praksa je da se ovi nazivi izdvoje unutar neke staticke promenljive.
         * */

        int resultCode = intent.getExtras().getInt("RESULT_CODE"); // iz SympleSyncTask-a

        if (intent.getAction().equals("SYNC_DATA")){

            //prepareNotification(resultCode,context,1,null);

            // Prosiren BroadcastReceiver da koristi allow_message Boolean
            boolean showMessage = sharedPreferences.getBoolean(context.getString(R.string.allow_message),false);

            if (showMessage) {
                //int resultCode = intent.getExtras().getInt("RESULT_CODE") ;
                prepareNotification(resultCode,context,1,null);
            }

        } else  if (intent.getAction().equals("COMMENT")) {
            prepareNotification(resultCode,context,2,intent.getExtras());
        }

    }

    // Saljemo i sadrzaj i naslov poruke, kao i Bundle objekat
    // Takodje cemo poslati i drugi notificationID
    private void prepareNotification(int resultCode, Context context, int notificationID, Bundle bundle) {
        NotificationManager notification = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder  = new NotificationCompat.Builder(context);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);

        Log.i("My_Android_App", "NOTIFICATION");

       if (bundle == null) {
           if (resultCode == ReviewerTools.TYPE_NOT_CONNECTED) {
               builder.setSmallIcon(R.drawable.ic_action_settings);
               builder.setContentTitle(context.getString(R.string.autosync_problem));
               builder.setContentText(context.getString(R.string.no_internet));
           } else if (resultCode == ReviewerTools.TYPE_MOBILE) {
               builder.setSmallIcon(R.drawable.ic_stat_order);
               builder.setContentTitle(context.getString(R.string.autosync_warning));
               builder.setContentText(context.getString(R.string.connect_to_wifi));
           } else {
               builder.setSmallIcon(R.drawable.ic_action_refresh);
               builder.setContentTitle(context.getString(R.string.autosync));
               builder.setContentText(context.getString(R.string.good_news_sync));
           }
       } else {
           String title = bundle.getString("title");
           String comment = bundle.getString("comment");

           builder.setSmallIcon(android.R.drawable.stat_notify_more);
           builder.setContentTitle(title);
           builder.setContentText(comment);
       }


        builder.setLargeIcon(bm);
        // notificationID allows you ti update notification later on
        notification.notify(notificationID, builder.build());

    }
}
