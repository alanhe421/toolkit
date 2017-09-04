package com.qq.reader.common.readertask;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

/* compiled from: ReaderQueue */
public class d {
    a a;
    Handler b = new Handler(this.a.getLooper(), this.a);

    /* compiled from: ReaderQueue */
    class a extends HandlerThread implements Callback {
        final /* synthetic */ d a;

        public a(d dVar, String str) {
            this.a = dVar;
            super(str);
        }

        public boolean handleMessage(Message message) {
            return true;
        }
    }

    public boolean a(Runnable runnable, long j) {
        if (this.b == null) {
            return false;
        }
        this.b.postDelayed(runnable, j);
        return true;
    }

    public void a(Runnable runnable) {
        if (this.b != null) {
            this.b.removeCallbacks(runnable);
        }
    }

    public d(String str) {
        this.a = new a(this, str);
        this.a.start();
    }
}
