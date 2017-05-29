package com.nikola.exampleactivities.async;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by nikola on 5/29/17.
 */

public class CommentService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        String title = intent.getExtras().getString("title");
        String comment = intent.getExtras().getString("comment");

        new CommentTask(getApplicationContext()).execute(title,comment);



        stopSelf();
        return START_NOT_STICKY;
    }
}
