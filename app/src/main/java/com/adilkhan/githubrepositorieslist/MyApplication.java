package com.adilkhan.githubrepositorieslist;

import android.app.Application;

/**
 * Created by Adilkhan on 10/05/2018.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(CheckInternet.CheckInternetListener listener) {
        CheckInternet.connectivityReceiverListener = listener;
    }
}
