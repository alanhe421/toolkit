package com.qq.reader.module.rookie.a;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GiftRule */
public class a {
    public int a = 0;
    public String b = "";
    private int c;
    private List<a> d;
    private int e = 0;
    private int f;
    private String g;
    private String h;

    /* compiled from: GiftRule */
    public class a {
        public int a;
        public String b;
        final /* synthetic */ a c;

        public a(a aVar) {
            this.c = aVar;
        }

        public String toString() {
            return "PreGift[id=" + this.a + " pos=" + this.b + "]";
        }
    }

    public String a() {
        return this.g;
    }

    public int b() {
        return this.c;
    }

    public List<a> c() {
        return this.d;
    }

    public String d() {
        return this.h;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        this.c = jSONObject.optInt("priority");
        JSONArray optJSONArray = jSONObject.optJSONArray("pregift");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.d = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                a aVar = new a(this);
                aVar.a = jSONObject2.optInt("id");
                aVar.b = jSONObject2.optString("pos");
                this.d.add(aVar);
            }
        }
        this.e = jSONObject.optInt("displaylimit");
        this.f = jSONObject.optInt("monthtype");
        this.g = jSONObject.optString("resurl").trim();
        this.h = jSONObject.optString("jumpurl").trim();
    }

    public boolean a(long j) {
        com.qq.reader.module.rookie.presenter.a.a().a("书籍是否显示:bid = " + j + " displayLimit =" + this.e + " showedBook =" + this.b);
        if (this.e <= 1 || j <= 0) {
            return false;
        }
        if (TextUtils.isEmpty(this.b) || !this.b.contains(j + "")) {
            return false;
        }
        return true;
    }

    public boolean e() {
        com.qq.reader.module.rookie.presenter.a.a().a("显示上限:displayCount = " + this.a + " displayLimit =[" + this.e + "]");
        return this.e != -1 && this.a >= this.e;
    }

    public boolean a(boolean z) {
        boolean z2 = true;
        if (this.f != 0) {
            boolean z3;
            boolean z4 = this.f;
            if (z) {
                z3 = true;
            } else {
                z3 = true;
            }
            if (z4 != z3) {
                z2 = false;
            }
        }
        com.qq.reader.module.rookie.presenter.a.a().a("满足包月:isMonthly = " + z + " monthType =" + this.f + " result=" + z2);
        return z2;
    }

    public void b(long j) {
        if (this.e > 1 && j > 0 && this.b != null) {
            this.b += j + "-";
        }
        this.a++;
    }

    public String toString() {
        return "GiftRule{priority=" + this.c + ", preGift=" + this.d + ", displayLimit=" + this.e + ", monthType=" + this.f + ", resUrl='" + this.g + '\'' + ", jumpUrl='" + this.h + '\'' + ", displayCount=" + this.a + ", showedBook='" + this.b + '\'' + '}';
    }
}
