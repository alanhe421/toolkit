package com.qq.reader.common.conn.http.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.IOException;
import okhttp3.e;
import okhttp3.f;

/* compiled from: BaseRequestListener */
public abstract class a implements f {
    boolean a = true;
    Handler b = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ a a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.a(message);
        }
    };

    public abstract void a(int i, String str);

    abstract void a(Message message);

    public abstract void a(Exception exception);

    public final void a(e eVar, IOException iOException) {
        if (this.a) {
            this.b.obtainMessage(0, iOException).sendToTarget();
        } else {
            a((Exception) iOException);
        }
    }
}
