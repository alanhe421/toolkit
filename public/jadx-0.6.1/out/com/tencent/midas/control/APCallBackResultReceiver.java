package com.tencent.midas.control;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.tencent.midas.comm.APLog;

public class APCallBackResultReceiver extends ResultReceiver {
    private Receiver a;

    public interface Receiver {
        void onReceiveResult(int i, Bundle bundle);
    }

    public APCallBackResultReceiver(Handler handler) {
        super(handler);
    }

    protected void onReceiveResult(int i, Bundle bundle) {
        APLog.i("APCallBackResultReceiver", "onReceiveResult resultCode:" + i + " mReceiver:" + this.a);
        if (this.a != null) {
            this.a.onReceiveResult(i, bundle);
        }
    }

    public void setReceiver(Receiver receiver) {
        this.a = receiver;
    }
}
