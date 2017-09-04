package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.taf.jce.JceStruct;
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
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class e implements Serializable, Cloneable, a<e, a> {
    public static final Map<a, b> k;
    private static final j l = new j("ClientUploadDataItem");
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("channel", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("data", JceStruct.STRUCT_END, (short) 2);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("name", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("counter", (byte) 10, (short) 4);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("timestamp", (byte) 10, (short) 5);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("fromSdk", (byte) 2, (short) 6);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("sourcePackage", JceStruct.STRUCT_END, (short) 8);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 9);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("extra", JceStruct.SIMPLE_LIST, (short) 10);
    public String a;
    public String b;
    public String c;
    public long d;
    public long e;
    public boolean f;
    public String g;
    public String h;
    public String i;
    public Map<String, String> j;
    private BitSet w = new BitSet(3);

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("channel", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new b("data", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.c, new b("name", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("counter", (byte) 2, new c((byte) 10)));
        enumMap.put(a.e, new b("timestamp", (byte) 2, new c((byte) 10)));
        enumMap.put(a.f, new b("fromSdk", (byte) 2, new c((byte) 2)));
        enumMap.put(a.g, new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new b("sourcePackage", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.i, new b("id", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.j, new b("extra", (byte) 2, new org.apache.thrift.meta_data.e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        k = Collections.unmodifiableMap(enumMap);
        b.a(e.class, k);
    }

    public e a(long j) {
        this.d = j;
        a(true);
        return this;
    }

    public e a(String str) {
        this.a = str;
        return this;
    }

    public String a() {
        return this.a;
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                o();
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
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.b = eVar.w();
                        break;
                    }
                case (short) 3:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
                        break;
                    }
                case (short) 4:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.d = eVar.u();
                    a(true);
                    break;
                case (short) 5:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = eVar.u();
                    b(true);
                    break;
                case (short) 6:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = eVar.q();
                    d(true);
                    break;
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
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.w.set(0, z);
    }

    public boolean a(e eVar) {
        if (eVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = eVar.b();
        if ((b || b2) && (!b || !b2 || !this.a.equals(eVar.a))) {
            return false;
        }
        b = c();
        b2 = eVar.c();
        if ((b || b2) && (!b || !b2 || !this.b.equals(eVar.b))) {
            return false;
        }
        b = d();
        b2 = eVar.d();
        if ((b || b2) && (!b || !b2 || !this.c.equals(eVar.c))) {
            return false;
        }
        b = e();
        b2 = eVar.e();
        if ((b || b2) && (!b || !b2 || this.d != eVar.d)) {
            return false;
        }
        b = g();
        b2 = eVar.g();
        if ((b || b2) && (!b || !b2 || this.e != eVar.e)) {
            return false;
        }
        b = h();
        b2 = eVar.h();
        if ((b || b2) && (!b || !b2 || this.f != eVar.f)) {
            return false;
        }
        b = i();
        b2 = eVar.i();
        if ((b || b2) && (!b || !b2 || !this.g.equals(eVar.g))) {
            return false;
        }
        b = k();
        b2 = eVar.k();
        if ((b || b2) && (!b || !b2 || !this.h.equals(eVar.h))) {
            return false;
        }
        b = m();
        b2 = eVar.m();
        if ((b || b2) && (!b || !b2 || !this.i.equals(eVar.i))) {
            return false;
        }
        b = n();
        b2 = eVar.n();
        return !(b || b2) || (b && b2 && this.j.equals(eVar.j));
    }

    public int b(e eVar) {
        if (!getClass().equals(eVar.getClass())) {
            return getClass().getName().compareTo(eVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(eVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, eVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(eVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.b, eVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(eVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, eVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(eVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.d, eVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(eVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.e, eVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(eVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.f, eVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(eVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.g, eVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(eVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.h, eVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(eVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.i, eVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(eVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.j, eVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public e b(long j) {
        this.e = j;
        b(true);
        return this;
    }

    public e b(String str) {
        this.b = str;
        return this;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        o();
        eVar.a(l);
        if (this.a != null && b()) {
            eVar.a(m);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && c()) {
            eVar.a(n);
            eVar.a(this.b);
            eVar.b();
        }
        if (this.c != null && d()) {
            eVar.a(o);
            eVar.a(this.c);
            eVar.b();
        }
        if (e()) {
            eVar.a(p);
            eVar.a(this.d);
            eVar.b();
        }
        if (g()) {
            eVar.a(q);
            eVar.a(this.e);
            eVar.b();
        }
        if (h()) {
            eVar.a(r);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && i()) {
            eVar.a(s);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && k()) {
            eVar.a(t);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && m()) {
            eVar.a(u);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && n()) {
            eVar.a(v);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
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
        this.w.set(1, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public e c(String str) {
        this.c = str;
        return this;
    }

    public e c(boolean z) {
        this.f = z;
        d(true);
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((e) obj);
    }

    public e d(String str) {
        this.g = str;
        return this;
    }

    public void d(boolean z) {
        this.w.set(2, z);
    }

    public boolean d() {
        return this.c != null;
    }

    public e e(String str) {
        this.h = str;
        return this;
    }

    public boolean e() {
        return this.w.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof e)) ? a((e) obj) : false;
    }

    public long f() {
        return this.e;
    }

    public e f(String str) {
        this.i = str;
        return this;
    }

    public boolean g() {
        return this.w.get(1);
    }

    public boolean h() {
        return this.w.get(2);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.g != null;
    }

    public String j() {
        return this.h;
    }

    public boolean k() {
        return this.h != null;
    }

    public String l() {
        return this.i;
    }

    public boolean m() {
        return this.i != null;
    }

    public boolean n() {
        return this.j != null;
    }

    public void o() {
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("ClientUploadDataItem(");
        Object obj2 = 1;
        if (b()) {
            stringBuilder.append("channel:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("data:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("name:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("counter:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (g()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("timestamp:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (h()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("fromSdk:");
            stringBuilder.append(this.f);
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("category:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
            obj2 = null;
        }
        if (k()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("sourcePackage:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
            obj2 = null;
        }
        if (m()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("id:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        } else {
            obj = obj2;
        }
        if (n()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("extra:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
