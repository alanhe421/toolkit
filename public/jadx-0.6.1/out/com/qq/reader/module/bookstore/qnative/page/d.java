package com.qq.reader.module.bookstore.qnative.page;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PageRankInfo */
public class d {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private List<a> e = new ArrayList();
    private List<b> f = new ArrayList();
    private String g = "0";
    private int h;
    private String i = null;
    private String j = "";
    private int k = 1;

    /* compiled from: PageRankInfo */
    public class a {
        public String a = "";
        public boolean b = false;
        public String c = "";
        public String d = "1";
        final /* synthetic */ d e;

        public a(d dVar) {
            this.e = dVar;
        }

        public String a() {
            return this.d;
        }
    }

    /* compiled from: PageRankInfo */
    public class b {
        public String a = "";
        public String b = "";
        public boolean c = false;
        final /* synthetic */ d d;

        public b(d dVar) {
            this.d = dVar;
        }
    }

    public String a() {
        return this.i;
    }

    public void a(d dVar) {
        this.a = dVar.a;
        this.b = dVar.b;
        this.c = dVar.c;
        this.d = dVar.d;
        this.e.clear();
        this.e.addAll(dVar.e);
        this.f.clear();
        this.f.addAll(dVar.f);
        this.g = dVar.g;
        this.h = dVar.h;
        this.i = dVar.i;
        this.j = dVar.j;
        this.k = dVar.k;
    }

    public void a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            try {
                this.i = jSONObject.toString();
                this.a = jSONObject.optString("actionTag");
                this.b = jSONObject.optString("actionId");
                this.j = jSONObject.optString("l2CateId");
                this.d = jSONObject.optString("pagetitle");
                this.k = jSONObject.optInt("type");
                JSONArray optJSONArray = jSONObject.optJSONArray("actionIdList");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                        a aVar = new a(this);
                        aVar.a = jSONObject2.optString("title");
                        aVar.b = jSONObject2.optBoolean("isSelected");
                        aVar.c = jSONObject2.optString("actionId");
                        aVar.d = jSONObject2.optString("actionTag");
                        this.e.add(aVar);
                        if (aVar.b) {
                            this.b = aVar.c;
                            this.c = aVar.a;
                            this.g = aVar.a();
                            this.h = i2;
                        }
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("actionTagList");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    while (i < optJSONArray2.length()) {
                        JSONObject jSONObject3 = optJSONArray2.getJSONObject(i);
                        b bVar = new b(this);
                        bVar.a = jSONObject3.optString("title");
                        bVar.c = jSONObject3.optBoolean("isSelected");
                        bVar.b = jSONObject3.optString("actionTag");
                        if (bVar.c) {
                            this.a = bVar.b;
                        }
                        this.f.add(bVar);
                        i++;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String b() {
        return this.g;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public String e() {
        return this.j;
    }

    public List<a> f() {
        return this.e;
    }

    public List<b> g() {
        return this.f;
    }

    public String h() {
        return this.c;
    }

    public String i() {
        return this.d;
    }

    public int j() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public int k() {
        return this.k;
    }

    public boolean a(Object obj) {
        if (!(obj instanceof d)) {
            return true;
        }
        d dVar = (d) obj;
        if (this.e == null || dVar.e == null || this.e.size() != dVar.e.size()) {
            return true;
        }
        return false;
    }
}
