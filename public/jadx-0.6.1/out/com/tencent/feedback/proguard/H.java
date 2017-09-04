package com.tencent.feedback.proguard;

import com.etrump.jni.ETConverter;
import com.qq.taf.jce.JceStruct;
import com.tencent.feedback.eup.jni.c;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: RQDSRC */
public final class h {
    private ByteBuffer a;
    private String b = "GBK";

    public h(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    public h(byte[] bArr, int i) {
        this.a = ByteBuffer.wrap(bArr);
        this.a.position(4);
    }

    public final void a(byte[] bArr) {
        if (this.a != null) {
            this.a.clear();
        }
        this.a = ByteBuffer.wrap(bArr);
    }

    private static int a(c cVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        cVar.a = (byte) (b & 15);
        cVar.b = (b & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >> 4;
        if (cVar.b != 15) {
            return 1;
        }
        cVar.b = byteBuffer.get();
        return 2;
    }

    private boolean a(int i) {
        try {
            c cVar = new c();
            while (true) {
                int a = a(cVar, this.a.duplicate());
                if (i > cVar.b && cVar.a != JceStruct.STRUCT_END) {
                    this.a.position(a + this.a.position());
                    a(cVar.a);
                }
            }
            if (i == cVar.b) {
                return true;
            }
            return false;
        } catch (g e) {
            return false;
        } catch (BufferUnderflowException e2) {
            return false;
        }
    }

    private void a() {
        c cVar = new c();
        do {
            a(cVar, this.a);
            a(cVar.a);
        } while (cVar.a != JceStruct.STRUCT_END);
    }

    private void a(byte b) {
        int i = 0;
        int a;
        c cVar;
        switch (b) {
            case (byte) 0:
                this.a.position(this.a.position() + 1);
                return;
            case (byte) 1:
                this.a.position(2 + this.a.position());
                return;
            case (byte) 2:
                this.a.position(this.a.position() + 4);
                return;
            case (byte) 3:
                this.a.position(this.a.position() + 8);
                return;
            case (byte) 4:
                this.a.position(this.a.position() + 4);
                return;
            case (byte) 5:
                this.a.position(this.a.position() + 8);
                return;
            case (byte) 6:
                i = this.a.get();
                if (i < 0) {
                    i += 256;
                }
                this.a.position(i + this.a.position());
                return;
            case (byte) 7:
                this.a.position(this.a.getInt() + this.a.position());
                return;
            case (byte) 8:
                a = a(0, 0, true);
                while (i < (a << 1)) {
                    cVar = new c();
                    a(cVar, this.a);
                    a(cVar.a);
                    i++;
                }
                return;
            case (byte) 9:
                a = a(0, 0, true);
                while (i < a) {
                    cVar = new c();
                    a(cVar, this.a);
                    a(cVar.a);
                    i++;
                }
                return;
            case (byte) 10:
                a();
                return;
            case (byte) 11:
            case (byte) 12:
                return;
            case (byte) 13:
                c cVar2 = new c();
                a(cVar2, this.a);
                if (cVar2.a != (byte) 0) {
                    throw new g("skipField with invalid type, type value: " + b + ", " + cVar2.a);
                }
                this.a.position(a(0, 0, true) + this.a.position());
                return;
            default:
                throw new g("invalid type.");
        }
    }

    public final boolean a(int i, boolean z) {
        if (a((byte) 0, i, z) != (byte) 0) {
            return true;
        }
        return false;
    }

    public final byte a(byte b, int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 0:
                    return this.a.get();
                case (byte) 12:
                    return (byte) 0;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return b;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final short a(short s, int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 0:
                    return (short) this.a.get();
                case (byte) 1:
                    return this.a.getShort();
                case (byte) 12:
                    return (short) 0;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final int a(int i, int i2, boolean z) {
        if (a(i2)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 0:
                    return this.a.get();
                case (byte) 1:
                    return this.a.getShort();
                case (byte) 2:
                    return this.a.getInt();
                case (byte) 12:
                    return 0;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final long a(long j, int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 0:
                    return (long) this.a.get();
                case (byte) 1:
                    return (long) this.a.getShort();
                case (byte) 2:
                    return (long) this.a.getInt();
                case (byte) 3:
                    return this.a.getLong();
                case (byte) 12:
                    return 0;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new g("require field not exist.");
        }
    }

    private float a(float f, int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 4:
                    return this.a.getFloat();
                case (byte) 12:
                    return 0.0f;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return f;
        } else {
            throw new g("require field not exist.");
        }
    }

    private double a(double d, int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 4:
                    return (double) this.a.getFloat();
                case (byte) 5:
                    return this.a.getDouble();
                case (byte) 12:
                    return 0.0d;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final String b(int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            int i2;
            byte[] bArr;
            switch (cVar.a) {
                case (byte) 6:
                    i2 = this.a.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    bArr = new byte[i2];
                    this.a.get(bArr);
                    try {
                        return new String(bArr, this.b);
                    } catch (UnsupportedEncodingException e) {
                        return new String(bArr);
                    }
                case (byte) 7:
                    i2 = this.a.getInt();
                    if (i2 > JceStruct.JCE_MAX_STRING_LENGTH || i2 < 0) {
                        throw new g("String too long: " + i2);
                    }
                    bArr = new byte[i2];
                    this.a.get(bArr);
                    try {
                        return new String(bArr, this.b);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr);
                    }
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final <K, V> HashMap<K, V> a(Map<K, V> map, int i, boolean z) {
        return (HashMap) a(new HashMap(), map, i, z);
    }

    private <K, V> Map<K, V> a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Entry entry = (Entry) map2.entrySet().iterator().next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 8:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new g("size invalid: " + a);
                    }
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(a(key, 0, true), a(value, 1, true));
                    }
                    return map;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return map;
        } else {
            throw new g("require field not exist.");
        }
    }

    private boolean[] d(int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new g("size invalid: " + a);
                    }
                    boolean[] zArr = new boolean[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        boolean z2;
                        if (a((byte) 0, 0, true) != (byte) 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zArr[i2] = z2;
                    }
                    return zArr;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final byte[] c(int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            byte[] bArr;
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new g("size invalid: " + a);
                    }
                    bArr = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr[i2] = a(bArr[0], 0, true);
                    }
                    return bArr;
                case (byte) 13:
                    c cVar2 = new c();
                    a(cVar2, this.a);
                    if (cVar2.a != (byte) 0) {
                        throw new g("type mismatch, tag: " + i + ", type: " + cVar.a + ", " + cVar2.a);
                    }
                    int a2 = a(0, 0, true);
                    if (a2 < 0) {
                        throw new g("invalid size, tag: " + i + ", type: " + cVar.a + ", " + cVar2.a + ", size: " + a2);
                    }
                    bArr = new byte[a2];
                    this.a.get(bArr);
                    return bArr;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    private short[] e(int i, boolean z) {
        short[] sArr = null;
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        sArr = new short[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            sArr[i2] = a(sArr[0], 0, true);
                        }
                        break;
                    }
                    throw new g("size invalid: " + a);
                default:
                    throw new g("type mismatch.");
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return sArr;
    }

    private int[] f(int i, boolean z) {
        int[] iArr = null;
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        iArr = new int[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            iArr[i2] = a(iArr[0], 0, true);
                        }
                        break;
                    }
                    throw new g("size invalid: " + a);
                default:
                    throw new g("type mismatch.");
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return iArr;
    }

    private long[] g(int i, boolean z) {
        long[] jArr = null;
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        jArr = new long[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            jArr[i2] = a(jArr[0], 0, true);
                        }
                        break;
                    }
                    throw new g("size invalid: " + a);
                default:
                    throw new g("type mismatch.");
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return jArr;
    }

    private float[] h(int i, boolean z) {
        float[] fArr = null;
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        fArr = new float[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            fArr[i2] = a(fArr[0], 0, true);
                        }
                        break;
                    }
                    throw new g("size invalid: " + a);
                default:
                    throw new g("type mismatch.");
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return fArr;
    }

    private double[] i(int i, boolean z) {
        double[] dArr = null;
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        dArr = new double[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            dArr[i2] = a(dArr[0], 0, true);
                        }
                        break;
                    }
                    throw new g("size invalid: " + a);
                default:
                    throw new g("type mismatch.");
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return dArr;
    }

    private <T> T[] a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return b(tArr[0], i, z);
        }
        throw new g("unable to get type of key and value.");
    }

    private <T> T[] b(T t, int i, boolean z) {
        if (a(i)) {
            c cVar = new c();
            a(cVar, this.a);
            switch (cVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new g("size invalid: " + a);
                    }
                    Object[] objArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        objArr[i2] = a((Object) t, 0, true);
                    }
                    return objArr;
                default:
                    throw new g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new g("require field not exist.");
        }
    }

    public final j a(j jVar, int i, boolean z) {
        j jVar2 = null;
        if (a(i)) {
            try {
                jVar2 = (j) jVar.getClass().newInstance();
                c cVar = new c();
                a(cVar, this.a);
                if (cVar.a != (byte) 10) {
                    throw new g("type mismatch.");
                }
                jVar2.a(this);
                a();
            } catch (Exception e) {
                throw new g(e.getMessage());
            }
        } else if (z) {
            throw new g("require field not exist.");
        }
        return jVar2;
    }

    public final <T> Object a(T t, int i, boolean z) {
        int i2 = 0;
        if (t instanceof Byte) {
            return Byte.valueOf(a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            boolean z2;
            if (a((byte) 0, i, z) != (byte) 0) {
                z2 = true;
            }
            return Boolean.valueOf(z2);
        } else if (t instanceof Short) {
            return Short.valueOf(a((short) 0, i, z));
        } else {
            if (t instanceof Integer) {
                return Integer.valueOf(a(0, i, z));
            }
            if (t instanceof Long) {
                return Long.valueOf(a(0, i, z));
            }
            if (t instanceof Float) {
                return Float.valueOf(a(0.0f, i, z));
            }
            if (t instanceof Double) {
                return Double.valueOf(a(0.0d, i, z));
            }
            if (t instanceof String) {
                return String.valueOf(b(i, z));
            }
            if (t instanceof Map) {
                return (HashMap) a(new HashMap(), (Map) t, i, z);
            } else if (t instanceof List) {
                List list = (List) t;
                if (list == null || list.isEmpty()) {
                    return new ArrayList();
                }
                Object[] b = b(list.get(0), i, z);
                if (b == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                while (i2 < b.length) {
                    arrayList.add(b[i2]);
                    i2++;
                }
                return arrayList;
            } else if (t instanceof j) {
                return a((j) t, i, z);
            } else {
                if (!t.getClass().isArray()) {
                    throw new g("read object error: unsupport type.");
                } else if ((t instanceof byte[]) || (t instanceof Byte[])) {
                    return c(i, z);
                } else {
                    if (t instanceof boolean[]) {
                        return d(i, z);
                    }
                    if (t instanceof short[]) {
                        return e(i, z);
                    }
                    if (t instanceof int[]) {
                        return f(i, z);
                    }
                    if (t instanceof long[]) {
                        return g(i, z);
                    }
                    if (t instanceof float[]) {
                        return h(i, z);
                    }
                    if (t instanceof double[]) {
                        return i(i, z);
                    }
                    return a((Object[]) t, i, z);
                }
            }
        }
    }

    public final int a(String str) {
        this.b = str;
        return 0;
    }
}
