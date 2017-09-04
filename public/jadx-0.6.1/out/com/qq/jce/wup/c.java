package com.qq.jce.wup;

import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: UniAttribute */
public class c extends b {
    protected HashMap<String, byte[]> e = null;
    com.qq.taf.jce.c f = new com.qq.taf.jce.c();
    private HashMap<String, Object> g = new HashMap();

    public <T> void a(String str, T t) {
        if (this.e == null) {
            super.a(str, (Object) t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            d dVar = new d();
            dVar.a(this.c);
            dVar.a((Object) t, 0);
            this.e.put(str, e.a(dVar.a()));
        }
    }

    public <T> T b(String str) throws ObjectCreateException {
        if (this.e == null) {
            return super.b(str);
        }
        throw new RuntimeException("data is encoded by new version, please use getByClass(String name, T proxy)");
    }

    public byte[] a() {
        if (this.e == null) {
            return super.a();
        }
        d dVar = new d(0);
        dVar.a(this.c);
        dVar.a(this.e, 0);
        return e.a(dVar.a());
    }

    public void a(byte[] bArr) {
        try {
            super.a(bArr);
        } catch (Exception e) {
            this.f.a(bArr);
            this.f.a(this.c);
            Map hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.e = this.f.a(hashMap, 0, false);
        }
    }
}
