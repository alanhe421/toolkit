package com.qq.reader.plugin.wps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WPSReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("ThirdPackage");
            if (stringExtra != null && stringExtra.equals("com.qq.reader")) {
                String stringExtra2 = intent.getStringExtra("CloseFile");
                float floatExtra = intent.getFloatExtra("ViewProgress", 0.0f);
                a.a().a(stringExtra2, intent.getFloatExtra("ViewScale", 1.0f), floatExtra, intent.getIntExtra("ViewScrollX", 0), intent.getIntExtra("ViewScrollY", 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
