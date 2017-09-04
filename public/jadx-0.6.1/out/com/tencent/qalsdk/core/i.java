package com.tencent.qalsdk.core;

import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.util.QLog;
import java.util.ArrayList;
import qalsdk.o;

/* compiled from: MsfAlarmer */
public class i {
    static final String b = "MSF.C.NetConnTag";
    j a;
    private volatile Handler c;

    /* compiled from: MsfAlarmer */
    class a implements Runnable {
        final /* synthetic */ i a;
        private int b = 0;

        public a(i iVar, int i) {
            this.a = iVar;
            this.b = i;
        }

        public void run() {
            ArrayList arrayList = (ArrayList) this.a.a.d.f().remove(Integer.valueOf(this.b));
            if (arrayList != null) {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "remove merged ssoseq list: " + arrayList.toString() + " from SSO LoginMerge " + this.b);
                }
            } else if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "SSO LoginMerge's ssoseq " + this.b + "list has been removed by error code.");
            }
        }
    }

    /* compiled from: MsfAlarmer */
    class b implements Runnable {
        final /* synthetic */ i a;
        private ToServiceMsg b;

        public b(i iVar, ToServiceMsg toServiceMsg) {
            this.a = iVar;
            this.b = toServiceMsg;
        }

        public void run() {
            int i = 1002;
            if (this.b != null) {
                try {
                    ToServiceMsg toServiceMsg = (ToServiceMsg) this.a.a.d.e().get(Integer.valueOf(this.b.getRequestSsoSeq()));
                    if (toServiceMsg != null && toServiceMsg.isNeedCallback()) {
                        toServiceMsg = (ToServiceMsg) this.a.a.d.e().remove(Integer.valueOf(this.b.getRequestSsoSeq()));
                        if (toServiceMsg != null) {
                            String str = "wait serverResp timeout";
                            if (QLog.isDevelopLevel()) {
                                QLog.i("MSF.C.NetConnTag", 1, "[MsfAlarmer] netRecv ssoSeq:" + toServiceMsg.getRequestSsoSeq() + " uin:" + toServiceMsg.getUin() + " cmd:" + toServiceMsg.getServiceCmd() + " len:" + 0 + " costTime:" + toServiceMsg.getTimeout() + NetConnInfoCenter.getSignalStrengthsLog() + " code:" + 1002 + " failMsg:" + str);
                            } else {
                                QLog.i("MSF.C.NetConnTag", 1, "[MsfAlarmer] netRecv ssoSeq:" + toServiceMsg.getRequestSsoSeq() + " uin:" + MsfSdkUtils.getShortUin(toServiceMsg.getUin()) + " cmd:" + toServiceMsg.getServiceCmd() + " len:" + 0 + " costTime:" + toServiceMsg.getTimeout() + NetConnInfoCenter.getSignalStrengthsLog() + " code:" + 1002 + " failMsg:" + str);
                            }
                            FromServiceMsg a = k.a(toServiceMsg);
                            if (!this.a.a.d.a.a() && this.a.a.d.a.c()) {
                                i = com.tencent.qalsdk.base.a.e;
                                QLog.i("MSF.C.NetConnTag", "package timeout, no try conn");
                            } else if (!this.a.a.d.a.a() && this.a.a.d.a.b()) {
                                i = com.tencent.qalsdk.base.a.d;
                                QLog.i("MSF.C.NetConnTag", "package timeout, no conn && no network");
                            }
                            a.setBusinessFail(i, str);
                            if (this.a.a.d.a(toServiceMsg, a)) {
                                try {
                                    this.a.a.g().a(toServiceMsg, a);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (QLog.isColorLevel()) {
                            QLog.d("MSF.C.NetConnTag", 2, "timeout msg " + toServiceMsg + " also received resp, return.");
                        }
                    }
                } catch (Throwable e2) {
                    QLog.d("MSF.C.NetConnTag", 1, "got fail msg. " + e2, e2);
                }
            } else if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "req null, return.");
            }
        }
    }

    /* compiled from: MsfAlarmer */
    class c implements Runnable {
        final /* synthetic */ i a;
        private o b;

        public c(i iVar, o oVar) {
            this.a = iVar;
            this.b = oVar;
        }

        public void run() {
            if (this.b != null) {
                try {
                    this.b.e();
                } catch (Exception e) {
                    QLog.d("MSF.C.NetConnTag", 1, "call loginConnectTimeOut error " + e);
                }
            }
        }
    }

    public i(j jVar) {
        this.a = jVar;
    }

    public void a() {
        HandlerThread handlerThread = new HandlerThread("MsfCoreTimeoutChecker", 5);
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
    }

    public Runnable a(ToServiceMsg toServiceMsg, long j) {
        Runnable bVar = new b(this, toServiceMsg);
        if (toServiceMsg != null) {
            this.c.postDelayed(bVar, j);
        }
        return bVar;
    }

    public Runnable a(int i, long j) {
        Runnable aVar = new a(this, i);
        this.c.postDelayed(aVar, j);
        return aVar;
    }

    public Runnable a(o oVar, long j) {
        if (oVar == null) {
            return null;
        }
        Runnable cVar = new c(this, oVar);
        this.c.postDelayed(cVar, j);
        return cVar;
    }

    public void a(Runnable runnable) {
        this.c.removeCallbacks(runnable);
    }

    public Handler b() {
        return this.c;
    }
}
