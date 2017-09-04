package com.qrcomic.downloader;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.qrcomic.activity.QRComicMainTabActivity;
import com.qrcomic.util.g;
import com.tencent.android.tpush.common.MessageKey;
import java.lang.reflect.Method;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class QRComicDownloadService extends Service {
    private volatile Looper a;
    private volatile b b;
    private a c = new a(this);

    public class a extends Binder {
        final /* synthetic */ QRComicDownloadService a;

        public a(QRComicDownloadService qRComicDownloadService) {
            this.a = qRComicDownloadService;
        }

        public void a(Message message) {
            if (this.a.b != null) {
                this.a.b.sendMessage(message);
            }
        }
    }

    private final class b extends Handler {
        final /* synthetic */ QRComicDownloadService a;

        public b(QRComicDownloadService qRComicDownloadService, Looper looper) {
            this.a = qRComicDownloadService;
            super(looper);
        }

        @TargetApi(16)
        public Notification a(int i, String str, String str2, String str3) {
            if (VERSION.SDK_INT < 11) {
                if (TextUtils.isEmpty(str3)) {
                    str3 = str2;
                }
                return new Notification(i, str3, System.currentTimeMillis());
            }
            Builder builder = new Builder(this.a.getApplicationContext());
            builder.setContentTitle(str);
            builder.setContentText(str2);
            builder.setSmallIcon(i);
            if (!TextUtils.isEmpty(str3)) {
                builder.setTicker(str3);
            }
            if (VERSION.SDK_INT < 16) {
                return builder.getNotification();
            }
            return builder.build();
        }

        @TargetApi(11)
        public void handleMessage(Message message) {
            try {
                Bundle data = message.getData();
                Notification a = a(message.what == 1000 ? 17301633 : 17301634, data.getString("title"), data.getString(MessageKey.MSG_CONTENT), data.getString("ticker"));
                if (a != null) {
                    PendingIntent activity = PendingIntent.getActivity(this.a.getApplicationContext(), 0, new Intent(this.a.getApplicationContext(), QRComicMainTabActivity.class), SigType.WLOGIN_PT4Token);
                    try {
                        if (VERSION.SDK_INT < 11) {
                            Method method = Notification.class.getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class});
                            if (method != null) {
                                method.invoke(a, new Object[]{this.a.getApplicationContext(), r1, r2, activity});
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    NotificationManager notificationManager = (NotificationManager) this.a.getSystemService("notification");
                    switch (message.what) {
                        case 1000:
                            if (notificationManager != null) {
                                notificationManager.cancel(10000001);
                            }
                            a.contentIntent = activity;
                            this.a.startForeground(10000000, a);
                            return;
                        case 1001:
                        case 1002:
                            this.a.stopForeground(true);
                            a.flags = 16;
                            a.contentIntent = activity;
                            if (notificationManager != null) {
                                notificationManager.notify(10000001, a);
                                return;
                            }
                            return;
                        case 1003:
                            this.a.stopForeground(true);
                            return;
                        default:
                            return;
                    }
                    if (g.a()) {
                        g.b("qqcomic.downloader.QRComicDownloadService", g.d, "downloadService handle message exception msg=" + e.getMessage());
                    }
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                if (g.a()) {
                    g.b("qqcomic.downloader.QRComicDownloadService", g.d, "downloadService handle message exception msg=" + e2.getMessage());
                }
                e2.printStackTrace();
            }
        }
    }

    public void onCreate() {
        if (g.a()) {
            g.b("qqcomic.downloader.QRComicDownloadService", g.d, "qqcomic downloader service oncreate");
        }
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("VipComicIntentServiceThread");
        handlerThread.start();
        this.a = handlerThread.getLooper();
        this.b = new b(this, this.a);
    }

    public IBinder onBind(Intent intent) {
        if (g.a()) {
            g.b("qqcomic.downloader.QRComicDownloadService", g.d, "qqcomic downloader service onBind");
        }
        return this.c;
    }

    public void onDestroy() {
        if (g.a()) {
            g.b("qqcomic.downloader.QRComicDownloadService", g.d, "qqcomic downloader service onDestroy");
        }
        stopForeground(true);
        super.onDestroy();
    }
}
