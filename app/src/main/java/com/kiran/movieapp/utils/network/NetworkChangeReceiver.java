package com.kiran.movieapp.utils.network;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import java.util.List;

/**
 * Summary
 * This java file is used to broadcast the change in internet connectivity
 * this is continuous running background service
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        int status = InternetConnectionUtil.getConnectivityStatus(context);

    }

}
