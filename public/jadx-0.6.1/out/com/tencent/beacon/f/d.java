package com.tencent.beacon.f;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;

/* compiled from: ProGuard */
public final class d {
    private static HashMap<String, byte[]> e = null;
    private HashMap<String, byte[]> a = new HashMap();
    private a b = new a();
    private String c = "GBK";
    private e d = new e();

    public final void a() {
        this.d.b = 1;
    }

    public final void a(String str) {
        this.d.d = str;
    }

    public final void b(String str) {
        this.d.c = str;
    }

    public final <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            b bVar = new b();
            bVar.a(this.c);
            bVar.a((Object) t, 0);
            ByteBuffer a = bVar.a();
            Object obj = new byte[a.position()];
            System.arraycopy(a.array(), 0, obj, 0, obj.length);
            this.a.put(str, obj);
        }
    }

    public final byte[] b() {
        b bVar = new b(0);
        bVar.a(this.c);
        bVar.a(this.a, 0);
        this.d.a = (short) 3;
        e eVar = this.d;
        ByteBuffer a = bVar.a();
        Object obj = new byte[a.position()];
        System.arraycopy(a.array(), 0, obj, 0, obj.length);
        eVar.e = obj;
        bVar = new b(0);
        bVar.a(this.c);
        this.d.a(bVar);
        a = bVar.a();
        Object obj2 = new byte[a.position()];
        System.arraycopy(a.array(), 0, obj2, 0, obj2.length);
        int length = obj2.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(obj2).flip();
        return allocate.array();
    }

    public final void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            a aVar = new a(bArr, (byte) 0);
            aVar.a(this.c);
            this.d.a(aVar);
            aVar = new a(this.d.e);
            aVar.a(this.c);
            if (e == null) {
                HashMap hashMap = new HashMap();
                e = hashMap;
                hashMap.put("", new byte[0]);
            }
            this.a = aVar.a(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final <T> T b(String str, T t) throws Exception {
        if (!this.a.containsKey(str)) {
            return null;
        }
        try {
            this.b.a((byte[]) this.a.get(str));
            this.b.a(this.c);
            return this.b.a((Object) t, 0, true);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }
}
