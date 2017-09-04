package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class v implements Serializable, Cloneable, org.apache.thrift.a<v, a> {
    public static final Map<a, b> f;
    private static final j g = new j("Target");
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("channelId", (byte) 10, (short) 1);
    private static final org.apache.thrift.protocol.b i = new org.apache.thrift.protocol.b("userId", JceStruct.STRUCT_END, (short) 2);
    private static final org.apache.thrift.protocol.b j = new org.apache.thrift.protocol.b("server", JceStruct.STRUCT_END, (short) 3);
    private static final org.apache.thrift.protocol.b k = new org.apache.thrift.protocol.b("resource", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b l = new org.apache.thrift.protocol.b("isPreview", (byte) 2, (short) 5);
    public long a = 5;
    public String b;
    public String c = "xiaomi.com";
    public String d = "";
    public boolean e = false;
    private BitSet m = new BitSet(2);

    public enum a {
        CHANNEL_ID((short) 1, "channelId"),
        USER_ID((short) 2, "userId"),
        SERVER((short) 3, "server"),
        RESOURCE((short) 4, "resource"),
        IS_PREVIEW((short) 5, "isPreview");
        
        private static final Map<String, a> f = null;
        private final short g;
        private final String h;

        static {
            f = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                f.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.g = s;
            this.h = str;
        }

        public String a() {
            return this.h;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CHANNEL_ID, new b("channelId", (byte) 1, new c((byte) 10)));
        enumMap.put(a.USER_ID, new b("userId", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.SERVER, new b("server", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.RESOURCE, new b("resource", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.IS_PREVIEW, new b("isPreview", (byte) 2, new c((byte) 2)));
        f = Collections.unmodifiableMap(enumMap);
        b.a(v.class, f);
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (a()) {
                    f();
                    return;
                }
                throw new f("Required field 'channelId' was not found in serialized data! Struct: " + toString());
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = eVar.u();
                    a(true);
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
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.e = eVar.q();
                    b(true);
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public void a(boolean z) {
        this.m.set(0, z);
    }

    public boolean a() {
        return this.m.get(0);
    }

    public boolean a(v vVar) {
        if (vVar == null || this.a != vVar.a) {
            return false;
        }
        boolean b = b();
        boolean b2 = vVar.b();
        if ((b || b2) && (!b || !b2 || !this.b.equals(vVar.b))) {
            return false;
        }
        b = c();
        b2 = vVar.c();
        if ((b || b2) && (!b || !b2 || !this.c.equals(vVar.c))) {
            return false;
        }
        b = d();
        b2 = vVar.d();
        if ((b || b2) && (!b || !b2 || !this.d.equals(vVar.d))) {
            return false;
        }
        b = e();
        b2 = vVar.e();
        return !(b || b2) || (b && b2 && this.e == vVar.e);
    }

    public int b(v vVar) {
        if (!getClass().equals(vVar.getClass())) {
            return getClass().getName().compareTo(vVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(vVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, vVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(vVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, vVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(vVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, vVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(vVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.d, vVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(vVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, vVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        f();
        eVar.a(g);
        eVar.a(h);
        eVar.a(this.a);
        eVar.b();
        if (this.b != null) {
            eVar.a(i);
            eVar.a(this.b);
            eVar.b();
        }
        if (this.c != null && c()) {
            eVar.a(j);
            eVar.a(this.c);
            eVar.b();
        }
        if (this.d != null && d()) {
            eVar.a(k);
            eVar.a(this.d);
            eVar.b();
        }
        if (e()) {
            eVar.a(l);
            eVar.a(this.e);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.m.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((v) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.m.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof v)) ? a((v) obj) : false;
    }

    public void f() {
        if (this.b == null) {
            throw new f("Required field 'userId' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Target(");
        stringBuilder.append("channelId:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("userId:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("server:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("resource:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("isPreview:");
            stringBuilder.append(this.e);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
