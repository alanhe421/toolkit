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
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class d implements Serializable, Cloneable, org.apache.thrift.a<d, a> {
    public static final Map<a, b> b;
    private static final j c = new j("ClientUploadData");
    private static final org.apache.thrift.protocol.b d = new org.apache.thrift.protocol.b("uploadDataItems", (byte) 15, (short) 1);
    public List<e> a;

    public enum a {
        UPLOAD_DATA_ITEMS((short) 1, "uploadDataItems");
        
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
        enumMap.put(a.UPLOAD_DATA_ITEMS, new b("uploadDataItems", (byte) 1, new org.apache.thrift.meta_data.d((byte) 15, new g(JceStruct.ZERO_TAG, e.class))));
        b = Collections.unmodifiableMap(enumMap);
        b.a(d.class, b);
    }

    public int a() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(e eVar) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(eVar);
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
                        e eVar2 = new e();
                        eVar2.a(eVar);
                        this.a.add(eVar2);
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

    public boolean a(d dVar) {
        if (dVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = dVar.b();
        return !(b || b2) || (b && b2 && this.a.equals(dVar.a));
    }

    public int b(d dVar) {
        if (!getClass().equals(dVar.getClass())) {
            return getClass().getName().compareTo(dVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(dVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, dVar.a);
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
            for (e b : this.a) {
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
            throw new f("Required field 'uploadDataItems' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((d) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof d)) ? a((d) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ClientUploadData(");
        stringBuilder.append("uploadDataItems:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
