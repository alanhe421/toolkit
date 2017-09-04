package com.qq.reader.module.findhome;

import com.qq.reader.common.monitor.debug.c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FindHomeExpand */
public class a {
    protected List<com.qq.reader.module.findhome.base.a> a = new ArrayList();
    protected List<com.qq.reader.module.findhome.base.a> b = new ArrayList();
    protected int c;
    protected final int d = 10;
    protected List<com.qq.reader.module.findhome.base.a> e = new ArrayList();
    private int f;

    public boolean a(JSONObject jSONObject) {
        int i;
        com.qq.reader.module.findhome.base.a a;
        boolean z = false;
        this.c = jSONObject.optInt("orderby");
        JSONArray optJSONArray = jSONObject.optJSONArray("topad");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (i = 0; i < optJSONArray.length(); i++) {
                a = b.a(optJSONArray.optJSONObject(i));
                if (a != null) {
                    a.a(true);
                    this.a.add(a);
                }
            }
        }
        optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (i = 0; i < optJSONArray.length(); i++) {
                a = b.a(optJSONArray.optJSONObject(i));
                if (a != null) {
                    this.b.add(a);
                }
            }
        }
        if (this.a.size() > 0 || this.b.size() > 0) {
            z = true;
        }
        if (z) {
            a();
        }
        return z;
    }

    public void a() {
        int i = 0;
        long currentTimeMillis = System.currentTimeMillis();
        this.e.clear();
        if (this.a.size() > 0) {
            this.e.addAll(this.a);
        }
        com.qq.reader.module.findhome.base.a aVar;
        if (this.c == 1) {
            int min = Math.min(this.b.size(), 10);
            for (int i2 = 0; i2 < min; i2++) {
                if (this.f >= this.b.size()) {
                    this.f = 0;
                }
                aVar = (com.qq.reader.module.findhome.base.a) this.b.get(this.f);
                aVar.b(i2);
                this.e.add(aVar);
                this.f++;
            }
        } else {
            Random random = new Random();
            Set hashSet = new HashSet();
            int min2 = Math.min(this.b.size(), 10);
            while (i < min2) {
                int nextInt;
                do {
                    nextInt = random.nextInt(this.b.size());
                } while (hashSet.contains(Integer.valueOf(nextInt)));
                hashSet.add(Integer.valueOf(nextInt));
                aVar = (com.qq.reader.module.findhome.base.a) this.b.get(nextInt);
                aVar.b(i);
                this.e.add(aVar);
                i++;
            }
        }
        c.e("FindHomeExpand", "refresh data cost time :" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public int b() {
        return this.a.size() + this.b.size();
    }

    public List<com.qq.reader.module.findhome.base.a> c() {
        return this.e;
    }
}
