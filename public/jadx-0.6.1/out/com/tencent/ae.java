package com.tencent;

import com.tencent.imcore.RunClosure;
import com.tencent.imsdk.QLog;

final class ae implements Runnable {
    private /* synthetic */ RunClosure a;

    ae(aa aaVar, RunClosure runClosure) {
        this.a = runClosure;
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
