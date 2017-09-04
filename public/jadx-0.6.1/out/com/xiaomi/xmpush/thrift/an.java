package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import java.io.Serializable;
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

public class an implements Serializable, Cloneable, a<an, a> {
    public static final Map<a, b> k;
    private static final j l = new j("XmPushActionUnRegistration");
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("regId", JceStruct.STRUCT_END, (short) 5);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("appVersion", JceStruct.STRUCT_END, (short) 6);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b(Constants.FLAG_TOKEN, JceStruct.STRUCT_END, (short) 8);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b(Constants.FLAG_DEVICE_ID, JceStruct.STRUCT_END, (short) 9);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("aliasName", JceStruct.STRUCT_END, (short) 10);
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

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("appId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new b("regId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.f, new b("appVersion", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.g, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new b(Constants.FLAG_TOKEN, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.i, new b(Constants.FLAG_DEVICE_ID, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.j, new b("aliasName", (byte) 2, new c(JceStruct.STRUCT_END)));
        k = Collections.unmodifiableMap(enumMap);
        b.a(an.class, k);
    }

    public an a(String str) {
        this.c = str;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                k();
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
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(an anVar) {
        if (anVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = anVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(anVar.a))) {
            return false;
        }
        a = b();
        a2 = anVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(anVar.b))) {
            return false;
        }
        a = c();
        a2 = anVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(anVar.c))) {
            return false;
        }
        a = d();
        a2 = anVar.d();
        if ((a || a2) && (!a || !a2 || !this.d.equals(anVar.d))) {
            return false;
        }
        a = e();
        a2 = anVar.e();
        if ((a || a2) && (!a || !a2 || !this.e.equals(anVar.e))) {
            return false;
        }
        a = f();
        a2 = anVar.f();
        if ((a || a2) && (!a || !a2 || !this.f.equals(anVar.f))) {
            return false;
        }
        a = g();
        a2 = anVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(anVar.g))) {
            return false;
        }
        a = h();
        a2 = anVar.h();
        if ((a || a2) && (!a || !a2 || !this.h.equals(anVar.h))) {
            return false;
        }
        a = i();
        a2 = anVar.i();
        if ((a || a2) && (!a || !a2 || !this.i.equals(anVar.i))) {
            return false;
        }
        a = j();
        a2 = anVar.j();
        return !(a || a2) || (a && a2 && this.j.equals(anVar.j));
    }

    public int b(an anVar) {
        if (!getClass().equals(anVar.getClass())) {
            return getClass().getName().compareTo(anVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(anVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, anVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(anVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, anVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(anVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, anVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(anVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, anVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(anVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, anVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(anVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, anVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(anVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.g, anVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(anVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.h, anVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(anVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.i, anVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(anVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.j, anVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public an b(String str) {
        this.d = str;
        return this;
    }

    public void b(e eVar) {
        k();
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
        if (this.d != null) {
            eVar.a(p);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && e()) {
            eVar.a(q);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && f()) {
            eVar.a(r);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && g()) {
            eVar.a(s);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && h()) {
            eVar.a(t);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && i()) {
            eVar.a(u);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && j()) {
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

    public an c(String str) {
        this.e = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((an) obj);
    }

    public an d(String str) {
        this.g = str;
        return this;
    }

    public boolean d() {
        return this.d != null;
    }

    public an e(String str) {
        this.h = str;
        return this;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof an)) ? a((an) obj) : false;
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

    public void k() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnRegistration(");
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
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("appVersion:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("token:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
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
