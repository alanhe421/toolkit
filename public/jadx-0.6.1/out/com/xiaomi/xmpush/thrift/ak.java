package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.a;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class ak implements Serializable, Cloneable, a<ak, a> {
    public static final Map<a, b> m;
    private static final j n = new j("XmPushActionSendMessage");
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 5);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("topic", JceStruct.STRUCT_END, (short) 6);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("aliasName", JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("message", JceStruct.ZERO_TAG, (short) 8);
    private static final org.apache.thrift.protocol.b w = new org.apache.thrift.protocol.b("needAck", (byte) 2, (short) 9);
    private static final org.apache.thrift.protocol.b x = new org.apache.thrift.protocol.b("params", JceStruct.SIMPLE_LIST, (short) 10);
    private static final org.apache.thrift.protocol.b y = new org.apache.thrift.protocol.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, JceStruct.STRUCT_END, (short) 11);
    private static final org.apache.thrift.protocol.b z = new org.apache.thrift.protocol.b("userAccount", JceStruct.STRUCT_END, (short) 12);
    private BitSet A = new BitSet(1);
    public String a;
    public v b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public r h;
    public boolean i = true;
    public Map<String, String> j;
    public String k;
    public String l;

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("appId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.f, new b("topic", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.g, new b("aliasName", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new b("message", (byte) 2, new g(JceStruct.ZERO_TAG, r.class)));
        enumMap.put(a.i, new b("needAck", (byte) 2, new c((byte) 2)));
        enumMap.put(a.j, new b("params", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        enumMap.put(a.k, new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.l, new b("userAccount", (byte) 2, new c(JceStruct.STRUCT_END)));
        m = Collections.unmodifiableMap(enumMap);
        b.a(ak.class, m);
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                t();
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
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.h = new r();
                    this.h.a(eVar);
                    break;
                case (short) 9:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.i = eVar.q();
                    a(true);
                    break;
                case (short) 10:
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.j = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.j.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
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
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.A.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(ak akVar) {
        if (akVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = akVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(akVar.a))) {
            return false;
        }
        a = b();
        a2 = akVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(akVar.b))) {
            return false;
        }
        a = d();
        a2 = akVar.d();
        if ((a || a2) && (!a || !a2 || !this.c.equals(akVar.c))) {
            return false;
        }
        a = f();
        a2 = akVar.f();
        if ((a || a2) && (!a || !a2 || !this.d.equals(akVar.d))) {
            return false;
        }
        a = g();
        a2 = akVar.g();
        if ((a || a2) && (!a || !a2 || !this.e.equals(akVar.e))) {
            return false;
        }
        a = i();
        a2 = akVar.i();
        if ((a || a2) && (!a || !a2 || !this.f.equals(akVar.f))) {
            return false;
        }
        a = k();
        a2 = akVar.k();
        if ((a || a2) && (!a || !a2 || !this.g.equals(akVar.g))) {
            return false;
        }
        a = m();
        a2 = akVar.m();
        if ((a || a2) && (!a || !a2 || !this.h.a(akVar.h))) {
            return false;
        }
        a = n();
        a2 = akVar.n();
        if ((a || a2) && (!a || !a2 || this.i != akVar.i)) {
            return false;
        }
        a = o();
        a2 = akVar.o();
        if ((a || a2) && (!a || !a2 || !this.j.equals(akVar.j))) {
            return false;
        }
        a = q();
        a2 = akVar.q();
        if ((a || a2) && (!a || !a2 || !this.k.equals(akVar.k))) {
            return false;
        }
        a = s();
        a2 = akVar.s();
        return !(a || a2) || (a && a2 && this.l.equals(akVar.l));
    }

    public int b(ak akVar) {
        if (!getClass().equals(akVar.getClass())) {
            return getClass().getName().compareTo(akVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(akVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, akVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(akVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, akVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(akVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, akVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(akVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.d, akVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(akVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.e, akVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(akVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.f, akVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(akVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.g, akVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(akVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.h, akVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(akVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.i, akVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(o()).compareTo(Boolean.valueOf(akVar.o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (o()) {
            compareTo = org.apache.thrift.b.a(this.j, akVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(q()).compareTo(Boolean.valueOf(akVar.q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (q()) {
            compareTo = org.apache.thrift.b.a(this.k, akVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(s()).compareTo(Boolean.valueOf(akVar.s()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (s()) {
            compareTo = org.apache.thrift.b.a(this.l, akVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        t();
        eVar.a(n);
        if (this.a != null && a()) {
            eVar.a(o);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(p);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(q);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null) {
            eVar.a(r);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && g()) {
            eVar.a(s);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && i()) {
            eVar.a(t);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && k()) {
            eVar.a(u);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && m()) {
            eVar.a(v);
            this.h.b(eVar);
            eVar.b();
        }
        if (n()) {
            eVar.a(w);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && o()) {
            eVar.a(x);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (this.k != null && q()) {
            eVar.a(y);
            eVar.a(this.k);
            eVar.b();
        }
        if (this.l != null && s()) {
            eVar.a(z);
            eVar.a(this.l);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public String c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((ak) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public String e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof ak)) ? a((ak) obj) : false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.e != null;
    }

    public String h() {
        return this.f;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public String j() {
        return this.g;
    }

    public boolean k() {
        return this.g != null;
    }

    public r l() {
        return this.h;
    }

    public boolean m() {
        return this.h != null;
    }

    public boolean n() {
        return this.A.get(0);
    }

    public boolean o() {
        return this.j != null;
    }

    public String p() {
        return this.k;
    }

    public boolean q() {
        return this.k != null;
    }

    public String r() {
        return this.l;
    }

    public boolean s() {
        return this.l != null;
    }

    public void t() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendMessage(");
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
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("message:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("needAck:");
            stringBuilder.append(this.i);
        }
        if (o()) {
            stringBuilder.append(", ");
            stringBuilder.append("params:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (q()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (s()) {
            stringBuilder.append(", ");
            stringBuilder.append("userAccount:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
