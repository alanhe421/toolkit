package com.qq.reader.cservice.buy.chapter;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.l;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* compiled from: AudioChapterPayWorker */
public class a extends Thread {
    private OnlineTag a = null;
    private b b = null;
    private String c;
    private Context d;
    private int e;

    public a(OnlineTag onlineTag, List<Integer> list, int i, Context context) {
        this.a = onlineTag;
        this.c = ao.a((List) list);
        this.e = i;
        this.d = context;
    }

    public void run() {
        Throwable th;
        InputStream inputStream = null;
        Object obj = d.c() + "";
        if (obj.equals("0")) {
            obj = "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("text_type", "1");
        hashMap.put("sid", obj);
        com.qq.reader.common.login.b.a c = c.c();
        if (c.b()) {
            switch (c.d()) {
                case 1:
                    String a = c.a(this.d);
                    hashMap.put("skey", a);
                    hashMap.put("ckey", d.a(a));
                    break;
                case 2:
                case 10:
                case 50:
                    hashMap.put("usid", c.a(this.d));
                    hashMap.put("uid", c.c());
                    break;
                default:
                    hashMap.put("usid", "");
                    break;
            }
        }
        hashMap.put("timi", d.z(this.d));
        hashMap.put("qimei", d.h(this.d));
        hashMap.put("nosid", "1");
        hashMap.put("c_platform", "android");
        hashMap.put("c_version", "qqreader_6.5.3.0888_android");
        hashMap.put("ua", d.a());
        hashMap.put("loginType", String.valueOf(c.d()));
        CharSequence a2 = l.a(this.a.k());
        if (!TextUtils.isEmpty(a2)) {
            hashMap.put(s.STATPARAM_KEY, a2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.am);
        stringBuffer.append("adid=" + this.a.k());
        stringBuffer.append("&");
        stringBuffer.append("acids=" + this.c);
        String stringBuffer2 = stringBuffer.toString();
        ChapterPayResult chapterPayResult = new ChapterPayResult(this.a.k());
        InputStream a3;
        try {
            a3 = b.a(stringBuffer2, null, Constants.HTTP_GET, hashMap, null, null);
            try {
                JSONObject jSONObject = new JSONObject(b.a(a3));
                int optInt = jSONObject.optInt("retCode");
                chapterPayResult.setCode(optInt);
                jSONObject.optInt("realprice", 0);
                chapterPayResult.setResultStr(jSONObject.getString("retMsg"));
                if (optInt == 0) {
                    if (this.b != null && optInt == 0) {
                        this.b.a(chapterPayResult);
                    }
                } else if (optInt == -100199) {
                    chapterPayResult.setCode(optInt);
                    chapterPayResult.setResultStr(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_off_market_tip));
                    if (this.b != null) {
                        this.b.b(chapterPayResult);
                    }
                } else if (optInt == -100204) {
                    chapterPayResult.setCode(optInt);
                    chapterPayResult.setResultStr(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_check_level_limit));
                    if (this.b != null) {
                        this.b.b(chapterPayResult);
                    }
                } else {
                    chapterPayResult.setResultStr(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_error_common_tip));
                    if (this.b != null) {
                        this.b.b(chapterPayResult);
                    }
                }
                if (a3 != null) {
                    try {
                        a3.close();
                    } catch (IOException e) {
                    }
                }
            } catch (HttpResponseException e2) {
                try {
                    if (this.b != null) {
                        chapterPayResult.setResultStr("网络连接错误，本次支付没有成功");
                        this.b.b(chapterPayResult);
                    }
                    if (a3 != null) {
                        try {
                            a3.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (Throwable th2) {
                    inputStream = a3;
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                if (this.b != null) {
                    chapterPayResult.setResultStr("网络连接错误，本次支付没有成功");
                    this.b.b(chapterPayResult);
                }
                if (a3 != null) {
                    try {
                        a3.close();
                    } catch (IOException e6) {
                    }
                }
            }
        } catch (HttpResponseException e7) {
            a3 = null;
            if (this.b != null) {
                chapterPayResult.setResultStr("网络连接错误，本次支付没有成功");
                this.b.b(chapterPayResult);
            }
            if (a3 != null) {
                a3.close();
            }
        } catch (Exception e8) {
            a3 = null;
            if (this.b != null) {
                chapterPayResult.setResultStr("网络连接错误，本次支付没有成功");
                this.b.b(chapterPayResult);
            }
            if (a3 != null) {
                a3.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public void a(b bVar) {
        this.b = bVar;
    }
}
