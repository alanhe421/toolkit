package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.xiaomi.channel.commonutils.b.c;

class k implements ServiceConnection {
    final /* synthetic */ al a;

    k(al alVar) {
        this.a = alVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.a) {
            this.a.e = new Messenger(iBinder);
            this.a.j = false;
            for (Message send : this.a.i) {
                try {
                    this.a.e.send(send);
                } catch (Throwable e) {
                    c.a(e);
                }
            }
            this.a.i.clear();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.a.e = null;
        this.a.j = false;
    }
}
