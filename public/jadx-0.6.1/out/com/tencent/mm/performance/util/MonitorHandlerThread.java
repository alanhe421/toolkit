package com.tencent.mm.performance.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.mm.performance.wxperformancetool.WxPerformanceClient;

public class MonitorHandlerThread extends HandlerThread {
    private static final int PROCESS_MONITOR = 1;
    public static int sInterval = 2500;
    private WxPerformanceClient mClient;
    public Handler mHandler;
    private boolean mPaused = false;

    public MonitorHandlerThread(String str, WxPerformanceClient wxPerformanceClient) {
        super(str);
        this.mClient = wxPerformanceClient;
    }

    public void setHandler(Looper looper) {
        if (looper == null) {
            throw new IllegalArgumentException("setHandler, threadLooper is null");
        }
        this.mHandler = new Handler(looper) {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        MonitorHandlerThread.this.mClient.processMonitor();
                        if (!MonitorHandlerThread.this.mPaused) {
                            MonitorHandlerThread.this.mHandler.sendEmptyMessageDelayed(1, (long) MonitorHandlerThread.sInterval);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.mHandler.sendEmptyMessageDelayed(1, (long) sInterval);
    }

    public void setPause(boolean z) {
        if (this.mPaused != z) {
            this.mPaused = z;
            this.mHandler.removeMessages(1);
            if (!this.mPaused) {
                this.mHandler.removeMessages(1);
                this.mHandler.sendEmptyMessageDelayed(1, (long) sInterval);
            }
        }
    }
}
