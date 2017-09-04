package com.qq.reader.plugin.audiobook.core;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: MediaButtonHelper */
public class h {
    public static h a = null;
    private static String d = "MediaButtonHelper";
    private static boolean e = false;
    private AudioManager b;
    private final String c;

    public h(Context context) {
        this.b = (AudioManager) context.getSystemService("audio");
        this.c = context.getPackageName();
    }

    public void a() {
        try {
            c.a(d, "registerMediaButtonEventReceiver");
            this.b.registerMediaButtonEventReceiver(new ComponentName(this.c, MediaButtonIntentReceiver.class.getName()));
            e = true;
        } catch (Exception e) {
        }
    }

    public void b() {
        try {
            c.a(d, "unregisterMediaButtonEventReceiver");
            this.b.unregisterMediaButtonEventReceiver(new ComponentName(this.c, MediaButtonIntentReceiver.class.getName()));
            e = false;
        } catch (Exception e) {
        }
    }

    public void c() {
        try {
            c.a(d, "registerTtsMediaButtonEventReceiver");
            this.b.registerMediaButtonEventReceiver(new ComponentName(this.c, TtsMediaButtonReceiver.class.getName()));
            if (e) {
                this.b.unregisterMediaButtonEventReceiver(new ComponentName(this.c, MediaButtonIntentReceiver.class.getName()));
            }
        } catch (Exception e) {
        }
    }

    public void d() {
        try {
            c.a(d, "unregisterTtsMediaButtonEventReceiver");
            this.b.unregisterMediaButtonEventReceiver(new ComponentName(this.c, TtsMediaButtonReceiver.class.getName()));
            if (e) {
                this.b.registerMediaButtonEventReceiver(new ComponentName(this.c, MediaButtonIntentReceiver.class.getName()));
            }
        } catch (Exception e) {
        }
    }
}
