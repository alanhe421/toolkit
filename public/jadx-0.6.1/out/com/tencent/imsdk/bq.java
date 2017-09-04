package com.tencent.imsdk;

import android.os.Process;
import android.util.Log;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

final class bq extends Thread {
    bq(bp bpVar, String str) {
        super(str);
    }

    public final void run() {
        int i;
        int i2 = 0;
        if (QLogImpl.isLogToFile && QLogImpl.isInitLogFileDone.compareAndSet(false, true)) {
            try {
                QLogImpl.processName = MsfSdkUtils.getProcessName(QLogImpl.sContext);
                try {
                    QLogImpl.packageName = QLogImpl.sContext.getPackageName();
                } catch (Exception e) {
                    QLogImpl.packageName = "unknow";
                }
                QLogImpl.myProcessId = Process.myPid();
                Log.d("appMemory", "QLog init retry ");
                QLogImpl.initLogFile(System.currentTimeMillis());
                QLogImpl.t.setName("logWriteThread");
                QLogImpl.t.start();
                QLogImpl.retryInitHandler.removeCallbacks(QLogImpl.acutualInitRunnable);
            } catch (Exception e2) {
                QLogImpl.isInitLogFileDone.set(false);
                e2.printStackTrace();
                i = QLogImpl.retryInitTimes.get();
                Log.d("appMemory", "QLog init post retry " + i + " times, interval " + QLogImpl.INTERVAL_RETRY_INIT[i]);
                QLogImpl.retryInitHandler.removeCallbacks(QLogImpl.acutualInitRunnable);
                QLogImpl.retryInitHandler.postDelayed(QLogImpl.acutualInitRunnable, (long) (QLogImpl.INTERVAL_RETRY_INIT[i] * 60000));
                i++;
                if (i < QLogImpl.INTERVAL_RETRY_INIT.length) {
                    i2 = i;
                }
                QLogImpl.retryInitTimes.set(i2);
            }
        }
    }
}
