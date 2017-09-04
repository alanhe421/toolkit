package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
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

public class ae implements Serializable, Cloneable, org.apache.thrift.a<ae, a> {
    public static final Map<a, b> d;
    private static final j e = new j("XmPushActionNormalConfig");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("normalConfigs", (byte) 15, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("appId", (byte) 10, (short) 4);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 5);
    public List<o> a;
    public long b;
    public String c;
    private BitSet i = new BitSet(1);

    public enum a {
        NORMAL_CONFIGS((short) 1, "normalConfigs"),
        APP_ID((short) 4, "appId"),
        PACKAGE_NAME((short) 5, Constants.FLAG_PACKAGE_NAME);
        
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
        enumMap.put(a.NORMAL_CONFIGS, new b("normalConfigs", (byte) 1, new d((byte) 15, new g(JceStruct.ZERO_TAG, o.class))));
        enumMap.put(a.APP_ID, new b("appId", (byte) 2, new c((byte) 10)));
        enumMap.put(a.PACKAGE_NAME, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(ae.class, d);
    }

    public List<o> a() {
        return this.a;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                e();
                return;
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 15) {
                        h.a(eVar, i.b);
                        break;
                    }
                    org.apache.thrift.protocol.c m = eVar.m();
                    this.a = new ArrayList(m.b);
                    for (int i2 = 0; i2 < m.b; i2++) {
                        o oVar = new o();
                        oVar.a(eVar);
                        this.a.add(oVar);
                    }
                    eVar.n();
                    break;
                case (short) 4:
                    if (i.b != (byte) 10) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.u();
                    a(true);
                    break;
                case (short) 5:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.c = eVar.w();
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

    public boolean a(ae aeVar) {
        if (aeVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = aeVar.b();
        if ((b || b2) && (!b || !b2 || !this.a.equals(aeVar.a))) {
            return false;
        }
        b = c();
        b2 = aeVar.c();
        if ((b || b2) && (!b || !b2 || this.b != aeVar.b)) {
            return false;
        }
        b = d();
        b2 = aeVar.d();
        return !(b || b2) || (b && b2 && this.c.equals(aeVar.c));
    }

    public int b(ae aeVar) {
        if (!getClass().equals(aeVar.getClass())) {
            return getClass().getName().compareTo(aeVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(aeVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, aeVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(aeVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.b, aeVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(aeVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.c, aeVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(e eVar) {
        e();
        eVar.a(e);
        if (this.a != null) {
            eVar.a(f);
            eVar.a(new org.apache.thrift.protocol.c(JceStruct.ZERO_TAG, this.a.size()));
            for (o b : this.a) {
                b.b(eVar);
            }
            eVar.e();
            eVar.b();
        }
        if (c()) {
            eVar.a(g);
            eVar.a(this.b);
            eVar.b();
        }
        if (this.c != null && d()) {
            eVar.a(h);
            eVar.a(this.c);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.a != null;
    }

    public boolean c() {
        return this.i.get(0);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((ae) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public void e() {
        if (this.a == null) {
            throw new f("Required field 'normalConfigs' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof ae)) ? a((ae) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionNormalConfig(");
        stringBuilder.append("normalConfigs:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            stringBuilder.append(this.b);
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
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
