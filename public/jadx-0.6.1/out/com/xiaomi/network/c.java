package com.xiaomi.network;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class c {
    private String a;
    private final ArrayList<b> b = new ArrayList();

    public c(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.a = str;
    }

    public synchronized b a() {
        b bVar;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            bVar = (b) this.b.get(size);
            if (bVar.a()) {
                f.a().f(bVar.e());
                break;
            }
        }
        bVar = null;
        return bVar;
    }

    public synchronized c a(JSONObject jSONObject) {
        this.a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.b.add(new b(this.a).a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized void a(b bVar) {
        int i = 0;
        while (i < this.b.size()) {
            if (((b) this.b.get(i)).a(bVar)) {
                this.b.set(i, bVar);
                break;
            }
            i++;
        }
        if (i >= this.b.size()) {
            this.b.add(bVar);
        }
    }

    public synchronized void a(boolean z) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            b bVar = (b) this.b.get(size);
            if (z) {
                if (bVar.c()) {
                    this.b.remove(size);
                }
            } else if (!bVar.b()) {
                this.b.remove(size);
            }
        }
    }

    public ArrayList<b> b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public synchronized JSONObject d() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("host", this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            jSONArray.put(((b) it.next()).f());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("\n");
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            stringBuilder.append((b) it.next());
        }
        return stringBuilder.toString();
    }
}
