package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
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
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class s implements Serializable, Cloneable, org.apache.thrift.a<s, a> {
    public static final Map<a, b> m;
    private static final j n = new j("PushMetaInfo");
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("id", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b("messageTs", (byte) 10, (short) 2);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("topic", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("title", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b s = new org.apache.thrift.protocol.b(SocialConstants.PARAM_COMMENT, JceStruct.STRUCT_END, (short) 5);
    private static final org.apache.thrift.protocol.b t = new org.apache.thrift.protocol.b("notifyType", (byte) 8, (short) 6);
    private static final org.apache.thrift.protocol.b u = new org.apache.thrift.protocol.b(SocialConstants.PARAM_URL, JceStruct.STRUCT_END, (short) 7);
    private static final org.apache.thrift.protocol.b v = new org.apache.thrift.protocol.b("passThrough", (byte) 8, (short) 8);
    private static final org.apache.thrift.protocol.b w = new org.apache.thrift.protocol.b("notifyId", (byte) 8, (short) 9);
    private static final org.apache.thrift.protocol.b x = new org.apache.thrift.protocol.b("extra", JceStruct.SIMPLE_LIST, (short) 10);
    private static final org.apache.thrift.protocol.b y = new org.apache.thrift.protocol.b("internal", JceStruct.SIMPLE_LIST, (short) 11);
    private static final org.apache.thrift.protocol.b z = new org.apache.thrift.protocol.b("ignoreRegInfo", (byte) 2, (short) 12);
    private BitSet A;
    public String a;
    public long b;
    public String c;
    public String d;
    public String e;
    public int f;
    public String g;
    public int h;
    public int i;
    public Map<String, String> j;
    public Map<String, String> k;
    public boolean l;

    public enum a {
        ID((short) 1, "id"),
        MESSAGE_TS((short) 2, "messageTs"),
        TOPIC((short) 3, "topic"),
        TITLE((short) 4, "title"),
        DESCRIPTION((short) 5, SocialConstants.PARAM_COMMENT),
        NOTIFY_TYPE((short) 6, "notifyType"),
        URL((short) 7, SocialConstants.PARAM_URL),
        PASS_THROUGH((short) 8, "passThrough"),
        NOTIFY_ID((short) 9, "notifyId"),
        EXTRA((short) 10, "extra"),
        INTERNAL((short) 11, "internal"),
        IGNORE_REG_INFO((short) 12, "ignoreRegInfo");
        
        private static final Map<String, a> m = null;
        private final short n;
        private final String o;

        static {
            m = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                m.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.n = s;
            this.o = str;
        }

        public String a() {
            return this.o;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.ID, new b("id", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.MESSAGE_TS, new b("messageTs", (byte) 1, new c((byte) 10)));
        enumMap.put(a.TOPIC, new b("topic", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.TITLE, new b("title", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.DESCRIPTION, new b(SocialConstants.PARAM_COMMENT, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.NOTIFY_TYPE, new b("notifyType", (byte) 2, new c((byte) 8)));
        enumMap.put(a.URL, new b(SocialConstants.PARAM_URL, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.PASS_THROUGH, new b("passThrough", (byte) 2, new c((byte) 8)));
        enumMap.put(a.NOTIFY_ID, new b("notifyId", (byte) 2, new c((byte) 8)));
        enumMap.put(a.EXTRA, new b("extra", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        enumMap.put(a.INTERNAL, new b("internal", (byte) 2, new e(JceStruct.SIMPLE_LIST, new c(JceStruct.STRUCT_END), new c(JceStruct.STRUCT_END))));
        enumMap.put(a.IGNORE_REG_INFO, new b("ignoreRegInfo", (byte) 2, new c((byte) 2)));
        m = Collections.unmodifiableMap(enumMap);
        b.a(s.class, m);
    }

    public s() {
        this.A = new BitSet(5);
        this.l = false;
    }

    public s(s sVar) {
        Map hashMap;
        this.A = new BitSet(5);
        this.A.clear();
        this.A.or(sVar.A);
        if (sVar.c()) {
            this.a = sVar.a;
        }
        this.b = sVar.b;
        if (sVar.g()) {
            this.c = sVar.c;
        }
        if (sVar.i()) {
            this.d = sVar.d;
        }
        if (sVar.k()) {
            this.e = sVar.e;
        }
        this.f = sVar.f;
        if (sVar.n()) {
            this.g = sVar.g;
        }
        this.h = sVar.h;
        this.i = sVar.i;
        if (sVar.t()) {
            hashMap = new HashMap();
            for (Entry entry : sVar.j.entrySet()) {
                hashMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            this.j = hashMap;
        }
        if (sVar.v()) {
            hashMap = new HashMap();
            for (Entry entry2 : sVar.k.entrySet()) {
                hashMap.put((String) entry2.getKey(), (String) entry2.getValue());
            }
            this.k = hashMap;
        }
        this.l = sVar.l;
    }

    public s a() {
        return new s(this);
    }

    public s a(int i) {
        this.f = i;
        b(true);
        return this;
    }

    public s a(String str) {
        this.a = str;
        return this;
    }

    public s a(Map<String, String> map) {
        this.j = map;
        return this;
    }

    public void a(String str, String str2) {
        if (this.j == null) {
            this.j = new HashMap();
        }
        this.j.put(str, str2);
    }

    public void a(org.apache.thrift.protocol.e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (e()) {
                    y();
                    return;
                }
                throw new f("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            d k;
            int i2;
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
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.u();
                    a(true);
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
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.f = eVar.t();
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
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.h = eVar.t();
                    c(true);
                    break;
                case (short) 9:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.i = eVar.t();
                    d(true);
                    break;
                case (short) 10:
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    k = eVar.k();
                    this.j = new HashMap(k.c * 2);
                    for (i2 = 0; i2 < k.c; i2++) {
                        this.j.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
                case (short) 11:
                    if (i.b != JceStruct.SIMPLE_LIST) {
                        h.a(eVar, i.b);
                        break;
                    }
                    k = eVar.k();
                    this.k = new HashMap(k.c * 2);
                    for (i2 = 0; i2 < k.c; i2++) {
                        this.k.put(eVar.w(), eVar.w());
                    }
                    eVar.l();
                    break;
                case (short) 12:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.l = eVar.q();
                    e(true);
                    break;
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

    public boolean a(s sVar) {
        if (sVar == null) {
            return false;
        }
        boolean c = c();
        boolean c2 = sVar.c();
        if (((c || c2) && (!c || !c2 || !this.a.equals(sVar.a))) || this.b != sVar.b) {
            return false;
        }
        c = g();
        c2 = sVar.g();
        if ((c || c2) && (!c || !c2 || !this.c.equals(sVar.c))) {
            return false;
        }
        c = i();
        c2 = sVar.i();
        if ((c || c2) && (!c || !c2 || !this.d.equals(sVar.d))) {
            return false;
        }
        c = k();
        c2 = sVar.k();
        if ((c || c2) && (!c || !c2 || !this.e.equals(sVar.e))) {
            return false;
        }
        c = m();
        c2 = sVar.m();
        if ((c || c2) && (!c || !c2 || this.f != sVar.f)) {
            return false;
        }
        c = n();
        c2 = sVar.n();
        if ((c || c2) && (!c || !c2 || !this.g.equals(sVar.g))) {
            return false;
        }
        c = p();
        c2 = sVar.p();
        if ((c || c2) && (!c || !c2 || this.h != sVar.h)) {
            return false;
        }
        c = r();
        c2 = sVar.r();
        if ((c || c2) && (!c || !c2 || this.i != sVar.i)) {
            return false;
        }
        c = t();
        c2 = sVar.t();
        if ((c || c2) && (!c || !c2 || !this.j.equals(sVar.j))) {
            return false;
        }
        c = v();
        c2 = sVar.v();
        if ((c || c2) && (!c || !c2 || !this.k.equals(sVar.k))) {
            return false;
        }
        c = x();
        c2 = sVar.x();
        return !(c || c2) || (c && c2 && this.l == sVar.l);
    }

    public int b(s sVar) {
        if (!getClass().equals(sVar.getClass())) {
            return getClass().getName().compareTo(sVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(sVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.a, sVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(sVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.b, sVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(sVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.c, sVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(sVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.d, sVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(sVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.e, sVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(sVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.f, sVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(sVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.g, sVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(p()).compareTo(Boolean.valueOf(sVar.p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (p()) {
            compareTo = org.apache.thrift.b.a(this.h, sVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(r()).compareTo(Boolean.valueOf(sVar.r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (r()) {
            compareTo = org.apache.thrift.b.a(this.i, sVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(t()).compareTo(Boolean.valueOf(sVar.t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (t()) {
            compareTo = org.apache.thrift.b.a(this.j, sVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(v()).compareTo(Boolean.valueOf(sVar.v()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (v()) {
            compareTo = org.apache.thrift.b.a(this.k, sVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(x()).compareTo(Boolean.valueOf(sVar.x()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (x()) {
            compareTo = org.apache.thrift.b.a(this.l, sVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public s b(int i) {
        this.h = i;
        c(true);
        return this;
    }

    public s b(String str) {
        this.c = str;
        return this;
    }

    public String b() {
        return this.a;
    }

    public void b(String str, String str2) {
        if (this.k == null) {
            this.k = new HashMap();
        }
        this.k.put(str, str2);
    }

    public void b(org.apache.thrift.protocol.e eVar) {
        y();
        eVar.a(n);
        if (this.a != null) {
            eVar.a(o);
            eVar.a(this.a);
            eVar.b();
        }
        eVar.a(p);
        eVar.a(this.b);
        eVar.b();
        if (this.c != null && g()) {
            eVar.a(q);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null && i()) {
            eVar.a(r);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && k()) {
            eVar.a(s);
            eVar.a(this.e);
            eVar.b();
        }
        if (m()) {
            eVar.a(t);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null && n()) {
            eVar.a(u);
            eVar.a(this.g);
            eVar.b();
        }
        if (p()) {
            eVar.a(v);
            eVar.a(this.h);
            eVar.b();
        }
        if (r()) {
            eVar.a(w);
            eVar.a(this.i);
            eVar.b();
        }
        if (this.j != null && t()) {
            eVar.a(x);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                eVar.a((String) entry.getKey());
                eVar.a((String) entry.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (this.k != null && v()) {
            eVar.a(y);
            eVar.a(new d(JceStruct.STRUCT_END, JceStruct.STRUCT_END, this.k.size()));
            for (Entry entry2 : this.k.entrySet()) {
                eVar.a((String) entry2.getKey());
                eVar.a((String) entry2.getValue());
            }
            eVar.d();
            eVar.b();
        }
        if (x()) {
            eVar.a(z);
            eVar.a(this.l);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.A.set(1, z);
    }

    public s c(int i) {
        this.i = i;
        d(true);
        return this;
    }

    public s c(String str) {
        this.d = str;
        return this;
    }

    public void c(boolean z) {
        this.A.set(2, z);
    }

    public boolean c() {
        return this.a != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((s) obj);
    }

    public long d() {
        return this.b;
    }

    public s d(String str) {
        this.e = str;
        return this;
    }

    public void d(boolean z) {
        this.A.set(3, z);
    }

    public void e(boolean z) {
        this.A.set(4, z);
    }

    public boolean e() {
        return this.A.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof s)) ? a((s) obj) : false;
    }

    public String f() {
        return this.c;
    }

    public boolean g() {
        return this.c != null;
    }

    public String h() {
        return this.d;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.d != null;
    }

    public String j() {
        return this.e;
    }

    public boolean k() {
        return this.e != null;
    }

    public int l() {
        return this.f;
    }

    public boolean m() {
        return this.A.get(1);
    }

    public boolean n() {
        return this.g != null;
    }

    public int o() {
        return this.h;
    }

    public boolean p() {
        return this.A.get(2);
    }

    public int q() {
        return this.i;
    }

    public boolean r() {
        return this.A.get(3);
    }

    public Map<String, String> s() {
        return this.j;
    }

    public boolean t() {
        return this.j != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMetaInfo(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.b);
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("title:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("description:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyType:");
            stringBuilder.append(this.f);
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("url:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (p()) {
            stringBuilder.append(", ");
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.h);
        }
        if (r()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyId:");
            stringBuilder.append(this.i);
        }
        if (t()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (v()) {
            stringBuilder.append(", ");
            stringBuilder.append("internal:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (x()) {
            stringBuilder.append(", ");
            stringBuilder.append("ignoreRegInfo:");
            stringBuilder.append(this.l);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public Map<String, String> u() {
        return this.k;
    }

    public boolean v() {
        return this.k != null;
    }

    public boolean w() {
        return this.l;
    }

    public boolean x() {
        return this.A.get(4);
    }

    public void y() {
        if (this.a == null) {
            throw new f("Required field 'id' was not present! Struct: " + toString());
        }
    }
}
