package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
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

public class x implements Serializable, Cloneable, a<x, a> {
    private static final b A = new b("messageTs", (byte) 10, (short) 5);
    private static final b B = new b("topic", JceStruct.STRUCT_END, (short) 6);
    private static final b C = new b("aliasName", JceStruct.STRUCT_END, (short) 7);
    private static final b D = new b(SocialConstants.TYPE_REQUEST, JceStruct.ZERO_TAG, (short) 8);
    private static final b E = new b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 9);
    private static final b F = new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, JceStruct.STRUCT_END, (short) 10);
    private static final b G = new b("isOnline", (byte) 2, (short) 11);
    private static final b H = new b("regId", JceStruct.STRUCT_END, (short) 12);
    private static final b I = new b("callbackUrl", JceStruct.STRUCT_END, (short) 13);
    private static final b J = new b("userAccount", JceStruct.STRUCT_END, (short) 14);
    private static final b K = new b("deviceStatus", (byte) 6, (short) 15);
    private static final b L = new b("geoMsgStatus", (byte) 6, (short) 16);
    private static final b M = new b("imeiMd5", JceStruct.STRUCT_END, (short) 20);
    private static final b N = new b(Constants.FLAG_DEVICE_ID, JceStruct.STRUCT_END, (short) 21);
    private static final b O = new b("passThrough", (byte) 8, (short) 22);
    private static final b P = new b("extra", JceStruct.SIMPLE_LIST, (short) 23);
    public static final Map<a, org.apache.thrift.meta_data.b> u;
    private static final j v = new j("XmPushActionAckMessage");
    private static final b w = new b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final b x = new b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final b y = new b("id", JceStruct.STRUCT_END, (short) 3);
    private static final b z = new b("appId", JceStruct.STRUCT_END, (short) 4);
    private BitSet Q = new BitSet(5);
    public String a;
    public v b;
    public String c;
    public String d;
    public long e;
    public String f;
    public String g;
    public ak h;
    public String i;
    public String j;
    public boolean k = false;
    public String l;
    public String m;
    public String n;
    public short o;
    public short p;
    public String q;
    public String r;
    public int s;
    public Map<String, String> t;

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("appId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("messageTs", (byte) 1, new c((byte) 10)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("topic", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b("aliasName", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b(SocialConstants.TYPE_REQUEST, (byte) 2, new g(JceStruct.ZERO_TAG, ak.class)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.k, new org.apache.thrift.meta_data.b("isOnline", (byte) 2, new c((byte) 2)));
        enumMap.put(a.l, new org.apache.thrift.meta_data.b("regId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.m, new org.apache.thrift.meta_data.b("callbackUrl", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.n, new org.apache.thrift.meta_data.b("userAccount", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.o, new org.apache.thrift.meta_data.b("deviceStatus", (byte) 2, new c((byte) 6)));
        enumMap.put(a.p, new org.apache.thrift.meta_data.b("geoMsgStatus", (byte) 2, new c((byte) 6)));
        enumMap.put(a.q, new org.apache.thrift.meta_data.b("imeiMd5", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.r, new org.apache.thrift.meta_data.b(Constants.FLAG_DEVICE_ID, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.s, new org.apache.thrift.meta_data.b("passThrough", (byte) 2, new c((byte) 8)));
        enumMap.put(a.t, new org.apache.thrift.meta_data.b("extra", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        u = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(x.class, u);
    }

    public x a(long j) {
        this.e = j;
        a(true);
        return this;
    }

    public x a(String str) {
        this.c = str;
        return this;
    }

    public x a(short s) {
        this.o = s;
        c(true);
        return this;
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (e()) {
                    u();
                    return;
                }
                throw new f("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
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
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = eVar.u();
                    a(true);
                    break;
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
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.h = new ak();
                    this.h.a(eVar);
                    break;
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
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.k = eVar.q();
                    b(true);
                    break;
                case (short) 12:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.l = eVar.w();
                        break;
                    }
                case (short) 13:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.m = eVar.w();
                        break;
                    }
                case (short) 14:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.n = eVar.w();
                        break;
                    }
                case (short) 15:
                    if (i.b != (byte) 6) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.o = eVar.s();
                    c(true);
                    break;
                case (short) 16:
                    if (i.b != (byte) 6) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.p = eVar.s();
                    d(true);
                    break;
                case (short) 20:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.q = eVar.w();
                        break;
                    }
                case (short) 21:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.r = eVar.w();
                        break;
                    }
                case (short) 22:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.s = eVar.t();
                    e(true);
                    break;
                case (short) 23:
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.t = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.t.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.Q.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(x xVar) {
        if (xVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = xVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(xVar.a))) {
            return false;
        }
        a = b();
        a2 = xVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(xVar.b))) {
            return false;
        }
        a = c();
        a2 = xVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(xVar.c))) {
            return false;
        }
        a = d();
        a2 = xVar.d();
        if (((a || a2) && (!a || !a2 || !this.d.equals(xVar.d))) || this.e != xVar.e) {
            return false;
        }
        a = f();
        a2 = xVar.f();
        if ((a || a2) && (!a || !a2 || !this.f.equals(xVar.f))) {
            return false;
        }
        a = g();
        a2 = xVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(xVar.g))) {
            return false;
        }
        a = h();
        a2 = xVar.h();
        if ((a || a2) && (!a || !a2 || !this.h.a(xVar.h))) {
            return false;
        }
        a = i();
        a2 = xVar.i();
        if ((a || a2) && (!a || !a2 || !this.i.equals(xVar.i))) {
            return false;
        }
        a = j();
        a2 = xVar.j();
        if ((a || a2) && (!a || !a2 || !this.j.equals(xVar.j))) {
            return false;
        }
        a = k();
        a2 = xVar.k();
        if ((a || a2) && (!a || !a2 || this.k != xVar.k)) {
            return false;
        }
        a = l();
        a2 = xVar.l();
        if ((a || a2) && (!a || !a2 || !this.l.equals(xVar.l))) {
            return false;
        }
        a = m();
        a2 = xVar.m();
        if ((a || a2) && (!a || !a2 || !this.m.equals(xVar.m))) {
            return false;
        }
        a = n();
        a2 = xVar.n();
        if ((a || a2) && (!a || !a2 || !this.n.equals(xVar.n))) {
            return false;
        }
        a = o();
        a2 = xVar.o();
        if ((a || a2) && (!a || !a2 || this.o != xVar.o)) {
            return false;
        }
        a = p();
        a2 = xVar.p();
        if ((a || a2) && (!a || !a2 || this.p != xVar.p)) {
            return false;
        }
        a = q();
        a2 = xVar.q();
        if ((a || a2) && (!a || !a2 || !this.q.equals(xVar.q))) {
            return false;
        }
        a = r();
        a2 = xVar.r();
        if ((a || a2) && (!a || !a2 || !this.r.equals(xVar.r))) {
            return false;
        }
        a = s();
        a2 = xVar.s();
        if ((a || a2) && (!a || !a2 || this.s != xVar.s)) {
            return false;
        }
        a = t();
        a2 = xVar.t();
        return !(a || a2) || (a && a2 && this.t.equals(xVar.t));
    }

    public int b(x xVar) {
        if (!getClass().equals(xVar.getClass())) {
            return getClass().getName().compareTo(xVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(xVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, xVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(xVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, xVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(xVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, xVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(xVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, xVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(xVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, xVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(xVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, xVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(xVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.g, xVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(xVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.h, xVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(xVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.i, xVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(xVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.j, xVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(xVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.k, xVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(xVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.l, xVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(xVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.m, xVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(xVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.n, xVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(xVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.b.a(this.o, xVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(p()).compareTo(Boolean.valueOf(xVar.p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (p()) {
            compareTo = org.apache.thrift.b.a(this.p, xVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(xVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.b.a(this.q, xVar.q);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(r()).compareTo(Boolean.valueOf(xVar.r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (r()) {
            compareTo = org.apache.thrift.b.a(this.r, xVar.r);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(s()).compareTo(Boolean.valueOf(xVar.s()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (s()) {
            compareTo = org.apache.thrift.b.a(this.s, xVar.s);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(t()).compareTo(Boolean.valueOf(xVar.t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (t()) {
            compareTo = org.apache.thrift.b.a(this.t, xVar.t);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public x b(String str) {
        this.d = str;
        return this;
    }

    public x b(short s) {
        this.p = s;
        d(true);
        return this;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        u();
        eVar.a(v);
        if (this.a != null && a()) {
            eVar.a(w);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(x);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(y);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null) {
            eVar.a(z);
            eVar.a(this.d);
            eVar.b();
        }
        eVar.a(A);
        eVar.a(this.e);
        eVar.b();
        if (this.f != null && f()) {
            eVar.a(B);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && g()) {
            eVar.a(C);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && h()) {
            eVar.a(D);
            this.h.b(eVar);
            eVar.b();
        }
        if (this.i != null && i()) {
            eVar.a(E);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && j()) {
            eVar.a(F);
            eVar.a(this.j);
            eVar.b();
        }
        if (k()) {
            eVar.a(G);
            eVar.a(this.k);
            eVar.b();
        }
        if (this.l != null && l()) {
            eVar.a(H);
            eVar.a(this.l);
            eVar.b();
        }
        if (this.m != null && m()) {
            eVar.a(I);
            eVar.a(this.m);
            eVar.b();
        }
        if (this.n != null && n()) {
            eVar.a(J);
            eVar.a(this.n);
            eVar.b();
        }
        if (o()) {
            eVar.a(K);
            eVar.a(this.o);
            eVar.b();
        }
        if (p()) {
            eVar.a(L);
            eVar.a(this.p);
            eVar.b();
        }
        if (this.q != null && q()) {
            eVar.a(M);
            eVar.a(this.q);
            eVar.b();
        }
        if (this.r != null && r()) {
            eVar.a(N);
            eVar.a(this.r);
            eVar.b();
        }
        if (s()) {
            eVar.a(O);
            eVar.a(this.s);
            eVar.b();
        }
        if (this.t != null && t()) {
            eVar.a(P);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.t.size()));
            for (Entry entry : this.t.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.Q.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public x c(String str) {
        this.f = str;
        return this;
    }

    public void c(boolean z) {
        this.Q.set(2, z);
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((x) obj);
    }

    public x d(String str) {
        this.g = str;
        return this;
    }

    public void d(boolean z) {
        this.Q.set(3, z);
    }

    public boolean d() {
        return this.d != null;
    }

    public void e(boolean z) {
        this.Q.set(4, z);
    }

    public boolean e() {
        return this.Q.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof x)) ? a((x) obj) : false;
    }

    public boolean f() {
        return this.f != null;
    }

    public boolean g() {
        return this.g != null;
    }

    public boolean h() {
        return this.h != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.i != null;
    }

    public boolean j() {
        return this.j != null;
    }

    public boolean k() {
        return this.Q.get(1);
    }

    public boolean l() {
        return this.l != null;
    }

    public boolean m() {
        return this.m != null;
    }

    public boolean n() {
        return this.n != null;
    }

    public boolean o() {
        return this.Q.get(2);
    }

    public boolean p() {
        return this.Q.get(3);
    }

    public boolean q() {
        return this.q != null;
    }

    public boolean r() {
        return this.r != null;
    }

    public boolean s() {
        return this.Q.get(4);
    }

    public boolean t() {
        return this.t != null;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionAckMessage(");
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
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.e);
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("isOnline:");
            stringBuilder.append(this.k);
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("callbackUrl:");
            if (this.m == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.m);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("userAccount:");
            if (this.n == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.n);
            }
        }
        if (o()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceStatus:");
            stringBuilder.append(this.o);
        }
        if (p()) {
            stringBuilder.append(", ");
            stringBuilder.append("geoMsgStatus:");
            stringBuilder.append(this.p);
        }
        if (q()) {
            stringBuilder.append(", ");
            stringBuilder.append("imeiMd5:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        }
        if (r()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.r);
            }
        }
        if (s()) {
            stringBuilder.append(", ");
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.s);
        }
        if (t()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.t == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.t);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void u() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        }
    }
}
