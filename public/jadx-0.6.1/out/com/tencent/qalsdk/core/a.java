package com.tencent.qalsdk.core;

import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.tencent.qalsdk.util.CodecWarpper;
import com.tencent.qalsdk.util.QLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: AccountCenter */
public class a {
    private static final String a = "MSF.C.AccountCenter";
    private static final String b = "key_account_head_";
    private String c = null;
    private ConcurrentHashMap<String, com.tencent.qalsdk.sdk.a> d = new ConcurrentHashMap();

    public void a() {
        ArrayList arrayList = new ArrayList();
        try {
            a(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b((com.tencent.qalsdk.sdk.a) it.next());
            }
        } catch (UnsatisfiedLinkError e) {
            QLog.e(a, "AccountCenter init UnsatisfiedLinkError. so init:" + j.a.get());
        }
    }

    private void b(com.tencent.qalsdk.sdk.a aVar) {
        try {
            c(aVar);
            o.l = aVar.f;
            CodecWarpper.setAccountKey(aVar.b, null, aVar.c, null, null, aVar.d, null, aVar.e, null, null);
            o.a(aVar.b, false);
            QLog.i(a, 2, "handle account: " + aVar.b + ":" + aVar.a);
        } catch (Throwable e) {
            QLog.e(a, 1, "parse account error " + e.toString(), e);
        }
    }

    private void a(ArrayList<com.tencent.qalsdk.sdk.a> arrayList) {
        if (arrayList.size() == 0) {
            String[] configList = l.a().getConfigList(b);
            if (configList != null && configList.length > 0) {
                QLog.d(a, 1, "try load accounts " + configList.length);
                for (String a : configList) {
                    try {
                        c cVar = new c(com.qq.taf.jce.a.a(a));
                        com.tencent.qalsdk.sdk.a aVar = new com.tencent.qalsdk.sdk.a();
                        aVar.readFrom(cVar);
                        arrayList.add(aVar);
                        QLog.i(a, 2, "load account tinyid:" + aVar.d() + ":" + aVar.a + " registed:" + aVar.i());
                    } catch (Throwable th) {
                        QLog.w(a, 1, "parse account error " + th.toString(), th);
                    }
                }
            }
        }
    }

    public boolean a(String str) {
        e(str);
        QLog.i(a, 2, "del user " + str + " succ.");
        this.d.remove(str);
        return true;
    }

    public ArrayList<String> b() {
        ArrayList<String> arrayList = new ArrayList();
        for (String add : this.d.keySet()) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public boolean b(String str) {
        com.tencent.qalsdk.sdk.a c = c(str);
        if (c == null || c.g != (byte) 1) {
            return false;
        }
        return true;
    }

    com.tencent.qalsdk.sdk.a c(String str) {
        return (com.tencent.qalsdk.sdk.a) this.d.get(str);
    }

    private void c(com.tencent.qalsdk.sdk.a aVar) {
        this.d.put(aVar.d(), aVar);
        QLog.i(a, "addAccount " + aVar.b + ":" + aVar.a);
    }

    private void e(String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    l.a().n_removeConfig(b + str);
                }
            } catch (Exception e) {
                QLog.e(a, "removeAccount exception:" + e.getMessage());
            }
        }
    }

    private void f(String str) {
        try {
            com.tencent.qalsdk.sdk.a c = c(str);
            d dVar = new d();
            c.writeTo(dVar);
            l.a().n_setConfig(b + str, com.qq.taf.jce.a.a(dVar.b()));
            QLog.i(a, 4, "storeAccount tinyID:" + str);
        } catch (Exception e) {
            QLog.e(a, "storeAccount exception:" + e.getMessage());
        }
    }

    public synchronized void a(com.tencent.qalsdk.sdk.a aVar) {
        try {
            c(aVar);
            o.l = aVar.f;
            CodecWarpper.setAccountKey(aVar.b, null, aVar.c, null, null, aVar.d, null, aVar.e, null, null);
            o.a(aVar.d(), false);
            this.c = aVar.b;
            f(aVar.b);
        } catch (Exception e) {
            QLog.e(a, "setAndStorAccount exception:" + e.getMessage());
        }
    }

    public String c() {
        return this.c;
    }

    public ConcurrentHashMap<String, com.tencent.qalsdk.sdk.a> d() {
        return this.d;
    }

    public synchronized void a(String str, long j) {
        com.tencent.qalsdk.sdk.a c = c(str);
        if (c != null) {
            c.a(j);
        }
    }

    public long d(String str) {
        com.tencent.qalsdk.sdk.a c = c(str);
        if (c != null) {
            return c.j();
        }
        return 0;
    }
}
