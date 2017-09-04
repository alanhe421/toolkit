package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class n implements Serializable, Cloneable, org.apache.thrift.a<n, a> {
    public static final Map<a, b> d;
    private static final j e = new j("LocationInfo");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("wifiList", (byte) 15, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("cellList", (byte) 15, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("gps", JceStruct.ZERO_TAG, (short) 3);
    public List<w> a;
    public List<c> b;
    public j c;

    public enum a {
        WIFI_LIST((short) 1, "wifiList"),
        CELL_LIST((short) 2, "cellList"),
        GPS((short) 3, "gps");
        
        private static final Map<String, a> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                d.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.WIFI_LIST, new b("wifiList", (byte) 2, new d((byte) 15, new g(JceStruct.ZERO_TAG, w.class))));
        enumMap.put(a.CELL_LIST, new b("cellList", (byte) 2, new d((byte) 15, new g(JceStruct.ZERO_TAG, c.class))));
        enumMap.put(a.GPS, new b("gps", (byte) 2, new g(JceStruct.ZERO_TAG, j.class)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(n.class, d);
    }

    public n a(j jVar) {
        this.c = jVar;
        return this;
    }

    public n a(List<w> list) {
        this.a = list;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                d();
                return;
            }
            c m;
            int i2;
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    m = eVar.m();
                    this.a = new ArrayList(m.b);
                    for (i2 = 0; i2 < m.b; i2++) {
                        w wVar = new w();
                        wVar.a(eVar);
                        this.a.add(wVar);
                    }
                    eVar.n();
                    break;
                case (short) 2:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    m = eVar.m();
                    this.b = new ArrayList(m.b);
                    for (i2 = 0; i2 < m.b; i2++) {
                        c cVar = new c();
                        cVar.a(eVar);
                        this.b.add(cVar);
                    }
                    eVar.n();
                    break;
                case (short) 3:
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.c = new j();
                    this.c.a(eVar);
                    break;
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

    public boolean a(n nVar) {
        if (nVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = nVar.a();
        if ((a || a2) && (!a || !a2 || !this.a.equals(nVar.a))) {
            return false;
        }
        a = b();
        a2 = nVar.b();
        if ((a || a2) && (!a || !a2 || !this.b.equals(nVar.b))) {
            return false;
        }
        a = c();
        a2 = nVar.c();
        return !(a || a2) || (a && a2 && this.c.a(nVar.c));
    }

    public int b(n nVar) {
        if (!getClass().equals(nVar.getClass())) {
            return getClass().getName().compareTo(nVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(nVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, nVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(nVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, nVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(nVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, nVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public n b(List<c> list) {
        this.b = list;
        return this;
    }

    public void b(e eVar) {
        d();
        eVar.a(e);
        if (this.a != null && a()) {
            eVar.a(f);
            eVar.a(new c(JceStruct.ZERO_TAG, this.a.size()));
            for (w b : this.a) {
                b.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.b != null && b()) {
            eVar.a(g);
            eVar.a(new c(JceStruct.ZERO_TAG, this.b.size()));
            for (c b2 : this.b) {
                b2.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.c != null && c()) {
            eVar.a(h);
            this.c.b(eVar);
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
        return b((n) obj);
    }

    public void d() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof n)) ? a((n) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("LocationInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("wifiList:");
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
            stringBuilder.append("cellList:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        } else {
            obj = obj2;
        }
        if (c()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("gps:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
