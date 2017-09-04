package qalsdk;

import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.ae;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;

/* compiled from: NetExceptionStatistics */
public class m {
    public static final String a = "MSF.C.NetExceptionStat";
    private static ae<a> b = new ae(100);
    private static long c = (System.currentTimeMillis() - 180000);

    /* compiled from: NetExceptionStatistics */
    public enum b {
        MessageTimeout;

        public static b a(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] a() {
            return (b[]) b.clone();
        }
    }

    public static void a(b bVar) {
        if (!com.tencent.qalsdk.core.m.b()) {
            return;
        }
        if (b.size() >= 100) {
            QLog.d(a, 2, "addNetException NetExceptionEvent count = " + b.size() + "too much drop");
            return;
        }
        Object aVar = new a();
        aVar.a = bVar;
        long currentTimeMillis = System.currentTimeMillis();
        aVar.b = currentTimeMillis;
        try {
            b.b(aVar);
            QLog.d(a, 2, "addNetException NetExceptionEvent count = " + b.size() + "");
            while (true) {
                try {
                    a aVar2 = (a) b.peek();
                    if (aVar2 == null || currentTimeMillis - aVar2.b < ((long) d.q())) {
                        QLog.d(a, 2, "addNetException after remove expire event NetExceptionEvent count = " + b.size());
                    } else {
                        b.a();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            QLog.d(a, 2, "addNetException after remove expire event NetExceptionEvent count = " + b.size());
            if (currentTimeMillis - c > ((long) d.q()) && b.size() >= d.r()) {
                QLog.d(a, 2, "NetExceptionEvent count = " + b.size() + " report to ui now");
                FromServiceMsg fromServiceMsg = new FromServiceMsg(com.tencent.qalsdk.core.m.b.i(), j.f(), "0", a.ab);
                fromServiceMsg.setMsgSuccess();
                fromServiceMsg.setMsfCommand(MsfCommand.onConnWeakNet);
                MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
                com.tencent.qalsdk.core.m.b.a(null, fromServiceMsg);
                c = currentTimeMillis;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
