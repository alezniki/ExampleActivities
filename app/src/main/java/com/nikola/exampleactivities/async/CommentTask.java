package com.nikola.exampleactivities.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by nikola on 5/29/17.
 */

public class CommentTask extends AsyncTask<String,Void,String[]>{

    private Context context;

    public CommentTask (Context context) {
        this.context = context;
    }

    @Override
    protected String[] doInBackground(String... params) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return params;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);

        //BroadcastReceiver

        Intent broadcast = new Intent("COMMENT");
        //Intent broadcast = new Intent();
        //broadcast.setAction("COMMENT");

        broadcast.putExtra("title",strings[0]);
        broadcast.putExtra("comment", strings[1]);

        context.sendBroadcast(broadcast); // SimpleReceiver

    }
}
