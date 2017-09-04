package com.qq.reader.plugin.audiobook;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: MusicOnlineWorker */
public class k extends Thread {
    MusicOnlineTag a;
    private j b = null;

    public k(MusicOnlineTag musicOnlineTag) {
        this.a = musicOnlineTag;
    }

    public void run() {
        Context a;
        InputStream inputStream = null;
        if (this.b != null) {
            a = this.b.a();
        } else {
            a = null;
        }
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        String a2 = c.c().a(a);
        HashMap hashMap = new HashMap();
        hashMap.put("text_type", "1");
        hashMap.put("sid", obj);
        hashMap.put("usid", a2);
        hashMap.put("nosid", "1");
        hashMap.put("c_platform", "android");
        hashMap.put("c_version", "qqreader_6.5.3.0888_android");
        hashMap.put("ua", d.a());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.ay);
        stringBuffer.append("bid=" + this.a.getBid());
        if (this.a.getCid() > 0) {
            stringBuffer.append("&");
            stringBuffer.append("cid=" + this.a.getCid());
        }
        try {
            inputStream = b.a(stringBuffer.toString(), null, Constants.HTTP_GET, hashMap, null, a);
            JSONObject jSONObject = new JSONObject(b.a(inputStream));
            String str = (String) jSONObject.get("code");
            a2 = (String) jSONObject.get("message");
            String str2 = (String) jSONObject.get("down_url");
            String str3 = (String) jSONObject.get("buy_url");
            if ("1".equals(str)) {
                this.a.setBuyUrl(str3);
                this.b.b(this.a);
            } else if ("0".equals(str)) {
                this.a.setDownloadUrl(str2);
                this.b.a(this.a);
            } else {
                this.b.a(this.a, a2);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (HttpResponseException e2) {
            f.a("NetTask", "HttpResponseException:" + e2.getStateCode());
            this.b.a(this.a, "网络链接失败！");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        } catch (Exception e4) {
            this.b.a(this.a, "网络异常，请稍后重试");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                }
            }
        }
    }

    public void a(j jVar) {
        this.b = jVar;
    }
}
