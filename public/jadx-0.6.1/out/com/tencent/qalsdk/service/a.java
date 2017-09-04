package com.tencent.qalsdk.service;

import android.os.DeadObjectException;
import com.dynamicload.Lib.DLConstants;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.InvalidProtocolBufferMicroException;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IMsfServiceCallbacker;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.protocol.imsdk.msg_push.ReqBody;
import com.tencent.qalsdk.protocol.imsdk.msg_push.RspBody;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.w;
import com.tencent.qalsdk.util.QLog;

/* compiled from: AppProcessManager */
class a extends Thread {
    private static final String e = "im_open_push.msg_push";
    private static final int f = 10;
    volatile Object a = new Object();
    volatile boolean b = false;
    volatile boolean c = true;
    int d = 0;

    a() {
    }

    public void run() {
        while (true) {
            if (this.c) {
                this.c = false;
                this.d = 0;
                for (String str : c.c.keySet()) {
                    b bVar = (b) c.c.get(str);
                    if (bVar != null) {
                        if (a(str, bVar)) {
                            this.c = true;
                        }
                        this.d += bVar.g.size();
                    }
                }
            } else {
                this.b = true;
                synchronized (this.a) {
                    if (this.b) {
                        try {
                            if (this.d == 0) {
                                this.a.wait(61440);
                            } else {
                                this.a.wait(600);
                            }
                            this.c = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static boolean a(String str, b bVar) {
        int i;
        int i2 = 0;
        while (!bVar.g.isEmpty()) {
            i = i2 + 1;
            if (i > 10) {
                break;
            }
            w wVar = (w) bVar.g.peek();
            if (wVar == null) {
                break;
            }
            int i3;
            IMsfServiceCallbacker c = bVar.c();
            if (c == null) {
                int i4 = 1;
            } else {
                boolean z = false;
            }
            if (i4 == 0) {
                try {
                    if (wVar.a == null) {
                        c.onRecvPushResp(wVar.b);
                        QLog.d("MSF.S.AppProcessManager", 2, "send push " + str + " from:" + wVar.b.getUin() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getRequestSsoSeq());
                    } else {
                        c.onResponse(wVar.a, wVar.b);
                        QLog.d("MSF.S.AppProcessManager", 2, "service send resp " + str + System.currentTimeMillis() + DLConstants.DEPENDENCY_PACKAGE_DIV + " from:" + wVar.b.getUin() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getRequestSsoSeq());
                    }
                    i3 = i4;
                } catch (DeadObjectException e) {
                    bVar.d();
                    QLog.i("MSF.S.AppProcessManager", 2, "DeadObjectException");
                    i3 = 1;
                } catch (Throwable e2) {
                    QLog.e("MSF.S.AppProcessManager", 2, wVar.toString() + " " + wVar.b, e2);
                    i3 = i4;
                }
            } else {
                i3 = i4;
            }
            if (i3 == 0) {
                bVar.g.poll();
            } else {
                QLog.d("MSF.S.AppProcessManager", 2, "boot proccess" + str + " from:" + wVar.b.getUin() + ":" + wVar.b.getMsfCommand() + ":" + wVar.b.getServiceCmd());
                if (wVar.b.getMsfCommand() != MsfCommand.onRecvPushMsg) {
                    QLog.d("MSF.S.AppProcessManager", "proccess died,msf command need no boot");
                    bVar.g.poll();
                    break;
                }
                try {
                    bVar.a(wVar.b.getUin(), wVar.b);
                    bVar.g.poll();
                    if (!wVar.b.getServiceCmd().equals(e)) {
                        return false;
                    }
                    a(wVar.b);
                    return false;
                } catch (Throwable e3) {
                    QLog.d("MSF.S.AppProcessManager", 1, "Boot exception", e3);
                }
            }
            i2 = i;
        }
        i = i2;
        if (i > 10) {
            return true;
        }
        return false;
    }

    private static void a(FromServiceMsg fromServiceMsg) {
        ReqBody reqBody = new ReqBody();
        try {
            reqBody.mergeFrom(b.a(fromServiceMsg));
            RspBody rspBody = new RspBody();
            rspBody.bytes_session_data.setHasFlag(true);
            rspBody.bytes_session_data.set(ByteStringMicro.copyFrom(reqBody.bytes_session_data.get().toByteArray()));
            ToServiceMsg toServiceMsg = new ToServiceMsg("", fromServiceMsg.getUin(), e);
            byte[] toByteArray = rspBody.toByteArray();
            toServiceMsg.setAppId(com.tencent.qalsdk.base.a.bm);
            toServiceMsg.setNeedCallback(false);
            toServiceMsg.setTimeout(com.tencent.qalsdk.base.a.ap);
            toServiceMsg.setRequestSsoSeq(j.f());
            toServiceMsg.putWupBuffer(o.b(toByteArray));
            try {
                QalService.getCore().a(toServiceMsg);
            } catch (Throwable e) {
                QLog.w("MSF.S.AppProcessManager", 1, "query push error " + e, e);
            }
        } catch (InvalidProtocolBufferMicroException e2) {
            e2.printStackTrace();
        }
    }

    public void a() {
        synchronized (this.a) {
            this.c = true;
            this.b = false;
            this.a.notify();
        }
    }
}
