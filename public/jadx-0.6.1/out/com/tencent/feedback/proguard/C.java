package com.tencent.feedback.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: RQDSRC */
public class c extends a {
    protected HashMap<String, byte[]> d = null;
    private HashMap<String, Object> e = new HashMap();
    private h f = new h();

    public void e() {
        this.d = new HashMap();
    }

    public <T> void a(String str, T t) {
        if (this.d == null) {
            super.a(str, (Object) t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            i iVar = new i();
            iVar.a(this.b);
            iVar.a((Object) t, 0);
            this.d.put(str, k.a(iVar.a()));
        }
    }

    public final <T> T b(String str, T t) throws b {
        T a;
        if (this.d != null) {
            if (!this.d.containsKey(str)) {
                return null;
            }
            if (this.e.containsKey(str)) {
                return this.e.get(str);
            }
            try {
                this.f.a((byte[]) this.d.get(str));
                this.f.a(this.b);
                a = this.f.a((Object) t, 0, true);
                if (a == null) {
                    return a;
                }
                this.e.put(str, a);
                return a;
            } catch (Exception e) {
                throw new b(e);
            }
        } else if (!this.a.containsKey(str)) {
            return null;
        } else {
            if (this.e.containsKey(str)) {
                return this.e.get(str);
            }
            byte[] bArr;
            byte[] bArr2 = new byte[0];
            Iterator it = ((HashMap) this.a.get(str)).entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                bArr = (byte[]) entry.getValue();
            } else {
                bArr = bArr2;
            }
            try {
                this.f.a(bArr);
                this.f.a(this.b);
                a = this.f.a((Object) t, 0, true);
                this.e.put(str, a);
                return a;
            } catch (Exception e2) {
                throw new b(e2);
            }
        }
    }

    public byte[] a() {
        if (this.d == null) {
            return super.a();
        }
        i iVar = new i(0);
        iVar.a(this.b);
        iVar.a(this.d, 0);
        return k.a(iVar.a());
    }

    public void a(byte[] bArr) {
        try {
            super.a(bArr);
        } catch (Exception e) {
            this.f.a(bArr);
            this.f.a(this.b);
            Map hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.d = this.f.a(hashMap, 0, false);
        }
    }
}
