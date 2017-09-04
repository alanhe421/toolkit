package com.qq.reader.liveshow.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: LivePresenter */
public abstract class i {
    protected Handler a = new Handler(Looper.getMainLooper());
    private j b;

    protected abstract boolean a(Message message);

    public void a(j jVar) {
        this.b = jVar;
        this.b.a(this);
    }

    public void b(Message message) {
        this.b.a(message);
    }
}
