package com.xiaomi.xmpush.thrift;

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

public class ao implements Serializable, Cloneable, a<ao, a> {
    public static final Map<a, b> i;
    private static final j j = new j("XmPushActionUnRegistrationResult");
    private static final org.apache.thrift.protocol.b k = new org.apache.thrift.protocol.b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b l = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b(SocialConstants.TYPE_REQUEST, JceStruct.ZERO_TAG, (short) 5);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("errorCode", (byte) 10, (short) 6);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("reason", JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 8);
    public String a;
    public v b;
    public String c;
    public String d;
    public an e;
    public long f;
    public String g;
    public String h;
    private BitSet s = new BitSet(1);

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("appId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new b(SocialConstants.TYPE_REQUEST, (byte) 2, new g(JceStruct.ZERO_TAG, an.class)));
        enumMap.put(a.f, new b("errorCode", (byte) 1, new c((byte) 10)));
        enumMap.put(a.g, new b("reason", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.h, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        i = Collections.unmodifiableMap(enumMap);
        b.a(ao.class, i);
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (f()) {
                    j();
                    return;
                }
                throw new f("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
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
                    this.e = new an();
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

    public boolean a(ao aoVar) {
        if (aoVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = aoVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(aoVar.a))) {
            return false;
        }
        a = b();
        a2 = aoVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(aoVar.b))) {
            return false;
        }
        a = c();
        a2 = aoVar.c();
        if ((a || a2) && (!a || !a2 || !this.c.equals(aoVar.c))) {
            return false;
        }
        a = d();
        a2 = aoVar.d();
        if ((a || a2) && (!a || !a2 || !this.d.equals(aoVar.d))) {
            return false;
        }
        a = e();
        a2 = aoVar.e();
        if (((a || a2) && (!a || !a2 || !this.e.a(aoVar.e))) || this.f != aoVar.f) {
            return false;
        }
        a = g();
        a2 = aoVar.g();
        if ((a || a2) && (!a || !a2 || !this.g.equals(aoVar.g))) {
            return false;
        }
        a = i();
        a2 = aoVar.i();
        return !(a || a2) || (a && a2 && this.h.equals(aoVar.h));
    }

    public int b(ao aoVar) {
        if (!getClass().equals(aoVar.getClass())) {
            return getClass().getName().compareTo(aoVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(aoVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, aoVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(aoVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, aoVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(aoVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, aoVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(aoVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, aoVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(aoVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, aoVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(aoVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, aoVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(aoVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.g, aoVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(aoVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.h, aoVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        j();
        eVar.a(j);
        if (this.a != null && a()) {
            eVar.a(k);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(l);
            this.b.b(eVar);
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
        if (this.e != null && e()) {
            eVar.a(o);
            this.e.b(eVar);
            eVar.b();
        }
        eVar.a(p);
        eVar.a(this.f);
        eVar.b();
        if (this.g != null && g()) {
            eVar.a(q);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && i()) {
            eVar.a(r);
            eVar.a(this.h);
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
        return b((ao) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof ao)) ? a((ao) obj) : false;
    }

    public boolean f() {
        return this.s.get(0);
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

    public void j() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnRegistrationResult(");
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
            stringBuilder.append("request:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.f);
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
