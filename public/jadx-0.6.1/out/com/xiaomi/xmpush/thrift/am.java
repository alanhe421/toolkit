package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.apache.thrift.a;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class am implements Serializable, Cloneable, a<am, a> {
    public static final Map<a, b> k;
    private static final j l = new j("XmPushActionSubscriptionResult");
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b(SocialConstants.TYPE_REQUEST, JceStruct.ZERO_TAG, (short) 5);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("errorCode", (byte) 10, (short) 6);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b("reason", JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("topic", JceStruct.STRUCT_END, (short) 8);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 9);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, JceStruct.STRUCT_END, (short) 10);
    public String a;
    public v b;
    public String c;
    public String d;
    public al e;
    public long f;
    public String g;
    public String h;
    public String i;
    public String j;
    private BitSet w = new BitSet(1);

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("appId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new b(SocialConstants.TYPE_REQUEST, (byte) 2, new g(JceStruct.ZERO_TAG, al.class)));
        enumMap.put(a.f, new b("errorCode", (byte) 2, new c((byte) 10)));
        enumMap.put(a.g, new b("reason", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new b("topic", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.i, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.j, new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, (byte) 2, new c(JceStruct.STRUCT_END)));
        k = Collections.unmodifiableMap(enumMap);
        b.a(am.class, k);
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                m();
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
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = new al();
                    this.e.a(eVar);
                    break;
                case (short) 6:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = eVar.u();
                    a(true);
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
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.j = eVar.w();
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
        this.w.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(am amVar) {
        if (amVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = amVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(amVar.a))) {
            return false;
        }
        a = b();
        a2 = amVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(amVar.b))) {
            return false;
        }
        a = c();
        a2 = amVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(amVar.c))) {
            return false;
        }
        a = d();
        a2 = amVar.d();
        if ((a || a2) && (!a || !a2 || !this.d.equals(amVar.d))) {
            return false;
        }
        a = e();
        a2 = amVar.e();
        if ((a || a2) && (!a || !a2 || !this.e.a(amVar.e))) {
            return false;
        }
        a = f();
        a2 = amVar.f();
        if ((a || a2) && (!a || !a2 || this.f != amVar.f)) {
            return false;
        }
        a = g();
        a2 = amVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(amVar.g))) {
            return false;
        }
        a = i();
        a2 = amVar.i();
        if ((a || a2) && (!a || !a2 || !this.h.equals(amVar.h))) {
            return false;
        }
        a = j();
        a2 = amVar.j();
        if ((a || a2) && (!a || !a2 || !this.i.equals(amVar.i))) {
            return false;
        }
        a = l();
        a2 = amVar.l();
        return !(a || a2) || (a && a2 && this.j.equals(amVar.j));
    }

    public int b(am amVar) {
        if (!getClass().equals(amVar.getClass())) {
            return getClass().getName().compareTo(amVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(amVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, amVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(amVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, amVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(amVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, amVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(amVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, amVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(amVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, amVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(amVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, amVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(amVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.g, amVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(amVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.h, amVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(amVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.i, amVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(amVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.j, amVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        m();
        eVar.a(l);
        if (this.a != null && a()) {
            eVar.a(m);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(n);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(o);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null && d()) {
            eVar.a(p);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && e()) {
            eVar.a(q);
            this.e.b(eVar);
            eVar.b();
        }
        if (f()) {
            eVar.a(r);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && g()) {
            eVar.a(s);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && i()) {
            eVar.a(t);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && j()) {
            eVar.a(u);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && l()) {
            eVar.a(v);
            eVar.a(this.j);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((am) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof am)) ? a((am) obj) : false;
    }

    public boolean f() {
        return this.w.get(0);
    }

    public boolean g() {
        return this.g != null;
    }

    public String h() {
        return this.h;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.h != null;
    }

    public boolean j() {
        return this.i != null;
    }

    public String k() {
        return this.j;
    }

    public boolean l() {
        return this.j != null;
    }

    public void m() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSubscriptionResult(");
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
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("errorCode:");
            stringBuilder.append(this.f);
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
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
