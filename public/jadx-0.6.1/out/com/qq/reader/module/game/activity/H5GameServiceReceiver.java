package com.qq.reader.module.game.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class H5GameServiceReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setAction("com.qq.reader.module.game.activity.H5GameProcessService");
        intent2.setPackage(context.getPackageName());
        context.startService(intent2);
    }
}
