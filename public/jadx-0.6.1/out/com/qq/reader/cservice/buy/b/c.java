package com.qq.reader.cservice.buy.b;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: FontPayWorker */
public class c extends Thread {
    private b a;
    private a b;
    private Context c;

    public c(b bVar, Context context) {
        this.a = bVar;
        this.c = context;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void run() {
        InputStream a;
        HttpResponseException e;
        Throwable th;
        super.run();
        Context context = this.c;
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("text_type", "1");
        hashMap.put("sid", obj);
        a c = com.qq.reader.common.login.c.c();
        if (com.qq.reader.common.login.c.b()) {
            switch (c.d()) {
                case 1:
                    String a2 = c.a(this.c);
                    hashMap.put("skey", a2);
                    hashMap.put("ckey", d.a(a2));
                    break;
                case 2:
                case 10:
                case 50:
                    hashMap.put("usid", c.a(this.c));
                    hashMap.put("uid", c.c());
                    break;
            }
        }
        hashMap.put("timi", d.z(this.c));
        hashMap.put("qimei", d.h(this.c));
        hashMap.put("nosid", "1");
        hashMap.put("c_platform", "android");
        hashMap.put("c_version", "qqreader_6.5.3.0888_android");
        hashMap.put("ua", d.a());
        hashMap.put("channel", ao.h(context));
        hashMap.put("loginType", String.valueOf(c.d()));
        hashMap.put("safekey", d.y(context));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.cO);
        stringBuffer.append("?id=" + this.a.a());
        try {
            a = b.a(stringBuffer.toString(), null, Constants.HTTP_GET, hashMap, null, context);
            try {
                JSONObject jSONObject = new JSONObject(b.a(a));
                int i = jSONObject.getInt("code");
                if (i == 0) {
                    this.a.c(jSONObject.getString("plugin_url"));
                    if (this.b != null) {
                        this.b.a(this.a);
                    }
                } else if (i == -1) {
                    this.a.b("支付失败");
                    if (this.b != null) {
                        this.b.b(this.a);
                    }
                } else if (i == -3) {
                    this.a.b("书币余额不足，请充值");
                    if (this.b != null) {
                        this.b.c(this.a);
                    }
                } else {
                    this.a.b("系统错误");
                    if (this.b != null) {
                        this.b.b(this.a);
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
                    this.a.b("网络链接失败！");
                    if (this.b != null) {
                        this.b.b(this.a);
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
                this.a.b("网络异常，请稍后重试");
                if (this.b != null) {
                    this.b.b(this.a);
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
            this.a.b("网络链接失败！");
            if (this.b != null) {
                this.b.b(this.a);
            }
            if (a != null) {
                a.close();
            }
        } catch (Exception e9) {
            a = null;
            this.a.b("网络异常，请稍后重试");
            if (this.b != null) {
                this.b.b(this.a);
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
}
