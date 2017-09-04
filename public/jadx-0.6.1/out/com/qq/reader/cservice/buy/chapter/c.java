package com.qq.reader.cservice.buy.chapter;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.monitor.l;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ChapterPayWorker */
public class c extends Thread {
    private OnlineTag a = null;
    private b b = null;
    private String c;
    private Context d;
    private int e;

    public c(OnlineTag onlineTag, List<Integer> list, int i, Context context) {
        this.a = onlineTag;
        this.c = ao.a((List) list);
        this.e = i;
        this.d = context;
    }

    public void run() {
        InputStream inputStream;
        Throwable th;
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
        if (this.a == null) {
            stringBuffer.append(e.ak);
            stringBuffer.append("bid=" + this.a.k());
            stringBuffer.append("&");
            stringBuffer.append("buyspec=1");
            stringBuffer.append("&");
            stringBuffer.append("scids=" + this.c);
        } else if (this.a.F() == 2) {
            stringBuffer.append(e.am);
            stringBuffer.append("adid=" + this.a.k());
            stringBuffer.append("&");
            stringBuffer.append("buyspec=1");
            stringBuffer.append("&");
            stringBuffer.append("acids=" + this.c);
        } else {
            stringBuffer.append(e.ak);
            stringBuffer.append("bid=" + this.a.k());
            stringBuffer.append("&");
            stringBuffer.append("buyspec=1");
            stringBuffer.append("&");
            stringBuffer.append("scids=" + this.c);
        }
        stringBuffer.append("&");
        stringBuffer.append("cost=" + this.e);
        stringBuffer.append("&");
        stringBuffer.append("channel=" + ao.h(this.d));
        String stringBuffer2 = stringBuffer.toString();
        ChapterPayResult chapterPayResult = new ChapterPayResult(this.a.k());
        InputStream a3;
        try {
            a3 = b.a(stringBuffer2, null, Constants.HTTP_GET, hashMap, null, null);
            try {
                JSONObject jSONObject = new JSONObject(b.a(a3));
                int parseInt = Integer.parseInt(jSONObject.getString("code"));
                chapterPayResult.setCode(parseInt);
                int parseInt2 = Integer.parseInt(jSONObject.getString("realprice"));
                chapterPayResult.setResultStr(jSONObject.getString(SocialConstants.PARAM_SEND_MSG));
                if (parseInt == 0 || parseInt == -4 || parseInt == -5 || parseInt == -8) {
                    jSONObject = jSONObject.getJSONObject("payInfo");
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        stringBuffer2 = (String) keys.next();
                        int i = jSONObject.getInt(stringBuffer2);
                        if (i == 0) {
                            chapterPayResult.addPayedChapters(Integer.parseInt(stringBuffer2));
                        } else if (i == -1) {
                            chapterPayResult.addNeedBuyChapters(Integer.parseInt(stringBuffer2));
                        } else if (i == 1) {
                        }
                    }
                    k.a(this.d).a(chapterPayResult.getBookId(), chapterPayResult.getPayedChapters());
                    if (this.b != null) {
                        if (parseInt == 0) {
                            this.b.a(chapterPayResult);
                        } else if (parseInt == -4) {
                            chapterPayResult.setRealCost(parseInt2);
                            this.b.c(chapterPayResult);
                        } else if (parseInt == -5) {
                            this.b.c(chapterPayResult);
                        } else if (parseInt == -8) {
                            j.a(61, 1);
                            i.a("event_B62", null, this.d);
                            StatisticsManager.a().a("event_B62", null);
                            chapterPayResult.setResultStr("实际需要支付：" + parseInt2 + "书币");
                            chapterPayResult.setRealCost(parseInt2);
                            this.b.c(chapterPayResult);
                        }
                    }
                } else if (this.b != null) {
                    this.b.b(chapterPayResult);
                }
                if (a3 != null) {
                    try {
                        a3.close();
                    } catch (IOException e) {
                    }
                }
            } catch (HttpResponseException e2) {
                inputStream = a3;
            } catch (Exception e3) {
            }
        } catch (HttpResponseException e4) {
            inputStream = null;
            try {
                if (this.b != null) {
                    chapterPayResult.setResultStr("网络连接错误，本次支付没有成功");
                    this.b.b(chapterPayResult);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                a3 = inputStream;
                th = th3;
                if (a3 != null) {
                    try {
                        a3.close();
                    } catch (IOException e6) {
                    }
                }
                throw th;
            }
        } catch (Exception e7) {
            a3 = null;
            try {
                if (this.b != null) {
                    chapterPayResult.setResultStr("网络连接错误，本次支付没有成功");
                    this.b.b(chapterPayResult);
                }
                if (a3 != null) {
                    try {
                        a3.close();
                    } catch (IOException e8) {
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                if (a3 != null) {
                    a3.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            a3 = null;
            if (a3 != null) {
                a3.close();
            }
            throw th;
        }
    }

    public void a(b bVar) {
        this.b = bVar;
    }
}
