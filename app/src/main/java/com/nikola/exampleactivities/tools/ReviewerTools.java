package com.nikola.exampleactivities.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Dzoni on 5/26/2017.
 */

public class ReviewerTools {
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Active network
        NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
        if (null != activeNetworkInfo) {
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return  TYPE_WIFI;
            }

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }

        return TYPE_NOT_CONECTED;
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



}
