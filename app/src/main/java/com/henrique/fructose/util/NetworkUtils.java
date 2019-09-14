package com.henrique.fructose.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.*;

public class NetworkUtils {

    public static boolean isNetworkOnline(Context c) {
        boolean status = false;
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    c.getSystemService(CONNECTIVITY_SERVICE);
            if (connMgr != null) {
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                status = (networkInfo != null && networkInfo.isConnected());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }
}