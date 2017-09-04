package com.pay.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.tencent.midas.comm.APLog;
import java.util.HashMap;

public class APHttpHandle extends Handler {
    private static APHttpHandle a;
    private static byte[] b = new byte[0];
    private HashMap<String, IAPHttpAnsObserver> c = new HashMap();

    private APHttpHandle() {
    }

    private void a(Message message) {
        int i = message.what;
        APBaseHttpAns aPBaseHttpAns = (APBaseHttpAns) message.obj;
        String httpReqKey = aPBaseHttpAns.getHttpReqKey();
        IAPHttpAnsObserver iAPHttpAnsObserver = (IAPHttpAnsObserver) this.c.get(httpReqKey);
        if (iAPHttpAnsObserver == null) {
            Log.i("HttpHandler", "observer is null");
            return;
        }
        unregister(httpReqKey);
        switch (i) {
            case 3:
                iAPHttpAnsObserver.onFinish(aPBaseHttpAns);
                return;
            case 4:
                iAPHttpAnsObserver.onError(aPBaseHttpAns);
                return;
            case 5:
                iAPHttpAnsObserver.onStop(aPBaseHttpAns);
                return;
            default:
                return;
        }
    }

    public static APHttpHandle getIntanceHandel() {
        synchronized (b) {
            if (a == null) {
                try {
                    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                        Looper.prepare();
                    }
                    a = new APHttpHandle();
                } catch (Exception e) {
                    APLog.i("APHttpHandle", e.toString());
                }
            }
        }
        return a;
    }

    public void handleMessage(Message message) {
        a(message);
    }

    public void register(String str, IAPHttpAnsObserver iAPHttpAnsObserver) {
        this.c.put(str, iAPHttpAnsObserver);
    }

    public void release() {
        a = null;
    }

    public void unregister(String str) {
        this.c.remove(str);
    }
}
