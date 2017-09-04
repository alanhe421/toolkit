package com.qq.reader.module.findhome;

import org.json.JSONObject;

/* compiled from: FindHomeGameExpand */
public class c extends a {
    public boolean a(JSONObject jSONObject) {
        return super.a(jSONObject);
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.c != 1) {
            super.a();
        } else {
            this.e.clear();
            if (this.a.size() > 0) {
                this.e.addAll(this.a);
            }
            if (this.b.size() <= 10) {
                this.e.addAll(this.b);
            } else {
                for (int i = 0; i < 10; i++) {
                    this.e.add(this.b.get(i));
                }
            }
        }
        com.qq.reader.common.monitor.debug.c.e("FindHomeGameExpand", "refresh data cost time :" + (System.currentTimeMillis() - currentTimeMillis));
    }
}
