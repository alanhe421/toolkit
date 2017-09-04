package com.tencent.qalsdk.service;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.WindowManager;
import com.dynamicload.Lib.DLConstants;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.MsfServiceBindInfo;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.k;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.im_open.UserData.QALUserData;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;
import qalsdk.f;

/* compiled from: MsfServiceReqHandler */
public class d {
    private static String a = "MSF.S.ReqHandler";

    void a(Context context, ToServiceMsg toServiceMsg, int i) {
        if (toServiceMsg != null) {
            if (j.a.get()) {
                String b = g.b(toServiceMsg);
                toServiceMsg.addAttribute(a.aH, Long.valueOf(System.currentTimeMillis()));
                switch (e.a[toServiceMsg.getMsfCommand().ordinal()]) {
                    case 1:
                        MsfServiceBindInfo msfServiceBindInfo = (MsfServiceBindInfo) toServiceMsg.getAttributes().get(v.o);
                        c.a(msfServiceBindInfo.getProcessName(), msfServiceBindInfo.getBootBoradcastName(), msfServiceBindInfo.getMsfServiceCallbacker(), toServiceMsg.getAppId());
                        j.a(g.b(toServiceMsg), toServiceMsg.getAppId());
                        j.a().n();
                        return;
                    case 2:
                        Boolean valueOf = Boolean.valueOf(true);
                        if (toServiceMsg.extraData.containsKey(v.b)) {
                            valueOf = Boolean.valueOf(toServiceMsg.extraData.getBoolean(v.b));
                        }
                        c.a(b, valueOf);
                        return;
                    case 3:
                        QalService.core.b(toServiceMsg);
                        return;
                    case 4:
                        QalService.core.c(toServiceMsg);
                        return;
                    case 5:
                        QalService.core.d(toServiceMsg);
                        return;
                    case 6:
                        QalService.core.f(toServiceMsg);
                        return;
                    case 7:
                        QalService.core.e(toServiceMsg);
                        return;
                    case 8:
                        String m = o.m();
                        FromServiceMsg a = g.a(toServiceMsg);
                        a.addAttribute(toServiceMsg.getServiceCmd(), m);
                        a.setMsgSuccess();
                        c.a(b, toServiceMsg, a);
                        return;
                    case 9:
                        a(toServiceMsg);
                        return;
                    case 10:
                        c(toServiceMsg);
                        return;
                    case 11:
                        d(toServiceMsg);
                        return;
                    case 12:
                        b(toServiceMsg);
                        return;
                    case 13:
                        e(toServiceMsg);
                        return;
                    default:
                        f(toServiceMsg);
                        QalService.core.a(toServiceMsg);
                        return;
                }
            }
            QLog.e(a, "so not load yet,return!");
        }
    }

    private void a(ToServiceMsg toServiceMsg) {
        QalService.core.d.e().remove(Integer.valueOf(toServiceMsg.getRequestSsoSeq()));
        QalService.core.m().b().removeCallbacks((Runnable) toServiceMsg.getAttributes().remove(v.j));
    }

    private void b(ToServiceMsg toServiceMsg) {
        j.a().d().d();
    }

    public static void a(byte[] bArr, int i, long j) {
        bArr[i] = (byte) ((int) (j >> 24));
        bArr[i + 1] = (byte) ((int) (j >> 16));
        bArr[i + 2] = (byte) ((int) (j >> 8));
        bArr[i + 3] = (byte) ((int) j);
    }

    private void c(ToServiceMsg toServiceMsg) {
        QalService.appVersion = (String) toServiceMsg.getAttribute("appVersion");
        QalService.appChannel = (String) toServiceMsg.getAttribute("appChannel");
    }

    private void d(ToServiceMsg toServiceMsg) {
        int intValue = ((Integer) toServiceMsg.getAttribute("logLevel")).intValue();
        QalService.getCore().a(intValue);
        QLog.setOutputLogLevel(intValue);
    }

    private void e(ToServiceMsg toServiceMsg) {
        QalService.getCore().b(((Integer) toServiceMsg.getAttribute("serverEnv")).intValue());
    }

    private void f(ToServiceMsg toServiceMsg) {
        if (QalService.core.d != null && QalService.core.d.a != null && !QalService.core.d.a.p.get()) {
            QalService.core.d.a.p.set(true);
            try {
                QALUserData qALUserData = new QALUserData();
                qALUserData.sdk_version.set(a.bg);
                if (QalService.appVersion != null) {
                    qALUserData.app_version.set(QalService.appVersion);
                }
                if (QalService.appChannel != null) {
                    qALUserData.app_channel.set(QalService.appChannel);
                }
                if (m.e() || (!m.d() && m.l() == null)) {
                    qALUserData.apn.set("wifi");
                } else {
                    qALUserData.apn.set(f.b());
                    qALUserData.radio_access.set(f.c());
                }
                qALUserData.os.set(1);
                qALUserData.os_version.set(VERSION.RELEASE);
                qALUserData.device.set(Build.MODEL);
                qALUserData.brand.set(Build.MANUFACTURER);
                WindowManager windowManager = (WindowManager) QalService.context.getSystemService("window");
                qALUserData.screen_width.set(windowManager.getDefaultDisplay().getWidth());
                qALUserData.screen_height.set(windowManager.getDefaultDisplay().getHeight());
                if (k.a() != null) {
                    qALUserData.imei.set(k.a());
                }
                if (k.b() != null) {
                    qALUserData.imsi.set(Long.valueOf(k.b()).longValue());
                }
                byte[] toByteArray = qALUserData.toByteArray();
                ToServiceMsg toServiceMsg2 = new ToServiceMsg("", toServiceMsg.getUin(), "qal.userdata");
                toServiceMsg2.setRequestSsoSeq(j.f());
                toServiceMsg2.putWupBuffer(o.b(toByteArray));
                toServiceMsg2.setUinType(20);
                toServiceMsg2.setNeedCallback(false);
                toServiceMsg2.setAppId(a.bm);
                toServiceMsg2.setTimeout(a.ap);
                QalService.core.a(toServiceMsg2);
                QLog.d(a, "report userdata:" + (qALUserData.sdk_version.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.app_version.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.app_channel.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.apn.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.radio_access.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.os.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.os_version.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.device.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.brand.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.screen_height.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.screen_width.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.imei.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALUserData.imsi.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
