package com.qq.reader.plugin.tts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.t.d;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.tts.g.a;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TtsFakeService extends Service {
    private static final Class[] b = new Class[]{Integer.TYPE, Notification.class};
    private static final Class[] c = new Class[]{Boolean.TYPE};
    private String a = TtsFakeService.class.getName();
    private NotificationManager d;
    private Method e;
    private Method f;
    private Object[] g = new Object[2];
    private Object[] h = new Object[1];
    private a i = new a(this) {
        final /* synthetic */ TtsFakeService a;

        {
            this.a = r1;
        }
    };

    public void onCreate() {
        super.onCreate();
        this.d = (NotificationManager) getSystemService("notification");
        try {
            this.e = getClass().getMethod("startForeground", b);
            this.f = getClass().getMethod("stopForeground", c);
        } catch (NoSuchMethodException e) {
            this.e = null;
            this.f = null;
        }
    }

    public void onStart(Intent intent, int i) {
        a();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        a();
        return 3;
    }

    public IBinder onBind(Intent intent) {
        return this.i;
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        a(R.string.app_name);
        super.onDestroy();
    }

    private void a() {
        Intent intent = new Intent(this, ReaderPageActivity.class);
        intent.setFlags(2097152);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        d y = ao.y(getApplicationContext());
        y.c(getResources().getString(R.string.app_name));
        y.b(String.format(getResources().getString(R.string.tts_notification_text), new Object[]{com.qq.reader.common.c.a.X}));
        y.a(activity);
        a(R.string.app_name, y.a());
    }

    private void a(int i, Notification notification) {
        if (this.e != null) {
            this.g[0] = Integer.valueOf(i);
            this.g[1] = notification;
            try {
                this.e.invoke(this, this.g);
                return;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return;
            }
        }
        this.d.notify(i, notification);
    }

    private void a(int i) {
        if (this.f != null) {
            this.h[0] = Boolean.TRUE;
            try {
                this.f.invoke(this, this.h);
                return;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return;
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return;
            }
        }
        this.d.cancel(i);
    }
}
