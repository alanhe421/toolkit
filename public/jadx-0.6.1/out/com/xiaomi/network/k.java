package com.xiaomi.network;

import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

class k implements Comparable<k> {
    String a;
    protected int b;
    private final LinkedList<a> c;
    private long d;

    public k() {
        this(null, 0);
    }

    public k(String str) {
        this(str, 0);
    }

    public k(String str, int i) {
        this.c = new LinkedList();
        this.d = 0;
        this.a = str;
        this.b = i;
    }

    public int a(k kVar) {
        return kVar == null ? 1 : kVar.b - this.b;
    }

    public synchronized k a(JSONObject jSONObject) {
        this.d = jSONObject.getLong("tt");
        this.b = jSONObject.getInt("wt");
        this.a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.c.add(new a().a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized JSONObject a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("tt", this.d);
        jSONObject.put("wt", this.b);
        jSONObject.put("host", this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            jSONArray.put(((a) it.next()).b());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    protected synchronized void a(a aVar) {
        if (aVar != null) {
            this.c.add(aVar);
            int a = aVar.a();
            if (a > 0) {
                this.b += aVar.a();
            } else {
                int i = 0;
                int size = this.c.size() - 1;
                while (size >= 0 && ((a) this.c.get(size)).a() < 0) {
                    i++;
                    size--;
                }
                this.b += a * i;
            }
            if (this.c.size() > 30) {
                this.b -= ((a) this.c.remove()).a();
            }
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((k) obj);
    }

    public String toString() {
        return this.a + ":" + this.b;
    }
}
