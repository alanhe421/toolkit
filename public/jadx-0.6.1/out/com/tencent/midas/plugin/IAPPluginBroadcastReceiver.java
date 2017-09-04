package com.tencent.midas.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;

public interface IAPPluginBroadcastReceiver {
    void IInit(String str, String str2, BroadcastReceiver broadcastReceiver, ClassLoader classLoader, PackageInfo packageInfo, boolean z);

    void IOnReceive(Context context, Intent intent);
}
