package com.qq.reader.plugin.audiobook.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.view.KeyEvent;
import com.qq.reader.common.monitor.debug.c;

public class MediaButtonIntentReceiver extends BroadcastReceiver {
    private static final String a = MediaButtonIntentReceiver.class.getSimpleName();
    private static long b = 0;
    private static boolean c = false;
    private static boolean d = false;
    private boolean e = false;

    public void onReceive(Context context, Intent intent) {
        c.d(a, intent.getPackage() + "," + intent.getAction() + "," + intent.getDataString());
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        String str = "android.intent.action.HEADSET_PLUG";
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            if (l.a != null) {
                try {
                    if (l.a.k() == 0) {
                        l.a.h();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (keyEvent != null && keyEvent.getKeyCode() == 79) {
            try {
                if (keyEvent.getAction() == 1 && l.a != null) {
                    if (l.a.a() || l.a.k() == 4 || l.a.k() == 5) {
                        l.a.c();
                        b.a = 0;
                    } else if (l.a.k() == 1 || l.a.k() == 6) {
                        l.a.g();
                    } else {
                        l.a.d();
                    }
                }
            } catch (RemoteException e2) {
            }
        }
    }
}
