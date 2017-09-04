package com.qq.reader.common.login.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.conn.http.HttpErrorException;
import com.qq.reader.common.login.d;
import com.qq.reader.common.login.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.GetUserAgreementVersionCodeTask;
import com.qq.reader.common.readertask.protocol.H5GameGetOpenidTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.networkUtil.b;
import com.tencent.connect.common.Constants;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AbsLoginHelper */
public abstract class a {
    protected static com.qq.reader.common.login.b.a d;
    protected Context a;
    protected e b;
    protected Map<String, String> c = new HashMap();
    private String e = (com.qq.reader.common.c.a.l + "login.log");

    public abstract boolean b();

    public abstract com.qq.reader.common.login.b.a e();

    public void a(int i, boolean z) {
        d.a(this.a, i, false);
        f.a("ReaderLoginHelper", "setLoginType:" + i);
        com.qq.reader.common.login.define.a.a(this.a, i);
        g.a().a(new GetUserAgreementVersionCodeTask(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        int optInt = new JSONObject(str).optInt("agreementVersion", -1);
                        if (optInt != -1) {
                            Map hashMap = new HashMap();
                            hashMap.put("user", com.qq.reader.common.login.define.a.e(this.a.a));
                            hashMap.put("qimei", com.qq.reader.appconfig.a.d.h(this.a.a));
                            hashMap.put("agreementVersion", optInt + "");
                            hashMap.put(Constants.PARAM_PLATFORM, "Android");
                            hashMap.put("ipAdress", b.a());
                            hashMap.put("time", this.a.a(System.currentTimeMillis()));
                            i.a("login_success_user_agreement", hashMap, this.a.a);
                        }
                    } catch (Exception e) {
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }));
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.loginok");
        intent.putExtra("loginSuccess", true);
        intent.putExtra("hasLogin", z);
        this.a.sendBroadcast(intent);
        ReaderTask h5GameGetOpenidTask = new H5GameGetOpenidTask();
        h5GameGetOpenidTask.setListener(new com.qq.reader.module.dicovery.a.c(null));
        g.a().a(h5GameGetOpenidTask);
        if (this.b != null) {
            this.b.onLoginSuccess(i);
        } else {
            f.a("login callback", "listener is null");
        }
        c();
    }

    public void a(int i, int i2, String str, Exception exception) {
        a();
        com.qq.reader.common.login.define.a.a(this.a, -1);
        int i3 = com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN;
        if (exception != null) {
            try {
                if (exception instanceof SocketTimeoutException) {
                    i3 = 1001;
                } else if (exception instanceof HttpErrorException) {
                    i3 = ((HttpErrorException) exception).getStateCode();
                } else if (exception instanceof SocketException) {
                    if (exception instanceof ConnectException) {
                        if (!(exception.getMessage() == null || exception.getMessage().indexOf("ECONNREFUSED") == -1)) {
                            i3 = 1005;
                        }
                    } else if (!(exception.getMessage() == null || exception.getMessage().indexOf("No route") == -1)) {
                        i3 = 1006;
                    }
                } else if (exception instanceof IllegalArgumentException) {
                    i3 = 1007;
                }
            } catch (Throwable th) {
            }
            this.c.put("param_FailCode", "" + i3);
        }
        if (this.b != null) {
            this.b.onLoginError(str, i, i2);
        }
        c();
    }

    public void a() {
        d = null;
        com.qq.reader.common.login.define.a.b(this.a, false);
        d.a(this.a);
        com.qq.reader.common.login.define.a.a(this.a, -1);
        com.qq.reader.common.login.define.a.a(this.a, "");
        com.qq.reader.common.login.define.a.c(this.a, null);
        com.qq.reader.common.login.define.a.l(this.a, "");
    }

    public void c() {
        this.b = null;
    }

    public int d() {
        return com.qq.reader.common.login.define.a.h(ReaderApplication.getApplicationImp().getApplicationContext());
    }

    protected void a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("time:").append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()))).append("\t");
        stringBuilder.append("action:").append(str).append("\n");
        stringBuilder.append("result:").append(str2);
        if (com.qq.reader.appconfig.b.a) {
            stringBuilder.append("\n");
            stringBuilder.append(com.qq.reader.common.monitor.debug.c.b());
            stringBuilder.append("\n");
            stringBuilder.append(" sky = ").append(e().a(ReaderApplication.getApplicationImp()));
            stringBuilder.append(" uin = ").append(e().c());
        }
        stringBuilder.append("\n");
        ao.g(stringBuilder.toString(), this.e);
    }

    public void a(e eVar) {
        this.b = eVar;
    }

    protected void f() {
        g.a().a(new ProfileNetTask(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    this.a.a(new JSONObject(str));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                f.a("LoginHelper", "success");
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.appconfig.a.d.c(this.a.a, System.currentTimeMillis());
                f.a("LoginHelper", "error");
            }
        }));
    }

    public void a(JSONObject jSONObject) throws JSONException {
        com.qq.reader.appconfig.a.d.c(null, System.currentTimeMillis());
        if (b()) {
            d = e();
            com.qq.reader.common.login.b.a.a(d, jSONObject);
        }
        if (jSONObject.optBoolean("isLogin")) {
            com.qq.reader.module.b.b.a().a(jSONObject.toString());
        }
    }

    private String a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }
}
