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

public class w implements Serializable, Cloneable, org.apache.thrift.a<w, a> {
    public static final Map<a, b> d;
    private static final j e = new j("Wifi");
    private static final org.apache.thrift.protocol.b f = new org.apache.thrift.protocol.b("macAddress", JceStruct.STRUCT_END, (short) 1);
    private static final org.apache.thrift.protocol.b g = new org.apache.thrift.protocol.b("signalStrength", (byte) 8, (short) 2);
    private static final org.apache.thrift.protocol.b h = new org.apache.thrift.protocol.b("ssid", JceStruct.STRUCT_END, (short) 3);
    public String a;
    public int b;
    public String c;
    private BitSet i = new BitSet(1);

    public enum a {
        MAC_ADDRESS((short) 1, "macAddress"),
        SIGNAL_STRENGTH((short) 2, "signalStrength"),
        SSID((short) 3, "ssid");
        
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
        enumMap.put(a.MAC_ADDRESS, new b("macAddress", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.SIGNAL_STRENGTH, new b("signalStrength", (byte) 1, new c((byte) 8)));
        enumMap.put(a.SSID, new b("ssid", (byte) 2, new c(JceStruct.STRUCT_END)));
        d = Collections.unmodifiableMap(enumMap);
        b.a(w.class, d);
    }

    public w a(int i) {
        this.b = i;
        a(true);
        return this;
    }

    public w a(String str) {
        this.a = str;
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (b()) {
                    d();
                    return;
                }
                throw new f("Required field 'signalStrength' was not found in serialized data! Struct: " + toString());
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
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.t();
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

    public boolean a() {
        return this.a != null;
    }

    public boolean a(w wVar) {
        if (wVar == null) {
            return false;
        }
        boolean a = a();
        boolean a2 = wVar.a();
        if (((a || a2) && (!a || !a2 || !this.a.equals(wVar.a))) || this.b != wVar.b) {
            return false;
        }
        a = c();
        a2 = wVar.c();
        return !(a || a2) || (a && a2 && this.c.equals(wVar.c));
    }

    public int b(w wVar) {
        if (!getClass().equals(wVar.getClass())) {
            return getClass().getName().compareTo(wVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(wVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, wVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(wVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, wVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(wVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.c, wVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public w b(String str) {
        this.c = str;
        return this;
    }

    public void b(e eVar) {
        d();
        eVar.a(e);
        if (this.a != null) {
            eVar.a(f);
            eVar.a(this.a);
            eVar.b();
        }
        eVar.a(g);
        eVar.a(this.b);
        eVar.b();
        if (this.c != null && c()) {
            eVar.a(h);
            eVar.a(this.c);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public boolean b() {
        return this.i.get(0);
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((w) obj);
    }

    public void d() {
        if (this.a == null) {
            throw new f("Required field 'macAddress' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof w)) ? a((w) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Wifi(");
        stringBuilder.append("macAddress:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signalStrength:");
        stringBuilder.append(this.b);
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("ssid:");
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
