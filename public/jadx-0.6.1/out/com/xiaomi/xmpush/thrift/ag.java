package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.api.MidEntity;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.a;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class ag implements Serializable, Cloneable, a<ag, a> {
    private static final b A = new b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final b B = new b("id", JceStruct.STRUCT_END, (short) 3);
    private static final b C = new b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final b D = new b("appVersion", JceStruct.STRUCT_END, (short) 5);
    private static final b E = new b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 6);
    private static final b F = new b(Constants.FLAG_TOKEN, JceStruct.STRUCT_END, (short) 7);
    private static final b G = new b(Constants.FLAG_DEVICE_ID, JceStruct.STRUCT_END, (short) 8);
    private static final b H = new b("aliasName", JceStruct.STRUCT_END, (short) 9);
    private static final b I = new b("sdkVersion", JceStruct.STRUCT_END, (short) 10);
    private static final b J = new b("regId", JceStruct.STRUCT_END, (short) 11);
    private static final b K = new b("pushSdkVersionName", JceStruct.STRUCT_END, (short) 12);
    private static final b L = new b("pushSdkVersionCode", (byte) 8, (short) 13);
    private static final b M = new b("appVersionCode", (byte) 8, (short) 14);
    private static final b N = new b("androidId", JceStruct.STRUCT_END, (short) 15);
    private static final b O = new b(MidEntity.TAG_IMEI, JceStruct.STRUCT_END, (short) 16);
    private static final b P = new b("serial", JceStruct.STRUCT_END, (short) 17);
    private static final b Q = new b("imeiMd5", JceStruct.STRUCT_END, (short) 18);
    private static final b R = new b("spaceId", (byte) 8, (short) 19);
    private static final b S = new b("reason", (byte) 8, (short) 20);
    private static final b T = new b("connectionAttrs", JceStruct.SIMPLE_LIST, (short) 100);
    private static final b U = new b("cleanOldRegInfo", (byte) 2, (short) 101);
    private static final b V = new b("oldRegId", JceStruct.STRUCT_END, (short) 102);
    public static final Map<a, org.apache.thrift.meta_data.b> x;
    private static final j y = new j("XmPushActionRegistration");
    private static final b z = new b("debug", JceStruct.STRUCT_END, (short) 1);
    private BitSet W = new BitSet(4);
    public String a;
    public v b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public int m;
    public int n;
    public String o;
    public String p;
    public String q;
    public String r;
    public int s;
    public u t;
    public Map<String, String> u;
    public boolean v = false;
    public String w;

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("appId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("appVersion", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b(Constants.FLAG_TOKEN, (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b(Constants.FLAG_DEVICE_ID, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b("aliasName", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b("sdkVersion", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.k, new org.apache.thrift.meta_data.b("regId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.l, new org.apache.thrift.meta_data.b("pushSdkVersionName", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.m, new org.apache.thrift.meta_data.b("pushSdkVersionCode", (byte) 2, new c((byte) 8)));
        enumMap.put(a.n, new org.apache.thrift.meta_data.b("appVersionCode", (byte) 2, new c((byte) 8)));
        enumMap.put(a.o, new org.apache.thrift.meta_data.b("androidId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.p, new org.apache.thrift.meta_data.b(MidEntity.TAG_IMEI, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.q, new org.apache.thrift.meta_data.b("serial", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.r, new org.apache.thrift.meta_data.b("imeiMd5", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.s, new org.apache.thrift.meta_data.b("spaceId", (byte) 2, new c((byte) 8)));
        enumMap.put(a.t, new org.apache.thrift.meta_data.b("reason", (byte) 2, new org.apache.thrift.meta_data.a((byte) 16, u.class)));
        enumMap.put(a.u, new org.apache.thrift.meta_data.b("connectionAttrs", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        enumMap.put(a.v, new org.apache.thrift.meta_data.b("cleanOldRegInfo", (byte) 2, new c((byte) 2)));
        enumMap.put(a.w, new org.apache.thrift.meta_data.b("oldRegId", (byte) 2, new c(JceStruct.STRUCT_END)));
        x = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(ag.class, x);
    }

    public ag a(int i) {
        this.m = i;
        a(true);
        return this;
    }

    public ag a(u uVar) {
        this.t = uVar;
        return this;
    }

    public ag a(String str) {
        this.c = str;
        return this;
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                z();
                return;
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.a = eVar.w();
                        break;
                    }
                case (short) 2:
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = new v();
                    this.b.a(eVar);
                    break;
                case (short) 3:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                case (short) 4:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.d = eVar.w();
                        break;
                    }
                case (short) 5:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.e = eVar.w();
                        break;
                    }
                case (short) 6:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.f = eVar.w();
                        break;
                    }
                case (short) 7:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.g = eVar.w();
                        break;
                    }
                case (short) 8:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.h = eVar.w();
                        break;
                    }
                case (short) 9:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.i = eVar.w();
                        break;
                    }
                case (short) 10:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.j = eVar.w();
                        break;
                    }
                case (short) 11:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.k = eVar.w();
                        break;
                    }
                case (short) 12:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.l = eVar.w();
                        break;
                    }
                case (short) 13:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.m = eVar.t();
                    a(true);
                    break;
                case (short) 14:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.n = eVar.t();
                    b(true);
                    break;
                case (short) 15:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.o = eVar.w();
                        break;
                    }
                case (short) 16:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.p = eVar.w();
                        break;
                    }
                case (short) 17:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.q = eVar.w();
                        break;
                    }
                case (short) 18:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.r = eVar.w();
                        break;
                    }
                case (short) 19:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.s = eVar.t();
                    c(true);
                    break;
                case (short) 20:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.t = u.a(eVar.t());
                        break;
                    }
                case (short) 100:
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.u = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.u.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
                case (short) 101:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.v = eVar.q();
                    d(true);
                    break;
                case (short) 102:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.w = eVar.w();
                        break;
                    }
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.W.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(ag agVar) {
        if (agVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = agVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(agVar.a))) {
            return false;
        }
        a = b();
        a2 = agVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(agVar.b))) {
            return false;
        }
        a = c();
        a2 = agVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(agVar.c))) {
            return false;
        }
        a = e();
        a2 = agVar.e();
        if ((a || a2) && (!a || !a2 || !this.d.equals(agVar.d))) {
            return false;
        }
        a = f();
        a2 = agVar.f();
        if ((a || a2) && (!a || !a2 || !this.e.equals(agVar.e))) {
            return false;
        }
        a = g();
        a2 = agVar.g();
        if ((a || a2) && (!a || !a2 || !this.f.equals(agVar.f))) {
            return false;
        }
        a = i();
        a2 = agVar.i();
        if ((a || a2) && (!a || !a2 || !this.g.equals(agVar.g))) {
            return false;
        }
        a = j();
        a2 = agVar.j();
        if ((a || a2) && (!a || !a2 || !this.h.equals(agVar.h))) {
            return false;
        }
        a = k();
        a2 = agVar.k();
        if ((a || a2) && (!a || !a2 || !this.i.equals(agVar.i))) {
            return false;
        }
        a = l();
        a2 = agVar.l();
        if ((a || a2) && (!a || !a2 || !this.j.equals(agVar.j))) {
            return false;
        }
        a = m();
        a2 = agVar.m();
        if ((a || a2) && (!a || !a2 || !this.k.equals(agVar.k))) {
            return false;
        }
        a = n();
        a2 = agVar.n();
        if ((a || a2) && (!a || !a2 || !this.l.equals(agVar.l))) {
            return false;
        }
        a = o();
        a2 = agVar.o();
        if ((a || a2) && (!a || !a2 || this.m != agVar.m)) {
            return false;
        }
        a = p();
        a2 = agVar.p();
        if ((a || a2) && (!a || !a2 || this.n != agVar.n)) {
            return false;
        }
        a = q();
        a2 = agVar.q();
        if ((a || a2) && (!a || !a2 || !this.o.equals(agVar.o))) {
            return false;
        }
        a = r();
        a2 = agVar.r();
        if ((a || a2) && (!a || !a2 || !this.p.equals(agVar.p))) {
            return false;
        }
        a = s();
        a2 = agVar.s();
        if ((a || a2) && (!a || !a2 || !this.q.equals(agVar.q))) {
            return false;
        }
        a = t();
        a2 = agVar.t();
        if ((a || a2) && (!a || !a2 || !this.r.equals(agVar.r))) {
            return false;
        }
        a = u();
        a2 = agVar.u();
        if ((a || a2) && (!a || !a2 || this.s != agVar.s)) {
            return false;
        }
        a = v();
        a2 = agVar.v();
        if ((a || a2) && (!a || !a2 || !this.t.equals(agVar.t))) {
            return false;
        }
        a = w();
        a2 = agVar.w();
        if ((a || a2) && (!a || !a2 || !this.u.equals(agVar.u))) {
            return false;
        }
        a = x();
        a2 = agVar.x();
        if ((a || a2) && (!a || !a2 || this.v != agVar.v)) {
            return false;
        }
        a = y();
        a2 = agVar.y();
        return !(a || a2) || (a && a2 && this.w.equals(agVar.w));
    }

    public int b(ag agVar) {
        if (!getClass().equals(agVar.getClass())) {
            return getClass().getName().compareTo(agVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(agVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, agVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(agVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, agVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(agVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, agVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(agVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.d, agVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(agVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.e, agVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(agVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.f, agVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(agVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.g, agVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(agVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.h, agVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(agVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.i, agVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(agVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.j, agVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(agVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.k, agVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(agVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.l, agVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(agVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.b.a(this.m, agVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(p()).compareTo(Boolean.valueOf(agVar.p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (p()) {
            compareTo = org.apache.thrift.b.a(this.n, agVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(agVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.b.a(this.o, agVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(r()).compareTo(Boolean.valueOf(agVar.r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (r()) {
            compareTo = org.apache.thrift.b.a(this.p, agVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(s()).compareTo(Boolean.valueOf(agVar.s()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (s()) {
            compareTo = org.apache.thrift.b.a(this.q, agVar.q);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(t()).compareTo(Boolean.valueOf(agVar.t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (t()) {
            compareTo = org.apache.thrift.b.a(this.r, agVar.r);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(u()).compareTo(Boolean.valueOf(agVar.u()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (u()) {
            compareTo = org.apache.thrift.b.a(this.s, agVar.s);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(v()).compareTo(Boolean.valueOf(agVar.v()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (v()) {
            compareTo = org.apache.thrift.b.a(this.t, agVar.t);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(w()).compareTo(Boolean.valueOf(agVar.w()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (w()) {
            compareTo = org.apache.thrift.b.a(this.u, agVar.u);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(x()).compareTo(Boolean.valueOf(agVar.x()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (x()) {
            compareTo = org.apache.thrift.b.a(this.v, agVar.v);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(y()).compareTo(Boolean.valueOf(agVar.y()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (y()) {
            compareTo = org.apache.thrift.b.a(this.w, agVar.w);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public ag b(int i) {
        this.n = i;
        b(true);
        return this;
    }

    public ag b(String str) {
        this.d = str;
        return this;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        z();
        eVar.a(y);
        if (this.a != null && a()) {
            eVar.a(z);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(A);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(B);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null) {
            eVar.a(C);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && f()) {
            eVar.a(D);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && g()) {
            eVar.a(E);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null) {
            eVar.a(F);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && j()) {
            eVar.a(G);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && k()) {
            eVar.a(H);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && l()) {
            eVar.a(I);
            eVar.a(this.j);
            eVar.b();
        }
        if (this.k != null && m()) {
            eVar.a(J);
            eVar.a(this.k);
            eVar.b();
        }
        if (this.l != null && n()) {
            eVar.a(K);
            eVar.a(this.l);
            eVar.b();
        }
        if (o()) {
            eVar.a(L);
            eVar.a(this.m);
            eVar.b();
        }
        if (p()) {
            eVar.a(M);
            eVar.a(this.n);
            eVar.b();
        }
        if (this.o != null && q()) {
            eVar.a(N);
            eVar.a(this.o);
            eVar.b();
        }
        if (this.p != null && r()) {
            eVar.a(O);
            eVar.a(this.p);
            eVar.b();
        }
        if (this.q != null && s()) {
            eVar.a(P);
            eVar.a(this.q);
            eVar.b();
        }
        if (this.r != null && t()) {
            eVar.a(Q);
            eVar.a(this.r);
            eVar.b();
        }
        if (u()) {
            eVar.a(R);
            eVar.a(this.s);
            eVar.b();
        }
        if (this.t != null && v()) {
            eVar.a(S);
            eVar.a(this.t.a());
            eVar.b();
        }
        if (this.u != null && w()) {
            eVar.a(T);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.u.size()));
            for (Entry entry : this.u.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (x()) {
            eVar.a(U);
            eVar.a(this.v);
            eVar.b();
        }
        if (this.w != null && y()) {
            eVar.a(V);
            eVar.a(this.w);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.W.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public ag c(int i) {
        this.s = i;
        c(true);
        return this;
    }

    public ag c(String str) {
        this.e = str;
        return this;
    }

    public void c(boolean z) {
        this.W.set(2, z);
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((ag) obj);
    }

    public ag d(String str) {
        this.f = str;
        return this;
    }

    public String d() {
        return this.d;
    }

    public void d(boolean z) {
        this.W.set(3, z);
    }

    public ag e(String str) {
        this.g = str;
        return this;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof ag)) ? a((ag) obj) : false;
    }

    public ag f(String str) {
        this.h = str;
        return this;
    }

    public boolean f() {
        return this.e != null;
    }

    public ag g(String str) {
        this.l = str;
        return this;
    }

    public boolean g() {
        return this.f != null;
    }

    public ag h(String str) {
        this.o = str;
        return this;
    }

    public String h() {
        return this.g;
    }

    public int hashCode() {
        return 0;
    }

    public ag i(String str) {
        this.p = str;
        return this;
    }

    public boolean i() {
        return this.g != null;
    }

    public ag j(String str) {
        this.q = str;
        return this;
    }

    public boolean j() {
        return this.h != null;
    }

    public ag k(String str) {
        this.r = str;
        return this;
    }

    public boolean k() {
        return this.i != null;
    }

    public boolean l() {
        return this.j != null;
    }

    public boolean m() {
        return this.k != null;
    }

    public boolean n() {
        return this.l != null;
    }

    public boolean o() {
        return this.W.get(0);
    }

    public boolean p() {
        return this.W.get(1);
    }

    public boolean q() {
        return this.o != null;
    }

    public boolean r() {
        return this.p != null;
    }

    public boolean s() {
        return this.q != null;
    }

    public boolean t() {
        return this.r != null;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionRegistration(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("debug:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("appVersion:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("token:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("sdkVersion:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("pushSdkVersionName:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        if (o()) {
            stringBuilder.append(", ");
            stringBuilder.append("pushSdkVersionCode:");
            stringBuilder.append(this.m);
        }
        if (p()) {
            stringBuilder.append(", ");
            stringBuilder.append("appVersionCode:");
            stringBuilder.append(this.n);
        }
        if (q()) {
            stringBuilder.append(", ");
            stringBuilder.append("androidId:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
        }
        if (r()) {
            stringBuilder.append(", ");
            stringBuilder.append("imei:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
        }
        if (s()) {
            stringBuilder.append(", ");
            stringBuilder.append("serial:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        }
        if (t()) {
            stringBuilder.append(", ");
            stringBuilder.append("imeiMd5:");
            if (this.r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.r);
            }
        }
        if (u()) {
            stringBuilder.append(", ");
            stringBuilder.append("spaceId:");
            stringBuilder.append(this.s);
        }
        if (v()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.t == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.t);
            }
        }
        if (w()) {
            stringBuilder.append(", ");
            stringBuilder.append("connectionAttrs:");
            if (this.u == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.u);
            }
        }
        if (x()) {
            stringBuilder.append(", ");
            stringBuilder.append("cleanOldRegInfo:");
            stringBuilder.append(this.v);
        }
        if (y()) {
            stringBuilder.append(", ");
            stringBuilder.append("oldRegId:");
            if (this.w == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.w);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean u() {
        return this.W.get(2);
    }

    public boolean v() {
        return this.t != null;
    }

    public boolean w() {
        return this.u != null;
    }

    public boolean x() {
        return this.W.get(3);
    }

    public boolean y() {
        return this.w != null;
    }

    public void z() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new f("Required field 'token' was not present! Struct: " + toString());
        }
    }
}
