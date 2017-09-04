package com.tencent.imsdk;

import android.text.format.DateFormat;
import com.tencent.IMCoreWrapper;
import com.tencent.TIMLogLevel;

final class bo implements Runnable {
    private /* synthetic */ TIMLogLevel a;
    private /* synthetic */ String b;
    private /* synthetic */ String c;

    bo(TIMLogLevel tIMLogLevel, String str, String str2) {
        this.a = tIMLogLevel;
        this.b = str;
        this.c = str2;
    }

    public final void run() {
        try {
            if (IMMsfCoreProxy.get().getMode() == 1 && !IMCoreWrapper.get().isLogInited()) {
                long currentTimeMillis = System.currentTimeMillis();
                QLog.initLogFile(currentTimeMillis);
                StringBuilder append = new StringBuilder("[").append(DateFormat.format("yy-MM-dd HH:mm:ss", currentTimeMillis).toString()).append("][").append(this.a.getDescr()).append("][][").append(this.b).append("]").append(this.c).append("\n");
                if (QLog.writer != null && QLog.lock.tryLock()) {
                    QLog.writer.write(append.toString());
                    QLog.writer.flush();
                    QLog.lock.unlock();
                }
            } else if (IMCoreWrapper.get().isLogInited() && QLog.writer != null) {
                QLog.writer.close();
                QLog.writer = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
