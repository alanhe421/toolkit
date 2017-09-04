package com.qq.reader.plugin.audiobook.core;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.qq.reader.common.monitor.f;
import java.util.HashMap;

/* compiled from: QQMusicServiceHelper */
public class l {
    public static f a = null;
    private static HashMap<Context, a> b = new HashMap();

    public static boolean a(Context context, ServiceConnection serviceConnection) {
        boolean z = false;
        try {
            context.startService(new Intent(context, QQPlayerService.class));
            ServiceConnection aVar = new a(serviceConnection);
            b.put(context, aVar);
            z = context.bindService(new Intent().setClass(context, QQPlayerService.class), aVar, 0);
        } catch (Exception e) {
            f.a("bindToService", e.toString());
        }
        return z;
    }

    public static void a(Context context) {
        context.sendBroadcast(new Intent(e.b));
        b.clear();
        if (b.isEmpty()) {
            a = null;
        }
    }

    public static void b(Context context) {
        try {
            a aVar = (a) b.remove(context);
            if (aVar != null) {
                context.unbindService(aVar);
            }
        } catch (Exception e) {
        }
    }
}
