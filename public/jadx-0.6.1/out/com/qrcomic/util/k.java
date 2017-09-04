package com.qrcomic.util;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* compiled from: WeakReferenceHandler */
public class k extends Handler {
    private WeakReference<Callback> a;

    public k(Callback callback) {
        this.a = new WeakReference(callback);
    }

    public k(Looper looper, Callback callback) {
        super(looper);
        this.a = new WeakReference(callback);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.a != null) {
            Callback callback = (Callback) this.a.get();
            if (callback != null) {
                callback.handleMessage(message);
            }
        }
    }
}
