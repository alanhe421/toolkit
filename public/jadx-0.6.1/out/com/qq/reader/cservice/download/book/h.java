package com.qq.reader.cservice.download.book;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

/* compiled from: ObtainDownloadUrlWorker */
public class h extends Thread {
    g a;
    private WeakReference<f> b = null;
    private Context c;

    public h(Context context, g gVar) {
        this.a = gVar;
        this.c = context;
    }

    public void run() {
        InputStream a;
        HttpResponseException e;
        InputStream inputStream;
        f fVar;
        Throwable th;
        if (this.c != null) {
            String a2;
            Context context = this.c;
            Object obj = d.c() + "";
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
                        a2 = c.a(this.c);
                        hashMap.put("skey", a2);
                        hashMap.put("ckey", d.a(a2));
                        break;
                    case 2:
                    case 10:
                    case 50:
                        hashMap.put("usid", c.a(this.c));
                        hashMap.put("uid", c.c());
                        break;
                    default:
                        hashMap.put("usid", "");
                        break;
                }
                hashMap.put("loginType", String.valueOf(c.d()));
            }
            hashMap.put("timi", d.z(this.c));
            hashMap.put("qimei", d.h(this.c));
            hashMap.put("nosid", "1");
            hashMap.put("c_platform", "android");
            hashMap.put("c_version", "qqreader_6.5.3.0888_android");
            hashMap.put("ua", d.a());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(e.aw);
            stringBuffer.append("bid=").append(this.a.e());
            if (!TextUtils.isEmpty(this.a.f())) {
                stringBuffer.append("&format=").append(this.a.f());
            }
            stringBuffer.append("&allowlimitdownload=").append("1");
            try {
                a = b.a(stringBuffer.toString(), null, Constants.HTTP_GET, hashMap, null, context);
                try {
                    f fVar2;
                    JSONObject jSONObject = new JSONObject(b.a(a));
                    String str = (String) jSONObject.get("code");
                    a2 = (String) jSONObject.get("message");
                    String str2 = (String) jSONObject.get("down_url");
                    String str3 = (String) jSONObject.get("buy_url");
                    this.a.a(jSONObject.optInt("paycheckcode", -1));
                    if (this.a.a() == 5) {
                        this.a.a(new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.CHINA).parse(jSONObject.optString("endTime")).getTime());
                    }
                    this.a.a(a2);
                    if (this.b != null) {
                        fVar2 = (f) this.b.get();
                    } else {
                        fVar2 = null;
                    }
                    if ("-10".equals(str)) {
                        this.a.d(str3);
                        str = (String) jSONObject.opt("trial_down_url");
                        if (!TextUtils.isEmpty(str)) {
                            this.a.c(str);
                        }
                        if (fVar2 != null) {
                            fVar2.c(this.a);
                        }
                    } else if ("0".equals(str)) {
                        this.a.b(str2);
                        if (fVar2 != null) {
                            fVar2.a(this.a);
                        }
                    } else if (fVar2 != null) {
                        fVar2.b(this.a);
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (HttpResponseException e3) {
                    e = e3;
                    inputStream = a;
                    try {
                        f.a("NetTask", "HttpResponseException:" + e.getStateCode());
                        this.a.a("网络链接失败！");
                        if (this.b != null) {
                            fVar = (f) this.b.get();
                            if (fVar != null) {
                                fVar.b(this.a);
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        a = inputStream;
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception e6) {
                    try {
                        this.a.a("网络异常，请稍后重试");
                        if (this.b != null) {
                            fVar = (f) this.b.get();
                            if (fVar != null) {
                                fVar.b(this.a);
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e7) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            } catch (HttpResponseException e8) {
                e = e8;
                inputStream = null;
                f.a("NetTask", "HttpResponseException:" + e.getStateCode());
                this.a.a("网络链接失败！");
                if (this.b != null) {
                    fVar = (f) this.b.get();
                    if (fVar != null) {
                        fVar.b(this.a);
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e9) {
                a = null;
                this.a.a("网络异常，请稍后重试");
                if (this.b != null) {
                    fVar = (f) this.b.get();
                    if (fVar != null) {
                        fVar.b(this.a);
                    }
                }
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th4) {
                th = th4;
                a = null;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        }
    }

    public void a(f fVar) {
        this.b = new WeakReference(fVar);
    }
}
