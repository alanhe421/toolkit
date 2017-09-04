package com.qq.jce.wup;

import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: OldUniAttribute */
class b {
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap();
    protected HashMap<String, Object> b = new HashMap();
    protected String c = "GBK";
    c d = new c();
    private HashMap<String, Object> e = new HashMap();

    b() {
    }

    public void a(String str) {
        this.c = str;
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            d dVar = new d();
            dVar.a(this.c);
            dVar.a((Object) t, 0);
            Object a = e.a(dVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a(arrayList, (Object) t);
            hashMap.put(a.a(arrayList), a);
            this.e.remove(str);
            this.a.put(str, hashMap);
        }
    }

    public <T> T b(String str) throws ObjectCreateException {
        String str2 = null;
        if (!this.a.containsKey(str)) {
            return null;
        }
        if (this.e.containsKey(str)) {
            return this.e.get(str);
        }
        byte[] bArr;
        byte[] bArr2 = new byte[0];
        Iterator it = ((HashMap) this.a.get(str)).entrySet().iterator();
        if (it.hasNext()) {
            Entry entry = (Entry) it.next();
            str2 = (String) entry.getKey();
            bArr = (byte[]) entry.getValue();
        } else {
            bArr = bArr2;
        }
        try {
            Object c = c(str2);
            this.d.a(bArr);
            this.d.a(this.c);
            T a = this.d.a(c, 0, true);
            b(str, a);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectCreateException(e);
        }
    }

    private Object c(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        Object b = a.b(str);
        this.b.put(str, b);
        return b;
    }

    private void b(String str, Object obj) {
        this.e.put(str, obj);
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a((ArrayList) arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a((ArrayList) arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a((ArrayList) arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    public byte[] a() {
        d dVar = new d(0);
        dVar.a(this.c);
        dVar.a(this.a, 0);
        return e.a(dVar.a());
    }

    public void a(byte[] bArr) {
        this.d.a(bArr);
        this.d.a(this.c);
        Map hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.d.a(hashMap, 0, false);
    }
}
