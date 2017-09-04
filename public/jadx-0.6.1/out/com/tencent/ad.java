package com.tencent;

import com.tencent.imcore.ThreadEntry;
import com.tencent.imsdk.QLog;

final class ad implements Runnable {
    private /* synthetic */ ThreadEntry a;

    ad(aa aaVar, ThreadEntry threadEntry) {
        this.a = threadEntry;
    }

    public final void run() {
        try {
            this.a.run();
        } catch (Throwable th) {
            String exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(aa.a, 1, exceptionInfo);
            TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener != null) {
                exceptionListener.onException(exceptionInfo);
            }
        }
    }
}
