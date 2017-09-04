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
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class ad implements Serializable, Cloneable, org.apache.thrift.a<ad, a> {
    public static final Map<a, b> b;
    private static final j c = new j("XmPushActionCustomConfig");
    private static final org.apache.thrift.protocol.b d = new org.apache.thrift.protocol.b("customConfigs", (byte) 15, (short) 1);
    public List<q> a;

    public enum a {
        CUSTOM_CONFIGS((short) 1, "customConfigs");
        
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
        enumMap.put(a.CUSTOM_CONFIGS, new b("customConfigs", (byte) 1, new d((byte) 15, new g(JceStruct.ZERO_TAG, q.class))));
        b = Collections.unmodifiableMap(enumMap);
        b.a(ad.class, b);
    }

    public List<q> a() {
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
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    c m = eVar.m();
                    this.a = new ArrayList(m.b);
                    for (int i2 = 0; i2 < m.b; i2++) {
                        q qVar = new q();
                        qVar.a(eVar);
                        this.a.add(qVar);
                    }
                    eVar.n();
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public boolean a(ad adVar) {
        if (adVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = adVar.b();
        return !(b || b2) || (b && b2 && this.a.equals(adVar.a));
    }

    public int b(ad adVar) {
        if (!getClass().equals(adVar.getClass())) {
            return getClass().getName().compareTo(adVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(adVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, adVar.a);
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
            eVar.a(new c(JceStruct.ZERO_TAG, this.a.size()));
            for (q b : this.a) {
                b.b(eVar);
            }
            eVar.e();
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
            throw new f("Required field 'customConfigs' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((ad) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof ad)) ? a((ad) obj) : false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCustomConfig(");
        stringBuilder.append("customConfigs:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
