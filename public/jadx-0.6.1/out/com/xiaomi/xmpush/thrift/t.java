package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.f;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.j;

public class t implements Serializable, Cloneable, org.apache.thrift.a<t, a> {
    public static final Map<a, b> b;
    private static final j c = new j("RegisteredGeoFencing");
    private static final org.apache.thrift.protocol.b d = new org.apache.thrift.protocol.b("geoFencings", (byte) 14, (short) 1);
    public Set<k> a;

    public enum a {
        GEO_FENCINGS((short) 1, "geoFencings");
        
        private static final Map<String, a> b = null;
        private final short c;
        private final String d;

        static {
            b = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                b.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.c = s;
            this.d = str;
        }

        public String a() {
            return this.d;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.GEO_FENCINGS, new b("geoFencings", (byte) 1, new f((byte) 14, new g(JceStruct.ZERO_TAG, k.class))));
        b = Collections.unmodifiableMap(enumMap);
        b.a(t.class, b);
    }

    public t a(Set<k> set) {
        this.a = set;
        return this;
    }

    public Set<k> a() {
        return this.a;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                c();
                return;
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 14) {
                        h.a(eVar, i.b);
                        break;
                    }
                    i o = eVar.o();
                    this.a = new HashSet(o.b * 2);
                    for (int i2 = 0; i2 < o.b; i2++) {
                        k kVar = new k();
                        kVar.a(eVar);
                        this.a.add(kVar);
                    }
                    eVar.p();
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public boolean a(t tVar) {
        if (tVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = tVar.b();
        return !(b || b2) || (b && b2 && this.a.equals(tVar.a));
    }

    public int b(t tVar) {
        if (!getClass().equals(tVar.getClass())) {
            return getClass().getName().compareTo(tVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(tVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, tVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        c();
        eVar.a(c);
        if (this.a != null) {
            eVar.a(d);
            eVar.a(new i(JceStruct.ZERO_TAG, this.a.size()));
            for (k b : this.a) {
                b.b(eVar);
            }
            eVar.f();
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.a != null;
    }

    public void c() {
        if (this.a == null) {
            throw new org.apache.thrift.protocol.f("Required field 'geoFencings' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((t) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof t)) ? a((t) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("RegisteredGeoFencing(");
        stringBuilder.append("geoFencings:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
