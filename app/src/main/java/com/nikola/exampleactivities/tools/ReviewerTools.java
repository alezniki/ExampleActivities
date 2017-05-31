package com.nikola.exampleactivities.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nikola.exampleactivities.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Dzoni on 5/26/2017.
 */

public class ReviewerTools {
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Active network
        NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
        if (null != activeNetworkInfo) {
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI;
            }

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }

        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectionType(Integer type) {
        switch (type) {
            case 1:
                return "Connected to WI-FI";
            case 2:
                return "Connected to Mobile Data";
            default:
                return "Not Connected";
        }

    }

    public static int calculateTimeTillNextSync(int minutes){
        return 1000 * 60 * minutes;
    }


    // RAD SA TEKSTUALNIM FAJLOVIMA

    public static void writeToFile(String data,Context context, String filename){
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_APPEND); // OTVARA FAJL U APPEND REZIMU
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream); // PRETVARA U NIZ BAJTOVA
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }
    public static String readFromFile(Context context, String file) {

        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(file); // CITA VREDNOST IZ FAJLA

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // FAJL IZ KOGA CITA VREDNOST
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // PRIKAZUJE VREDNOST
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder(); // CUVA VREDNOST

                while ( (receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString(); // POVRATNA VREDNOST
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    /*******/
    public static void  fillAdapter(String[] products, Context context) {
        // Create an ArrayAdaptar from the array of Strings
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.list_item,products);
        ListView listView = (ListView) ((Activity)context).findViewById(R.id.list_view);
        // fragment_master.xml

        //Assign adapter to ListView
        listView.setAdapter(adapter);
    }

    public static void readFile(Context context) {
        String text = ReviewerTools.readFromFile(context,"my-file.txt");
        String[] data = text.split("\n");
        fillAdapter(data,context);
    }

    public static boolean isFileExists(Context context, String filename) {
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + filename);

        if (file.exists()){
            return true;
        } else {
            return false;
        }
    }
}
