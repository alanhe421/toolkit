package com.tencent.mid.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.LocalServerSocket;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.b.g;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.a;
import com.tencent.mid.util.b;
import com.tencent.mid.util.f;
import com.tencent.mid.util.h;
import com.tencent.mid.util.i;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

public class c {
    private static f c = Util.getLogger();
    private static c i = null;
    private static Context j = null;
    int a = -1;
    LocalServerSocket b = null;
    private a d = null;
    private a e = null;
    private long f = 0;
    private int g = 0;
    private int h = -1;

    private c(Context context) {
        try {
            j = context.getApplicationContext();
        } catch (Throwable th) {
            c.f(th);
        }
    }

    static Context a() {
        return j;
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (i == null) {
                i = new c(context);
            }
            cVar = i;
        }
        return cVar;
    }

    private static void a(String str, int i) {
        if (Util.isMidValid(str)) {
            if (!Util.isMidValid(g.a(j).b())) {
                i = 3;
            }
            c.b("updateNewVersionMidEntity reset:" + i);
            if (i > 0) {
                MidEntity midEntity = new MidEntity();
                midEntity.setMid(str);
                midEntity.setMac(Util.getWifiMacAddress(j));
                midEntity.setImei(Util.getImei(j));
                midEntity.setImsi(Util.getImsi(j));
                midEntity.setTimestamps(System.currentTimeMillis());
                midEntity.setVersion(3);
                c.b("server return new version mid midEntity:" + midEntity.toString());
                switch (i) {
                    case 1:
                        g.a(j).b(midEntity);
                        break;
                    case 2:
                        g.a(j).c(midEntity);
                        break;
                    case 3:
                        g.a(j).a(midEntity);
                        break;
                    case 4:
                        g.a(j).f(midEntity);
                        g.a(j).a(midEntity);
                        break;
                    case 8:
                        g.a(j).f(midEntity);
                        g.a(j).a(midEntity);
                        g.a(j).g(midEntity);
                        break;
                }
                g.a(j).a(-1, -1);
            }
        }
    }

    private static void a(String str, int i, MidCallback midCallback) {
        if (Util.isMidValid(str)) {
            if (!Util.isMidValid(g.c(j))) {
                i = 4;
            }
            c.b("updateMidEntity reset:" + i);
            if (i > 0) {
                MidEntity midEntity = new MidEntity();
                midEntity.setMid(str);
                midEntity.setMac(Util.getWifiMacAddress(j));
                midEntity.setImei(Util.getImei(j));
                midEntity.setImsi(Util.getImsi(j));
                midEntity.setTimestamps(System.currentTimeMillis());
                midEntity.setVersion(3);
                c.b("server return new mid midEntity:" + midEntity.toString());
                midCallback.onSuccess(midEntity.toString());
                switch (i) {
                    case 1:
                        g.a(j).d(midEntity);
                        break;
                    case 2:
                        g.a(j).e(midEntity);
                        break;
                    case 3:
                        g.a(j).f(midEntity);
                        break;
                    case 4:
                        g.a(j).f(midEntity);
                        g.a(j).g(midEntity);
                        break;
                }
                g.a(j).a(-1, -1);
            }
        }
    }

    private void b(int i, e eVar, MidCallback midCallback) {
        Throwable th;
        a aVar = null;
        c.b(" enter http request, type:" + i);
        a aVar2 = null;
        a aVar3;
        try {
            String str = "";
            if (e()) {
                str = "Http request failed too much, please check the network.";
                c.f(str);
                if (midCallback != null) {
                    midCallback.onFail(MidConstants.ERROR_HTTP_FAILED_TOO_MUCH, str);
                }
                if (null != null) {
                    try {
                        aVar2.a();
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                }
                return;
            }
            b a = b.a(j);
            aVar3 = new a(Util.getHttpAddr(j), null);
            try {
                JSONObject jSONObject = new JSONObject();
                eVar.a(jSONObject);
                jSONObject.put("rty", i);
                if (this.h > 0) {
                    jSONObject.put("seq", this.h);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("android", jSONObject);
                jSONObject2.put("mid_list", Util.queryMids(j, 1));
                jSONObject2.put("mid_list_new", Util.queryMids(j, 2));
                String jSONObject3 = jSONObject2.toString();
                c.b("jsonBodyStr:" + jSONObject3);
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(jSONObject3.length());
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(jSONObject3.getBytes("UTF-8"));
                gZIPOutputStream.close();
                byteArrayOutputStream.flush();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                a a2 = a(i);
                byteArrayOutputStream.reset();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                jSONObject3 = a.f();
                if (i == 1 || i == 3) {
                    jSONObject3 = i == 1 ? a.d() : a.e();
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(64);
                    byteArrayOutputStream2.write(a2.b());
                    byteArrayOutputStream2.write(a2.c());
                    byteArrayOutputStream2.close();
                    byte[] toByteArray2 = byteArrayOutputStream2.toByteArray();
                    h.a(a.b());
                    toByteArray2 = h.a(toByteArray2);
                    dataOutputStream.writeShort(a.a());
                    dataOutputStream.writeShort(toByteArray2.length);
                    dataOutputStream.write(toByteArray2);
                }
                dataOutputStream.write(a2.a(toByteArray));
                dataOutputStream.close();
                byteArrayOutputStream.close();
                d a3 = aVar3.a(jSONObject3, byteArrayOutputStream.toByteArray(), "gzip", i);
                String str2;
                if (a3.a() != 200) {
                    str2 = "response code invalid:" + a3.a();
                    c.d(str2);
                    midCallback.onFail(a3.a(), str2);
                    if (aVar3 != null) {
                        try {
                            aVar3.a();
                            return;
                        } catch (Throwable th22) {
                            th22.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                int i2;
                str2 = "";
                jSONObject = a3.b();
                if (jSONObject.has("ret_code") || jSONObject.has("ret_msg")) {
                    i2 = jSONObject.getInt("ret_code");
                    String str3 = "response code:" + i2 + ",msg:" + jSONObject.getString("ret_msg");
                    c.d(str3);
                    if (i2 != 0) {
                        midCallback.onFail(i2, str3);
                        if (aVar3 != null) {
                            try {
                                aVar3.a();
                                return;
                            } catch (Throwable th222) {
                                th222.printStackTrace();
                                return;
                            }
                        }
                        return;
                    }
                }
                if (!jSONObject.isNull("seq")) {
                    this.h = jSONObject.getInt("seq");
                }
                if (!jSONObject.isNull("mid")) {
                    a(jSONObject.getString("mid"), jSONObject.optInt("reset", 0), midCallback);
                }
                i2 = jSONObject.optInt("locW", -1);
                if (i2 > -1) {
                    i.a(j).a("ten.mid.allowCheckAndRewriteLocal.bool", i2);
                }
                a(jSONObject.optString(MidConstants.NEW_MID_TAG), jSONObject.optInt("reset_new", 0));
                if (aVar3 != null) {
                    try {
                        aVar3.a();
                    } catch (Throwable th2222) {
                        th2222.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th2222 = th3;
                if (aVar3 != null) {
                    aVar3.a();
                }
                throw th2222;
            }
        } catch (Throwable th4) {
            th2222 = th4;
            aVar3 = null;
            if (aVar3 != null) {
                aVar3.a();
            }
            throw th2222;
        }
    }

    private void c() {
        this.f = 0;
        this.g = 0;
    }

    private void d() {
        this.g++;
        this.f = System.currentTimeMillis();
    }

    private boolean e() {
        if (this.g > 3) {
            if (System.currentTimeMillis() - this.f < 1800000) {
                return true;
            }
            c();
        }
        return false;
    }

    private boolean f() {
        String str = "com.tencent.teg.mid.sock.lock";
        try {
            this.b = new LocalServerSocket(str);
            c.h("open socket mLocalServerSocket:" + this.b);
            return true;
        } catch (IOException e) {
            c.d("socket Name:" + str + " is in use.");
            return false;
        } catch (Throwable th) {
            c.d("something wrong while create LocalServerSocket.");
            return false;
        }
    }

    private void g() {
        if (this.b != null) {
            try {
                this.b.close();
                c.b("close socket  mLocalServerSocket:" + this.b);
                this.b = null;
            } catch (Throwable th) {
            }
        }
    }

    a a(int i) {
        if (i == 1) {
            if (this.d == null) {
                this.d = new a();
                this.d.e();
            }
            return this.d;
        }
        if (this.e == null) {
            this.e = new a();
            this.e.a("key-/.*$!xx", "vec-;*5@)&%(");
        }
        return this.e;
    }

    void a(int i, e eVar, MidCallback midCallback) {
        if (eVar == null || midCallback == null) {
            if (midCallback != null) {
                midCallback.onFail(-10000, "packet == null || handler == null");
            }
            c.f("packet == null || handler == null || cb == null");
        } else if (Util.isNetworkAvailable(j)) {
            MidEntity a;
            int i2 = 0;
            while (!f()) {
                int i3 = i2 + 1;
                if (i2 >= 10) {
                    break;
                }
                try {
                    Thread.sleep(500);
                    i2 = i3;
                } catch (InterruptedException e) {
                    i2 = i3;
                }
            }
            if (i == 1) {
                a = g.a(j);
                if (Util.isMidValid(a)) {
                    midCallback.onSuccess(a);
                    g();
                    return;
                }
            }
            if (i == 3) {
                a = g.a(j).a();
                if (Util.isMidValid(a)) {
                    midCallback.onSuccess(a);
                    g();
                    return;
                }
            }
            if (b()) {
                b(i, eVar, midCallback);
                g();
                return;
            }
            g();
        } else {
            midCallback.onFail(MidConstants.ERROR_NETWORK, "network not available.");
        }
    }

    boolean b() {
        int i = this.a;
        this.a = i + 1;
        if (i > 1000) {
            c.f("send count limit " + this.a);
            return false;
        }
        SharedPreferences a = com.tencent.mid.api.a.a(j).a();
        if (a != null) {
            String str = "SEND_LIMIT_" + Util.getDateString(0);
            if (this.a == 0) {
                this.a = a.getInt(str, 0);
            }
            a.edit().putInt(str, this.a);
        }
        return true;
    }
}
