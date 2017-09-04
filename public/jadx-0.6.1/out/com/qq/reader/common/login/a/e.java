package com.qq.reader.common.login.a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.b.f;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.WXRefreshTokenTask;
import com.qq.reader.common.readertask.protocol.WeixinLoginTask;
import com.qq.reader.common.readertask.protocol.WeixinUserInfoTask;
import com.qq.reader.view.af;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import java.util.Calendar;
import org.json.JSONObject;

/* compiled from: WXLoginHelper */
public class e extends a {
    private static volatile e e;
    private final Req f = new Req();
    private boolean g = false;

    public e(Context context) {
        this.a = context.getApplicationContext();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (e == null) {
                synchronized (e.class) {
                    if (e == null) {
                        e = new e(context);
                    }
                }
            }
            eVar = e;
        }
        return eVar;
    }

    public void a(Activity activity, Bundle bundle) {
        try {
            if (this.f == null || !WXApiManager.getInstance(this.a).isWXinstalled()) {
                this.c.put("do_login", "not installed");
                a("login by wx", "failed:didn't install wx client");
                af.a((Context) activity, (CharSequence) "请先安装微信客户端", 0).a();
                return;
            }
            this.f.scope = "snsapi_userinfo";
            this.f.state = "qq_reader_wx_login";
            WXApiManager.getInstance(activity).getWXAPIInterface().sendReq(this.f);
            a("login by wx", MessageKey.MSG_ACCEPT_TIME_START);
            this.c.put("do_login", "installed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        super.a();
        a.f(this.a, a.d(this.a));
        a.e(this.a, null);
        a.g(this.a, null);
        a.i(this.a, null);
    }

    public boolean b() {
        if (a.h(this.a) != 2 || a.d(this.a).length() <= 0) {
            return false;
        }
        return true;
    }

    public com.qq.reader.common.login.b.a e() {
        if (d == null || d.z) {
            d.z = false;
            d = new f();
        }
        return d;
    }

    public void c() {
        super.c();
    }

    public void a(String str) {
        this.c.put("get_code", "success");
        ReaderTask weixinLoginTask = new WeixinLoginTask(str);
        weixinLoginTask.registerNetTaskListener(new c(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (this.a.b(str)) {
                    a.a(this.a.a, 2);
                    this.a.g();
                    return;
                }
                i.a("event_login_by_wx", false, 0, 0, null, false, false, this.a.a);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.a(2, -4, "网络错误，请稍后重试", exception);
                this.a.a("async get access_token", "fail:" + exception.toString());
            }
        });
        g.a().a(weixinLoginTask);
    }

    public synchronized void a(final boolean z) {
        if (d() == 2) {
            ReaderTask wXRefreshTokenTask = new WXRefreshTokenTask(a.i(this.a));
            wXRefreshTokenTask.registerNetTaskListener(new c(this) {
                final /* synthetic */ e b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    boolean a = this.b.b(str);
                    this.b.a("async get access_token", "success");
                    this.b.c.put("refresh_token", "success");
                    if (a) {
                        com.qq.reader.common.monitor.f.a("ReaderLoginHelper", "token refresh ok");
                        this.b.a(2, true);
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    if (!z) {
                        this.b.c.put("refresh_token", "failed");
                        this.b.a(3, -2, "网络错误，请稍后重试", exception);
                    }
                }
            });
            g.a().a(wXRefreshTokenTask);
        }
    }

    private void g() {
        com.qq.reader.common.login.b.a e = e();
        if (e.d() == 2) {
            ReaderTask weixinUserInfoTask = new WeixinUserInfoTask(e.a(this.a), ((f) e).b(this.a));
            weixinUserInfoTask.registerNetTaskListener(new c(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.a.a(str, 2);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.c.put("get_userinfo", "failed");
                    this.a.a("get wx useinfo", "fail:" + exception.toString());
                    this.a.a(2, -2, "网络错误，请稍后重试", exception);
                }
            });
            weixinUserInfoTask.setPriority(4);
            g.a().a(weixinUserInfoTask);
            a("get wx useinfo", MessageKey.MSG_ACCEPT_TIME_START);
        }
    }

    private boolean b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("errcode");
            if (optInt > 40000) {
                a("async get access_token", "fail:" + str);
                this.c.put("get_accesstoken", "failed");
                a(optInt);
                return false;
            }
            String optString = jSONObject.optString(Constants.PARAM_ACCESS_TOKEN);
            String optString2 = jSONObject.optString("openid");
            String optString3 = jSONObject.optString("refresh_token");
            String optString4 = jSONObject.optString(Constants.PARAM_SCOPE);
            String optString5 = jSONObject.optString("unionid");
            a.a(this.a, optString);
            a.g(this.a, optString2);
            a.i(this.a, optString5);
            a.e(this.a, optString3);
            a.a(this.a, Calendar.getInstance().getTimeInMillis() + 7200000);
            a.j(this.a, optString4);
            this.c.put("get_accesstoken", "success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            a("async get access_token", "fail:" + e.toString());
            this.c.put("get_accesstoken", "failed");
            a(2, -4, "登录失败，请重新登录", e);
            return false;
        }
    }

    private void a(int i) {
        switch (i) {
            case 40030:
            case 42002:
                a(2, -1, "登录态失效，请重新登录", new Exception("wexin refresh token expire"));
                return;
            case 42001:
                a(false);
                return;
            default:
                a(2, -4, "登录失败，请重新登录", new Exception("wexin refresh token expire"));
                return;
        }
    }

    private void a(String str, int i) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("nickname");
            String optString2 = jSONObject.optString("avatar");
            String optString3 = jSONObject.optString("uid");
            a.c(this.a, optString);
            a.b(this.a, optString3);
            this.g = false;
            if (TextUtils.isEmpty(a.e(this.a))) {
                throw new Exception("no uid");
            }
            this.g = true;
            a.d(this.a, optString2);
            f();
            com.qq.reader.common.monitor.g.b(this.a);
            this.c.put("get_userinfo", "success");
            a(i, this.g);
            i.a("event_login_by_wx", true, 0, 0, null, false, false, this.a);
        } catch (Exception e) {
            this.c.put("get_userinfo", "failed");
            a(2, -4, "登录失败，请重新登录", e);
            e.printStackTrace();
            a("get wx useinfo", "fail:" + e.toString());
            i.a("event_login_by_wx", false, 0, 0, null, false, false, this.a);
        }
    }
}
