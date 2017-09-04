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
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class y implements Serializable, Cloneable, a<y, a> {
    public static final Map<a, b> l;
    private static final j m = new j("XmPushActionAckNotification");
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("type", JceStruct.STRUCT_END, (short) 5);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b(SocialConstants.TYPE_REQUEST, JceStruct.ZERO_TAG, (short) 6);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("errorCode", (byte) 10, (short) 7);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("reason", JceStruct.STRUCT_END, (short) 8);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("extra", JceStruct.SIMPLE_LIST, (short) 9);
    private static final org.apache.thrift.protocol.b w = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 10);
    private static final org.apache.thrift.protocol.b x = new org.apache.thrift.protocol.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, JceStruct.STRUCT_END, (short) 11);
    public String a;
    public v b;
    public String c;
    public String d;
    public String e;
    public af f;
    public long g;
    public String h;
    public Map<String, String> i;
    public String j;
    public String k;
    private BitSet y = new BitSet(1);

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.b, new b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.c, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.d, new b("appId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.e, new b("type", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.f, new b(SocialConstants.TYPE_REQUEST, (byte) 2, new g(JceStruct.ZERO_TAG, af.class)));
        enumMap.put(a.g, new b("errorCode", (byte) 1, new c((byte) 10)));
        enumMap.put(a.h, new b("reason", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.i, new b("extra", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        enumMap.put(a.j, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.k, new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, (byte) 2, new c(JceStruct.STRUCT_END)));
        l = Collections.unmodifiableMap(enumMap);
        b.a(y.class, l);
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (h()) {
                    m();
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
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.e = eVar.w();
                        break;
                    }
                case (short) 6:
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = new af();
                    this.f.a(eVar);
                    break;
                case (short) 7:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.g = eVar.u();
                    a(true);
                    break;
                case (short) 8:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.h = eVar.w();
                        break;
                    }
                case (short) 9:
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.i = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.i.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
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
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.y.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(y yVar) {
        if (yVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = yVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(yVar.a))) {
            return false;
        }
        a = b();
        a2 = yVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(yVar.b))) {
            return false;
        }
        a = d();
        a2 = yVar.d();
        if ((a || a2) && (!a || !a2 || !this.c.equals(yVar.c))) {
            return false;
        }
        a = e();
        a2 = yVar.e();
        if ((a || a2) && (!a || !a2 || !this.d.equals(yVar.d))) {
            return false;
        }
        a = f();
        a2 = yVar.f();
        if ((a || a2) && (!a || !a2 || !this.e.equals(yVar.e))) {
            return false;
        }
        a = g();
        a2 = yVar.g();
        if (((a || a2) && (!a || !a2 || !this.f.a(yVar.f))) || this.g != yVar.g) {
            return false;
        }
        a = i();
        a2 = yVar.i();
        if ((a || a2) && (!a || !a2 || !this.h.equals(yVar.h))) {
            return false;
        }
        a = j();
        a2 = yVar.j();
        if ((a || a2) && (!a || !a2 || !this.i.equals(yVar.i))) {
            return false;
        }
        a = k();
        a2 = yVar.k();
        if ((a || a2) && (!a || !a2 || !this.j.equals(yVar.j))) {
            return false;
        }
        a = l();
        a2 = yVar.l();
        return !(a || a2) || (a && a2 && this.k.equals(yVar.k));
    }

    public int b(y yVar) {
        if (!getClass().equals(yVar.getClass())) {
            return getClass().getName().compareTo(yVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(yVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, yVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(yVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, yVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(yVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, yVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(yVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.d, yVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(yVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.e, yVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(yVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.f, yVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(yVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.g, yVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(yVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.h, yVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(yVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.i, yVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(yVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.j, yVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(yVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.k, yVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        m();
        eVar.a(m);
        if (this.a != null && a()) {
            eVar.a(n);
            eVar.a(this.a);
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(o);
            this.b.b(eVar);
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(p);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null && e()) {
            eVar.a(q);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && f()) {
            eVar.a(r);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && g()) {
            eVar.a(s);
            this.f.b(eVar);
            eVar.b();
        }
        eVar.a(t);
        eVar.a(this.g);
        eVar.b();
        if (this.h != null && i()) {
            eVar.a(u);
            eVar.a(this.h);
            eVar.b();
        }
        if (this.i != null && j()) {
            eVar.a(v);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.i.size()));
            for (Entry entry : this.i.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (this.j != null && k()) {
            eVar.a(w);
            eVar.a(this.j);
            eVar.b();
        }
        if (this.k != null && l()) {
            eVar.a(x);
            eVar.a(this.k);
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
        return b((y) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof y)) ? a((y) obj) : false;
    }

    public boolean f() {
        return this.e != null;
    }

    public boolean g() {
        return this.f != null;
    }

    public boolean h() {
        return this.y.get(0);
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

    public boolean k() {
        return this.j != null;
    }

    public boolean l() {
        return this.k != null;
    }

    public void m() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionAckNotification(");
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
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("type:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.g);
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
