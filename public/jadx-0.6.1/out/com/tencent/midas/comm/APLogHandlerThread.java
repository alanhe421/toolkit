package com.tencent.midas.comm;

import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class APLogHandlerThread extends HandlerThread implements Callback {
    public APLogHandlerThread(String str) {
        super(str);
    }

    public boolean handleMessage(Message message) {
        return false;
    }
}
