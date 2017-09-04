package com.qq.reader.cservice.buy.a;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.l;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: BookPayWorker */
public class d extends Thread {
    private c a;
    private b b = null;
    private Context c;
    private String d = "";

    public d(Context context, String str) {
        this.c = context;
        this.a = new c(str);
    }

    public void a(int i) {
        this.a.b(i);
    }

    public void a(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public void run() {
        InputStream a;
        HttpResponseException e;
        Throwable th;
        Context context = this.c;
        Object obj = com.qq.reader.appconfig.a.d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("text_type", "1");
        hashMap.put("sid", obj);
        a c = c.c();
        if (c.b()) {
            switch (c.d()) {
                case 1:
                    String a2 = c.a(this.c);
                    hashMap.put("skey", a2);
                    hashMap.put("ckey", com.qq.reader.appconfig.a.d.a(a2));
                    break;
                case 2:
                case 10:
                case 50:
                    hashMap.put("usid", c.a(this.c));
                    hashMap.put("uid", c.c());
                    break;
            }
        }
        hashMap.put("timi", com.qq.reader.appconfig.a.d.z(this.c));
        hashMap.put("qimei", com.qq.reader.appconfig.a.d.h(this.c));
        hashMap.put("nosid", "1");
        hashMap.put("c_platform", "android");
        hashMap.put("c_version", "qqreader_6.5.3.0888_android");
        hashMap.put("ua", com.qq.reader.appconfig.a.d.a());
        hashMap.put("channel", ao.h(context));
        hashMap.put("loginType", String.valueOf(c.d()));
        hashMap.put("safekey", com.qq.reader.appconfig.a.d.y(context));
        obj = l.a(this.a.c());
        if (TextUtils.isEmpty(obj)) {
            obj = a();
        }
        hashMap.put(s.STATPARAM_KEY, obj);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.ag);
        stringBuffer.append("bid=" + this.a.c());
        try {
            a = b.a(stringBuffer.toString(), null, Constants.HTTP_GET, hashMap, null, context);
            try {
                JSONObject jSONObject = new JSONObject(b.a(a));
                int optInt = jSONObject.optInt("code");
                String optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                this.a.a(optInt);
                if (optInt == 0) {
                    this.a.b(jSONObject.optString("down_url"));
                    if (this.b != null) {
                        this.b.b(this.a);
                    }
                } else if (optInt == -6) {
                    this.a.a(ReaderApplication.getApplicationImp().getString(R.string.resign_not_enough_balance));
                    if (this.b != null) {
                        this.b.c(this.a);
                    }
                } else {
                    this.a.a(optString);
                    if (this.b != null) {
                        this.b.c(this.a);
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (HttpResponseException e3) {
                e = e3;
                try {
                    f.a("NetTask", "HttpResponseException:" + e.getStateCode());
                    this.a.a("网络链接失败！");
                    this.a.a(-1000);
                    if (this.b != null) {
                        this.b.c(this.a);
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                this.a.a(-1000);
                this.a.a("网络异常，请稍后重试");
                if (this.b != null) {
                    this.b.c(this.a);
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (IOException e7) {
                    }
                }
            }
        } catch (HttpResponseException e8) {
            e = e8;
            a = null;
            f.a("NetTask", "HttpResponseException:" + e.getStateCode());
            this.a.a("网络链接失败！");
            this.a.a(-1000);
            if (this.b != null) {
                this.b.c(this.a);
            }
            if (a != null) {
                a.close();
            }
        } catch (Exception e9) {
            a = null;
            this.a.a(-1000);
            this.a.a("网络异常，请稍后重试");
            if (this.b != null) {
                this.b.c(this.a);
            }
            if (a != null) {
                a.close();
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public void a(b bVar) {
        this.b = bVar;
    }
}
