package com.qq.reader.plugin.tts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;

/* compiled from: TtsFakeServiceHelper */
public class o {
    public static g a = null;
    private static HashMap<Context, a> b = new HashMap();

    /* compiled from: TtsFakeServiceHelper */
    private static class a implements ServiceConnection {
        ServiceConnection a;

        a(ServiceConnection serviceConnection) {
            this.a = serviceConnection;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            o.a = com.qq.reader.plugin.tts.g.a.a(iBinder);
            if (this.a != null) {
                this.a.onServiceConnected(componentName, iBinder);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (this.a != null) {
                this.a.onServiceDisconnected(componentName);
            }
            o.a = null;
        }
    }

    public static boolean a(Context context, ServiceConnection serviceConnection) {
        boolean z = false;
        try {
            context.startService(new Intent(context, TtsFakeService.class));
            ServiceConnection aVar = new a(serviceConnection);
            b.put(context, aVar);
            z = context.bindService(new Intent(context, TtsFakeService.class), aVar, 0);
        } catch (Exception e) {
        }
        return z;
    }

    public static void a(Context context) {
        try {
            a aVar = (a) b.remove(context);
            if (aVar != null) {
                context.stopService(new Intent(context, TtsFakeService.class));
                context.unbindService(aVar);
                if (b.isEmpty()) {
                    a = null;
                }
            }
        } catch (Exception e) {
        }
    }
}
