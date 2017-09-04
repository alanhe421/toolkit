package com.qq.reader.plugin.audiobook.core;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.qq.reader.plugin.audiobook.core.f.a;

/* compiled from: QQMusicServiceHelper */
class l$a implements ServiceConnection {
    ServiceConnection a;

    l$a(ServiceConnection serviceConnection) {
        this.a = serviceConnection;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        l.a = a.a(iBinder);
        if (this.a != null) {
            this.a.onServiceConnected(componentName, iBinder);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (this.a != null) {
            this.a.onServiceDisconnected(componentName);
        }
        l.a = null;
    }
}
