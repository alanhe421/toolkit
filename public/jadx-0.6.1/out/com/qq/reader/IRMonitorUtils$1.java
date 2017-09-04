package com.qq.reader;

import android.app.Application;
import com.hmt.analytics.HMTAgent;
import com.hmt.analytics.interfaces.HMTNetWorkCallback;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;

class IRMonitorUtils$1 extends ReaderIOTask {
    final /* synthetic */ Application val$applicationImp;

    IRMonitorUtils$1(Application application) {
        this.val$applicationImp = application;
    }

    public void run() {
        super.run();
        HMTAgent.setHMTNetWorkCallback(new HMTNetWorkCallback(this) {
            final /* synthetic */ IRMonitorUtils$1 a;

            {
                this.a = r1;
            }

            public void sendSuccess(String str) {
                c.a("QQ_READER==========>sendSuccess", str);
            }

            public void sendFail(String str, int i) {
                c.a("QQ_READER==========>sendFail", str + "====>" + i);
            }

            public void preSend(String str) {
                c.a("QQ_READER==========>preSend", str);
            }
        });
        HMTAgent.Initialize(this.val$applicationImp, 0, new String[0]);
    }
}
