package qalsdk;

import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.x;
import com.tencent.qalsdk.util.QLog;

/* compiled from: PushManager */
class al extends Thread {
    final /* synthetic */ aj a;

    al(aj ajVar) {
        this.a = ajVar;
    }

    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QLog.i("MSF.C.PushManager", "onConnClosed send open conn,net is ok: " + m.b());
        if (m.b()) {
            QLog.i("MSF.C.PushManager", "onConnClosed send open conn");
            if (this.a.b.v == null) {
                QLog.i("MSF.C.PushManager", "no conn,send open conn");
                ToServiceMsg a = x.a("");
                MsfSdkUtils.addToMsgProcessName("", a);
                this.a.b.a(a);
            }
        }
    }
}
