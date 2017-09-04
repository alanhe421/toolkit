package com.tencent.mm.performance.loopertime;

import android.os.Looper;
import android.util.Printer;
import java.lang.ref.WeakReference;

public class LooperPrinter implements Printer {
    private static final int DEFAULT_MAX_TIME = 3000;
    public static boolean sHasBeginTag = false;
    public static boolean sHasEndTag = false;
    private final String BEGIN_TAG = ">>>>> Dispatching to";
    private final String END_TAG = "<<<<< Finished to";
    public String mBeginSting;
    private LooperMonitorController mController;
    public long mLastBeginTime = -1;
    public long mLastTimeoutBeginTime = -1;
    public int mMaxTime = 3000;
    private WeakReference<Looper> mWeakLooper;

    public LooperPrinter(LooperMonitorController looperMonitorController, WeakReference<Looper> weakReference) {
        this.mController = looperMonitorController;
        this.mWeakLooper = weakReference;
    }

    public void println(String str) {
        if (str.startsWith(">>>>> Dispatching to")) {
            this.mLastBeginTime = System.currentTimeMillis();
            this.mLastTimeoutBeginTime = -1;
            sHasBeginTag = true;
            this.mBeginSting = str;
        } else if (str.startsWith("<<<<< Finished to")) {
            sHasEndTag = true;
            final long currentTimeMillis = System.currentTimeMillis() - this.mLastBeginTime;
            if (this.mLastTimeoutBeginTime == -1 && currentTimeMillis > ((long) this.mMaxTime)) {
                final Looper looper = (Looper) this.mWeakLooper.get();
                if (looper != null) {
                    this.mController.mClient.postToMoniterThread(new Runnable() {
                        public void run() {
                            LooperPrinter.this.mController.notifyLooperTimeout(looper, currentTimeMillis, LooperPrinter.this.mMaxTime, true, LooperPrinter.this.mBeginSting);
                        }
                    });
                }
            }
            this.mLastBeginTime = -1;
            this.mLastTimeoutBeginTime = -1;
        }
    }

    public long getCurrentDiffTime() {
        if (this.mLastBeginTime == -1 || !sHasBeginTag || !sHasEndTag || this.mLastTimeoutBeginTime == this.mLastBeginTime) {
            return -1;
        }
        return System.currentTimeMillis() - this.mLastBeginTime;
    }
}
