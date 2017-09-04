package com.qq.taf.jce;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: JceDisplayer */
public final class b {
    private StringBuilder a;
    private int b = 0;

    private void a(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append('\t');
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public b(StringBuilder stringBuilder, int i) {
        this.a = stringBuilder;
        this.b = i;
    }

    public b a(boolean z, String str) {
        a(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public b a(byte b, String str) {
        a(str);
        this.a.append(b).append('\n');
        return this;
    }

    public b a(char c, String str) {
        a(str);
        this.a.append(c).append('\n');
        return this;
    }

    public b a(short s, String str) {
        a(str);
        this.a.append(s).append('\n');
        return this;
    }

    public b a(int i, String str) {
        a(str);
        this.a.append(i).append('\n');
        return this;
    }

    public b a(long j, String str) {
        a(str);
        this.a.append(j).append('\n');
        return this;
    }

    public b a(float f, String str) {
        a(str);
        this.a.append(f).append('\n');
        return this;
    }

    public b a(double d, String str) {
        a(str);
        this.a.append(d).append('\n');
        return this;
    }

    public b a(String str, String str2) {
        a(str2);
        if (str == null) {
            this.a.append("null").append('\n');
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public b a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            this.a.append("null").append('\n');
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []").append('\n');
        } else {
            this.a.append(bArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (byte a : bArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(short[] sArr, String str) {
        a(str);
        if (sArr == null) {
            this.a.append("null").append('\n');
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []").append('\n');
        } else {
            this.a.append(sArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (short a : sArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(int[] iArr, String str) {
        a(str);
        if (iArr == null) {
            this.a.append("null").append('\n');
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []").append('\n');
        } else {
            this.a.append(iArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (int a : iArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(long[] jArr, String str) {
        a(str);
        if (jArr == null) {
            this.a.append("null").append('\n');
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []").append('\n');
        } else {
            this.a.append(jArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (long a : jArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(float[] fArr, String str) {
        a(str);
        if (fArr == null) {
            this.a.append("null").append('\n');
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []").append('\n');
        } else {
            this.a.append(fArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (float a : fArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public b a(double[] dArr, String str) {
        a(str);
        if (dArr == null) {
            this.a.append("null").append('\n');
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []").append('\n');
        } else {
            this.a.append(dArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (double a : dArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public <K, V> b a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            this.a.append("null").append('\n');
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}").append('\n');
        } else {
            this.a.append(map.size()).append(", {").append('\n');
            b bVar = new b(this.a, this.b + 1);
            b bVar2 = new b(this.a, this.b + 2);
            for (Entry entry : map.entrySet()) {
                bVar.a('(', null);
                bVar2.a(entry.getKey(), null);
                bVar2.a(entry.getValue(), null);
                bVar.a(')', null);
            }
            a('}', null);
        }
        return this;
    }

    public <T> b a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            this.a.append("null").append('\n');
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []").append('\n');
        } else {
            this.a.append(tArr.length).append(", [").append('\n');
            b bVar = new b(this.a, this.b + 1);
            for (Object a : tArr) {
                bVar.a(a, null);
            }
            a(']', null);
        }
        return this;
    }

    public <T> b a(Collection<T> collection, String str) {
        if (collection != null) {
            return a(collection.toArray(), str);
        }
        a(str);
        this.a.append("null").append('\t');
        return this;
    }

    public <T> b a(T t, String str) {
        if (t == null) {
            this.a.append("null").append('\n');
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((List) t, str);
        } else if (t instanceof JceStruct) {
            a((JceStruct) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((Object) (boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new JceEncodeException("write object error: unsupport type.");
        }
        return this;
    }

    public b a(JceStruct jceStruct, String str) {
        a('{', str);
        if (jceStruct == null) {
            this.a.append('\t').append("null");
        } else {
            jceStruct.display(this.a, this.b + 1);
        }
        a('}', null);
        return this;
    }
}
