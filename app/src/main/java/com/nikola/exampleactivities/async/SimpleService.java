package com.nikola.exampleactivities.async;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.nikola.exampleactivities.tools.ReviewerTools;

/**
 * Created by Dzoni on 5/26/2017.
 */

public class SimpleService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Metoda koja se poziva prilikom izvrsavanja zadatka servisa
     * Koristeci Intent mozemo prilikom startovanja servisa proslediti
     * odredjene parametre.
     * */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        /**
         * Provericemo trenutnu povezanost sa mrezom.
         * Za ovo koristimo dostupne pozive android operativnog sistema
         */

        // Get data from FirstActivity
        int status = intent.getExtras().getInt("STATUS");

        /*Primer poziva asinhronog zadatka ako ima veze ka mrezi
        npr. sinhronizacija mail-ova fotografija, muzike dokumenata isl*/
        if (status == ReviewerTools.TYPE_WIFI) {
            new SimpleSyncTask(getApplicationContext()).execute(status); // Integer... params
        }

        /**
         * Zaustaviti servis nakon obavljenog pokretanja asinhronog zadatka.
         * Ovu metodu nije potrebno pozvati ako zelimo da nasa aplikacija
         * konstantno osluskuje na neke izmene (npr. novi email, viber poruka isl)
         * */

        stopSelf();
        /**
         * Ako iz nekog razloga operativni sistem ubije servis
         * ne kreirati novi.
         * */

        return START_NOT_STICKY;
    }
}
