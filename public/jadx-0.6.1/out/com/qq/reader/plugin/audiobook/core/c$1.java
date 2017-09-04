package com.qq.reader.plugin.audiobook.core;

import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.RemoteException;

/* compiled from: AudioFocusHelper */
class c$1 implements OnAudioFocusChangeListener {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case -3:
            case -2:
                try {
                    if (c.a(this.a).a()) {
                        c.a(this.a, true);
                        c.a(this.a).c();
                        return;
                    }
                    return;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return;
                }
            case -1:
                try {
                    if (c.a(this.a).a()) {
                        c.a(this.a, true);
                        c.a(this.a).c();
                        return;
                    }
                    return;
                } catch (RemoteException e2) {
                    try {
                        e2.printStackTrace();
                        return;
                    } catch (Exception e3) {
                    }
                }
            case 1:
                if (c.a(this.a) != null) {
                    try {
                        if (!c.a(this.a).a() && c.b(this.a)) {
                            c.a(this.a, false);
                            c.a(this.a).g();
                            return;
                        }
                        return;
                    } catch (RemoteException e22) {
                        e22.printStackTrace();
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
