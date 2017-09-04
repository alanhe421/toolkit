package oicq.wlogin_sdk.request;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import oicq.wlogin_sdk.tools.ErrMsg;

/* compiled from: alert_thread */
public class e extends Thread {
    Runnable a = new f(this);
    private Context b;
    private ErrMsg c;

    public e(Context context, ErrMsg errMsg) {
        this.b = context;
        a(errMsg);
    }

    public void a(ErrMsg errMsg) {
        if (errMsg != null) {
            try {
                this.c = (ErrMsg) errMsg.clone();
                return;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                this.c = null;
                return;
            }
        }
        this.c = null;
    }

    public void run() {
        new Handler(Looper.getMainLooper()).post(this.a);
    }
}
