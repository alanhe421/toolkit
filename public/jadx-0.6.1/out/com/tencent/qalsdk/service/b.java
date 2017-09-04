package com.tencent.qalsdk.service;

import android.text.TextUtils;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IMsfServiceCallbacker;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.sdk.a;
import com.tencent.qalsdk.sdk.ae;
import com.tencent.qalsdk.sdk.w;
import com.tencent.qalsdk.util.QLog;

/* compiled from: AppProcessInfo */
public class b {
    private static final String h = "MSF.S.AppProcessInfo";
    volatile boolean a = true;
    volatile long b = 0;
    volatile boolean c = false;
    volatile long d = 0;
    String e;
    String f;
    ae<w> g = new ae();
    private IMsfServiceCallbacker i;

    public void a(String str, String str2, IMsfServiceCallbacker iMsfServiceCallbacker) {
        this.e = str;
        a(str2);
        if (iMsfServiceCallbacker != null) {
            a(iMsfServiceCallbacker);
            this.a = true;
        } else if (c() == null) {
            this.a = false;
        } else {
            this.a = true;
        }
        this.d = 0;
        this.c = false;
        if (QLog.isColorLevel()) {
            QLog.d(h, 2, str + " onAppBind, isAppConnected " + this.a);
        }
    }

    public void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        this.g.add(new w(toServiceMsg, fromServiceMsg));
        c.d.a();
    }

    public String a() {
        return this.e + "," + b() + "," + this.a;
    }

    public void a(String str) {
        this.f = str;
    }

    public String b() {
        return this.f;
    }

    public IMsfServiceCallbacker c() {
        return this.i;
    }

    public void a(IMsfServiceCallbacker iMsfServiceCallbacker) {
        this.i = iMsfServiceCallbacker;
    }

    public void d() {
        a(null);
        this.a = false;
        this.c = false;
        if (QLog.isColorLevel()) {
            QLog.d(h, 2, this.e + " setAppDisConnected, isAppConnected " + this.a);
        }
    }

    public void a(String str, FromServiceMsg fromServiceMsg) {
        if (!TextUtils.isEmpty(this.f)) {
            int b = c.e.b(str);
            a aVar = (a) j.a().b().d().get(str);
            if (aVar != null) {
                g.a(QalService.context, this.e, aVar.a, this.f, b, fromServiceMsg.getServiceCmd(), a(fromServiceMsg));
            }
        }
    }

    public static byte[] a(FromServiceMsg fromServiceMsg) {
        Object wupBuffer = fromServiceMsg.getWupBuffer();
        if (wupBuffer.length - 4 < 0) {
            return null;
        }
        Object obj = new byte[(wupBuffer.length - 4)];
        System.arraycopy(wupBuffer, 4, obj, 0, wupBuffer.length - 4);
        return obj;
    }
}
