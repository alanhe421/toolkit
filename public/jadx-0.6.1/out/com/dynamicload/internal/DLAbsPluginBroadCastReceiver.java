package com.dynamicload.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.d;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.c;

public class DLAbsPluginBroadCastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            c.a(intent);
            boolean booleanExtra = intent.getBooleanExtra(DLConstants.EXTRA_FROM_NOTIFY, false);
            String stringExtra = intent.getStringExtra(DLConstants.EXTRA_PACKAGE);
            String stringExtra2 = intent.getStringExtra(DLConstants.EXTRA_CLASS);
            String action = intent.getAction();
            String stringExtra3 = intent.getStringExtra(DLConstants.EXTRA_NOTIFICATION_TYPE);
            c.a("DLAbsPluginBroadCastReceiver: isNotification= " + booleanExtra + " pkgName= " + stringExtra + " clsName= " + stringExtra2 + " action= " + action);
            if (booleanExtra) {
                intent.setClassName(stringExtra, stringExtra2);
                intent.removeExtra(DLConstants.EXTRA_FROM_NOTIFY);
                intent.removeExtra(DLConstants.EXTRA_NOTIFICATION_TYPE);
                intent.setData(null);
                if (DLConstants.NOTIFICATION_TYPE_ACTIVITY.equals(stringExtra3)) {
                    DLPluginManager.getInstance(context).startActivity(context, intent);
                } else if (DLConstants.NOTIFICATION_TYPE_SERVICE.equals(stringExtra3)) {
                    DLPluginManager.getInstance(context).startService(context, intent);
                } else if (DLConstants.NOTIFICATION_TYPE_BROADCAST.equals(stringExtra3)) {
                    DLPluginManager.getInstance(context).sendBroadcast(context, intent);
                } else {
                    c.c("wrong notificationType= " + stringExtra3);
                }
            } else if (action != null && action.startsWith("android.intent.action")) {
                d.a(context).a(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
