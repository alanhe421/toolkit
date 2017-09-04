package com.qq.reader.common.login.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.login.f;
import com.qq.reader.common.monitor.i;
import com.tencent.android.tpush.common.MessageKey;
import java.util.ArrayList;
import oicq.wlogin_sdk.request.WUserSigInfo;
import oicq.wlogin_sdk.request.WtloginHelper;
import oicq.wlogin_sdk.request.WtloginHelper.QuickLoginParam;
import oicq.wlogin_sdk.request.WtloginListener;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.RSACrypt;
import oicq.wlogin_sdk.tools.util;

/* compiled from: QQLoginHelper */
public class c extends a {
    public static WtloginHelper e = null;
    private static volatile c g;
    private static RSACrypt i;
    WtloginListener f = new WtloginListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onQuickLogin(String str, QuickLoginParam quickLoginParam, int i, ErrMsg errMsg) {
            this.a.a("QQLoginHelper", "quickLogin Result");
            if (i != 0) {
                if (true == util.shouldKick(i)) {
                    this.a.g().ClearUserLoginData(str, 683031601);
                }
                this.a.a(1, -3, errMsg.getMessage(), new Exception("uin:" + str + " login failed, ret error code: " + i));
                return;
            }
            this.a.c(str);
        }

        public void OnGetStWithoutPasswd(String str, long j, long j2, int i, long j3, WUserSigInfo wUserSigInfo, int i2, ErrMsg errMsg) {
            super.OnGetStWithoutPasswd(str, j, j2, i, j3, wUserSigInfo, i2, errMsg);
            if (i2 == 0) {
                this.a.c(str);
                return;
            }
            try {
                String e = a.e(this.a.a);
                if (e == null || !e.equals(str)) {
                    if (true == util.shouldKick(i2)) {
                        this.a.g().ClearUserLoginData(str, 683031601);
                    }
                    this.a.a(1, -3, errMsg.getMessage(), new Exception("uin:" + str + " login failed, ret error code: " + i2));
                }
            } catch (Exception e2) {
                com.qq.reader.common.monitor.debug.c.a("login", "OnGetStWithoutPasswd : " + e2.toString());
            }
        }
    };
    private f h;
    private Activity j;

    public c(Context context) {
        this.a = context.getApplicationContext();
        i = new RSACrypt(this.a);
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (g == null) {
                synchronized (c.class) {
                    if (g == null) {
                        g = new c(context);
                    }
                }
            }
            cVar = g;
        }
        return cVar;
    }

    public void a(f fVar) {
        this.h = fVar;
    }

    public synchronized WtloginHelper g() {
        if (e == null) {
            e = new WtloginHelper(this.a);
            e.SetListener(this.f);
        }
        return e;
    }

    private void a(Activity activity) {
        int quickLogin = g().quickLogin(activity, 683031601, 1, "6.5.3", null);
        if (quickLogin != 0) {
            String str = "quickLogin failed ret:" + quickLogin;
            a("QQLoginHelper", str);
            a(1, -3, str, null);
            return;
        }
        a("quick login by qq", MessageKey.MSG_ACCEPT_TIME_START);
        this.c.put("do_login_type", "quick");
    }

    public static QuickLoginParam h() {
        QuickLoginParam quickLoginParam = new QuickLoginParam();
        quickLoginParam.appid = 683031601;
        quickLoginParam.sigMap = 4096;
        return quickLoginParam;
    }

    public void a(Activity activity, Intent intent) {
        this.j = activity;
        if (g() != null) {
            g().SetImgType(3);
            int onQuickLoginActivityResultData = g().onQuickLoginActivityResultData(h(), intent);
            if (-1001 != onQuickLoginActivityResultData) {
                String str = "onQuickLoginActivityResultData failed " + onQuickLoginActivityResultData;
                a("QQLoginHelper", str);
                a(1, -3, str, null);
                return;
            }
            a("quick login by qq", "get big ticket success");
            this.c.put("get_bigticket", "success");
        } else if (this.b != null) {
            this.b.onLoginError("登录失败，请重试", 1, -6);
            a("quick login by qq", "get big ticket fail");
            this.c.put("get_bigticket", "failed");
            i.a("event_login_by_qq", false, 0, 0, this.c, this.a);
        }
    }

    private boolean b(String str) {
        return g().IsNeedLoginWithPasswd(str, 683031601).booleanValue();
    }

    public ArrayList<WloginLoginInfo> i() {
        return (ArrayList) g().GetAllLoginInfo();
    }

    public void a(String str, byte[] bArr) {
        g().CheckPictureAndGetSt(str, bArr, new WUserSigInfo());
    }

    public void a(String str) {
        g().RefreshPictureData(str, new WUserSigInfo());
    }

    private void c(String str) {
        a("quick/normal login by qq", "success");
        boolean z = !TextUtils.isEmpty(a.e(this.a));
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        g().GetBasicUserInfo(str, wloginSimpleInfo);
        a.a(this.a, 1);
        String l = new Long(wloginSimpleInfo._uin).toString();
        if (l == null || l.length() <= 0) {
            a.b(this.a, str);
        } else {
            a.b(this.a, l);
        }
        l = new String(wloginSimpleInfo._nick);
        if (l == null || l.equals("")) {
            l = new Long(wloginSimpleInfo._uin).toString();
        }
        String str2 = new String(wloginSimpleInfo._img_url);
        a.c(this.a, l);
        if (str2 != null && str2.length() > 0) {
            a.d(this.a, str2);
        }
        f();
        a(1, z);
        this.c.put("get_userinfo", "success");
        i.a("event_login_by_qq", true, 0, 0, this.c, true, false, this.a);
        a("get qq user info", MessageKey.MSG_ACCEPT_TIME_START);
    }

    public void a(Activity activity, Bundle bundle) {
        a(activity);
    }

    public void a(int i, boolean z) {
        super.a(i, z);
    }

    public void a(int i, int i2, String str, Exception exception) {
        super.a(i, i2, str, exception);
        i.a("event_login_by_qq", false, 0, 0, this.c, true, false, this.a);
    }

    public void a() {
        super.a();
    }

    public boolean b() {
        Context applicationContext = ReaderApplication.getApplicationImp().getApplicationContext();
        if (a.h(applicationContext) == 1) {
            com.qq.reader.common.login.b.a e = e();
            if (e != null) {
                String a = e.a(applicationContext);
                if (a == null || a.length() <= 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public com.qq.reader.common.login.b.a e() {
        if (d == null || d.z) {
            d.z = false;
            d = new com.qq.reader.common.login.b.d();
        }
        return d;
    }

    public void c() {
        super.c();
        this.j = null;
        e = null;
    }

    public String j() {
        com.qq.reader.common.login.b.a e = e();
        if (e != null) {
            String c = e.c();
            if (!b(c)) {
                WUserSigInfo GetLocalSig = g().GetLocalSig(c, 683031601);
                if (g().GetOpenKeyWithoutPasswd(c, 683031601, Long.parseLong("100686853"), 20480, GetLocalSig) == -1001) {
                    byte[] GetTicketSigKey = WtloginHelper.GetTicketSigKey(GetLocalSig, 16384);
                    if (GetTicketSigKey != null && GetTicketSigKey.length > 0) {
                        return util.buf_to_string(GetTicketSigKey);
                    }
                }
            }
        }
        return "";
    }

    public boolean k() {
        String c = e().c();
        if (b(c)) {
            return false;
        }
        g().GetStWithoutPasswd(c, 683031601, 683031601, 1, 4096, g().GetLocalSig(c, 683031601));
        return true;
    }
}
