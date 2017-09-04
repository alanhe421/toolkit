package com.tencent.beacon.f;

import com.etrump.jni.ETConverter;
import com.qq.taf.jce.JceStruct;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public final class a {
    private ByteBuffer a;
    private String b = "GBK";

    public a(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    public a(byte[] bArr, byte b) {
        this.a = ByteBuffer.wrap(bArr);
        this.a.position(4);
    }

    public final void a(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    private static int a(a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.a = (byte) (b & 15);
        aVar.b = (b & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >> 4;
        if (aVar.b != 15) {
            return 1;
        }
        aVar.b = byteBuffer.get() & 255;
        return 2;
    }

    private boolean a(int i) {
        try {
            a aVar = new a();
            while (true) {
                int a = a(aVar, this.a.duplicate());
                if (aVar.a == JceStruct.STRUCT_END) {
                    return false;
                }
                if (i <= aVar.b) {
                    break;
                }
                this.a.position(a + this.a.position());
                a(aVar.a);
            }
            if (i == aVar.b) {
                return true;
            }
            return false;
        } catch (BufferUnderflowException e) {
            return false;
        } catch (RuntimeException e2) {
            return false;
        }
    }

    private void a() {
        a aVar = new a();
        do {
            a(aVar, this.a);
            a(aVar.a);
        } while (aVar.a != JceStruct.STRUCT_END);
    }

    private void a(byte b) {
        int i = 0;
        int a;
        a aVar;
        switch (b) {
            case (byte) 0:
                this.a.position(this.a.position() + 1);
                return;
            case (byte) 1:
                this.a.position(this.a.position() + 2);
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
                    aVar = new a();
                    a(aVar, this.a);
                    a(aVar.a);
                    i++;
                }
                return;
            case (byte) 9:
                a = a(0, 0, true);
                while (i < a) {
                    aVar = new a();
                    a(aVar, this.a);
                    a(aVar.a);
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
                a aVar2 = new a();
                a(aVar2, this.a);
                if (aVar2.a != (byte) 0) {
                    throw new RuntimeException("skipField with invalid type, type value: " + b + ", " + aVar2.a);
                }
                this.a.position(a(0, 0, true) + this.a.position());
                return;
            default:
                throw new RuntimeException("invalid type.");
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
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 0:
                    return this.a.get();
                case (byte) 12:
                    return (byte) 0;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return b;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final short a(short s, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 0:
                    return (short) this.a.get();
                case (byte) 1:
                    return this.a.getShort();
                case (byte) 12:
                    return (short) 0;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final int a(int i, int i2, boolean z) {
        if (a(i2)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 0:
                    return this.a.get();
                case (byte) 1:
                    return this.a.getShort();
                case (byte) 2:
                    return this.a.getInt();
                case (byte) 12:
                    return 0;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final long a(long j, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
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
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    private float a(float f, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 4:
                    return this.a.getFloat();
                case (byte) 12:
                    return 0.0f;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return f;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    private double a(double d, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 4:
                    return (double) this.a.getFloat();
                case (byte) 5:
                    return this.a.getDouble();
                case (byte) 12:
                    return 0.0d;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final String b(int i, boolean z) {
        byte[] bArr;
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            int i2;
            switch (aVar.a) {
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
                    if (i2 > JceStruct.JCE_MAX_STRING_LENGTH || i2 < 0 || i2 > this.a.capacity()) {
                        throw new RuntimeException("String too long: " + i2);
                    }
                    bArr = new byte[i2];
                    this.a.get(bArr);
                    try {
                        return new String(bArr, this.b);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr);
                    }
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final <K, V> HashMap<K, V> a(Map<K, V> map) {
        return (HashMap) a(new HashMap(), map, 0, false);
    }

    private <K, V> Map<K, V> a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Entry entry = (Entry) map2.entrySet().iterator().next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 8:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new RuntimeException("size invalid: " + a);
                    }
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(a(key, 0, true), a(value, 1, true));
                    }
                    return map;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return map;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    private boolean[] d(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new RuntimeException("size invalid: " + a);
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
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final byte[] c(int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            byte[] bArr;
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0 || a > this.a.capacity()) {
                        throw new RuntimeException("size invalid: " + a);
                    }
                    bArr = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr[i2] = a(bArr[0], 0, true);
                    }
                    return bArr;
                case (byte) 13:
                    a aVar2 = new a();
                    a(aVar2, this.a);
                    if (aVar2.a != (byte) 0) {
                        throw new RuntimeException("type mismatch, tag: " + i + ", type: " + aVar.a + ", " + aVar2.a);
                    }
                    int a2 = a(0, 0, true);
                    if (a2 < 0 || a2 > this.a.capacity()) {
                        throw new RuntimeException("invalid size, tag: " + i + ", type: " + aVar.a + ", " + aVar2.a + ", size: " + a2);
                    }
                    bArr = new byte[a2];
                    this.a.get(bArr);
                    return bArr;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    private short[] e(int i, boolean z) {
        short[] sArr = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        sArr = new short[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            sArr[i2] = a(sArr[0], 0, true);
                        }
                        break;
                    }
                    throw new RuntimeException("size invalid: " + a);
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (z) {
            throw new RuntimeException("require field not exist.");
        }
        return sArr;
    }

    private int[] f(int i, boolean z) {
        int[] iArr = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        iArr = new int[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            iArr[i2] = a(iArr[0], 0, true);
                        }
                        break;
                    }
                    throw new RuntimeException("size invalid: " + a);
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (z) {
            throw new RuntimeException("require field not exist.");
        }
        return iArr;
    }

    private long[] g(int i, boolean z) {
        long[] jArr = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        jArr = new long[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            jArr[i2] = a(jArr[0], 0, true);
                        }
                        break;
                    }
                    throw new RuntimeException("size invalid: " + a);
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (z) {
            throw new RuntimeException("require field not exist.");
        }
        return jArr;
    }

    private float[] h(int i, boolean z) {
        float[] fArr = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        fArr = new float[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            fArr[i2] = a(fArr[0], 0, true);
                        }
                        break;
                    }
                    throw new RuntimeException("size invalid: " + a);
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (z) {
            throw new RuntimeException("require field not exist.");
        }
        return fArr;
    }

    private double[] i(int i, boolean z) {
        double[] dArr = null;
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a >= 0) {
                        dArr = new double[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            dArr[i2] = a(dArr[0], 0, true);
                        }
                        break;
                    }
                    throw new RuntimeException("size invalid: " + a);
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (z) {
            throw new RuntimeException("require field not exist.");
        }
        return dArr;
    }

    private <T> T[] b(T t, int i, boolean z) {
        if (a(i)) {
            a aVar = new a();
            a(aVar, this.a);
            switch (aVar.a) {
                case (byte) 9:
                    int a = a(0, 0, true);
                    if (a < 0) {
                        throw new RuntimeException("size invalid: " + a);
                    }
                    Object[] objArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        objArr[i2] = a((Object) t, 0, true);
                    }
                    return objArr;
                default:
                    throw new RuntimeException("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new RuntimeException("require field not exist.");
        }
    }

    public final c a(c cVar, int i, boolean z) {
        c cVar2 = null;
        if (a(i)) {
            try {
                cVar2 = (c) cVar.getClass().newInstance();
                a aVar = new a();
                a(aVar, this.a);
                if (aVar.a != (byte) 10) {
                    throw new RuntimeException("type mismatch.");
                }
                cVar2.a(this);
                a();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else if (z) {
            throw new RuntimeException("require field not exist.");
        }
        return cVar2;
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
                return b(i, z);
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
            } else if (t instanceof c) {
                return a((c) t, i, z);
            } else {
                if (!t.getClass().isArray()) {
                    throw new RuntimeException("read object error: unsupport type.");
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
                    Object[] objArr = (Object[]) t;
                    if (objArr != null && objArr.length != 0) {
                        return b(objArr[0], i, z);
                    }
                    throw new RuntimeException("unable to get type of key and value.");
                }
            }
        }
    }

    public final int a(String str) {
        this.b = str;
        return 0;
    }
}
