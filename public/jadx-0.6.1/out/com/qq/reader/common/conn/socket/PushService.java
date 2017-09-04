package com.qq.reader.common.conn.socket;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.RemoteException;
import android.os.SystemClock;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;

public class PushService extends Service implements c {
    private b a;
    private a b;
    private Handler c;
    private WakeLock d;
    private ConnectivityManager e;
    private e f;
    private volatile boolean g = false;
    private com.qq.reader.common.conn.socket.a.a h = new com.qq.reader.common.conn.socket.a.a(this) {
        final /* synthetic */ PushService a;

        {
            this.a = r1;
        }

        public void a(String str) throws RemoteException {
            c.a("PushService", "sendMessage ->>" + str, true);
            Message obtain = Message.obtain();
            obtain.what = 1000;
            obtain.obj = str;
            if (this.a.c != null) {
                this.a.c.sendMessage(obtain);
            }
        }
    };
    private final BroadcastReceiver i = new BroadcastReceiver(this) {
        final /* synthetic */ PushService a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                boolean a = com.qq.reader.common.e.a.a();
                c.b("PushService", "NetWork state changed -->>isConnected : " + a, true);
                if (a) {
                    e eVar = new e(this.a.e.getActiveNetworkInfo());
                    c.b("PushService", "NetWork state  is : " + eVar, true);
                    if (eVar == null || !eVar.b(this.a.f)) {
                        this.a.f.a(eVar);
                        if (this.a.a != null) {
                            this.a.a.d();
                        }
                        this.a.c();
                        this.a.b();
                        return;
                    }
                    c.b("PushService", "NetWork state changed same -->>", true);
                    return;
                }
                this.a.f.a(null);
                this.a.c();
            } else if ("com.qq.reader.push.HEARTBEAT".equals(action)) {
                c.b("PushService", "Alarm heartbeat wake up -->>", true);
                if (this.a.c != null) {
                    this.a.c.removeMessages(1003);
                    this.a.c.sendEmptyMessage(1003);
                }
            } else if ("com.qq.reader.push.HEARTBEAT_WATCHDOG".equals(action)) {
                c.b("PushService", "Alarm watchdog happen -->>", true);
                this.a.c();
                this.a.b();
            }
        }
    };

    class a extends HandlerThread implements Callback {
        final /* synthetic */ PushService a;

        public a(PushService pushService, String str) {
            this.a = pushService;
            super(str);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1000:
                    this.a.b((String) message.obj);
                    return true;
                case 1001:
                    this.a.d.acquire();
                    this.a.d();
                    this.a.d.release();
                    return true;
                case 1002:
                    return true;
                case 1003:
                    this.a.e();
                    return true;
                default:
                    return false;
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        c.a("PushService", "onCreate ->>", true);
        this.d = ((PowerManager) getSystemService("power")).newWakeLock(1, "PushService");
        this.e = (ConnectivityManager) getSystemService("connectivity");
        this.f = new e(this.e.getActiveNetworkInfo());
        this.b = new a(this, "PushServiceThread");
        this.b.setPriority(1);
        this.b.start();
        this.c = new Handler(this.b.getLooper(), this.b);
        b();
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("com.qq.reader.push.HEARTBEAT");
            intentFilter.addAction("com.qq.reader.push.HEARTBEAT_WATCHDOG");
            registerReceiver(this.i, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.g = true;
    }

    public void onDestroy() {
        c.b("PushService", "onDestroy -->>", true);
        super.onDestroy();
        try {
            unregisterReceiver(this.i);
            d(getApplicationContext());
            b(getApplicationContext());
            this.g = false;
            c();
            this.b.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public IBinder onBind(Intent intent) {
        return this.h;
    }

    private void b() {
        if (this.c != null) {
            this.c.removeMessages(1001);
            Message obtain = Message.obtain();
            obtain.what = 1001;
            this.c.sendMessage(obtain);
        }
    }

    private void c() {
        if (this.a != null && this.a.g()) {
            this.a.f();
        }
    }

    private void a(long j) {
        if (this.c != null && !this.c.hasMessages(1001)) {
            this.c.sendEmptyMessageDelayed(1001, j);
        }
    }

    public void a() {
        c.a("PushService", "onClientOpen -->>", true);
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.RECEIVE_MESSAGE");
        Serializable qRPushMessage = new QRPushMessage();
        qRPushMessage.setMsgType(1000);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SocialConstants.PARAM_SEND_MSG, qRPushMessage);
        intent.putExtras(bundle);
        sendBroadcast(intent, "com.qq.reader.permission.READER_PUSH");
        c(getApplicationContext());
    }

    private void a(Context context) {
        c.b("PushService", "startWatchHeartbeatSendAlarm --->>", true);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.HEARTBEAT_WATCHDOG");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(broadcast);
        alarmManager.set(2, SystemClock.elapsedRealtime() + 90000, broadcast);
    }

    private void b(Context context) {
        c.b("PushService", "stopWatchHeartbeatSendAlarm --->>", true);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.HEARTBEAT_WATCHDOG");
        alarmManager.cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
    }

    private void c(Context context) {
        c.b("PushService", "startHeartBeatAlarm --->>", true);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.HEARTBEAT");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(broadcast);
        alarmManager.setRepeating(2, SystemClock.elapsedRealtime() + 270000, 270000, broadcast);
    }

    private void d(Context context) {
        c.b("PushService", "stopHeartBeatAlarm --->>", true);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.HEARTBEAT");
        alarmManager.cancel(PendingIntent.getBroadcast(context, 0, intent, 0));
    }

    public void a(String str) {
        b(getApplicationContext());
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.RECEIVE_MESSAGE");
        Serializable qRPushMessage = new QRPushMessage();
        qRPushMessage.setContent(str);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SocialConstants.PARAM_SEND_MSG, qRPushMessage);
        intent.putExtras(bundle);
        sendBroadcast(intent, "com.qq.reader.permission.READER_PUSH");
    }

    public void a(Exception exception, final long j) {
        b(getApplicationContext());
        if (this.g && com.qq.reader.common.e.a.a()) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ PushService b;

                public void run() {
                    if (this.b.g && com.qq.reader.common.e.a.a()) {
                        this.b.c();
                        this.b.a(j);
                    }
                }
            });
        }
    }

    public void a(int i, String str, boolean z, final long j) {
        b(getApplicationContext());
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ PushService b;

            public void run() {
                if (this.b.g && com.qq.reader.common.e.a.a()) {
                    this.b.a(j);
                }
            }
        });
    }

    public void a(WebSocket webSocket, Framedata framedata) {
        b(getApplicationContext());
    }

    public void b(WebSocket webSocket, Framedata framedata) {
        b(getApplicationContext());
    }

    private void d() {
        c.b("PushService", "connect -->>", true);
        if (this.a != null && this.a.g()) {
            c.b("PushService", "connection has established -->>", true);
        } else if (com.qq.reader.common.e.a.a()) {
            this.f = new e(this.e.getActiveNetworkInfo());
            try {
                this.a = new b(URI.create(d.a(getApplicationContext())), this);
                Map hashMap = new HashMap();
                hashMap.put("qimei", d.h(this));
                hashMap.put("uid", com.qq.reader.common.login.c.c().c());
                hashMap.put("os", "1");
                hashMap.put("qqheader", com.qq.reader.common.utils.a.a.a("qqreader_6.5.3.0888_android"));
                this.a.a(hashMap);
                this.a.c();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            c.b("PushService", "Network is DisConnected -->>", true);
        }
    }

    private void b(String str) {
        if (this.a != null) {
            this.a.b(str);
        }
    }

    private void e() {
        c.b("PushService", "sendHeartBeatPacket --->>", true);
        if (this.a == null || !this.a.g()) {
            b();
            return;
        }
        this.a.b();
        a(getApplicationContext());
    }
}
