package com.sina.weibo.sdk.b;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.t.d;

/* compiled from: SDKNotification */
public class h {
    private Context a;
    private Notification b;

    /* compiled from: SDKNotification */
    public static class a {
        private String a;
        private String b;
        private String c;
        private PendingIntent d;
        private long[] e;
        private Uri f;

        public static a a() {
            return new a();
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a a(PendingIntent pendingIntent) {
            this.d = pendingIntent;
            return this;
        }

        public h a(Context context) {
            d dVar = new d(context);
            dVar.c(true);
            dVar.a(this.d);
            dVar.c(this.a);
            dVar.a(b(context));
            dVar.a(System.currentTimeMillis());
            if (this.f != null) {
                dVar.a(this.f);
            }
            if (this.e != null) {
                dVar.a(this.e);
            }
            dVar.a(((BitmapDrawable) g.a(context, "weibosdk_notification_icon.png")).getBitmap());
            dVar.a(this.b);
            dVar.b(this.c);
            return new h(context, dVar.a());
        }

        private static int b(Context context) {
            int a = a(context, "com_sina_weibo_sdk_weibo_logo", "drawable");
            return a > 0 ? a : 17301659;
        }

        private static int a(Context context, String str, String str2) {
            String packageName = context.getApplicationContext().getPackageName();
            try {
                return context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(str, str2, packageName);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    private h(Context context, Notification notification) {
        this.a = context.getApplicationContext();
        this.b = notification;
    }

    public void a(int i) {
        if (this.b != null) {
            ((NotificationManager) this.a.getSystemService("notification")).notify(i, this.b);
        }
    }
}
