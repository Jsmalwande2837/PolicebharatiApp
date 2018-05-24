package com.solutionner.policebharatiapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.solutionner.policebharatiapp.application.PoliceBharatiApplication;


public class NetworkConnectivity {

    private static Context mContext = PoliceBharatiApplication.getContext();
    private static ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

    public static boolean isOnline() {

        NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}