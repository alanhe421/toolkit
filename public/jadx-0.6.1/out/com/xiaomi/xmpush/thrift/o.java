package com.xiaomi.xmpush.thrift;

import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.taf.jce.JceStruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.d;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class o implements Serializable, Cloneable, org.apache.thrift.a<o, a> {
    public static final Map<a, b> d;
    private static final j e = new j("NormalConfig");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, (byte) 8, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("configItems", (byte) 15, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("type", (byte) 8, (short) 3);
    public int a;
    public List<q> b;
    public g c;
    private BitSet i = new BitSet(1);

    public enum a {
        VERSION((short) 1, ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION),
        CONFIG_ITEMS((short) 2, "configItems"),
        TYPE((short) 3, "type");
        
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
        enumMap.put(a.VERSION, new b(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, (byte) 1, new c((byte) 8)));
        enumMap.put(a.CONFIG_ITEMS, new b("configItems", (byte) 1, new d((byte) 15, new g(JceStruct.ZERO_TAG, q.class))));
        enumMap.put(a.TYPE, new b("type", (byte) 1, new org.apache.thrift.meta_data.a((byte) 16, g.class)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(o.class, d);
    }

    public int a() {
        return this.a;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (b()) {
                    f();
                    return;
                }
                throw new f("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.a = eVar.t();
                    a(true);
                    break;
                case (short) 2:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    org.apache.thrift.protocol.c m = eVar.m();
                    this.b = new ArrayList(m.b);
                    for (int i2 = 0; i2 < m.b; i2++) {
                        q qVar = new q();
                        qVar.a(eVar);
                        this.b.add(qVar);
                    }
                    eVar.n();
                    break;
                case (short) 3:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = g.a(eVar.t());
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
        this.i.set(0, z);
    }

    public boolean a(o oVar) {
        if (oVar == null || this.a != oVar.a) {
            return false;
        }
        boolean c = c();
        boolean c2 = oVar.c();
        if ((c || c2) && (!c || !c2 || !this.b.equals(oVar.b))) {
            return false;
        }
        c = e();
        c2 = oVar.e();
        return !(c || c2) || (c && c2 && this.c.equals(oVar.c));
    }

    public int b(o oVar) {
        if (!getClass().equals(oVar.getClass())) {
            return getClass().getName().compareTo(oVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(oVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, oVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(oVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.b, oVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(oVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, oVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        f();
        eVar.a(e);
        eVar.a(f);
        eVar.a(this.a);
        eVar.b();
        if (this.b != null) {
            eVar.a(g);
            eVar.a(new org.apache.thrift.protocol.c(JceStruct.ZERO_TAG, this.b.size()));
            for (q b : this.b) {
                b.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (this.c != null) {
            eVar.a(h);
            eVar.a(this.c.a());
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.i.get(0);
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((o) obj);
    }

    public g d() {
        return this.c;
    }

    public boolean e() {
        return this.c != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof o)) ? a((o) obj) : false;
    }

    public void f() {
        if (this.b == null) {
            throw new f("Required field 'configItems' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new f("Required field 'type' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("NormalConfig(");
        stringBuilder.append("version:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("configItems:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
