package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class af implements Serializable, Cloneable, org.apache.thrift.a<af, a> {
    public static final Map<a, b> l;
    private static final j m = new j("XmPushActionNotification");
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("debug", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 2);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("appId", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("type", JceStruct.STRUCT_END, (short) 5);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b("requireAck", (byte) 2, (short) 6);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("payload", JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b("extra", JceStruct.SIMPLE_LIST, (short) 8);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 9);
    private static final org.apache.thrift.protocol.b w = new org.apache.thrift.protocol.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, JceStruct.STRUCT_END, (short) 10);
    private static final org.apache.thrift.protocol.b x = new org.apache.thrift.protocol.b("binaryExtra", JceStruct.STRUCT_END, (short) 14);
    public String a;
    public v b;
    public String c;
    public String d;
    public String e;
    public boolean f;
    public String g;
    public Map<String, String> h;
    public String i;
    public String j;
    public ByteBuffer k;
    private BitSet y;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, "appId"),
        TYPE((short) 5, "type"),
        REQUIRE_ACK((short) 6, "requireAck"),
        PAYLOAD((short) 7, "payload"),
        EXTRA((short) 8, "extra"),
        PACKAGE_NAME((short) 9, Constants.FLAG_PACKAGE_NAME),
        CATEGORY((short) 10, ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE),
        BINARY_EXTRA((short) 14, "binaryExtra");
        
        private static final Map<String, a> l = null;
        private final short m;
        private final String n;

        static {
            l = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                l.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.m = s;
            this.n = str;
        }

        public String a() {
            return this.n;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new b("debug", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.TARGET, new b("target", (byte) 2, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.ID, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.APP_ID, new b("appId", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.TYPE, new b("type", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.REQUIRE_ACK, new b("requireAck", (byte) 1, new c((byte) 2)));
        enumMap.put(a.PAYLOAD, new b("payload", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.EXTRA, new b("extra", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        enumMap.put(a.PACKAGE_NAME, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.CATEGORY, new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.BINARY_EXTRA, new b("binaryExtra", (byte) 2, new c(JceStruct.STRUCT_END)));
        l = Collections.unmodifiableMap(enumMap);
        b.a(af.class, l);
    }

    public af() {
        this.y = new BitSet(1);
        this.f = true;
    }

    public af(String str, boolean z) {
        this();
        this.c = str;
        this.f = z;
        b(true);
    }

    public af a(String str) {
        this.c = str;
        return this;
    }

    public af a(ByteBuffer byteBuffer) {
        this.k = byteBuffer;
        return this;
    }

    public af a(Map<String, String> map) {
        this.h = map;
        return this;
    }

    public af a(boolean z) {
        this.f = z;
        b(true);
        return this;
    }

    public af a(byte[] bArr) {
        a(ByteBuffer.wrap(bArr));
        return this;
    }

    public void a(String str, String str2) {
        if (this.h == null) {
            this.h = new HashMap();
        }
        this.h.put(str, str2);
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (g()) {
                    o();
                    return;
                }
                throw new f("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
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
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = eVar.q();
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
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    d k = eVar.k();
                    this.h = new HashMap(k.c * 2);
                    for (int i2 = 0; i2 < k.c; i2++) {
                        this.h.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
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
                case (short) 14:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.k = eVar.x();
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

    public boolean a(af afVar) {
        if (afVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = afVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(afVar.a))) {
            return false;
        }
        a = b();
        a2 = afVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.a(afVar.b))) {
            return false;
        }
        a = d();
        a2 = afVar.d();
        if ((a || a2) && (!a || !a2 || !this.c.equals(afVar.c))) {
            return false;
        }
        a = e();
        a2 = afVar.e();
        if ((a || a2) && (!a || !a2 || !this.d.equals(afVar.d))) {
            return false;
        }
        a = f();
        a2 = afVar.f();
        if (((a || a2) && (!a || !a2 || !this.e.equals(afVar.e))) || this.f != afVar.f) {
            return false;
        }
        a = h();
        a2 = afVar.h();
        if ((a || a2) && (!a || !a2 || !this.g.equals(afVar.g))) {
            return false;
        }
        a = j();
        a2 = afVar.j();
        if ((a || a2) && (!a || !a2 || !this.h.equals(afVar.h))) {
            return false;
        }
        a = k();
        a2 = afVar.k();
        if ((a || a2) && (!a || !a2 || !this.i.equals(afVar.i))) {
            return false;
        }
        a = l();
        a2 = afVar.l();
        if ((a || a2) && (!a || !a2 || !this.j.equals(afVar.j))) {
            return false;
        }
        a = n();
        a2 = afVar.n();
        return !(a || a2) || (a && a2 && this.k.equals(afVar.k));
    }

    public int b(af afVar) {
        if (!getClass().equals(afVar.getClass())) {
            return getClass().getName().compareTo(afVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(afVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, afVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(afVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, afVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(afVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, afVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(afVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.d, afVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(afVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.e, afVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(afVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.f, afVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(h()).compareTo(Boolean.valueOf(afVar.h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (h()) {
            compareTo = org.apache.thrift.b.a(this.g, afVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(afVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.a(this.h, afVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(afVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.i, afVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(afVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.j, afVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(afVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.k, afVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public af b(String str) {
        this.d = str;
        return this;
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        o();
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
        eVar.a(s);
        eVar.a(this.f);
        eVar.b();
        if (this.g != null && h()) {
            eVar.a(t);
            eVar.a(this.g);
            eVar.b();
        }
        if (this.h != null && j()) {
            eVar.a(u);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.h.size()));
            for (Entry entry : this.h.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (this.i != null && k()) {
            eVar.a(v);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && l()) {
            eVar.a(w);
            eVar.a(this.j);
            eVar.b();
        }
        if (this.k != null && n()) {
            eVar.a(x);
            eVar.a(this.k);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.y.set(0, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public af c(String str) {
        this.e = str;
        return this;
    }

    public String c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((af) obj);
    }

    public af d(String str) {
        this.i = str;
        return this;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof af)) ? a((af) obj) : false;
    }

    public boolean f() {
        return this.e != null;
    }

    public boolean g() {
        return this.y.get(0);
    }

    public boolean h() {
        return this.g != null;
    }

    public int hashCode() {
        return 0;
    }

    public Map<String, String> i() {
        return this.h;
    }

    public boolean j() {
        return this.h != null;
    }

    public boolean k() {
        return this.i != null;
    }

    public boolean l() {
        return this.j != null;
    }

    public byte[] m() {
        a(org.apache.thrift.b.c(this.k));
        return this.k.array();
    }

    public boolean n() {
        return this.k != null;
    }

    public void o() {
        if (this.c == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionNotification(");
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
        stringBuilder.append(", ");
        stringBuilder.append("requireAck:");
        stringBuilder.append(this.f);
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("payload:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (k()) {
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
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("binaryExtra:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                org.apache.thrift.b.a(this.k, stringBuilder);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
