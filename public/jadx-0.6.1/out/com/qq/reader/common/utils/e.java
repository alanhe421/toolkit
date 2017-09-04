package com.qq.reader.common.utils;

import android.os.Handler;
import android.os.Looper;
import com.qq.reader.common.monitor.debug.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: AudioPlayFloatingWindowManager */
public class e {
    public static long a = -1;
    private static e b;
    private ArrayList<WeakReference<a>> c = new ArrayList();
    private boolean d = false;
    private long e = a;
    private String f;

    /* compiled from: AudioPlayFloatingWindowManager */
    public interface a {
        void onAudioFloatingStateChange(int i, long j, boolean z, String str);
    }

    private e() {
    }

    public static e a() {
        if (b == null) {
            b = new e();
        }
        return b;
    }

    public void a(a aVar) {
        try {
            b(aVar);
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(new WeakReference(aVar));
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
    }

    public void b(a aVar) {
        try {
            if (this.c != null && aVar != null) {
                for (int i = 0; i < this.c.size(); i++) {
                    WeakReference weakReference = (WeakReference) this.c.get(i);
                    if (!(weakReference == null || weakReference.get() == null || ((a) weakReference.get()).hashCode() != aVar.hashCode())) {
                        this.c.remove(weakReference);
                    }
                }
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
    }

    public boolean b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public void a(int i, long j, boolean z, String str) {
        try {
            this.e = j;
            this.d = z;
            this.f = str;
            if (this.c != null) {
                for (int i2 = 0; i2 < this.c.size(); i2++) {
                    final WeakReference weakReference = (WeakReference) this.c.get(i2);
                    if (!(weakReference == null || weakReference.get() == null)) {
                        final int i3 = i;
                        final long j2 = j;
                        final boolean z2 = z;
                        final String str2 = str;
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ e f;

                            public void run() {
                                ((a) weakReference.get()).onAudioFloatingStateChange(i3, j2, z2, str2);
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
    }
}
