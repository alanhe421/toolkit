package com.tencent.qalsdk.core;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.im_open.stat_forceoffline.ReqBody;
import com.tencent.qalsdk.im_open.stat_forceoffline.RspBody;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;
import qalsdk.d;
import qalsdk.f;

/* compiled from: SsoRespHandler */
public class q {
    public static final int a = 210;
    public static final int b = -12003;
    public static final int c = -10001;
    public static final int d = -10003;
    public static final int e = -10004;
    public static final int f = -10005;
    public static final int g = -10006;
    public static final int h = -10000;
    public static final int i = -10007;
    public static final int j = -10009;
    public static final int k = -10101;
    public static final int l = -10102;
    public static final int m = -10103;
    public static final int n = -10104;
    public static final int o = -10105;
    public static final int p = -10106;
    static final String q = "OverLoadPush.notify";
    private static final String u = "MSF.C.SSORespHandler";
    private static final int v = 1000;
    j r;
    long s = -1;
    long t = -1;
    private Handler w = new r(this);

    public q(j jVar) {
        this.r = jVar;
    }

    protected boolean a(int i) {
        return i == b || i == 210 || i == -10001 || i == -10003 || i == -10004 || i == g || i == p;
    }

    protected boolean a(FromServiceMsg fromServiceMsg, ToServiceMsg toServiceMsg) {
        int businessFailCode = fromServiceMsg.getBusinessFailCode();
        boolean a = a(businessFailCode);
        if (!a && businessFailCode == -10005) {
            if (this.s == -1) {
                this.s = System.currentTimeMillis();
            }
            if (this.r.d.H.get()) {
                if (toServiceMsg == null || this.r.d.G == -1 || toServiceMsg.getRequestSsoSeq() < this.r.d.G) {
                    if (this.s != -1 && System.currentTimeMillis() - this.s > BuglyBroadcastRecevier.UPLOADLIMITED) {
                        if (QLog.isColorLevel()) {
                            QLog.i(u, 2, "set userTokenExpired after so long wait. ");
                        }
                    }
                } else if (QLog.isColorLevel()) {
                    QLog.i(u, 2, "set userTokenExpired. afterReloadD2SendSeq is " + this.r.d.G);
                    a = true;
                }
                a = true;
            }
        }
        if (a) {
            MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
            this.r.a(toServiceMsg, fromServiceMsg);
            return true;
        } else if (businessFailCode == k) {
            fromServiceMsg.setBusinessFail(a.o, fromServiceMsg.getBusinessFailMsg());
            MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
            this.r.a(toServiceMsg, fromServiceMsg);
            return true;
        } else if (businessFailCode == l) {
            this.r.j();
            fromServiceMsg.setBusinessFail(a.n, fromServiceMsg.getBusinessFailMsg());
            MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
            this.r.a(toServiceMsg, fromServiceMsg);
            return true;
        } else if (businessFailCode == j) {
            fromServiceMsg.setBusinessFail(a.s, fromServiceMsg.getBusinessFailMsg());
            MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
            this.r.a(toServiceMsg, fromServiceMsg);
            this.r.d.a(CloseConnReason.ssoInvalidCookie);
            return true;
        } else if (businessFailCode == n) {
            if (QLog.isColorLevel()) {
                QLog.d(u, 2, "get CODE_SSO_FORCEQUIT kill " + this.r.u.getPackageName() + ":qq now");
            }
            MsfSdkUtils.killProcess(this.r.u, this.r.u.getPackageName());
            return true;
        } else if (businessFailCode == o) {
            if (QLog.isColorLevel()) {
                QLog.d(u, 2, "get CODE_SSO_FORCEQUITSDK kill " + this.r.u.getPackageName() + ":msf now");
            }
            MsfSdkUtils.killProcess(this.r.u, this.r.u.getPackageName() + ":MSF");
            return true;
        } else if (fromServiceMsg.getServiceCmd().equals(a.aj)) {
            try {
                this.r.e.b(fromServiceMsg.getUin());
                fromServiceMsg.addAttribute(a.aR, Boolean.valueOf(false));
                fromServiceMsg.setBusinessFail(a.q, fromServiceMsg.getBusinessFailMsg());
                MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
                this.r.a(toServiceMsg, fromServiceMsg);
                return true;
            } catch (Exception e) {
                if (!QLog.isColorLevel()) {
                    return true;
                }
                QLog.e(u, 2, "handle forceLogout error " + e);
                return true;
            }
        } else if (!fromServiceMsg.getServiceCmd().equals("openqq.stat_forceoffline")) {
            return false;
        } else {
            try {
                this.r.e.b(fromServiceMsg.getUin());
                ReqBody reqBody = new ReqBody();
                Object obj = new byte[(fromServiceMsg.getWupBuffer().length - 4)];
                System.arraycopy(fromServiceMsg.getWupBuffer(), 4, obj, 0, obj.length);
                reqBody.mergeFrom(obj);
                if (reqBody.uint32_clear_sig.get() == 0) {
                    this.r.e.b(fromServiceMsg.getUin());
                    fromServiceMsg.addAttribute(a.aR, Integer.valueOf(reqBody.uint32_same_device.get()));
                    fromServiceMsg.addAttribute(a.aS, reqBody.str_info.get());
                    fromServiceMsg.addAttribute(a.aT, reqBody.str_title.get());
                    fromServiceMsg.setBusinessFail(a.q, reqBody.str_info.get());
                    MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
                    this.r.a(toServiceMsg, fromServiceMsg);
                }
                ToServiceMsg toServiceMsg2 = new ToServiceMsg("", fromServiceMsg.getUin(), "openqq.stat_forceoffline");
                toServiceMsg2.addAttribute(a.aR, Integer.valueOf(reqBody.uint32_same_device.get()));
                toServiceMsg2.addAttribute(a.aS, reqBody.str_info.get());
                toServiceMsg2.addAttribute(a.aT, reqBody.str_title.get());
                RspBody rspBody = new RspBody();
                rspBody.uint32_seqno.set(reqBody.uint32_seqno.get());
                rspBody.uint32_result.set(0);
                byte[] toByteArray = rspBody.toByteArray();
                if (reqBody.uint32_clear_sig.get() != 0) {
                    toServiceMsg2.setMsfCommand(MsfCommand._msf_kickedAndCleanTokenResp);
                } else {
                    toServiceMsg2.setMsfCommand(MsfCommand._msf_kickedResp);
                }
                toServiceMsg2.setNeedCallback(false);
                toServiceMsg2.putWupBuffer(o.b(toByteArray));
                toServiceMsg2.setRequestSsoSeq(j.f());
                toServiceMsg2.setAppId(this.r.i());
                toServiceMsg2.setTimeout(a.ap);
                toServiceMsg2.addAttribute(toServiceMsg2.getServiceCmd(), fromServiceMsg);
                this.r.d.b(toServiceMsg2);
                return true;
            } catch (Exception e2) {
                if (!QLog.isColorLevel()) {
                    return true;
                }
                QLog.e(u, 2, "send offlineResp error " + e2);
                return true;
            }
        }
    }

    public void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        if (toServiceMsg != null) {
            toServiceMsg.getAttributes().remove(v.j);
        }
        if (fromServiceMsg.getServiceCmd().startsWith(a.K)) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (o.s <= 0 || elapsedRealtime - o.s <= 0 || elapsedRealtime - o.s > 30000) {
                fromServiceMsg.addAttribute(a.aJ, Integer.valueOf(-1));
            } else {
                fromServiceMsg.addAttribute(a.aJ, Long.valueOf(o.s));
            }
        }
        if (!a(fromServiceMsg, toServiceMsg)) {
            if (!(!d.i || toServiceMsg == null || toServiceMsg.getMsfCommand() == MsfCommand._msf_RegPush || toServiceMsg.getMsfCommand() == MsfCommand._msf_queryPush || this.r.e == null)) {
                elapsedRealtime = System.currentTimeMillis();
                this.r.e.a(elapsedRealtime);
                this.r.e.a(toServiceMsg.getUin(), elapsedRealtime);
            }
            if (fromServiceMsg.getServiceCmd().equals(a.aA)) {
                ToServiceMsg toServiceMsg2 = new ToServiceMsg("", fromServiceMsg.getUin(), fromServiceMsg.getServiceCmd());
                toServiceMsg2.setRequestSsoSeq(fromServiceMsg.getRequestSsoSeq());
                toServiceMsg2.setAppId(this.r.i());
                toServiceMsg2.setNeedCallback(false);
                toServiceMsg2.setTimeout(a.ap);
                this.r.a(toServiceMsg2);
                Message obtainMessage = this.w.obtainMessage();
                obtainMessage.what = 1000;
                obtainMessage.obj = fromServiceMsg.getUin();
                if (this.w.hasMessages(1000)) {
                    this.w.removeMessages(1000);
                }
                this.w.sendMessageDelayed(obtainMessage, 3000);
            }
            if (toServiceMsg != null) {
                switch (s.a[toServiceMsg.getMsfCommand().ordinal()]) {
                    case 1:
                        this.r.e.a(toServiceMsg, fromServiceMsg);
                        a(toServiceMsg);
                        return;
                    case 2:
                        this.r.e.a(toServiceMsg, fromServiceMsg);
                        return;
                    case 3:
                        this.r.e.b(toServiceMsg, fromServiceMsg);
                        return;
                    case 4:
                        elapsedRealtime = System.currentTimeMillis() / 1000;
                        try {
                            if (fromServiceMsg.isSuccess()) {
                                elapsedRealtime = (long) MsfSdkUtils.convertBytes2Int(fromServiceMsg.getWupBuffer(), 4);
                            }
                        } catch (Exception e) {
                            if (QLog.isDevelopLevel()) {
                                QLog.d(u, 4, "handle serverTime error " + e);
                            }
                        }
                        NetConnInfoCenter.handleGetServerTimeResp(elapsedRealtime);
                        return;
                    case 5:
                        this.r.p.a(fromServiceMsg);
                        return;
                    default:
                        fromServiceMsg.setAppId(toServiceMsg.getAppId());
                        fromServiceMsg.setAppSeq(toServiceMsg.getAppSeq());
                        fromServiceMsg.setMsfCommand(toServiceMsg.getMsfCommand());
                        this.r.a(toServiceMsg, fromServiceMsg);
                        return;
                }
            } else if (fromServiceMsg.getServiceCmd().equals(a.aB)) {
                d.a(j.a(fromServiceMsg));
            } else {
                this.r.e.a(fromServiceMsg);
            }
        } else if (!fromServiceMsg.getServiceCmd().startsWith(a.H)) {
        }
    }

    private void a(ToServiceMsg toServiceMsg) {
        FromServiceMsg fromServiceMsg = new FromServiceMsg(this.r.i(), j.f(), "0", a.aa);
        fromServiceMsg.setMsgSuccess();
        fromServiceMsg.setMsfCommand(MsfCommand.onConnInfo);
        fromServiceMsg.addAttribute(v.J, f.b());
        fromServiceMsg.addAttribute(v.K, Integer.valueOf(f.c()));
        fromServiceMsg.addAttribute(v.L, this.r.d.a.o().e());
        fromServiceMsg.addAttribute(v.M, j.t);
        fromServiceMsg.getAttributes().put(v.c, v.n);
        QLog.d(u, "send conn info:" + f.b() + ":" + f.c() + ":" + this.r.d.a.o().e() + ":" + j.t);
        this.r.a(null, fromServiceMsg);
    }
}
