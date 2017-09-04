package com.dynamicload.internal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.t.d;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLException;
import com.qq.reader.common.utils.ao;

/* compiled from: DLNotificationManager */
public class e {
    private c a;

    public e(c cVar) {
        this.a = cVar;
    }

    public void a(Context context, int i, String str, String str2, PendingIntent pendingIntent, int i2) {
        String packageName = context.getPackageName();
        if (this.a.a(packageName) == null) {
            throw new DLException("package not installed when notifyPluginNotification");
        }
        d y = ao.y(context.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            y.a((CharSequence) str);
        }
        if (!TextUtils.isEmpty(str2)) {
            y.b((CharSequence) str2);
        }
        int hashCode = (packageName + i).hashCode();
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService("notification");
        Notification a = y.a();
        a.flags = i2;
        a.contentIntent = pendingIntent;
        notificationManager.notify(hashCode, a);
    }

    public void a(Context context, int i) {
        String packageName = context.getPackageName();
        if (this.a.a(packageName) == null) {
            throw new DLException("package not installed when cancelPluginNotifitication");
        }
        ((NotificationManager) context.getApplicationContext().getSystemService("notification")).cancel((packageName + i).hashCode());
    }

    public PendingIntent a(Context context, int i, DLIntent dLIntent, int i2) {
        dLIntent.setClass(context.getApplicationContext(), DLAbsPluginBroadCastReceiver.class);
        dLIntent.putExtra(DLConstants.EXTRA_FROM_NOTIFY, true);
        dLIntent.putExtra(DLConstants.EXTRA_NOTIFICATION_TYPE, DLConstants.NOTIFICATION_TYPE_ACTIVITY);
        return PendingIntent.getBroadcast(context.getApplicationContext(), i, dLIntent, i2);
    }

    public PendingIntent b(Context context, int i, DLIntent dLIntent, int i2) {
        dLIntent.setClass(context.getApplicationContext(), DLAbsPluginBroadCastReceiver.class);
        dLIntent.putExtra(DLConstants.EXTRA_FROM_NOTIFY, true);
        dLIntent.putExtra(DLConstants.EXTRA_NOTIFICATION_TYPE, DLConstants.NOTIFICATION_TYPE_SERVICE);
        return PendingIntent.getBroadcast(context.getApplicationContext(), i, dLIntent, i2);
    }

    public PendingIntent c(Context context, int i, DLIntent dLIntent, int i2) {
        dLIntent.setClass(context.getApplicationContext(), DLAbsPluginBroadCastReceiver.class);
        dLIntent.putExtra(DLConstants.EXTRA_FROM_NOTIFY, true);
        dLIntent.putExtra(DLConstants.EXTRA_NOTIFICATION_TYPE, DLConstants.NOTIFICATION_TYPE_BROADCAST);
        return PendingIntent.getBroadcast(context.getApplicationContext(), i, dLIntent, i2);
    }
}
