package com.tencent.util;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

public class WeakReferenceHandler extends Handler {
    private WeakReference<Callback> mWeakReferCallBack;

    public WeakReferenceHandler(Callback callback) {
        this.mWeakReferCallBack = new WeakReference(callback);
    }

    public WeakReferenceHandler(Looper looper, Callback callback) {
        super(looper);
        this.mWeakReferCallBack = new WeakReference(callback);
    }

    public void handleMessage(Message message) {
        Callback callback = (Callback) this.mWeakReferCallBack.get();
        if (callback != null) {
            callback.handleMessage(message);
        }
    }
}
