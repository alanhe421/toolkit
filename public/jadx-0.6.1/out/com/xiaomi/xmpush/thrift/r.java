package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
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

public class r implements Serializable, Cloneable, a<r, a> {
    public static final Map<a, b> i;
    private static final j j = new j("PushMessage");
    private static final org.apache.thrift.protocol.b k = new org.apache.thrift.protocol.b("to", JceStruct.ZERO_TAG, (short) 1);
    private static final org.apache.thrift.protocol.b l = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 2);
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("payload", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("createAt", (byte) 10, (short) 5);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("ttl", (byte) 10, (short) 6);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("collapseKey", JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 8);
    public v a;
    public String b;
    public String c;
    public String d;
    public long e;
    public long f;
    public String g;
    public String h;
    private BitSet s = new BitSet(2);

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("to", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.b, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.c, new b("appId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("payload", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new b("createAt", (byte) 2, new c((byte) 10)));
        enumMap.put(a.f, new b("ttl", (byte) 2, new c((byte) 10)));
        enumMap.put(a.g, new b("collapseKey", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        i = Collections.unmodifiableMap(enumMap);
        b.a(r.class, i);
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
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = new v();
                    this.a.a(eVar);
                    break;
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
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = eVar.u();
                    b(true);
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
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.s.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(r rVar) {
        if (rVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = rVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.a(rVar.a))) {
            return false;
        }
        a = c();
        a2 = rVar.c();
        if ((a || a2) && (!a || !a2 || !this.b.equals(rVar.b))) {
            return false;
        }
        a = e();
        a2 = rVar.e();
        if ((a || a2) && (!a || !a2 || !this.c.equals(rVar.c))) {
            return false;
        }
        a = g();
        a2 = rVar.g();
        if ((a || a2) && (!a || !a2 || !this.d.equals(rVar.d))) {
            return false;
        }
        a = i();
        a2 = rVar.i();
        if ((a || a2) && (!a || !a2 || this.e != rVar.e)) {
            return false;
        }
        a = j();
        a2 = rVar.j();
        if ((a || a2) && (!a || !a2 || this.f != rVar.f)) {
            return false;
        }
        a = k();
        a2 = rVar.k();
        if ((a || a2) && (!a || !a2 || !this.g.equals(rVar.g))) {
            return false;
        }
        a = l();
        a2 = rVar.l();
        return !(a || a2) || (a && a2 && this.h.equals(rVar.h));
    }

    public int b(r rVar) {
        if (!getClass().equals(rVar.getClass())) {
            return getClass().getName().compareTo(rVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(rVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, rVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(rVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.b, rVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(rVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, rVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(rVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.d, rVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(rVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.e, rVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(rVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.f, rVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(rVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.g, rVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(rVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.h, rVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public String b() {
        return this.b;
    }

    public void b(e eVar) {
        m();
        eVar.a(j);
        if (this.a != null && a()) {
            eVar.a(k);
            this.a.b(eVar);
            eVar.b();
        }
        if (this.b != null) {
            eVar.a(l);
            eVar.a(this.b);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(m);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null) {
            eVar.a(n);
            eVar.a(this.d);
            eVar.b();
        }
        if (i()) {
            eVar.a(o);
            eVar.a(this.e);
            eVar.b();
        }
        if (j()) {
            eVar.a(p);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && k()) {
            eVar.a(q);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && l()) {
            eVar.a(r);
            eVar.a(this.h);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.s.set(1, z);
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((r) obj);
    }

    public String d() {
        return this.c;
    }

    public boolean e() {
        return this.c != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof r)) ? a((r) obj) : false;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.d != null;
    }

    public long h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.s.get(0);
    }

    public boolean j() {
        return this.s.get(1);
    }

    public boolean k() {
        return this.g != null;
    }

    public boolean l() {
        return this.h != null;
    }

    public void m() {
        if (this.b == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'payload' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMessage(");
        Object obj = 1;
        if (a()) {
            stringBuilder.append("to:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("payload:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("createAt:");
            stringBuilder.append(this.e);
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("ttl:");
            stringBuilder.append(this.f);
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("collapseKey:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
