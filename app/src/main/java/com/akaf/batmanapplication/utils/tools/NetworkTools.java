package com.akaf.batmanapplication.utils.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.akaf.batmanapplication.utils.application.App;

import java.util.Objects;

public class NetworkTools {

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return activeNetworkInfo == null;
    }
}