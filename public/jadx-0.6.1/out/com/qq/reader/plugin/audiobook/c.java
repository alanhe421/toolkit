package com.qq.reader.plugin.audiobook;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.monitor.f;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MusicAllWorker */
public class c extends Thread {
    MusicAllTag a;
    private b b = null;

    public c(MusicAllTag musicAllTag) {
        this.a = musicAllTag;
    }

    public void run() {
        HttpResponseException e;
        Throwable th;
        Exception e2;
        Context context = null;
        if (this.b != null) {
            context = this.b.a();
        }
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        String a = com.qq.reader.common.login.c.c().a(context);
        HashMap hashMap = new HashMap();
        hashMap.put("text_type", "1");
        hashMap.put("sid", obj);
        hashMap.put("usid", a);
        hashMap.put("nosid", "1");
        hashMap.put("c_platform", "android");
        hashMap.put("c_version", "qqreader_6.5.3.0888_android");
        hashMap.put("ua", d.a());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.az);
        stringBuffer.append("bid=" + this.a.getBookId());
        InputStream a2;
        try {
            a2 = b.a(stringBuffer.toString(), null, Constants.HTTP_GET, hashMap, null, context);
            try {
                JSONObject jSONObject = new JSONObject(b.a(a2));
                int i = jSONObject.getInt("code");
                String str = (String) jSONObject.get("message");
                if (i == 0) {
                    JSONArray jSONArray = jSONObject.getJSONArray("chapters");
                    int length = jSONArray.length();
                    if (length > 0) {
                        long j = jSONObject.getLong("bid");
                        String string = jSONObject.getString("bname");
                        String string2 = jSONObject.getString("author");
                        String string3 = jSONObject.getString("reader");
                        String string4 = jSONObject.getString("btime");
                        String string5 = jSONObject.getString("price");
                        String string6 = jSONObject.getString("fileFormat");
                        int i2 = jSONObject.getInt("drm");
                        int i3 = jSONObject.getInt("ccount");
                        for (int i4 = 0; i4 < length; i4++) {
                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i4);
                            String string7 = jSONObject2.getString("csize");
                            String string8 = jSONObject2.getString("ctime");
                            long j2 = jSONObject2.getLong("cid");
                            String string9 = jSONObject2.getString("cname");
                            str = jSONObject2.getString("down_url");
                            MusicOnlineTag musicOnlineTag = new MusicOnlineTag(j);
                            musicOnlineTag.setBname(string).setAuthor(string2).setReader(string3).setBtime(string4).setPrice(string5).setCsize(string7).setCtime(string8).setCid(j2).setCname(string9).setFileFormat(string6).setDrmFlag(i2).setDownloadUrl(str).setChapterCount(i3);
                            this.a.addOnlineChapterTag(musicOnlineTag);
                        }
                        this.b.a(this.a);
                    } else {
                        this.b.a(this.a, "章节数目为0");
                    }
                } else if (i == -10) {
                    this.a.setBuyUrl((String) jSONObject.get("buy_url"));
                    this.b.b(this.a);
                } else {
                    this.b.a(this.a, str);
                }
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (HttpResponseException e4) {
                e = e4;
                try {
                    f.a("NetTask", "HttpResponseException:" + e.getStateCode());
                    this.b.a(this.a, "网络链接失败！");
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e2 = e7;
                e2.printStackTrace();
                this.b.a(this.a, "网络异常，请稍后重试");
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (IOException e8) {
                    }
                }
            }
        } catch (HttpResponseException e9) {
            e = e9;
            a2 = null;
            f.a("NetTask", "HttpResponseException:" + e.getStateCode());
            this.b.a(this.a, "网络链接失败！");
            if (a2 != null) {
                a2.close();
            }
        } catch (Exception e10) {
            e2 = e10;
            a2 = null;
            e2.printStackTrace();
            this.b.a(this.a, "网络异常，请稍后重试");
            if (a2 != null) {
                a2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            a2 = null;
            if (a2 != null) {
                a2.close();
            }
            throw th;
        }
    }

    public void a(b bVar) {
        this.b = bVar;
    }
}
