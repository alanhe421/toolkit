package com.qq.reader.liveshow.c;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.utils.LogConstants;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.SxbLog.SxbLogLevel;
import com.qq.reader.liveshow.utils.e;
import com.qq.reader.liveshow.utils.f;
import com.qq.reader.liveshow.utils.o;
import com.tencent.TIMCallBack;
import com.tencent.TIMLogLevel;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.tencent.TIMUserStatusListener;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSRefreshUserSigListener;
import tencent.tls.platform.TLSUserInfo;

/* compiled from: InitBusinessHelper */
public class c {
    private static boolean a;
    private static String b = "InitBusinessHelper";
    private static TLSLoginHelper c;
    private static TLSAccountHelper d;
    private static String e = "1.0";
    private static com.qq.reader.liveshow.avcontrollers.c f = null;

    public static void a(final Context context) {
        if (!a) {
            a = true;
            SxbLog.e(b, MsfSdkUtils.isMainProcess(context) + "    is main process");
            com.qq.reader.liveshow.avcontrollers.c.a(context);
            TIMManager.getInstance().disableBeaconReport();
            TIMManager.getInstance().disableCrashReport();
            if (n.a().e().c().ordinal() > SxbLogLevel.OFF.ordinal()) {
                o.a(context.getApplicationContext());
                TIMManager.getInstance().setLogPrintEnable(true);
            } else {
                TIMManager.getInstance().setLogPrintEnable(false);
            }
            switch (n.a().e().c()) {
                case OFF:
                    TIMManager.getInstance().setLogLevel(TIMLogLevel.OFF);
                    break;
                case WARN:
                    TIMManager.getInstance().setLogLevel(TIMLogLevel.WARN);
                    break;
                case DEBUG:
                    TIMManager.getInstance().setLogLevel(TIMLogLevel.DEBUG);
                    break;
                case INFO:
                    TIMManager.getInstance().setLogLevel(TIMLogLevel.INFO);
                    break;
            }
            TIMManager.getInstance().init(context);
            TIMManager.getInstance().disableStorage();
            TIMManager.getInstance().setUserStatusListener(new TIMUserStatusListener() {
                public void onForceOffline() {
                    SxbLog.d(c.b, "onForceOffline->entered!");
                    SxbLog.c(c.b, LogConstants.h + LogConstants.a + com.qq.reader.liveshow.model.c.a().b() + LogConstants.a + "on force off line");
                    m.a(context, context.getString(h.tip_force_offline), 0);
                    com.qq.reader.liveshow.model.c.a().a(context);
                    context.sendBroadcast(new Intent("bd_sxb_exit"));
                }

                public void onUserSigExpired() {
                    SxbLog.d(c.b, "onUserSigExpired->entered!");
                    c.d();
                }
            });
            b(context);
            f.a().a(context);
        }
    }

    private static void b(String str, String str2) {
        TIMUser tIMUser = new TIMUser();
        tIMUser.setAccountType(String.valueOf(8167));
        tIMUser.setAppIdAt3rd(String.valueOf(e.c));
        tIMUser.setIdentifier(str);
        TIMManager.getInstance().login(e.c, tIMUser, str2, new TIMCallBack() {
            public void onError(int i, String str) {
                SxbLog.e(c.b, "reLoginIM fail ：" + i + DLConstants.DEPENDENCY_PACKAGE_DIV + str);
            }

            public void onSuccess() {
                SxbLog.b(c.b, "reLoginIM IMLogin succ !");
            }
        });
    }

    private static void d() {
        final Object b = com.qq.reader.liveshow.model.c.a().b();
        if (TextUtils.isEmpty(b)) {
            SxbLog.d(b, "refreshSig->with empty identifier");
        } else {
            c.TLSRefreshUserSig(com.qq.reader.liveshow.model.c.a().b(), new TLSRefreshUserSigListener() {
                public void OnRefreshUserSigSuccess(TLSUserInfo tLSUserInfo) {
                    c.b(b, c.c.getUserSig(b));
                }

                public void OnRefreshUserSigFail(TLSErrInfo tLSErrInfo) {
                    SxbLog.d(c.b, "OnRefreshUserSigFail->" + tLSErrInfo.ErrCode + DLConstants.DEPENDENCY_PACKAGE_DIV + tLSErrInfo.Msg);
                }

                public void OnRefreshUserSigTimeout(TLSErrInfo tLSErrInfo) {
                    SxbLog.d(c.b, "OnRefreshUserSigTimeout->" + tLSErrInfo.ErrCode + DLConstants.DEPENDENCY_PACKAGE_DIV + tLSErrInfo.Msg);
                }
            });
        }
    }

    public static void b(Context context) {
        c = TLSLoginHelper.getInstance().init(context, (long) e.c, 8167, e);
        c.setTimeOut(5000);
        d = TLSAccountHelper.getInstance().init(context, (long) e.c, 8167, e);
        d.setTimeOut(5000);
    }
}
